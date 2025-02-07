package org.martin.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Terrestre.class, name = "terrestre"),
    @JsonSubTypes.Type(value = Acuatico.class, name = "acuatico"),
    @JsonSubTypes.Type(value = Volador.class, name = "volador")
})
public abstract class Animal {

  protected String nombre;
  protected String onomatopeya;

  public Animal() {
  }

  public Animal(String nombre, String onomatopeya) {
    this.nombre = nombre;
    this.onomatopeya = onomatopeya;
  }

  public abstract String getTipo();

  public void hacerSonido() {
    System.out.println(nombre + " hace: " + onomatopeya);
  }

  public String getNombre() {
    return nombre;
  }

  public String getOnomatopeya() {
    return onomatopeya;
  }
}
