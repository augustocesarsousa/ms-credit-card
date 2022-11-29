package com.acsousa.creditcad.mscards.infra.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acsousa.creditcad.mscards.domain.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
  List<Card> findByIncomeLessThanEqual(BigDecimal incomeBigDecimal);
}
