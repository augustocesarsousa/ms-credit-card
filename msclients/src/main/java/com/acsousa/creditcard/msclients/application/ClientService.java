package com.acsousa.creditcard.msclients.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acsousa.creditcard.msclients.domain.Client;
import com.acsousa.creditcard.msclients.infra.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

  private final ClientRepository repository;

  @Transactional
  public Client save(Client client) {
    return repository.save(client);
  }

  public Optional<Client> getByCPF(String cpf) {
    return repository.findByCpf(cpf);
  }
}
