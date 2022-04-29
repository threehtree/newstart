package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Board;

import java.util.List;

public interface GenericMapper<E, K> {

    void insert(E board);
    //SelectKey를 이용해서 마지막 index값 을 반환
    List<Board> selectList (@Param("skip") int num, @Param("size") int d);// 일단은 빈 생성자

    void delete(K bno);

    Board selectOne(K bno);

    void update(E board);

}
