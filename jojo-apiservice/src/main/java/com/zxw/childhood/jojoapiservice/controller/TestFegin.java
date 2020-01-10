package com.zxw.childhood.jojoapiservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxw
 * @date 2020-01-08 10:22
 */
@RestController()
@RequestMapping("/tera")
public class TestFegin {


    @PostMapping("/fly")
    public String add(@RequestParam(value = "name")String name){
        return "拿到fegin的内容";
    }

}
