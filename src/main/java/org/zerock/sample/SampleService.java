package org.zerock.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@ToString
@RequiredArgsConstructor//빈 생성자 생성
@Service //스프링이 관리하기 위한 bean객체 등록 (Service)
public class SampleService {
    private final SampleDAO sampleDAO; //의존성 주입
    // 생성자를 이용한 의존성 주입 -lombok 이용
}
