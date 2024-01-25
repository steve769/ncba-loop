package com.ncba.loop.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column(nullable = false)
    private String iban;
    @Column(nullable = false)
    private String bicSwift;
    @Column(nullable = false)
    private String clientId;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Card> cards;
}
