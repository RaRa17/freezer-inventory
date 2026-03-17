package com.freezer.inventory.controllers;

import com.freezer.inventory.models.VersionData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health and version", description = "Information about the health, variant and version of the application")
@RequestMapping("/api/health")
public class HealthController {
  //@Value("V1.1.1")
  @Value("${com.freezer.inventory.version}")
  private String version;

  //@Value("default-profile")
  @Value("${spring.profiles.active}")
  private String profile;

  //@Value("not-so-secret")
  @Value("${com.freezer.inventory.test-secret}")
  private String testSecret;

  //** --------------------------------------------------------------------
  //**
  @Operation(
    summary = "Version Information",
    description = "Currently running profile and version"
  )
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Item found",
      content = { @Content(mediaType = "application/json",
        examples = @ExampleObject(value = "{" +
          "\"version\": \"0.0.1\", " +
          "\"profile\": \"local_h2\" " +
          "}"),
        schema = @Schema(implementation = VersionData.class)) }),
  })
  @CrossOrigin
  @GetMapping("/version")
  public VersionData version() {
    return new VersionData(version, profile);
  }

  //** --------------------------------------------------------------------
  //**
  @GetMapping("/testsecret")
  public ResponseEntity<String> testSecret() {
    return ResponseEntity.ok(testSecret);
  }
}
