package com.hgk.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("w_product")
public class Product extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("price")
    private BigDecimal price;

    @TableField("num")
    private Integer num;


}
