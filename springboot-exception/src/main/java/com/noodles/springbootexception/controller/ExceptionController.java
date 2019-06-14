package com.noodles.springbootexception.controller;

import com.noodles.springbootexception.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot2
 * @description: ExceptionController
 * @author: Eric
 * @create: 2019-06-14 14:01
 **/
@RestController
public class ExceptionController {

    @GetMapping("/test3")
    public String test3(Integer num) {
        // TODO 演示需要，实际上参数是否为空通过 @RequestParam(required = true)  就可以控制
        if (num == null) {
            throw new CustomException(400, "num不能为空");
        }
        int i = 10 / num;
        return "result:" + i;
    }
}
