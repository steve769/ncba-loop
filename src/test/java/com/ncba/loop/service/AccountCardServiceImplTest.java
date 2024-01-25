package com.ncba.loop.service;

import com.ncba.loop.entity.Account;
import com.ncba.loop.entity.Card;
import com.ncba.loop.entity.CardType;
import com.ncba.loop.utils.APIResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountCardServiceImplTest {
    @Mock
    private AccountCardService accountCardService;

    @Test
    @DisplayName("Should fetch all cards that belong to a client")
    void shouldGetAllCardsBelongingToClient() {
        Account mockAccount1 = new Account(45L, "Iban2020", "Swift2020","Kevin", new ArrayList<>());
        String username ="kevin", password="pass";

        Card card1 = new Card(1L, "Kevin Kamau", CardType.PHYSICAL, mockAccount1);
        Card card2 = new Card(2L, "Kevin Kamau", CardType.VIRTUAL, mockAccount1);

        // Mock service
        when(accountCardService.getAllCardsByAccount(username, password)).thenReturn(APIResponse.genericResponse("success", mockAccount1, HttpStatus.OK));
        ResponseEntity<Map<String, Object>> actualResponse = accountCardService.getAllCardsByAccount("kevin", "pass");
        // Assert
        assertEquals(actualResponse.getBody().get("status"), "success");
    }
}