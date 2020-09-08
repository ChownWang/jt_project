package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @ClassName FileController
 * @Description
 * @Author ChownWang
 * @Date 2020/8/7 16:17
 * @Version 1.0
 */
@RestController
public class FileController {

    @Resource
    private FileService fileService;
    /**
     * 实现图片上传操作.
     * url地址:http://localhost:8091/pic/upload?dir=image
     * @param uploadFile
     * @return ImageVO对象
     */
    @RequestMapping("/pic/upload")
    public ImageVO upload(MultipartFile uploadFile) {

        return fileService.upload(uploadFile);
    }
}
