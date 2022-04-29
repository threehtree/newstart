package org.zerock.mapper;

import org.zerock.domain.Board;

import java.util.List;

public interface BoardMaper {
    //Mapper 작성하기전에 기준점을 잡아 두기위해 domain 을 먼저 설정한다
    void insert(Board board);
    List<Board> selectList (int num, int d);// 일단은 빈 생성자


}
