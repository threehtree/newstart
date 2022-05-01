package org.zerock.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@ToString
@Getter// setter은 바로 안넣으셧네
public class ListDTO {
    private int page;
    private int size;


    private String type;     //t, tc, tcw
    private String keyword;
    //검색 조건을 위한 프로퍼티
    //페이징을 해주는 부분이면 검색은 당연하니까
    //같이 모아둔 거 겟지(트렌젝션)

    private String link;

    public ListDTO(){
        this.page = 1;
        this.size = 10;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String[] getTypes(){
        if(type == null || type.trim().length() == 0){
            //////////****************************** video skip
//            if(type == null){
//            return null;
//            return new String[]{};
        }
        return type.split("");
        //t tc tcw 한 단어씩 끊어내서 확인 할것
    }

    public String getKeyword(){
        return keyword ==null  || keyword.trim().length() == 0 ? null: keyword.trim();
        //////////****************************** video skip
//        return keyword == null?null:keyword.trim()
        //null일수도 잇으니 일단 반환은 해주자
        //키워드로 공백 넣으면 검색이 매우 느려짐 null아니면 공백제거
    }
//앞선 작업에서 파라메터를 ListDTO로 잡았기 때문에 바로 mapper로 이동

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

    public String getLink(){

//        StringBuilder builder = new StringBuilder();
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder.queryParam("page", getPage())
                .queryParam("size", getSize());

        if(type !=null){
            builder.queryParam("type", type);
        }

        if(keyword !=null){
            String enStr = null;
            try {
                enStr = URLEncoder.encode(keyword,"UTF-8");
                builder.queryParam("keyword",enStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        return builder.build().toString();
    }
    //서버사이드 랜더링 >> 서버에서 모든 작업을 끝내고 화면은 보여만 준다
    //주소를 연결해서 만들어주는 from
}
