package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName ItemDesc
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/3 14:33
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@TableName("tb_item_desc")
@NoArgsConstructor
@AllArgsConstructor
public class ItemDesc extends BasePojo implements Serializable {
    private static final long serialVersionUID = -1674211217799168752L;
    @TableId
    private Long itemId;
    private String itemDesc;
}
