package com.acsousa.creditcad.mscards.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acsousa.creditcad.mscards.application.representation.CardByClientDTO;
import com.acsousa.creditcad.mscards.application.representation.CardSaveDTO;
import com.acsousa.creditcad.mscards.domain.Card;
import com.acsousa.creditcad.mscards.domain.ClientCard;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardController {

  private final CardService cardService;
  private final ClientCardService clientCardService;

  @GetMapping
  public String status() {
    return "I'm fine!";
  }

  @PostMapping
  public ResponseEntity<Card> save(@RequestBody CardSaveDTO cardDTO) {
    Card card = cardDTO.toModel();
    cardService.save(card);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(params = "income")
  public ResponseEntity<List<Card>> getCardsIncomeLessThanEqual(
    @RequestParam("income") Long income
  ) {
    List<Card> list = cardService.getCardsIncomeLessThanEqual(income);
    return ResponseEntity.ok(list);
  }

  @GetMapping(params = "cpf")
  public ResponseEntity<List<CardByClientDTO>> getCardByClient(
    @RequestParam("cpf") String cpf
  ) {
    List<ClientCard> list = clientCardService.getCardsByCpf(cpf);
    List<CardByClientDTO> cardsByCpf = list
      .stream()
      .map(CardByClientDTO::fromModel)
      .collect(Collectors.toList());
    return ResponseEntity.ok(cardsByCpf);
  }
}
