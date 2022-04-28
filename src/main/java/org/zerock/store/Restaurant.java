package org.zerock.store;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ToString
@Service
@RequiredArgsConstructor
public class Restaurant {
//    @Autowired//의존성 주입을 위해 사용되는 @
    private final Chef chef; //의존성 주입이 되지 않는다, 만들어진 Bean객체가  없다 0개다

}
