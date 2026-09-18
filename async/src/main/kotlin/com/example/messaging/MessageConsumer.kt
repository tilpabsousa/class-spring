package com.example.messaging

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class MessageConsumer {
    private val logger = LoggerFactory.getLogger(MessageConsumer::class.java)

    @KafkaListener(topics = ["\${app.kafka.topic}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun listen(message: String) {
        logger.info("Mensagem recebida: {}", message)
    }
}
