package com.acsousa.creditcard.msclients.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String cpf;

  @Column
  private String name;

  @Column
  private Integer age;

  public Client(String cpf, String name, Integer age) {
    this.cpf = cpf;
    this.name = name;
    this.age = age;
  }
}
