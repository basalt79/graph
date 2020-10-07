package at.aigner.graph.structure;

import com.google.gson.Gson;

public class Instance {

  private Location location;
  private Version version;


  private final transient Gson gson = new Gson();

  public Location getLocation() {
    return location;
  }

  public Instance setLocation(Location location) {
    this.location = location;
    return this;
  }

  public Version getVersion() {
    return version;
  }

  public Instance setVersion(Version version) {
    this.version = version;
    return this;
  }

  public String toString() {
    return gson.toJson(this);
  }

}
