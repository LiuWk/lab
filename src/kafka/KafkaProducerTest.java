package kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author lwk
 * @date 2019-11-28 16:22
 */
@Slf4j
public class KafkaProducerTest {
//    private static String topic = "streams-plaintext-input";
//    private static String topic = "sys.up.input";
    private static String topic = "partion-test";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.133.229:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        List<PartitionInfo> partions = producer.partitionsFor(topic);
        for (PartitionInfo partition : partions){
            log.info("partition:{}",JSONObject.toJSON(partition));
        }
//        for (int i = 0; i < 100; i++) {
//            String str = "{\"key\":\"lkljkj\",\"value\":\"this is a kafka message from kafka-go %s\"}";
//            producer.send(new ProducerRecord<>(topic, String.format(str,i+"-"+System.currentTimeMillis())));
//        }
        try {
            while (true) {
                Thread.sleep(1500);
                String str = "{\"key\":\"lkljkj\",\"value\":\"this is a kafka message from kafka-go %s\"}";
                String msg = String.format(str, "--" + DateTime.now().toString());
                log.info("send msg:{}",msg);
                Future<RecordMetadata> fu = producer.send(new ProducerRecord<>(topic, DateTime.now().toString(), msg));
                log.info("RecordMetadata:{}",fu.get().partition());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
