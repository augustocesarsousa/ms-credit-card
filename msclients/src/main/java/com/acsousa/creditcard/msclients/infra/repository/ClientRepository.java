package com.acsousa.creditcard.msclients.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acsousa.creditcard.msclients.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
  Optional<Client> findByCpf(String cpf);
}
