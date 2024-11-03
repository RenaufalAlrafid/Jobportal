package com.lawencon.jobportal.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

  public static final String EXCHANGE_NOTIF = "notif_exchange";
  public static final String QUEUE_NOTIF = "notif_queue";
  public static final String ROUTING_NOTIF = "*.notif";


  @Bean
  public Queue notifQueue() {
    return new Queue(QUEUE_NOTIF, true);
  }



  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(EXCHANGE_NOTIF);
  }



  @Bean
  public Binding bindingStatus(Queue historyQueue, TopicExchange notifExchange) {
    return BindingBuilder.bind(historyQueue).to(notifExchange).with(ROUTING_NOTIF);
  }

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter(new ObjectMapper());
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
    return rabbitTemplate;
  }


}
