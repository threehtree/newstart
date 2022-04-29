package org.zerock.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter //VO니까
@AllArgsConstructor //모든 프로퍼티 생성자로 사용
@Builder //생성자를 일부만 넣고 싶을때 유용 (AllArgsConstructor와 따라다닌다고 보자
@ToString //log찍어볼 부분이면 넣어주는게 좋다
@NoArgsConstructor //빈 생성자 , 의존성주입 할때 사용하거나 할때 사용할때 자주 사용됨

public class Board {

    private Integer bno;
    private String title;
    private String content;
    private String writter;


}
