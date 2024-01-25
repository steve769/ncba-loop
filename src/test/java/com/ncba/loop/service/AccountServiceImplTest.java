package com.ncba.loop.service;

import com.ncba.loop.entity.Account;
import com.ncba.loop.repository.AccountRepository;
import com.ncba.loop.utils.APIResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountService accountService;
    @Test
    @DisplayName("Should create an account")
    void shouldCreateAccountAndSaveToDB() {
        Account acc = new Account(1L, "Iban2020", "Swift2020","Kevin", new ArrayList<>());
        ResponseEntity<Map<String, Object>> mockedREsponseEntity = APIResponse.genericResponse("success", "Card created successfully", HttpStatus.OK);
        // Mock service method
        accountRepository.save(acc);

        Mockito.verify(accountRepository, Mockito.times(1)).save(ArgumentMatchers.any(Account.class));
        assertEquals("success", mockedREsponseEntity.getBody().get("status"));
    }

    @Test
    @DisplayName("Fetch Account Details from DB Success")
    void fetchAccountDetailsFromDBSuccess() {
        // Mock data
        Account mockAccount = new Account(45L, "Iban2020", "Swift2020","Kevin", new ArrayList<>());

        // Mock service method for a not found card
        when(accountRepository.findByAccountId(45L)).thenReturn(Optional.of(mockAccount));
        Optional<Account> actualAccount = accountRepository.findByAccountId(45L);
        // Assert
        assertEquals(actualAccount.get().getAccountId(), 45L);
    }

    @Test
    @DisplayName("Fetch Account Details from DB Not Found")
    void fetchAccountDetailsFromDBFailure() {
        // Mock data
        Account mockAccount = new Account(45L, "Iban2020", "Swift2020","Kevin", new ArrayList<>());

        // Mock service method for a not found card
        when(accountService.fetchAccountDetails("20")).thenReturn(APIResponse.genericResponse("failure", "Account not found", HttpStatus.NOT_FOUND));
        ResponseEntity<Map<String, Object>> actualAccount = accountService.fetchAccountDetails("20");
        // Assert
        assertEquals(actualAccount.getBody().get("status"), "failure");
    }

    @Test
    @DisplayName("Should delete account by Id")
    void shouldDeleteAccountById() {
        // Mock data
        Account mockAccount = new Account(45L, "Iban2020", "Swift2020","Kevin", new ArrayList<>());
        when(accountService.deleteAccount("45")).thenReturn(APIResponse.genericResponse("success", "Account deleted successfully", HttpStatus.OK));
        accountRepository.deleteByAccountId(45L);

        //Mockito.verify(accountRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Account.class));
        assertEquals("success", accountService.deleteAccount("45").getBody().get("status"));

    }

}