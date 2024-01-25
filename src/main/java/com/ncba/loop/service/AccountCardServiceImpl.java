package com.ncba.loop.service;

import com.ncba.loop.entity.Account;
import com.ncba.loop.entity.Card;
import com.ncba.loop.repository.AccountRepository;
import com.ncba.loop.utils.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountCardServiceImpl implements AccountCardService{
    private final AccountRepository accountRepository;
    private final Map<String , String> credentials = new HashMap<>();
    public AccountCardServiceImpl(AccountRepository accountRepository) {
        //sample credentials
        credentials.put("kevin", "password1");
        credentials.put("steve", "password2");
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllCardsByAccount(String clientId, String clientSecret) {
        try{
            if(credentials.containsKey(clientId)){
                if(!credentials.get(clientId).equals(clientSecret)){
                    return APIResponse.genericResponse("failure", "Please check your credentials", HttpStatus.FORBIDDEN);
                }
            }else{
                return APIResponse.genericResponse("failure", "Please check your credentials", HttpStatus.FORBIDDEN);
            }

            Map<String, Object> jsonResponse = new HashMap<>();
            Account fetchedAccount = accountRepository.findByClientId(clientId).orElse(null);

            if(fetchedAccount == null){
                return APIResponse.genericResponse("failure", "Account not found", HttpStatus.NOT_FOUND);
            }
            List<Card> cards = fetchedAccount.getCards();
            if(cards.size() == 0){
                return APIResponse.genericResponse("failure", "No cards found", HttpStatus.NOT_FOUND);
            }
            jsonResponse.put("clientId", fetchedAccount.getClientId());
            jsonResponse.put("cards", cards);
            return APIResponse.genericResponse("success", jsonResponse, HttpStatus.OK);
        }catch(Exception ex){
            return APIResponse.genericResponse("failure", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
