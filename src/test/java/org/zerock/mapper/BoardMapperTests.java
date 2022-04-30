package org.zerock.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.domain.Board;
import org.zerock.dto.ListDTO;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class BoardMapperTests {
   @Autowired(required = false)
   private BoardMaper boardMaper; //BoardMapper의존성이 필요하다

    @Test
    public void testInsert(){
       Board board= Board.builder()
               .writer("쓰기")
               .content("콘탠츠")
               .title("제목")
               .build();

       boardMaper.insert(board);
       log.info(board);

    }
    @Test
    public void testSelectList(){
        ListDTO listDTO = new ListDTO();
        //여기서 ListDTO 객체를 만들면 객체에 값이 들어가 있는가?
        listDTO.setType("tcw");
        listDTO.setKeyword("1111");
        List<Board> boardList = boardMaper.selectList(listDTO);
        boardList.forEach(board -> log.info(board));
    }// 2개 이상의 파라메터는 써야하는 정해진 이름으로 사용하면 가능하다

    @Test
    public void testDelete(){
        int bno = 5439387;
        boardMaper.delete(bno);

    }
    @Test
    public void testSelectOne(){
        int bno =5439386;
        Board board =boardMaper.selectOne(bno);
        log.info(board);
    }

    @Test
    public void testUpdate(){
        Board board= Board.builder()
                .writer("쓰기")
                .content("콘탠츠")
                .title("제목")
                .bno(5439386)
                .build();
        boardMaper.update(board);
    }


}
