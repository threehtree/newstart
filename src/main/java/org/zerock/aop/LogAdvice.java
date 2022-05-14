package org.zerock.aop;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class LogAdvice {
    @Before("execution(* org.zerock.service.*.*(..))") //정말 오류가 많습니다 주의
    public void printLog(JoinPoint joinPoint){

        Object[] params = joinPoint.getArgs();
        log.info("--------------------------");
        log.info("--------------------------");
        log.info("--------------------------");
        log.info(Arrays.toString(params));
        log.info("--------------------------");
        log.info("--------------------------");

    }
}
