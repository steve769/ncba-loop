package com.ncba.loop.repository;


import com.ncba.loop.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByClientId(String clientId);

    void deleteByAccountId(Long valueOf);

    Optional<Account> findByAccountId(Long accountId);
}
