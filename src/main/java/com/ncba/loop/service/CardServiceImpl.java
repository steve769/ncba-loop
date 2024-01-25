package com.ncba.loop.service;


import com.ncba.loop.dto.CardCreationDto;
import com.ncba.loop.dto.EditDetailsCardDto;
import com.ncba.loop.entity.Account;
import com.ncba.loop.entity.Card;
import com.ncba.loop.entity.CardType;
import com.ncba.loop.repository.AccountRepository;
import com.ncba.loop.repository.CardRepository;
import com.ncba.loop.utils.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardServiceImpl implements CardService{
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    public CardServiceImpl(AccountRepository accountRepository,
                           CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createCard(CardCreationDto cardCreationDto) {
        try{
            Card cardToCreate = new Card();
            cardToCreate.setCardAlias(cardCreationDto.getCardAlias());
            cardToCreate.setCardType(CardType.valueOf(cardCreationDto.getCardType()));

            //Check if account exists
            Account account = accountRepository.findByAccountId(Long.valueOf(cardCreationDto.getAccountId())).orElse(null);

            if(account == null){
                return APIResponse.genericResponse("failure", "Account does not exist, you can only create cards for existing accounts", HttpStatus.BAD_REQUEST);
            }

            cardToCreate.setAccount(account);
            cardRepository.save(cardToCreate);
            return APIResponse.genericResponse("success", "Card created successfully", HttpStatus.CREATED);
        }catch(Exception ex){
            return APIResponse.genericResponse("failure", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchCardById(String cardId) {
        try{
            Map<String, Object> jsonResponse = new HashMap<>();
            Card cardFromDB = (Card) cardRepository.findByCardId(Long.valueOf(cardId)).orElse(null);

            if(cardFromDB == null){
                return APIResponse.genericResponse("failure", "Card not found", HttpStatus.NOT_FOUND);
            }
            jsonResponse.put("cardDetails", cardFromDB);
            return APIResponse.genericResponse("success", jsonResponse, HttpStatus.OK);

        }catch(Exception ex){
            return APIResponse.genericResponse("failure", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> deleteCardById(String cardId) {
        try{
            Map<String, Object> jsonResponse = new HashMap<>();
            Card cardFromDB = (Card) cardRepository.findByCardId(Long.valueOf(cardId)).orElse(null);

            if(cardFromDB == null){
                return APIResponse.genericResponse("failure", "No such card", HttpStatus.BAD_REQUEST);
            }
            cardRepository.deleteByCardId(Long.valueOf(cardId));
            return APIResponse.genericResponse("success", "Card deleted successfully", HttpStatus.OK);

        }catch(Exception ex){
            return APIResponse.genericResponse("failure", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> editCardDetailsById(EditDetailsCardDto editDetailsCardDto, String cardId) {
        try{
            Map<String, Object> jsonResponse = new HashMap<>();
            Card cardFromDB = (Card) cardRepository.findByCardId(Long.valueOf(cardId)).orElse(null);

            if(cardFromDB == null){
                return APIResponse.genericResponse("failure", "No such card", HttpStatus.BAD_REQUEST);
            }

            if(editDetailsCardDto.getAlias() == null || editDetailsCardDto.getAlias().isEmpty()){
                return APIResponse.genericResponse("failure", "Please check your request body", HttpStatus.BAD_REQUEST);
            }

            cardFromDB.setCardAlias(editDetailsCardDto.getAlias());
            cardRepository.save(cardFromDB);
            return APIResponse.genericResponse("success", "Card details edited successfully", HttpStatus.OK);

        }catch(Exception ex){
            return APIResponse.genericResponse("failure", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
