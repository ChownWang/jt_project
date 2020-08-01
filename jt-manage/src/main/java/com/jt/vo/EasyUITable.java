package com.jt.vo;

import com.jt.pojo.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName EasyUITable
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/7/31 14:54
 * @Version 1.0
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITable {

    private Long total;
    private List<Item> rows;
}
