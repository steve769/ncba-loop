package com.ncba.loop.repository;

import com.ncba.loop.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Object> findByCardId(Long cardId);

    void deleteByCardId(Long cardId);
}
