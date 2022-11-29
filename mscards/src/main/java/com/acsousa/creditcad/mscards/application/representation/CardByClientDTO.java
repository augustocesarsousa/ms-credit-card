package com.acsousa.creditcad.mscards.application.representation;

import java.math.BigDecimal;

import com.acsousa.creditcad.mscards.domain.ClientCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardByClientDTO {

  private String name;
  private String flag;
  private BigDecimal limit;

  public static CardByClientDTO fromModel(ClientCard model) {
    return new CardByClientDTO(
      model.getCard().getName(),
      model.getCard().getFlag().toString(),
      model.getLimit()
    );
  }
}
