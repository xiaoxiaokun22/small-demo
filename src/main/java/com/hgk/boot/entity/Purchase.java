package com.hgk.boot.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("w_purchase")
public class Purchase extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Integer userId;

    @TableField("product_id")
    private Integer productId;

    @TableField("unit_price")
    private BigDecimal unitPrice;

    @TableField("num")
    private Integer num;

    @TableField("all_price")
    private BigDecimal allPrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private BigDecimal createdAt;
}
