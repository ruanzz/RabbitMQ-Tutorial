package com.ruanzz.rabbitmq.spring;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ListenerTest {

  @Autowired
  private AmqpTemplate amqpTemplate;

  @Test
  public void send() throws InterruptedException {
    amqpTemplate.convertAndSend("exchange.spring","item.update","Hello World!");
    TimeUnit.MINUTES.sleep(3);
  }
}
