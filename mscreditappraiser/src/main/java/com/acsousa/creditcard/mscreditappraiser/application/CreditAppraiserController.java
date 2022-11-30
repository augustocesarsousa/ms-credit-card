package com.acsousa.creditcard.mscreditappraiser.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientSituation;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("appraiser")
@RequiredArgsConstructor
public class CreditAppraiserController {

  private final CreditAppraiserService creditAppraiserService;

  @GetMapping
  public String status() {
    return "I'm fine!";
  }

  @GetMapping(value = "client-situation", params = "cpf")
  public ResponseEntity<ClientSituation> getClientSituation(@RequestParam("cpf") String cpf) {
    ClientSituation clientSituation = creditAppraiserService.getClientSituation(cpf);
    return ResponseEntity.ok(clientSituation);
  }
}
