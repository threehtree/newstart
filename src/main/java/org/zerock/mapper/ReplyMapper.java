package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Reply;
import org.zerock.dto.ListDTO;

import java.util.List;

public interface ReplyMapper extends GenericMapper<Reply,Integer> {

     List<Reply> selectListOfBoard(@Param("bno") Integer bno,@Param("listDTO") ListDTO listDTO);
     //상속으로 받는값이 Board 객체라서 따로 작성

     //페이징을 위해 값을 받아야하는데?
}
