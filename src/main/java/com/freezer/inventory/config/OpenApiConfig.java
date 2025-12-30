package com.freezer.inventory.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.*;

@OpenAPIDefinition(
  info = @Info(
    contact = @Contact(
      name = "Ralf Raimann",
      email = "ralf@r-raimann.de",
      url = "https://r-raimann.de"
    ),
    description = "Application to manage content of your freezer",
    title = "FREEZER-INVENTORY API",
    version = "0.1"
    //termsOfService = "to be used with my home lab documentation app"
  ),
  externalDocs = @ExternalDocumentation(
    description = "See more about this service here",
    url = "https://r-raimann.de/link-collection-doc/"
  )
  //,
  //servers = {
  //  @Server(url = "http://localhost:8080/swagger-ui/index.html", description = "local instance"),
  //  @Server(url = "https://it-tools.home.rara", description = "homelab instance")
  //}
)


public class OpenApiConfig {
}
