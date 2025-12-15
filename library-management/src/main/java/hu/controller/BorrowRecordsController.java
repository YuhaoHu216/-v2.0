package hu.controller;

import hu.service.BorrowRecordsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrowRecords")
public class BorrowRecordsController {
    @Resource
    private BorrowRecordsService borrowRecordsService;
}
