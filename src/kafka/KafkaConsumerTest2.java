package kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author lwk
 * @date 2019-11-28 16:07
 */
@Slf4j
public class KafkaConsumerTest2 {
    public static void main(String[] args) {
        boolean isRunning = true;
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.133.229:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 6000);
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("foo", "partion-test", "streams-wordcount-output", "sys.up.input"));
        try {
            while (isRunning) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                for (ConsumerRecord<String, String> record : records) {

                    System.out.printf("partion = %s offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 一次性提交
//                try {
                    consumer.commitSync();
//                } catch (CommitFailedException e) {
//                    log.error("commit exception:{}", e.getMessage(), e);
//                }
            }
            consumer.close();
        } catch (Exception e) {
            log.error("while error ：{}", e.getMessage(), e);
        } finally {
            consumer.close();
        }
    }
}
