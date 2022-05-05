package org.zerock.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//jsp로 전달될때 json형식으로 전달된다
@RequestMapping("/replies/")
@Log4j2
public class ReflyController {

    @GetMapping("/test")
    public String[] get1(){
        return new String[]{"AAA","BBB","CCC"};

    }
}
