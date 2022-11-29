package com.acsousa.creditcad.mscards.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acsousa.creditcad.mscards.domain.ClientCard;

@Repository
public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {
  List<ClientCard> findByCpf(String cpf);
}
