package com.acsousa.creditcard.msclients.application;

import java.net.URI;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.acsousa.creditcard.msclients.application.representation.ClientSaveDTO;
import com.acsousa.creditcard.msclients.domain.Client;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

  private final ClientService service;

  @GetMapping
  public String status() {
    log.info("Getting msclient service info");
    return "I'm fine!";
  }

  @PostMapping
  public ResponseEntity<Client> save(@RequestBody ClientSaveDTO clientDTO) {
    Client client = clientDTO.toModel();
    service.save(client);
    URI herderLocation = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .query("cpf={cpf}")
      .buildAndExpand(client.getCpf())
      .toUri();
    return ResponseEntity.created(herderLocation).build();
  }

  @GetMapping(params = "cpf")
  public ResponseEntity<Optional<Client>> getClientByCpf(
    @RequestParam("cpf") String cpf
  ) {
    var client = service.getByCPF(cpf);
    if (client.isEmpty()) return ResponseEntity.notFound().build();
    return ResponseEntity.ok().body(client);
  }
}
