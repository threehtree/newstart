package org.zerock.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
//여기에는 Builder가 따로 안들어간다
//우리는 DTO를 따로 관리해서 파라매터를 수집한다
//DTO를 이미 수집햇기때문에 만들지 않는다
public class ReplyDTO {
    private Integer rno;
    private Integer bno;
    private String replyText;
    private String replyer;
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime regdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime updateDate;
}
