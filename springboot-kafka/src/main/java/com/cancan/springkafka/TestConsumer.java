package com.cancan.springkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka消费者测试
 */
@Component
public class TestConsumer {

    //@KafkaListener(groupId = "b",topicPartitions = @TopicPartition(topic = "xixi",partitions = "0"))
//    @KafkaListener(topics = "MicsPointStatus_1822")
//    public void listen (ConsumerRecord<?, ?> record) throws Exception {
//        //四个参数依次为topic名字，偏移数也就是信息数，传输的数据，分区的号
//        System.out.printf("这一号消费者topic = %s, offset = %d, value = %s ,index=%s \n", record.topic(), record.offset(), record.value(),record.partition());
//    }

//    //@KafkaListener(groupId = "b",topicPartitions = @TopicPartition(topic = "xixi",partitions = "1"))
//    @KafkaListener(topics = "MicsPointStatus_1822")
//    //注意：同一个topic，同一个groudid，并且只有在开启分区 num.partitions=3 才会有类似轮询的效果
//    public void listen1 (ConsumerRecord<?, ?> record) throws Exception {
//        //四个参数依次为topic名字，偏移数也就是信息数，传输的数据，分区的号
//        System.out.printf("这是二号消费者topic = %s, offset = %d, value = %s ,index=%s \n", record.topic(), record.offset(), record.value(),record.partition());
//    }

    @KafkaListener(topics = "cs")
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
        //四个参数依次为topic名字，偏移数也就是信息数，传输的数据，分区的号
        System.out.printf("这一号消费者topic = %s, offset = %d, value = %s ,index=%s \n", record.topic(), record.offset(), record.value(),record.partition());
    }
}
