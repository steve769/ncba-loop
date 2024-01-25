package com.ncba.loop.controller;

import com.ncba.loop.entity.Account;
import com.ncba.loop.entity.Card;
import com.ncba.loop.service.AccountCardService;
import com.ncba.loop.utils.APIResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = AccountCardController.class)
public class AccountCardControllerTest {
    @MockBean
    private AccountCardService accountCardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return all cards by a Client")
    @Disabled
    void shouldFetchAllCardsByAccount() throws Exception{

        Account account = new Account(1L, "Iban2019", "bSwift2019", "kevin", Arrays.asList(new Card(), new Card()));

        ResponseEntity<Map<String, Object>> expectedResult = APIResponse.genericResponse("success", account, HttpStatus.OK);
        Mockito.when(accountCardService.getAllCardsByAccount("kevin", "password1")).thenReturn(expectedResult);

    }

}