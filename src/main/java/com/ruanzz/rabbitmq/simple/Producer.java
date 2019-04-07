package com.ruanzz.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ruanzz.rabbitmq.util.ConnectionUtil;

/**
 * @author ZhenZhuo.Ruan
 */
public class Producer {

  private final static String QUEUE_NAME = "simple_queue";

  public static void main(String[] argv) throws Exception {
    // 1.获取到连接
    Connection connection = ConnectionUtil.getConnection();
    // 2.创建通道
    Channel channel = connection.createChannel();
    String message = "Hello World!";
    // 3.向队列发送消息
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [Simple] Sent '" + message + "'");
    // 4.关闭通道和连接
    channel.close();
    connection.close();
  }

}
