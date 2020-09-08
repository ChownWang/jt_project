package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName Cart
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/22 14:57
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@TableName("tb_cart")
public class Cart extends BasePojo {
    private static final long serialVersionUID = 3803459761676831666L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long itemId;
    private String itemTitle;
    private String itemImage;
    private Long itemPrice;
    private Integer num;
}
