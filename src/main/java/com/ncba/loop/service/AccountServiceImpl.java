package com.ncba.loop.service;


import com.ncba.loop.dto.AccountCreationDto;
import com.ncba.loop.dto.EditDetailsAccountDto;
import com.ncba.loop.entity.Account;
import com.ncba.loop.entity.Card;
import com.ncba.loop.repository.AccountRepository;
import com.ncba.loop.repository.CardRepository;
import com.ncba.loop.utils.APIResponse;
import com.ncba.loop.utils.ValidationFieldResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    private final CardRepository cardRepository;

    public AccountServiceImpl(AccountRepository accountRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createAccount(AccountCreationDto accountCreationDto, BindingResult bindingResult) {
        try{
            //Check for Validation Errors
            if(bindingResult.hasErrors()){
                return ValidationFieldResponse.validationCheckingFailed(bindingResult);
            }

            //Create the account
            Map<String, Object> jsonResponse = new HashMap<>();
            Account accountToCreate = new Account();
            accountToCreate.setIban(accountCreationDto.getIban());
            accountToCreate.setBicSwift(accountCreationDto.getBicSwift());
            accountToCreate.setClientId(accountCreationDto.getClientId());

            List<Card> cards = new ArrayList<>();
            accountToCreate.setCards(cards);


            cardRepository.saveAll(cards);
            accountRepository.save(accountToCreate);
            jsonResponse.put("account", accountToCreate);
            jsonResponse.put("info", "Use the generated accountId to create a card");


            return APIResponse.genericResponse("success", jsonResponse, HttpStatus.CREATED);

        }catch(Exception ex){
            return APIResponse.genericResponse("failure", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchAccountDetails(String accountId) {
        try{
            Map<String, Object> jsonResponse = new HashMap<>();
            Account fetchedAccount = accountRepository.findByAccountId(Long.valueOf(accountId)).orElse(null);

            if(fetchedAccount == null){
                return APIResponse.genericResponse("failure", "Account not found", HttpStatus.NOT_FOUND);
            }

            jsonResponse.put("accountDetails", fetchedAccount);
            return APIResponse.genericResponse("success", jsonResponse, HttpStatus.OK);
        }catch(Exception ex){
            return APIResponse.genericResponse("failure", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> deleteAccount(String accountId) {
        try{
            Account accountToDelete = accountRepository.findByAccountId(Long.valueOf(accountId)).orElse(null);

            if(accountToDelete == null){
                return APIResponse.genericResponse("failure", "Account does not exist", HttpStatus.BAD_REQUEST);
            }

            accountRepository.deleteByAccountId(Long.valueOf(accountId));
            return APIResponse.genericResponse("success", "Account deleted successfully", HttpStatus.OK);
        }catch(Exception ex){
            return APIResponse.genericResponse("failure", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> editDetailsAccountDto(EditDetailsAccountDto editDetailsAccountDto, String accountId) {
        try{
            Account fetchedAccount = accountRepository.findByAccountId(Long.valueOf(accountId)).orElse(null);

            if(fetchedAccount == null){
                return APIResponse.genericResponse("failure", "Account not found", HttpStatus.BAD_REQUEST);
            }

            if(editDetailsAccountDto.getIban() != null){
                fetchedAccount.setIban(editDetailsAccountDto.getIban());
            }
            if(editDetailsAccountDto.getBicSwift() != null){
                fetchedAccount.setBicSwift(editDetailsAccountDto.getBicSwift());
            }

            if(editDetailsAccountDto == null){
                return APIResponse.genericResponse("failure", "Please check your request body", HttpStatus.BAD_REQUEST);
            }
            accountRepository.save(fetchedAccount);
            return APIResponse.genericResponse("success", "Account edited successfully", HttpStatus.OK);
        }catch(Exception ex){
            return APIResponse.genericResponse("failure", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
