package org.zerock.mapper;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")

public class ReplyMapperTests {
    @Autowired(required = false)
    private ReplyMapper replyMapper;

    @Test
    public void test1(){
    //274
        Integer bno = 451139;
        replyMapper.selectListOfBoard(bno).forEach(reply -> {log.info(reply);});
    }
//테스트도 끝났으니 이제 기능 넣자 > service
}
