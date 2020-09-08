package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName ImageVO
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/7 16:13
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO implements Serializable {
    /**
     * 确认是否有错误   0正常    1错误
     */
    private Integer error;
    /**
     * 图片访问的虚拟地址.
     */
    private String  url;
    /**
     * 宽度
     */
    private Integer width;
    /**
     * 高度
     */
    private Integer height;

    public static ImageVO fail(){

        return new ImageVO(1, null, null, null);
    }

    public static ImageVO success(String url){

        return new ImageVO(0, url, null, null);
    }

    public static ImageVO success(String url,Integer width,Integer height){

        return new ImageVO(0, url, width, height);
    }

}
