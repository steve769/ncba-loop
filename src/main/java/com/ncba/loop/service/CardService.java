package com.ncba.loop.service;

import com.ncba.loop.dto.CardCreationDto;
import com.ncba.loop.dto.EditDetailsCardDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CardService {
    ResponseEntity<Map<String, Object>> createCard(CardCreationDto cardCreationDto);

    ResponseEntity<Map<String, Object>> fetchCardById(String cardId);

    //ResponseEntity<Map<String, Object>> updateCardDetailsById(CardUpdateDto updateCardDto, String cardId);

    ResponseEntity<Map<String, Object>> deleteCardById(String cardId);

    ResponseEntity<Map<String, Object>> editCardDetailsById(EditDetailsCardDto editDetailsCardDto, String cardId);
}
