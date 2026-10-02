package com.example.messaging

import com.example.avro.PessoaEvent
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageProducer(
    private val kafkaTemplate: KafkaTemplate<String, PessoaEvent>,
    @Value("\${app.kafka.topic}") private val topic: String,
) {
    fun send(pessoa: PessoaEvent) {
        kafkaTemplate.send(topic, pessoa)
    }
}
