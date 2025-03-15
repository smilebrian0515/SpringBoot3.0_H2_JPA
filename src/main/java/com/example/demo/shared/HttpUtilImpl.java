package com.example.demo.shared;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HttpUtilImpl implements HttpUtil {
    @Override
    public String Get(String url, Map<String, String> params) {
        return "";
    }
}
