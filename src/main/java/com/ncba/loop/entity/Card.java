package com.ncba.loop.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;
    private String cardAlias;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @ManyToOne
    @JoinColumn(name = "accountId")
    @JsonBackReference
    private Account account;

}