package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.mapper.TimeMapper;

@Service
@Log4j2
@RequiredArgsConstructor//final에 filed에 생성자를 만들어 달라
public class TimeServiceImpl implements TimeService {

    private final TimeMapper timeMapper;

    @Override
    public void insertALL(String text) {
    timeMapper.insertA(text);
    timeMapper.insertB(text);
    }
}
