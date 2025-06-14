# reactor-rabbitMQ

> [!TIP]
> This lab implements the following use case :
> >
> ![image](https://github.com/user-attachments/assets/91ebb17c-efe2-4093-ae8d-4af7b894e8e0)
> >
> Considerations :
> > * The producer and the consumer are implemented in the same project with pragmatic aims
> > * CommandLineRunner is implemented to facilitate the execution of message sending and receiving.

> [!IMPORTANT]
> Test project
>  > requirements
>  > * SDK Java 21
>  > * Docker
>  > * Gradle 8 or major
>  >
> Steps to execute
> > 1. get rabbitmq
> > ```bash
> > # latest RabbitMQ 4.x
> > docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
> > ```
> > 2. go to http://localhost:15672/
> > * passw and user are guest
> > 
> > ![image](https://github.com/user-attachments/assets/2850d5da-5fc5-4296-98d1-709eb4731356)
> >  <br><br>
> > ![image](https://github.com/user-attachments/assets/19cc97fc-44e1-4dc5-a096-2a16be247d44)
> > 
> > 3. run reactor-spring application
> > logs :
> > ```text
> >   .   ____          _            __ _ _
> > /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
> >( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
> > \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
> >  '  |____| .__|_| |_|_| |_\__, | / / / /
> > =========|_|==============|___/=/_/_/_/
> >
> > :: Spring Boot ::                (v3.5.0)
> >
> >2025-06-13T20:11:22.208-05:00  INFO 28240 --- [Rabbit-workshop] [  restartedMain] c.r.demo.RabbitWorkshopApplication       : Starting RabbitWorkshopApplication using Java 21.0.7 with PID 28240 (C:\Users\User\Documents\Meli\rabbitMQ _demo\build\classes\java\main started by User in C:\Users\User\Documents\Meli\rabbitMQ _demo)
> >2025-06-13T20:11:22.210-05:00  INFO 28240 --- [Rabbit-workshop] [  restartedMain] c.r.demo.RabbitWorkshopApplication       : No active profile set, falling back to 1 default profile: "default"
> >2025-06-13T20:11:22.245-05:00  INFO 28240 --- [Rabbit-workshop] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
> >2025-06-13T20:11:22.245-05:00  INFO 28240 --- [Rabbit-workshop] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
> >2025-06-13T20:11:23.065-05:00  INFO 28240 --- [Rabbit-workshop] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
> >2025-06-13T20:11:23.217-05:00  INFO 28240 --- [Rabbit-workshop] [  restartedMain] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port 8080 (http)
> >2025-06-13T20:11:23.222-05:00  INFO 28240 --- [Rabbit-workshop] [  restartedMain] c.r.demo.RabbitWorkshopApplication       : Started RabbitWorkshopApplication in 1.297 seconds (process running for 1.548)
> >2025-06-13T20:11:23.328-05:00  INFO 28240 --- [Rabbit-workshop] [  restartedMain] reactor.rabbitmq.Receiver                : Consumer amq.ctag-yvBoo7uZ_hJUiqym9uJELg consuming from demo-queue has been registered
> >2025-06-13T20:11:24.332-05:00  INFO 28240 --- [Rabbit-workshop] [   rabbitmq-nio] c.r.demo.RabbitWorkshopApplication       : msg sent - correlationId : ba42d47e-df7c-4f7a-982c-016710992512
> >2025-06-13T20:11:24.332-05:00  INFO 28240 --- [Rabbit-workshop] [pool-8-thread-4] c.r.demo.RabbitWorkshopApplication       : Received message Message - 1 with correlationId ba42d47e-df7c-4f7a-982c-016710992512
> >2025-06-13T20:11:25.341-05:00  INFO 28240 --- [Rabbit-workshop] [   rabbitmq-nio] c.r.demo.RabbitWorkshopApplication       : msg sent - correlationId : eef9b253-f2d0-49dc-bd90-e6a1ec0e5c66
> >2025-06-13T20:11:25.341-05:00  INFO 28240 --- [Rabbit-workshop] [pool-8-thread-5] c.r.demo.RabbitWorkshopApplication       : Received message Message - 2 with correlationId eef9b253-f2d0-49dc-bd90-e6a1ec0e5c66
> >2025-06-13T20:11:26.350-05:00  INFO 28240 --- [Rabbit-workshop] [   rabbitmq-nio] c.r.demo.RabbitWorkshopApplication       : msg sent - correlationId : 1d263ad7-c824-425b-b703-dc1d938080f7
> >2025-06-13T20:11:26.350-05:00  INFO 28240 --- [Rabbit-workshop] [pool-8-thread-6] c.r.demo.RabbitWorkshopApplication       : Received message Message - 3 with correlationId 1d263ad7-c824-425b-b703-dc1d938080f7
> >2025-06-13T20:11:27.356-05:00  INFO 28240 --- [Rabbit-workshop] [   rabbitmq-nio] c.r.demo.RabbitWorkshopApplication       : msg sent - correlationId : e590b5fd-98a0-4876-accd-5bf30cfdd666
> >2025-06-13T20:11:27.357-05:00  INFO 28240 --- [Rabbit-workshop] [pool-8-thread-7] c.r.demo.RabbitWorkshopApplication       : Received message Message - 4 with correlationId e590b5fd-98a0-4876-accd-5bf30cfdd666
> >2025-06-13T20:11:28.367-05:00  INFO 28240 --- [Rabbit-workshop] [   rabbitmq-nio] c.r.demo.RabbitWorkshopApplication       : msg sent - correlationId : 1d063cca-b30a-4c7f-bf48-4366cf1e7e93
> >2025-06-13T20:11:28.367-05:00  INFO 28240 --- [Rabbit-workshop] [pool-8-thread-8] c.r.demo.RabbitWorkshopApplication       : Received message Message - 5 with correlationId 1d063cca-b30a-4c7f-bf48-4366cf1e7e93
> >2025-06-13T20:11:29.378-05:00  INFO 28240 --- [Rabbit-workshop] [   rabbitmq-nio] c.r.demo.RabbitWorkshopApplication       : msg sent - correlationId : 22cee2e9-7c4f-491f-95f0-ba884393ad13
> >2025-06-13T20:11:29.378-05:00  INFO 28240 --- [Rabbit-workshop] [pool-8-thread-9] c.r.demo.RabbitWorkshopApplication       : Received message Message - 6 with correlationId 22cee2e9-7c4f-491f-95f0-ba884393ad13
> >2025-06-13T20:11:30.388-05:00  INFO 28240 --- [Rabbit-workshop] [   rabbitmq-nio] c.r.demo.RabbitWorkshopApplication       : msg sent - correlationId : e6f6a331-be66-4987-89fe-bf82d6451ad5
> >2025-06-13T20:11:30.388-05:00  INFO 28240 --- [Rabbit-workshop] [ool-8-thread-10] c.r.demo.RabbitWorkshopApplication       : Received message Message - 7 with correlationId e6f6a331-be66-4987-89fe-bf82d6451ad5
> >2025-06-13T20:11:31.402-05:00  INFO 28240 --- [Rabbit-workshop] [   rabbitmq-nio] c.r.demo.RabbitWorkshopApplication       : msg sent - correlationId : 3225bdd5-3b9f-4cd1-be3f-8399d066b06a
> >2025-06-13T20:11:31.402-05:00  INFO 28240 --- [Rabbit-workshop] [ool-8-thread-11] c.r.demo.RabbitWorkshopApplication       : Received message Message - 8 with correlationId 3225bdd5-3b9f-4cd1-be3f-8399d066b06a
> >2025-06-13T20:11:32.403-05:00  INFO 28240 --- [Rabbit-workshop] [   rabbitmq-nio] c.r.demo.RabbitWorkshopApplication       : msg sent - correlationId : 3b2acc09-a240-470e-8da2-e5c858e20482
> >2025-06-13T20:11:32.403-05:00  INFO 28240 --- [Rabbit-workshop] [ool-8-thread-12] c.r.demo.RabbitWorkshopApplication       : Received message Message - 9 with correlationId 3b2acc09-a240-470e-8da2-e5c858e20482
> >2025-06-13T20:11:33.410-05:00  INFO 28240 --- [Rabbit-workshop] [   rabbitmq-nio] c.r.demo.RabbitWorkshopApplication       : msg sent - correlationId : db8e482f-e524-4aee-8c24-26b8c4b9f5c1
> >2025-06-13T20:11:33.410-05:00  INFO 28240 --- [Rabbit-workshop] [ool-8-thread-13] c.r.demo.RabbitWorkshopApplication       : Received message Message - 10 with correlationId db8e482f-e524-4aee-8c24-26b8c4b9f5c1
> >
>  ![image](https://github.com/user-attachments/assets/ed5dc5ff-d7ea-42d8-9079-0bf2ab671dc7)
> As you can see, the sender starts sending messages every second and the consumer intercepts them every time they are published in the queue.
