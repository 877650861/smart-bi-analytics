package com.yupi.springbootinit.bizmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * BI 异步任务的消息基础设施。
 *
 * <p>将原来需要手动运行的 {@link BiInitMain} 初始化动作交给 Spring AMQP 管理，
 * 使首次部署也能安全、幂等地创建交换机与队列。</p>
 */
@Configuration
public class BiRabbitConfig {

    @Bean
    public DirectExchange biExchange() {
        return new DirectExchange(BiMqConstant.BI_EXCHANGE_NAME, true, false);
    }

    @Bean
    public DirectExchange biDeadExchange() {
        return new DirectExchange(BiMqConstant.BI_DEAD_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue biDeadQueue() {
        return QueueBuilder.durable(BiMqConstant.BI_DEAD_QUEUE_NAME).build();
    }

    @Bean
    public Queue biQueue() {
        return QueueBuilder.durable(BiMqConstant.BI_QUEUE_NAME)
                .deadLetterExchange(BiMqConstant.BI_DEAD_EXCHANGE_NAME)
                .deadLetterRoutingKey(BiMqConstant.BI_DEAD_ROUTING_KEY)
                .maxLength(1)
                .ttl(10_000)
                .build();
    }

    @Bean
    public Binding biQueueBinding(Queue biQueue, DirectExchange biExchange) {
        return BindingBuilder.bind(biQueue).to(biExchange).with(BiMqConstant.BI_ROUTING_KEY);
    }

    @Bean
    public Binding biDeadQueueBinding(Queue biDeadQueue, DirectExchange biDeadExchange) {
        return BindingBuilder.bind(biDeadQueue).to(biDeadExchange).with(BiMqConstant.BI_DEAD_ROUTING_KEY);
    }
}
