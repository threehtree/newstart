package org.zerock.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.ListDTO;
import org.zerock.dto.ListResponseDTO;
import org.zerock.dto.PageMaker;
import org.zerock.service.BoardService;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board/")
@RequiredArgsConstructor //생생자를 위한 의존성 주입을 위해
public class BoardController {

    private final BoardService service;

    @GetMapping("/read/{bno}")
    public String read(@PathVariable("bno") Integer bno, ListDTO listDTO, Model model){
        //내가 따로 보내야하는값은 model을 이용한다

        log.info("---------------------------------");

        log.info(bno);

        log.info(listDTO);

        model.addAttribute("dto", service.getOne(bno));
        //쿼리 결과로 받은 boardDTO반환

//        return  "/board/read";
          return  "/board/read1";
//        return "/board/read2";
        //사용자가 정의한 DTO는 다른 정의 없어도 jsp로 전달이 된다
    }

    @GetMapping("/modify/{bno}")
    public String modifyGET(@PathVariable("bno") Integer bno, ListDTO listDTO, Model model){
        //내가 따로 보내야하는값은 model을 이용한다

        log.info("---------------------------------");

        log.info(bno);

        log.info(listDTO);

        model.addAttribute("dto", service.getOne(bno));
        //쿼리 결과로 받은 boardDTO반환

        return "/board/modify";

    }


    @GetMapping("/")
    public String basic(){
        return "redirect:/board/list";
//        /board/ 이런식으로 url 접근을 대비하기 위해
        //public String 은 현재 url에서 값이 변할때(return이 필요)
    }

    @GetMapping("/list")
    public void list(ListDTO listDTO, Model model){
        log.info("board list...");
        log.info(listDTO);
        ListResponseDTO<BoardDTO> responseDTO = service.getList(listDTO);
        model.addAttribute("dtoList",responseDTO.getDtoList());
        int total = responseDTO.getTotal();
//        model.addAttribute("total",total);
        //어차피 pageMaker에 들어가니까 필요없어 보이네

        model.addAttribute("pageMaker", new PageMaker(listDTO.getPage(),total));


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

    @GetMapping("/remove/{bno}")
    public String getNotsupported(){
        return "redirect:/board/list";
    }


    @PostMapping("/remove/{bno}")
    public String removePost(@PathVariable("bno") Integer bno,RedirectAttributes rttr){
        log.info("-------------");
        log.info("-------------");
        log.info("remove" + bno);
        log.info("-------------");

        service.remove(bno);

        log.info("-------------");
        rttr.addFlashAttribute("result", "removed");

        return "redirect:/board/list"; 
    }
    @PostMapping("/modify/{bno}")
    public String modifyPost(@PathVariable("bno") Integer bno,BoardDTO boardDTO, ListDTO listDTO,RedirectAttributes rttr){
        //튕겨내면서 파라메터를 줄려면 redirectAttribute를 써야함
        //검색조건 유지를 위해 ListDTO도 필요하다
        //**그렇게 궁금햇던 @PathVariable은 URL경로의 bno를 가져온거임[pdf4참고]
        log.info("-------------");
        log.info("-------------");
        boardDTO.setBno(bno);
        //수정하면서 bno값이 바뀔수 있어서 다시 지정
        log.info("modify" + boardDTO);
        //수정했으니까 수정내역을 보여줘야지
        log.info("-------------");
        service.update(boardDTO);

//        rttr.addAttribute("bno", bno);
        //redirect시 지금 나의bno값이 uri를 통해 전달된다?
//        rttr.addAttribute("page", listDTO.getPage());
        //우리는 이미 링크를 만들어 둿다

        log.info("-------------");
        rttr.addFlashAttribute("result", "modified");

        return "redirect:/board/read/"+bno+listDTO.getLink();
        //우리가 방금 열어봣던 글로 돌아가야하니까
        //listDTO에 이미 만들어 둿던 getLink사용
    }
}
