package com.acsousa.creditcard.mscreditappraiser.domain.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class IssuanceCardData {

    private Long id;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}