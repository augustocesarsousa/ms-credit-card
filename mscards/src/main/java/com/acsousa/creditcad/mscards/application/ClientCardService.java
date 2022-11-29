package com.acsousa.creditcad.mscards.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acsousa.creditcad.mscards.domain.ClientCard;
import com.acsousa.creditcad.mscards.infra.repository.ClientCardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientCardService {

  private final ClientCardRepository repository;

  public List<ClientCard> getCardsByCpf(String cpf) {
    return repository.findByCpf(cpf);
  }
}
