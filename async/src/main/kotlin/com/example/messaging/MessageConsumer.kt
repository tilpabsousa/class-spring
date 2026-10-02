package com.example.messaging

import com.example.avro.PessoaEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class MessageConsumer {
    private val logger = LoggerFactory.getLogger(MessageConsumer::class.java)

    @KafkaListener(topics = ["\${app.kafka.topic}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun listen(pessoa: PessoaEvent) {
        logger.info("Mensagem recebida: {}", pessoa)
    }
}
