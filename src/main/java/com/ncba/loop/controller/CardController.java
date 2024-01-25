package com.ncba.loop.controller;


import com.ncba.loop.dto.CardCreationDto;
import com.ncba.loop.dto.EditDetailsCardDto;
import com.ncba.loop.service.CardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/card")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/createCard")
    public ResponseEntity<Map<String, Object>> createCard(@Valid @RequestBody CardCreationDto cardCreationDto){
        return cardService.createCard(cardCreationDto);
    }

    @GetMapping("/getCard/{cardId}")
    public ResponseEntity<Map<String, Object>> fetchCardById(@PathVariable String cardId){
        return cardService.fetchCardById(cardId);
    }

    @PatchMapping("/editCardDetails/{cardId}")
    public ResponseEntity<Map<String, Object>> updateCardDetailsById(@Valid @RequestBody EditDetailsCardDto editDetailsCardDto, @PathVariable String cardId){
        return cardService.editCardDetailsById(editDetailsCardDto, cardId);
    }

    @DeleteMapping("/deleteCard/{cardId}")
    public ResponseEntity<Map<String, Object>> deleteCardById(@PathVariable String cardId){
        return cardService.deleteCardById(cardId);
    }


}
