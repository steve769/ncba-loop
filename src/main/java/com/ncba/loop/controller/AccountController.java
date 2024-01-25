package com.ncba.loop.controller;


import com.ncba.loop.dto.AccountCreationDto;
import com.ncba.loop.dto.EditDetailsAccountDto;
import com.ncba.loop.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Map<String, Object>> createAccount(@Valid @RequestBody AccountCreationDto accountCreationDto, BindingResult bindingResult){
        return accountService.createAccount(accountCreationDto, bindingResult);
    }

    @GetMapping("/fetchAccount/{accountId}")
    public ResponseEntity<Map<String, Object>> fetchAccountDetails(@PathVariable String accountId){
        return accountService.fetchAccountDetails(accountId);
    }
    @PatchMapping("/editAccountDetails/{accountId}")
    public ResponseEntity<Map<String, Object>> editAccountDetails(@Valid @RequestBody EditDetailsAccountDto editDetailsAccountDto, @PathVariable String accountId){
        return accountService.editDetailsAccountDto(editDetailsAccountDto, accountId);
    }

    @DeleteMapping("/deleteAccount/{accountId}")
    public ResponseEntity<Map<String, Object>> deleteAccount( @PathVariable String accountId){
        return accountService.deleteAccount(accountId);
    }


}
