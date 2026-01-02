package com.freezer.inventory.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class FrozenItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String name;

  int quantity;

  LocalDate frozenAt;
}
