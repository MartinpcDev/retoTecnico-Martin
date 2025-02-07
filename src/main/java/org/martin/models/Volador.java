package org.martin.models;

public class Volador extends Animal {

  public Volador() {
  }

  public Volador(String nombre, String onomatopeya) {
    super(nombre, onomatopeya);
  }

  @Override
  public String getTipo() {
    return "volador";
  }

}
