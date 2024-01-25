package com.ncba.loop.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class APIResponse {
    public static ResponseEntity<Map<String, Object>> genericResponse(String status, Object message, HttpStatus HTTP_STATUS){
        Map<String, Object> response = new HashMap<>();

        response.put("status", status);
        response.put("message", message);

        return ResponseEntity.status(HTTP_STATUS).body(response);

    }
}
