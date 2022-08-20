package com.ui.backend.kafkademo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaTestProducer implements Runnable{
    public static void initProducer(){
        new Thread(new KafkaTestProducer()).start();
    }
    @Override
    public void run() {
        Properties properties = new Properties();
        /**
         *bootstrap.server用于建立到Kafka集群的初始连接的主机/端口对的列表，如果有两台以上的机器，逗号分隔
         */
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        /**
         * acks有三种状态
         * acks=0 不等待服务器确认直接发送消息，无法保证服务器收到消息数据
         * acks=1 把消息记录写到本地，但不会保证所有的消息数据被确认记录的情况下进行释放
         * acks=all 确认所有的消息数据被同步副本确认，这样保证了记录不会丢失
         *
         */
        properties.put("acks", "all");
        /**
         * 设置成大于0将导致客户端重新发送任何发送失败的记录
         *
         */
        properties.put("retries", 0);
        /**
         *16384字节是默认设置的批处理的缓冲区
         */
        properties.put("batch.size", 16384);

        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        /**
         * 序列化类型。
         * kafka是以键值对的形式发送到kafka集群的，其中key是可选的，value可以是任意类型，Message再被发送到kafka之前，Producer需要
         * 把不同类型的消息转化成二进制类型。
         */
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; i < 10; i++) {
                for(int j = 0;j<10;j++){
                    producer.send(new ProducerRecord<String, String>("kafka-flink-view", "test11"));
                }
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
