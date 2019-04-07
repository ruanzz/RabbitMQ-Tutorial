package com.ruanzz.rabbitmq.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ruanzz.rabbitmq.util.ConnectionUtil;

/**
 * @author ZhenZhuo.Ruan
 */
public class Producer {

  private final static String EXCHANGE_NAME = "exchange_direct";

  public static void main(String[] argv) throws Exception {
    // 1.获取到连接
    Connection connection = ConnectionUtil.getConnection();
    // 2.获取通道
    Channel channel = connection.createChannel();
    // 3.声明exchange，指定类型为direct
    channel.exchangeDeclare(EXCHANGE_NAME, "direct");
    String message = "delete...";
    String routingKey = "delete";
//    message = "update...";
//    routingKey ="update";
    // 4.发送消息，并且指定routing key
    channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
    System.out.println(" [商品服务：] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
}
