package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName ItemCat
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/1 11:41
 * @Version 1.0
 */
@Data
@TableName(value="tb_item_cat")
@Accessors(chain = true)
public class ItemCat {

    /**
     * id : 560
     * parentId : 父级ID
     * name : 分类名称
     * status : 状态信息 1正常，2删除'
     * sortOrder : 排序号
     * isParent : 是否为父级. true/fase
     */
    @TableId(type=IdType.AUTO)
    private Long id;
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;
}
