package com.rabbitMQ.demo.DrivenAdapters.rabbitMQ.consumer;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.ReceiverOptions;

@Configuration
public class CRabbitConfig {

    @Bean()
    Mono<Connection> consumerConnectionMono() {
        var connectionFactory = new ConnectionFactory();
        connectionFactory.useNio();
        return Mono.fromCallable(() -> connectionFactory.newConnection("receiver-connection")).cache();
    }

    @Bean
    public ReceiverOptions receiverOptions(Mono<Connection> consumerConnectionMono) {
        return new ReceiverOptions()
                .connectionMono(consumerConnectionMono);
    }

    @Bean
    Receiver receiver(ReceiverOptions receiverOptions) {
        return RabbitFlux.createReceiver(receiverOptions);
    }
}
