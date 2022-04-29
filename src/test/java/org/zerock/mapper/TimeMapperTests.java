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
public class TimeMapperTests {

    @Autowired(required = false)
    // timeMapper는 현재 bean으로 만들어진게 없기에 자동스캔을 막아둔다
    private TimeMapper timeMapper;

    @Test
    public void testTime(){
        log.info("------------------------");
        log.info(timeMapper.getClass().getName());
        //com.sun.proxy.$Proxy39 AOP를 통해 우리가 객체를 만들지도 않았는데 호출됨
        log.info(timeMapper.getTime());
        //OK 잘 호출됨

    }

}
