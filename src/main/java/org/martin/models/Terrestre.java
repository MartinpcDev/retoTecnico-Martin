package org.martin.models;

public class Terrestre extends Animal {

  public Terrestre() {
  }

  public Terrestre(String nombre, String onomatopeya) {
    super(nombre, onomatopeya);
  }

  @Override
  public String getTipo() {
    return "terrestre";
  }
}
