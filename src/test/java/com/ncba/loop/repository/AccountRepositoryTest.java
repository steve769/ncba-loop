package com.ncba.loop.repository;

import com.ncba.loop.TestContainerSingletonInit;
import com.ncba.loop.entity.Account;
import com.ncba.loop.entity.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest extends TestContainerSingletonInit {
    @Autowired
    private AccountRepository accountRepository;
    @Test
    @DisplayName("Should save an instance of account object to the DB")
    void shouldAccountByFindByClientId() {
        Account expectedAccount = new Account(1L, "Iban2013", "Kevin", "kevin", new ArrayList<>());
        Account actualAccount = accountRepository.save(expectedAccount);
        assertThat(actualAccount).usingRecursiveComparison().ignoringFields("accountId").isEqualTo(expectedAccount);

    }

}