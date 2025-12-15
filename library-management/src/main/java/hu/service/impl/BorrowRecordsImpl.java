package hu.service.impl;

import hu.mapper.BorrowRecordsMapper;
import hu.service.BorrowRecordsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BorrowRecordsImpl implements BorrowRecordsService {

    @Resource
    private BorrowRecordsMapper borrowRecordsMapper;
}
