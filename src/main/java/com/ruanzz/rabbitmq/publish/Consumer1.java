package com.ruanzz.rabbitmq.publish;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.ruanzz.rabbitmq.util.ConnectionUtil;
import java.io.IOException;

/**
 * @author ZhenZhuo.Ruan
 */
public class Consumer1 {

  private final static String QUEUE_NAME = "exchange_fanout_queue_1";

  private final static String EXCHANGE_NAME = "exchange_fanout";

  public static void main(String[] argv) throws Exception {
    // 1.获取到连接
    Connection connection = ConnectionUtil.getConnection();
    // 2.获取通道
    Channel channel = connection.createChannel();
    // 3.声明队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);

    // 4.绑定队列到交换机
    channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

    // 5.定义队列的消费者
    DefaultConsumer consumer = new DefaultConsumer(channel) {
      // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
          byte[] body) throws IOException {
        // body 即消息体
        String msg = new String(body);
        System.out.println(" [Consumer-1] received : " + msg + "!");
      }
    };
    // 监听队列，自动返回完成
    channel.basicConsume(QUEUE_NAME, true, consumer);
  }
}
