package org.zerock.service;

import org.zerock.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {
//근데 전송할려면 DTO있어야 하잖아 DTO gogo
    List<ReplyDTO> getListOfBoard(Integer bno);
}

