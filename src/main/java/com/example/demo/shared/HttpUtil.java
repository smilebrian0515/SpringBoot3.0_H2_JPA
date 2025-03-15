package com.example.demo.shared;

import java.util.Map;

public interface HttpUtil {
    public String Get(String url, Map<String, String> params);
}
