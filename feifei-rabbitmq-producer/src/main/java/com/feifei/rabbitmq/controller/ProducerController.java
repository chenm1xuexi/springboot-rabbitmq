package com.feifei.rabbitmq.controller;

import com.feifei.rabbitmq.producer.server.RabbitTemplateProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author shixiongfei
 * @date 2019-12-08
 * @since
 */
@Controller
public class ProducerController {

    @Autowired
    private RabbitTemplateProducer producer;

    @GetMapping("/send")
    public ResponseEntity<String> send() {
        producer.sendQueue();
        return ResponseEntity.ok("success");
    }
}
