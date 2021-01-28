package com.websocketdemo.config;

import com.websocketdemo.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * Scheduler for the public topic to demonstrate that connection is alive
 *
 * @author Vamseedhar V
 */
@EnableScheduling
@Configuration
public class SchedulerConfig {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedDelay = 10000)
    public void sendAdhocMessages() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender("Scheduler");
        chatMessage.setType(ChatMessage.MessageType.CHAT);
        chatMessage.setContent("Scheduler sent message @" + LocalDateTime.now().toString());
        simpMessagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}
