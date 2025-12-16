package hu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hu.mapper.BorrowRecordsMapper;
import hu.pojo.PageBean;
import hu.pojo.Result;
import hu.query.BorrowQuery;
import hu.service.BorrowRecordsService;
import hu.vo.BorrowRecordsVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRecordsImpl implements BorrowRecordsService {

    @Resource
    private BorrowRecordsMapper borrowRecordsMapper;

    // 分页查询借阅记录
    @Override
    public Result page(BorrowQuery query) {
        PageHelper.startPage(query.getPage(),query.getPageSize());
        List<BorrowRecordsVO> borrowQueryList = borrowRecordsMapper.list(query);
        Page<BorrowRecordsVO> page = (Page<BorrowRecordsVO>) borrowQueryList;
        PageBean pageBean = new PageBean(page.getTotal(),page.getResult());
        return Result.success(pageBean);
    }
}
