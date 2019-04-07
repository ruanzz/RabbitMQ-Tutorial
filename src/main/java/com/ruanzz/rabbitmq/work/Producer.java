package com.ruanzz.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ruanzz.rabbitmq.util.ConnectionUtil;

/**
 * @author ZhenZhuo.Ruan
 */
public class Producer {

  private final static String QUEUE_NAME = "work_queue";

  public static void main(String[] argv) throws Exception {
    // 获取到连接
    Connection connection = ConnectionUtil.getConnection();
    // 获取通道
    Channel channel = connection.createChannel();
    // 声明队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    // 循环发布任务
    for (int i = 0; i < 50; i++) {
      // 消息内容
      String message = "task " + i;
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
      System.out.println(" [Producer] Sent '" + message + "'");

      Thread.sleep(i * 2);
    }
    // 关闭通道和连接
    channel.close();
    connection.close();
  }

}
