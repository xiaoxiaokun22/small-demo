package com.hgk.boot.config;

import com.hgk.boot.common.Project;
import com.hgk.boot.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Configuration
//@ComponentScan("com.hgk.boot.*")
public class AppConfig {
    @Bean(name="Brand")
//    @Bean
    public Brand initBrand(){
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("耐克");
        brand.setDesc("著名的鞋子品牌");
        return brand;
    }
}


