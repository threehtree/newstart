package org.zerock.mapper;

import org.zerock.domain.Reply;

import java.util.List;

public interface ReplyMapper extends GenericMapper<Reply,Integer> {

     List<Reply> selectListOfBoard(Integer bno);
     //상속으로 받는값이 Board 객체라서 따로 작성
}
