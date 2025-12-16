package hu.service;

import hu.pojo.Result;
import hu.query.BorrowQuery;
import org.springframework.stereotype.Service;

@Service
public interface BorrowRecordsService {
    /**
     * 分页查询借阅记录
     * @param query
     * @return
     */
    Result page(BorrowQuery query);
}
