package com.acsousa.creditcad.mscards.application.representation;

import java.math.BigDecimal;

import com.acsousa.creditcad.mscards.domain.Card;
import com.acsousa.creditcad.mscards.domain.CardFlag;

import lombok.Data;

@Data
public class CardSaveDTO {

  private String name;
  private CardFlag flag;
  private BigDecimal income;
  private BigDecimal basicLimit;

  public Card toModel() {
    return new Card(name, flag, income, basicLimit);
  }
}
