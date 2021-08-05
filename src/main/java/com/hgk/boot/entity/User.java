package com.hgk.boot.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("user")
public class User {

    @Value("2")
    public Long id;

    @Value("hgk2")
    public String name;

    @Value("note2")
    public String note;
}
