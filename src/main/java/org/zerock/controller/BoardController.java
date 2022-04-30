package org.zerock.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.dto.BoardDTO;

@Controller
@Log4j2
@RequestMapping("/board/")
public class BoardController {

    @GetMapping("/")
    public String basic(){
        return "redirect:/board/list";
//        /board/ 이런식으로 url 접근을 대비하기 위해
        //public String 은 현재 url에서 값이 변할때(return이 필요)
    }

    @GetMapping("/list")
    public void list(){
        log.info("board list...");
    }//servlet.xml에 안의 In ternalResourceViewResolver 를 통해 /
    ///WEB-INF/views/[  ].jsp 이런 식으로 맵핑해준다
    //public void 은 현재 url에서 값이 변할 필요 없을때 (return이 필요없음 )

    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO){
        log.info("----------------");
        log.info(boardDTO);

        return "redirect:/board/list?result=1234";
    }//Post값을 보내기위해 seo 툴을 사용
    // log를 통해 이 페이지가 호출 되는지 확인
    // 보내주기만 하고 돌아와야하니 redirect
    //보내주기위해 필요한 파라메터 BoardDTO 라는 통을 받아야함
    //list?result=1234 쿼리스트링으로 result에 값을 전달태스트
}
