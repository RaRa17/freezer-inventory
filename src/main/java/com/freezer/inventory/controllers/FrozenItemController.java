package com.freezer.inventory.controllers;

import com.freezer.inventory.models.FrozenItem;
import com.freezer.inventory.repositories.FrozenItemRepository;
import com.freezer.inventory.services.FrozenItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Frozen Items", description = "Manage what you put in and out of the freezer")
@RequestMapping("/api/frozen-items")
public class FrozenItemController {

  @Autowired
  FrozenItemService frozenItemService;
  @Autowired
  private FrozenItemRepository frozenItemRepository;

  //** --------------------------------------------------------------------
  //**
  @Operation(
    summary = "Total freezer content",
    // deprecated = true, // will mark the operation as deprecated in swagger ui, but still accessible
    // hidden = true, // will hide the operation completely from swagger ui
    // tags = {"<additional-tag>"},  // will create an additional section in the swagger ui
    description = "All items in the freezer will be returned"
  )
  @GetMapping
  public List<FrozenItem> getAllFrozenItems() {
    return frozenItemService.getAll();
  }

  //** --------------------------------------------------------------------
  //**
  @GetMapping("/count")
  public long countAllFrozenItems() {
    return frozenItemService.getAll().size();
  }

  //** --------------------------------------------------------------------
  //**
  @Operation(summary = "Get a specific item by its ID")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Item found",
      content = { @Content(mediaType = "application/json",
        examples = @ExampleObject(value = "{" +
          "\"id\": 99, " +
          "\"name\": \"Gulaschsuppe\", " +
          "\"quantity\": 2, " +
          "\"frozenAt\": \"2025-12-29\" " +
          "}"),
        schema = @Schema(implementation = FrozenItem.class)) }),
    @ApiResponse(responseCode = "404", description = "Topic with this ID not found",
      content = @Content)
  })
  @GetMapping("/{id}")
  public FrozenItem getFrozenItemById(
    @Parameter(
      description = "Id of the frozen item to be fetched",
      required = true,
      examples = @ExampleObject(value = "99")
    )
    @PathVariable Long id,
    HttpServletResponse response) {

    //return frozenItemService.getById(id).orElse(null);
    Optional<FrozenItem> foundItem = frozenItemService.getById(id);
    if (foundItem.isPresent()){
      return foundItem.get();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with ID " + id + " not found.");
  }

  //** --------------------------------------------------------------------
  //**
  @Operation(
    summary = "Create a new frozen item",
    // deprecated = true, // will mark the operation as deprecated in swagger ui, but still accessible
    // hidden = true, // will hide the operation completely from swagger ui
    // tags = {"<additional-tag>"},  // will create an additional section in the swagger ui
    description = "A new item will be __created__ e.g., if you put it into the freezer. "+
      "<br/>*If a id is given in request body and it exists in the database, the request is rejected. "+
      "Otherwise the given __id__ is ignored and replaced by a db-generated sequence*"
  )
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully created",
      content = { @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = FrozenItem.class),
        examples = @ExampleObject(value = "{" +
          "\"id\": 99, " +
          "\"name\": \"Gulaschsuppe\", " +
          "\"quantity\": 2, " +
          "\"frozenAt\": \"2025-12-29\" " +
          "}")
      ),
      }),
    @ApiResponse(responseCode = "404", description = "Failure during create",
      content = @Content),
    @ApiResponse(responseCode = "409", description = "Id given in request body but already existing in database",
      content = @Content)
  })
  @PostMapping("")
  public FrozenItem saveFrozenItem(@RequestBody FrozenItem frozenItem) {
    if (frozenItem.getId() != null) {
      Optional<FrozenItem> existingFrozenItem = frozenItemRepository.findById(frozenItem.getId());
      if (existingFrozenItem.isPresent()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Frozen item with id " + frozenItem.getId() + " already exists");
      }
    }
    return frozenItemService.save(frozenItem);
  }

}
