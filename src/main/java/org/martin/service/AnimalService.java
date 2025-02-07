package org.martin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.martin.models.Acuatico;
import org.martin.models.Animal;
import org.martin.models.Terrestre;
import org.martin.models.Volador;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

  private final JsonDatabase jsonDatabase;
  private List<Animal> animales;

  public AnimalService(JsonDatabase jsonDatabase) {
    this.jsonDatabase = jsonDatabase;
    this.animales = jsonDatabase.cargarAnimales();
  }

  public List<Animal> obtenerAnimales() {
    if (animales == null || animales.isEmpty()) {
      animales = jsonDatabase.cargarAnimales();
    }
    return animales;
  }

  public void agregarAnimal(String nombre, String tipo, String onomatopeya) {
    Animal animal = switch (tipo.toLowerCase()) {
      case "terrestre" -> new Terrestre(nombre, onomatopeya);
      case "acuatico" -> new Acuatico(nombre, onomatopeya);
      case "volador" -> new Volador(nombre, onomatopeya);
      default -> null;
    };

    animales.add(animal);
    jsonDatabase.guardarAnimales(animales);
    System.out.println("✅ Animal agregado correctamente.");
  }

  public void mostrarAnimalesAgrupados() {
    if (animales == null || animales.isEmpty()) {
      animales = jsonDatabase.cargarAnimales();
    }

    Map<String, List<Animal>> grupos = new HashMap<>();

    for (Animal animal : animales) {
      grupos.computeIfAbsent(animal.getTipo(), k -> new ArrayList<>()).add(animal);
    }

    grupos.forEach((tipo, lista) -> {
      System.out.println("\n🐾 Animales " + tipo + ":");
      lista.forEach(
          a -> System.out.println("- " + a.getNombre() + " (hace: " + a.getOnomatopeya() + ")"));
    });
  }
}
