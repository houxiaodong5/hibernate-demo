package com.hxd.service.impl;

import com.hxd.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    public String test() {
        return "test";
    }
}
