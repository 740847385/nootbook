package com.mendas.notebook.controller;

import com.mendas.notebook.utils.IdempotentUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("test")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {

        return "hello world";
    }


    @GetMapping("/hello1")
    public String hello(String id) {
        if (!IdempotentUtils.judge(id, this.getClass())) {
            return "执行失败";
        }
        System.out.println("添加用户ID:" + id);
        return "hello world " + id;

    }
}
