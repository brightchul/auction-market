package io.youngwon.app.utils.rabbitmq;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Producer {

    private final RabbitTemplate rabbitTemplate;


    public void sendTo(String message){
        this.rabbitTemplate.convertAndSend("ENTER_AUCTION_QUEUE", message);
    }

}
