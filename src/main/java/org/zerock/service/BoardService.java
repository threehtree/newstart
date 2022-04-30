package org.zerock.service;

import org.zerock.dto.BoardDTO;
import org.zerock.dto.ListDTO;
import org.zerock.dto.ListResponseDTO;

import java.util.List;

public interface BoardService {
    ListResponseDTO<BoardDTO> getList(ListDTO listDTO);

    //의존성 주입은 가능한 인터페이스로 느슨하게 잡아주자
}
