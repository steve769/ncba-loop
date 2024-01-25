package com.ncba.loop.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCreationDto {
    @NotNull(message = "Iban is null")
    private String iban;
    @NotNull(message = "BicSwift is null")
    private String bicSwift;
    @NotNull(message = "ClientId is null")
    private String clientId;

}
