package com.noodles.springbootrabbitmq.controller;

import com.noodles.springbootrabbitmq.bean.Book;
import com.noodles.springbootrabbitmq.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @program: springboot
 * @description: bookController
 * @author: Eric
 * @create: 2019-05-08 15:36
 **/

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public BookController(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping(value = "index")
    public String index(){
        return "Hello World";
    }

    @GetMapping(value = "defaultMessage")
    public void defaultMessage(){
        Book book = new Book();
        book.setId("1");
        book.setName("一起来学springboot");

        this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, book);
        this.rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_BOOK_QUEUE, book);
    }

    @GetMapping(value = "delayMessage")
    public void delayMessage() {
        Book book = new Book();
        book.setId("1");
        book.setName("一起来学Spring Boot");
        // 添加延时队列
        this.rabbitTemplate.convertAndSend(RabbitConfig.REGISTER_DELAY_EXCHANGE, RabbitConfig.DELAY_ROUTING_KEY, book, message -> {
            // TODO 第一句是可要可不要,根据自己需要自行处理
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, Book.class.getName());
				// TODO 如果配置了 params.put("x-message-ttl", 5 * 1000);
				// 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(5 * 1000 + "");
            return message;
        });
        log.info("[发送时间] - [{}]", LocalDateTime.now());
    }
}
