package com.hgk.boot.service.impl;

import com.hgk.boot.entity.Product;
import com.hgk.boot.entity.Purchase;
import com.hgk.boot.mapper.ProductMapper;
import com.hgk.boot.mapper.PurchaseMapper;
import com.hgk.boot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    @Transactional
    public boolean buyProduct(Integer userId,Integer productId, int num) {
        //查询库存
        Product product = productMapper.selectById(productId);
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(product.getNum() < num){
            System.out.println(productId + "==库存不足==");
            return false;
        }
        //减库存
        Integer leftNum =product.getNum() - num;
        product.setNum(leftNum);
        productMapper.updateById(product);
        //购买产品记录
        Purchase purchase = new Purchase();
        purchase.setUserId(userId);
        purchase.setProductId(productId);
        purchase.setUnitPrice(product.getPrice());
        purchase.setNum(num);
        BigDecimal allPrice = product.getPrice().multiply(new BigDecimal(num));
        purchase.setAllPrice(allPrice);
        purchaseMapper.insert(purchase);
        return true;
    }
}
