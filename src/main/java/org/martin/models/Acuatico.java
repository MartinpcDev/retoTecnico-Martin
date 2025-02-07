package org.martin.models;

public class Acuatico extends Animal {

  public Acuatico() {
  }

  public Acuatico(String nombre, String onomatopeya) {
    super(nombre, onomatopeya);
  }

  @Override
  public String getTipo() {
    return "acuatico";
  }
}
