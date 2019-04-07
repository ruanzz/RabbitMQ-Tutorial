package com.ruanzz.rabbitmq.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ruanzz.rabbitmq.util.ConnectionUtil;

/**
 * @author ZhenZhuo.Ruan
 */
public class Producer {

  private final static String EXCHANGE_NAME = "exchange_fanout";

  public static void main(String[] argv) throws Exception {
    // 获取到连接
    Connection connection = ConnectionUtil.getConnection();
    // 获取通道
    Channel channel = connection.createChannel();

    // 声明exchange，指定类型为fanout
    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

    // 消息内容
    String message = "Are you OK?";
    // 发布消息到Exchange
    channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
    System.out.println(" [Producer] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
}
