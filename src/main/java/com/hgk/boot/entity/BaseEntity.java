package com.hgk.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity<T> implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    protected Integer id;
}
