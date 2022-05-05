package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.ReplyDTO;
import org.zerock.service.ReplyService;

import java.awt.*;
import java.util.List;

@RestController
//jsp로 전달될때 json형식으로 전달된다
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReflyController {
    private final ReplyService replyService;

    @GetMapping("/test")
    public String[] get1(){
        return new String[]{"AAA","BBB","CCC"};
    }
   //RESTful는 권고지 강제는 아니다
    //다만 우리는 댓글 단독조회가 아니라 게시글을 들린다
    @GetMapping(value = "/list/{dto}", produces = MediaType.APPLICATION_JSON_VALUE )
    //값이 계속 바뀐다면 쿼리스트링, 아니면 Pathvariable
    //produces는 어떤 형식의 값들을 만들어 질 수 있는지
    //위에서는 "난 Json의 형식만 만든다"
    public List<ReplyDTO> getListOfBoard(@PathVariable("bno") Integer bno){
        return replyService.getListOfBoard(bno);
        //음.. Localdate 때문에 오류가 생기네
    }

}
