package org.zerock.sample;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTests {
    @Autowired
    private SampleService sampleService;
    //의존성 주입 테스트

    @Test
    public void test01(){
        log.info(sampleService);

    }
    //Creating shared instance of singleton bean
    //실행 해보니 singleton 이 만들어 졋다

}
