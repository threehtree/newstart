package org.zerock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListResponseDTO <E>{
    //listDTO와 getTotal은 항상 같이 필요한 내용이다 (트랜젝션)
    //그리고 다른 곳에서도 쓰일 것이기 때문에 Generic으로 만든다
    private List<E> dtoList;
    //extends ~DTO 같이 DTO상속한 것만 보게 가능하게도 할 수 있음
    private int total;
}
