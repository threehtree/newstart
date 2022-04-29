package org.zerock.store;

import org.springframework.stereotype.Repository;

@Repository
public class KoreanChef implements Chef{
    @Override
    public String cook() {
        return "김밥말이 달인";
    } //이제는 Chef에 의존성 주입이 가능하다
    //d
}
