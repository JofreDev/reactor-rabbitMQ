package com.rabbitMQ.demo;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

import java.time.Duration;
import java.util.logging.Level;


@SpringBootApplication
@RequiredArgsConstructor
@Log
public class RabbitWorkshopApplication implements CommandLineRunner {



	private final Sender sender;

	private final Receiver receiver;

	private Disposable disposable;

	private static final String QUEUE = "demo-queue";

    public static void main(String[] args) {



		SpringApplication.run(RabbitWorkshopApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {


		// Number of message that will be sent to the queue
		int messageCount = 10;


		// Represent the list of messages that will be sent
		Flux<OutboundMessage> outboundFlux  =
				Flux.range(1, messageCount)
						.map(i -> new OutboundMessage(
								"",
								QUEUE, ("Message - " + i).getBytes()
						)).delayElements(Duration.ofSeconds(1));


	/**
	 *	1. Declare the queue.
	 *	2. After the queue declaration is completed, it will call another Publisher (sendWithPublishConfirms method), which takes the flux of messages declared earlier.
	 *	3. In case of error, it will log the error.
	 *	4. Finally it subscribe a consumer to this flux, here we can check if the message if being acknowledged by the queue. We also decrement the CountDownLatch by one.
	*/
		sender.declareQueue(QueueSpecification.queue(QUEUE))
				.thenMany(sender.sendWithPublishConfirms(outboundFlux))
				.doOnNext(val-> log.info("msg sent - "+val.isAck()))
				.doOnError(e -> log.log(Level.SEVERE, "Send failed", e))
				.subscribe();

		disposable = receiver.consumeNoAck("demo-queue")
				.doOnNext(m -> log.info("Received message "+ new String(m.getBody())))
				.subscribe();


	}

	@PreDestroy
	public void onShutdown() {
		System.out.println("closing connections RabbitMQ...");
		try {
			sender.close();
			receiver.close();
			if (disposable != null && !disposable.isDisposed()) {
				disposable.dispose();
			}
		} catch (Exception e) {
			System.err.println("Error closing connections RabbitMQ: " + e.getMessage());
		}
	}




}
