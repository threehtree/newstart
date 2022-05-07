package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
    //DB연결후 가장 먼져 실행할 sql은 시간.
//    @Select("slect now()")
    //간단한 쿼리는 @이면 되지만 좀 길어지는 쿼리를 사용하기엔 어려워
    //mybatis를 사용해 xml을 쓰겟다
    public String getTime();
    @Insert("insert into tbl_a (text) values (#{text})")
    void insertA(String text);

    @Insert("insert into tbl_b (text) values (#{text})")
    void insertB(String text);
}
