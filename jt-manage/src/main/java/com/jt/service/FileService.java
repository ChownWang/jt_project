package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileService
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/7 16:20
 * @Version 1.0
 */
public interface FileService {
    ImageVO upload(MultipartFile uploadFile);
}
