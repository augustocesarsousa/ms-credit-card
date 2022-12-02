package com.acsousa.creditcard.mscreditappraiser.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnApprovedCards {
    private List<ApprovedCard> cards;
}
