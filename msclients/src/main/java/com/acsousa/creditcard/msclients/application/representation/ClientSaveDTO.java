package com.acsousa.creditcard.msclients.application.representation;

import com.acsousa.creditcard.msclients.domain.Client;

import lombok.Data;

@Data
public class ClientSaveDTO {

  private String cpf;
  private String name;
  private Integer age;

  public Client toModel() {
    return new Client(cpf, name, age);
  }
}
