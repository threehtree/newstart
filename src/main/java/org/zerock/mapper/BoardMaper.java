package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Board;

import java.util.List;

public interface BoardMaper extends GenericMapper<Board,Integer>{
//    //Mapper 작성하기전에 기준점을 잡아 두기위해 domain 을 먼저 설정한다
//    Integer insert(Board board);
//    //SelectKey를 이용해서 마지막 index값 을 반환
//    List<Board> selectList (@Param("skip") int num,@Param("size") int d);// 일단은 빈 생성자
//
//    void delete(Integer bno);
//
//    Board selectOne(Integer bno);
//
//    void update(Board board);
    void updateReplyCount(@Param("bno") Integer bno,@Param("amount") int amount);
    //DTO를 쓰는게 좋으나 여기서 설계 해서 넣을 정도는 아닌듯 하다

}
