package hu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data//代替getter和setter方法
@NoArgsConstructor//代替空参构造
@AllArgsConstructor//代替全参构造
public class PageBean {

    private Long total;//记录总数
    private List rows;//数据列表
}
