package com.cancan.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;

public class ConsumerDemo {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerDemo.class);
    private static final String micsPointConsumerGroup = "kafkaTopic";
    private static final String bootstrapServers = "10.51.9.130:30330,10.51.9.130:30331,10.51.9.130:30332";
    private static final String micsPointTopic = "TestHarness";

    public static void main(String[] args) {

        Map<String, Object> props = new HashMap<>();

        String consumerGroupId = micsPointConsumerGroup;

        //kafka服务地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        //消费后是否自动提交 当前为false
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        //获取消息后提交偏移量的最大时间
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        //超时时间，服务端没有收到心跳就会认为当前消费者失效
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        //默认消费组
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        //earliest  从头开始消费、latest获取最新消息 、none 没理解
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //序列化
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(Arrays.asList(micsPointTopic));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                String msg = record.value();
                System.out.println(msg);
//                String[] split = msg.split("\\^");
//                if (split.length > 2) {
//                    String stationId = split[2];
//                    String pointName = split[4];
//                    String pointValue = split[5];
//
//                    Map<String, String> pointMap = new HashMap<>();
//                    pointMap.put(pointName, pointValue);
//                    System.out.println(pointMap);
//                }
            }

            //有消息
            if (records.count() > 0) {
                //手动提交offset,当前线程会阻塞直到offset提交成功
                //一般使用同步提交，因为提交之后一般也没有什么逻辑代码了
                //手动同步提交
                consumer.commitSync();
            }
        }
    }
}