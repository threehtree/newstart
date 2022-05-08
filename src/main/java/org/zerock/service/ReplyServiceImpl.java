package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.domain.Reply;
import org.zerock.dto.ListDTO;
import org.zerock.dto.ReplyDTO;
import org.zerock.mapper.BoardMaper;
import org.zerock.mapper.ReplyMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{
    private final ReplyMapper replyMapper;
    //Board에 replyCount 가 있으니 댓글에 관련이 된다
    //댓글의 수정 삭제시 Board의 컬럼에 영향을 끼친다
    //>> 2개이상의 작업이 같이 되어야 한다 => 트랜젝션
    private final ModelMapper modelMapper;
    //Vo -> Dto
    private final BoardMaper boardMaper;

    @Override
    public List<ReplyDTO> getListOfBoard(Integer bno, ListDTO listDTO) {

        List<Reply> replyList = replyMapper.selectListOfBoard(bno, listDTO);

        List<ReplyDTO> dtoList = replyList.stream().map(reply -> modelMapper.map(reply, ReplyDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public void register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);

        replyMapper.insert(reply);
        //댓글추가
        boardMaper.updateReplyCount(replyDTO.getBno(), 1);
        //게시물의 댓글카운트 1증가

    }

}
