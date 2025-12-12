package hu.controller;

import hu.pojo.Result;
import hu.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

//用于上传文件
@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("文件上传:{}",image);
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //用uuid构造唯一文件名
        int index = originalFilename.lastIndexOf("."); //获取扩展名
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新文件名:{}",newFileName);

        //将文件保存在服务器磁盘目录中
        image.transferTo(new File("D:\\新建文件夹\\" + newFileName));
        return Result.success();
    }

//    @Autowired
//    private AliOSSUtils aliOSSUtils;
//
//    @PostMapping("upload")
//    public Result upload(MultipartFile image) throws Exception{
//        log.info("文件上传，文件名:{}",image.getOriginalFilename());
//        //调用阿里云OSS工具类进行文件上传
//        String url = aliOSSUtils.upload(image);
//        log.info("文件上传完成,文件访问的url:{}",url);
//        return Result.success( url );
//    }
}
