package org.zerock.domain;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    //reply DB참고
    private Integer rno;
    private Integer bno;
    private String replyText;
    private String replyer;
    private LocalDateTime regdate;
    private LocalDateTime updateDate;
}
