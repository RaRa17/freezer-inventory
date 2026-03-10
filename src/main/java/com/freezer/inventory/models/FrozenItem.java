package com.freezer.inventory.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@JsonPropertyOrder({"id", "name", "quantity", "frozenAt"})
public class FrozenItem {
  @Id
  //@GeneratedValue(strategy = GenerationType.SEQUENCE)    // used for PostgreSQL
  @GeneratedValue(strategy = GenerationType.IDENTITY)    // used for MySQL
  Long id;

  @NotNull(message = "Name must not be null")
  String name;

  int quantity;

  LocalDate frozenAt;
}
