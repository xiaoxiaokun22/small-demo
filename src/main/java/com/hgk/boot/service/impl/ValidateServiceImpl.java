package com.hgk.boot.service.impl;

import com.hgk.boot.service.ValidateService;
import org.springframework.stereotype.Service;

@Service
public class ValidateServiceImpl implements ValidateService {
    @Override
    public boolean validateName(String name) {
        System.out.println("validateName");
        return name != null;
    }
}
