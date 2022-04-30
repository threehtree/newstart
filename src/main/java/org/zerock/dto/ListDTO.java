package org.zerock.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter// setter은 바로 안넣으셧네
public class ListDTO {
    private int page;
    private int size;

    public ListDTO(){
        this.page = 1;
        this.size = 10;
    }

    public void setPage(int page) {
        this.page = page <= 0? 1:page;
    }
    public void setSize(int size){
        this.size = size <= 10? 10: size;
    }

    public int getSkip(){
        return (this.page -1) * size;
    }
    //ListDTO를 만드니 제약조건은 생성자나 setter를 통해 만들수 잇고
    //자바Bean 규칙으로 만든 사용자 정의 클래스라서 model에 자동으로
    //올라가서 Mybatis에서 getter로 찾아 #skip으로 사용가능
    //만약 EL이라면 프로퍼티를 private int skip 이런식으로 정해줘야
    //EL이 타고와서 getter를 확인 하고 값을 가져간다만
    // mybatis라서 getter만 보고 가져감

    //트렌젝션같이 하나의 작업에 값이 같이 필요할 것 같은것을 미리 설계 하면 나중에
//수정이 많이 줄어 들 수 있다
//여기서는 size, page , skip  값이 였다

    //1. 파라메터가 2개(이상or예정), 2. 복잡한 제약조건
}
