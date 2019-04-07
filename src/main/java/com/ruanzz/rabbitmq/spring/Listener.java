package com.ruanzz.rabbitmq.spring;

import java.util.concurrent.TimeUnit;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ZhenZhuo.Ruan
 */
@Component
public class Listener {

  @RabbitListener(bindings = @QueueBinding(
      value = @Queue(value = "exchange.spring.queue", durable = "true"),
      exchange = @Exchange(
          value = "exchange.spring",
          ignoreDeclarationExceptions = "true",
          type = ExchangeTypes.TOPIC
      ),
      key = {"#.#"}))
  public void listen(String msg) {
    try {
      TimeUnit.MINUTES.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("接收到消息：" + msg);
  }

}
