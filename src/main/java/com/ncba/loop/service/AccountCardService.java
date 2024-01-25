package com.ncba.loop.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AccountCardService {
    ResponseEntity<Map<String, Object>> getAllCardsByAccount(String clientId, String clientSecret);
}
