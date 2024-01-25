package com.ncba.loop.service;

import com.ncba.loop.dto.AccountCreationDto;
import com.ncba.loop.dto.EditDetailsAccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface AccountService {
    ResponseEntity<Map<String, Object>> createAccount(AccountCreationDto accountCreationDto, BindingResult bindingResult);

    ResponseEntity<Map<String, Object>> fetchAccountDetails(String accountId);


    ResponseEntity<Map<String, Object>> deleteAccount(String accountId);

    ResponseEntity<Map<String, Object>> editDetailsAccountDto(EditDetailsAccountDto editDetailsAccountDto, String accountId);
}
