package com.acsousa.creditcard.mscreditappraiser.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acsousa.creditcard.mscreditappraiser.domain.models.Card;
import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientCard;

@FeignClient(value = "mscards", path = "/cards")
public interface CardResourceClient {
    
    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getCardByClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
  public ResponseEntity<List<Card>> getCardsIncomeLessThanEqual(@RequestParam("income") Long income);
}
