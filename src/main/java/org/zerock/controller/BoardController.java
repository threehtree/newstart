package org.zerock.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.ListDTO;

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
    public void list(@ModelAttribute(name= "chi") ListDTO listDTO){
        log.info("board list...");
        log.info(listDTO);
    }//servlet.xml에 안의 In ternalResourceViewResolver 를 통해 /
    ///WEB-INF/views/[  ].jsp 이런 식으로 맵핑해준다
    //public void 은 현재 url에서 값이 변할 필요 없을때 (return이 필요없음 )
    //@RequestParam의 부족한 제약조건, page를 받을때 기본적으로 size도 같이쓰게 된다. 2파라메터 이상, page,size 트렌젝션
    //DTO를 쓰면 파라메터의 갯수 수정시 DTO 만 수정하여 수정이 최소화됨


    @GetMapping("/register")
    public void registerGet(){

    }

    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO, RedirectAttributes rttr){
        log.info("----------------");
        log.info(boardDTO);
        rttr.addFlashAttribute("result", 555555);
//        rttr.addAttribute("num", "321");
        return "redirect:/board/list";
    }//Post값을 보내기위해 seo 툴을 사용
    // log를 통해 이 페이지가 호출 되는지 확인
    // 보내주기만 하고 돌아와야하니 redirect
    //보내주기위해 필요한 파라메터 BoardDTO 라는 통을 받아야함
    //list?result=1234 쿼리스트링으로 result에 값을 전달태스트
    //BoardDTO라는 파라메터 이외에 필요한 값을 추가로 보내기위해 RedirectAttributes를 통해 전달
}
