package com.ruanzz.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ruanzz.rabbitmq.util.ConnectionUtil;

/**
 * @author ZhenZhuo.Ruan
 */
public class Producer {

  private final static String EXCHANGE_NAME = "exchange_topic";

  public static void main(String[] argv) throws Exception {
    // 1.获取到连接
    Connection connection = ConnectionUtil.getConnection();
    // 2.获取通道
    Channel channel = connection.createChannel();
    // 3.声明exchange，指定类型为topic
    channel.exchangeDeclare(EXCHANGE_NAME, "topic");
    String routingKey = "item.delete";
    routingKey = "item.delete.test";
    // 4.发送消息，并且指定routing key
    channel.basicPublish(EXCHANGE_NAME, routingKey, null, routingKey.getBytes());
    System.out.println(" [Consumer：] Sent '" + routingKey + "'");

    channel.close();
    connection.close();
  }
}
