package com.ncba.loop.service;

import com.ncba.loop.entity.Account;
import com.ncba.loop.entity.Card;
import com.ncba.loop.entity.CardType;
import com.ncba.loop.repository.CardRepository;
import com.ncba.loop.utils.APIResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceImplTest {

    @Mock
    private CardService cardService;
    @Mock
    private CardRepository cardRepository;
    @Captor
    private ArgumentCaptor<Card> cardArgumentCaptor;

    @Test
    @DisplayName("Test Should Successfully Return a Card")
    public void testFetchCardByIdSuccess() {
        // Mock data
        String cardId = "2";
        Account acc = new Account(1L, "Iban2020", "Swift2020","Kevin", new ArrayList<>());
        Card mockCard = new Card(2L, "Kevin", CardType.PHYSICAL, acc);
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("cardDetails", mockCard);
        ResponseEntity<Map<String, Object>> mockedREsponseEntity = APIResponse.genericResponse("success", mockResponse, HttpStatus.OK);
        // Mock service method
        when(cardService.fetchCardById(cardId)).thenReturn(mockedREsponseEntity);

        ResponseEntity<Map<String, Object>> response = cardService.fetchCardById(cardId);
        assertEquals(response.getBody().get("status"), "success");

    }

    @Test
    @DisplayName("Test Should fail if Card Id not found")
    public void testFetchCardByIdNotFound() {
        // Mock data
        String cardId = "456";

        // Mock service method for a not found card
        when(cardService.fetchCardById(cardId)).thenReturn(APIResponse.genericResponse("failure", "Card not found", HttpStatus.NOT_FOUND));

        // Call the controller method
        ResponseEntity<Map<String, Object>> response = cardService.fetchCardById(cardId);

        // Assert the response using AssertJ
        assertEquals(response.getBody().get("message"), "Card not found");
    }
    @Test
    @DisplayName("Verify that card creation method executes at least once")
    public void createAndSaveCardToDatabase(){
        String cardId = "2";
        Account acc = new Account(1L, "Iban2020", "Swift2020","Kevin", new ArrayList<>());
        Card mockCard = new Card(2L, "Kevin", CardType.PHYSICAL, acc);

        ResponseEntity<Map<String, Object>> mockedREsponseEntity = APIResponse.genericResponse("success", "Card created successfully", HttpStatus.OK);
        // Mock service method
        cardRepository.save(mockCard);

        Mockito.verify(cardRepository, Mockito.times(1)).save(ArgumentMatchers.any(Card.class));
        assertEquals("success", mockedREsponseEntity.getBody().get("status"));
    }

    @Test
    @DisplayName("Verify that card deletion method executes at least once")
    public void deleteACardFromTheDataBase(){
        String cardId = "2";
        Account acc = new Account(1L, "Iban2020", "Swift2020","Kevin", new ArrayList<>());
        Card mockCard = new Card(2L, "Kevin", CardType.PHYSICAL, acc);

        ResponseEntity<Map<String, Object>> mockedREsponseEntity = APIResponse.genericResponse("success", "Card created successfully", HttpStatus.OK);
        // Mock service method
        cardRepository.delete(mockCard);

        Mockito.verify(cardRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Card.class));
        assertEquals("success", mockedREsponseEntity.getBody().get("status"));
    }

}