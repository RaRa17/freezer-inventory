package com.freezer.inventory.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "quantity"})
public class FrozenItemDto {
  Long id;
  @NotNull(message = "name must not be null")
  String name;
  int quantity;
}
