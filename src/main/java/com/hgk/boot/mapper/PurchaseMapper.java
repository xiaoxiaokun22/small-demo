package com.hgk.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hgk.boot.entity.Product;
import com.hgk.boot.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper extends BaseMapper<Purchase> {
}
