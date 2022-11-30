package com.acsousa.creditcard.mscreditappraiser.domain.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ClientCard {
    private String name;
    private String flag;
    private BigDecimal limit;
}
