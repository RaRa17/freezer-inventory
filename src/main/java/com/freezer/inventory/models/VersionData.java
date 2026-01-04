package com.freezer.inventory.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VersionData {
  private String version;
  private String profile;

  public VersionData(String version, String profile) {
    this.version = version;
    this.profile = profile;
  }
}
