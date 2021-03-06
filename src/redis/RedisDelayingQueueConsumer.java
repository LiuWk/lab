package redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 【Redis 深度历险：核心原理与应用实践】书中延时队列demo
 *
 * @author Administrator
 */
@Slf4j
public class RedisDelayingQueueConsumer<T> {
    private Jedis jedis;
    private String queueKey;

    public RedisDelayingQueueConsumer(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void loop() {
        while (!Thread.interrupted()) {
            // 只取一条
            Set<String> values = jedis.zrangeByScore(queueKey, "0", String.valueOf(System.currentTimeMillis()), 0, 1);
            if (values.isEmpty()) {
                try {
                    // 延迟一秒扫描redis
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            // 抢到了
            values.stream().filter(s -> jedis.zrem(queueKey, s) > 0).forEach(s -> {
                log.info("task:{}",s);
            });
            /*String s = values.iterator().next();
            if (jedis.zrem(queueKey, s) > 0) { // 抢到了
//                // fastjson 反序列化
//                TaskItem<T> task = JSON.parseObject(s, TaskType);
//                this.handleMsg(task.msg);
                log.info("task:{}",s);
            }*/
        }
    }

    public void handleMsg(T msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        String host = "10.240.169.111";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        try {
            RedisDelayingQueueConsumer queue = new RedisDelayingQueueConsumer<>(jedis, "q-demo");
            Thread consumer = new Thread(() -> queue.loop());
            consumer.start();

            // 等待30s结束消费
            Thread.sleep(600000);
            consumer.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.quit();
            jedis.close();
        }
    }
}