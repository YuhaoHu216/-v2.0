package hu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//代替getter和setter方法
@NoArgsConstructor//代替空参构造
@AllArgsConstructor//代替全参构造
public class Result {
    private Integer code;//响应码,1为成功,0为失败
    private String msg;//响应信息,描述字符串
    private Object data;//返回的数据

    //不需要返回数据的操作
    public static Result success(){
        return new Result(1,"success",null);}

    //需要返回数据的操作
    public static Result success(Object data){
        return new Result(1,"success",data);
    }

    //失败响应
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
