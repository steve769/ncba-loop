package com.ncba.loop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardCreationDto {
    private String cardAlias;
    private String accountId;
    private String cardType;
}
