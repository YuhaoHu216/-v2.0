package hu.controller;

import hu.pojo.Result;
import hu.query.BorrowQuery;
import hu.service.BorrowRecordsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/borrowRecords")
@CrossOrigin(origins = "*")
public class BorrowRecordsController {
    @Resource
    private BorrowRecordsService borrowRecordsService;

    @PostMapping("/page")
    public Result page(@RequestBody BorrowQuery query){
        log.info("分页查询借阅记录:{}", query);
        return borrowRecordsService.page(query);
    }
}
