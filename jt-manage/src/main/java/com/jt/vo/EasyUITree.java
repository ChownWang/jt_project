package com.jt.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITree {
    /**
     * id : 与ItemCat中的Id一致的
     * text : itemCat中的name属性
     * state : 打开:open  关闭: closed
     */

    /**{"id":"2","text":"王者荣耀","state":"closed"}*/
    private Long id;
    private String text;
    private String state;

}