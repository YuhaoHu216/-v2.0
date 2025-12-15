package hu.controller;

import hu.pojo.Result;
//import hu.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

//用于上传文件
@Slf4j
@RestController
@CrossOrigin(origins = "*")
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

        String filePath = "D:/upload/";

        //将文件保存在服务器磁盘目录中
        image.transferTo(new File(filePath + newFileName));

        return Result.success("http://127.0.0.1:8080/images/" + newFileName);
    }

}
