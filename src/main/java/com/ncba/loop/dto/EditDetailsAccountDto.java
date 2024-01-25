package com.ncba.loop.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditDetailsAccountDto {
    private String iban;
    private String bicSwift;
}
