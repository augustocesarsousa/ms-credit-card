package com.acsousa.creditcad.mscards.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class IssuanceCardData {

    private Long id;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}
