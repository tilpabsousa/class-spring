package com.example.messaging

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${app.kafka.topic}") private val topic: String,
) {
    fun send(message: String) {
        kafkaTemplate.send(topic, message)
    }
}
