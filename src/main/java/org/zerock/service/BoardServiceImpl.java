package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.domain.Board;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.ListDTO;
import org.zerock.dto.ListResponseDTO;
import org.zerock.mapper.BoardMaper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{
//앞의 작업이 어쨋든 순수한 자바 Pojo 임
    private final BoardMaper boardMaper;// 기능을 구현하기 위해
    private final ModelMapper modelMapper; //Borad 로 받은 값을 BoardDto로 반환하기 위해
    @Override
    public ListResponseDTO<BoardDTO> getList(ListDTO listDTO) {
        List<Board> boardList = boardMaper.selectList(listDTO);
        //현재 반환타입이 Board 타입으로 받는다
        //하지만 service=> Controller는 DTO로 보내야 한다 => 변환환다
        List<BoardDTO> dtoList = boardList.stream().map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());
        // Board -> BoardDTO로 변환하기 위해 modelMapper 라이브러리 사용
        // .map(A,B) 은 A를 B 바꾸고 .collect 는 모아서 toList 는 그걸 List로 만들어준다
        return ListResponseDTO.<BoardDTO>builder()
                .dtoList(dtoList)
                .total(boardMaper.getTotal(listDTO))
                .build();
    }

    @Override
    public BoardDTO getOne(Integer bno) {
        Board board = boardMaper.selectOne(bno);
        //게시글 한가지 가져옴옴

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        //여기서는 리스트 배열을 받거나 하는게 아니라 보드 만. 받음음
        return boardDTO;
    }

    @Override
    public void update(BoardDTO boardDTO) {
        boardMaper.update(Board.builder()
                        .bno(boardDTO.getBno())
                        .title(boardDTO.getTitle())
                        .content(boardDTO.getContent())
                        .build());

    }

    @Override
    public void remove(Integer bno) {
        boardMaper.delete(bno);

    }


}
