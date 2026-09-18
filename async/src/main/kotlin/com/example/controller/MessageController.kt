package com.example.controller

import com.example.messaging.MessageProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(private val messageProducer: MessageProducer) {
    @PostMapping("/messages")
    fun publish(
        @RequestParam message: String,
    ) {
        messageProducer.send(message)
    }
}
