package org.martin;

import org.martin.config.AppConfig;
import org.martin.service.AnimalService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApp {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    AnimalService animalService = context.getBean(AnimalService.class);

    if (args.length == 0) {
      System.out.println("\uD83D\uDCE2 Mostrando animales en la base de datos:");
      animalService.mostrarAnimalesAgrupados();
    } else if (args.length == 3) {
      String nombre = args[0];
      String tipo = args[1];
      String onomatopeya = args[2];
      System.out.println(nombre + " " + tipo + " " + onomatopeya);
      animalService.agregarAnimal(nombre, tipo, onomatopeya);
    } else {
      System.out.println("❌ Uso incorrecto. Para agregar un animal usa: nombre tipo onomatopeya");
    }
  }
}