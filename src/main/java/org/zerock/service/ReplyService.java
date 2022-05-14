package org.zerock.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ListDTO;
import org.zerock.dto.ReplyDTO;

import java.util.List;

@Transactional
public interface ReplyService {
//근데 전송할려면 DTO있어야 하잖아 DTO gogo
    List<ReplyDTO> getListOfBoard(Integer bno, ListDTO listDTO);

    int register(ReplyDTO replyDTO);
    //이제는 새로운 댓글이 몇개 추가했는지 받아야 한다
}

