package com.ncba.loop.controller;

import com.ncba.loop.service.AccountCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/accountCard")
public class AccountCardController {

    private final AccountCardService accountCardService;

    public AccountCardController(AccountCardService accountCardService) {
        this.accountCardService = accountCardService;
    }

    @GetMapping("/getAllCardsOwnedByClient")
    public ResponseEntity<Map<String, Object>> getAllCardsByAccount(@RequestParam String clientId, @RequestParam String clientSecret){
        return accountCardService.getAllCardsByAccount(clientId, clientSecret);
    }
}
