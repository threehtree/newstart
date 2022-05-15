package org.zerock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UploadResultDTO {
    private String uuid;
    private String original;
    private String savePath;
    private boolean img;
    //한번에 값들을 보내기 위해 DTO로 묶음

    public String getLink(){//젝슨 라이브러리가 mybatis마냥 get제외하고 프로퍼티로 부를수 있음
        return savePath+"/"+uuid+original;

    }
    public String getThumbnail(){
        return savePath+"/s_"+uuid+"_"+original;
    }
}
