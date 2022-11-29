package com.acsousa.creditcad.mscards.application;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.acsousa.creditcad.mscards.domain.Card;
import com.acsousa.creditcad.mscards.infra.repository.CardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

  private final CardRepository repository;

  @Transactional
  public Card save(Card card) {
    return repository.save(card);
  }

  public List<Card> getCardsIncomeLessThanEqual(Long income) {
    var incomeBigDecimal = BigDecimal.valueOf(income);
    return repository.findByIncomeLessThanEqual(incomeBigDecimal);
  }
}
