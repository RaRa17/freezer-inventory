package com.freezer.inventory;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class VersionLogger implements ApplicationListener<WebServerInitializedEvent> {
  private final ObjectProvider<BuildProperties> buildPropertiesProvider;
  private final Environment environment;
  private volatile boolean logged = false;

  public VersionLogger(ObjectProvider<BuildProperties> buildPropertiesProvider, Environment environment) {
    this.buildPropertiesProvider = buildPropertiesProvider;
    this.environment = environment;
  }

  @Override
  public void onApplicationEvent(WebServerInitializedEvent event) {
    if (logged) return;
    logged = true;

    int port = event.getWebServer() != null ? event.getWebServer().getPort() : -1;
    String profiles = String.join(",", environment.getActiveProfiles());
    BuildProperties bp = buildPropertiesProvider.getIfAvailable();

    String name = bp != null ? bp.getName() : "my-freezer-inventory";
    String version = bp != null ? bp.getVersion() : "dev2";
    String artifact = bp != null ? bp.getArtifact() : "my-freezer-inventory2";
    String buildTime = bp != null ? DateTimeFormatter.ISO_INSTANT.format(bp.getTime()) : "n/a";
    String portStr = (port > 0 ? Integer.toString(port) : environment.getProperty("server.port", "n/a"));
    String profilesStr = profiles.isBlank() ? "default" : profiles;

    System.out.println("==================================================");
    System.out.println("Application: " + name);
    System.out.println("Artifact:    " + artifact);
    System.out.println("Version:     " + version);
    System.out.println("Build Time:  " + buildTime);
    System.out.println("Profiles:    " + profilesStr);
    System.out.println("Port:        " + portStr);
    System.out.println("==================================================");
  }
}

