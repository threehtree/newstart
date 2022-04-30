package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Board;
import org.zerock.dto.ListDTO;

import java.util.List;

public interface GenericMapper<E, K> { //CRUD는 계속 다른곳에서도 사용할 예정

    void insert(E board);
    //SelectKey를 이용해서 마지막 index값 을 반환
    List<Board> selectList (ListDTO listDTO);
    // 일단은 빈 생성자
    // 검색이 들어가면 파라메터가 엄청 늘어난다

    int getTotal(ListDTO listDTO);
    //조회를 해서 나온 게시물을 확인하겟다
    //Board의 원하는 게시물을 확인 가능
    void delete(K bno);

    Board selectOne(K bno);

    void update(E board);

}
