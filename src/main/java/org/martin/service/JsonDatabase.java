package org.martin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.martin.models.Animal;
import org.springframework.stereotype.Service;

@Service
public class JsonDatabase {

  private final String filePath;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public JsonDatabase() {
    this.filePath = "data/animales.json";
  }

  public JsonDatabase(String filePath) {
    this.filePath = filePath;
  }

  public List<Animal> cargarAnimales() {
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        file.getParentFile().mkdirs();
        file.createNewFile();
        return List.of();
      }
      return objectMapper.readValue(file,
          TypeFactory.defaultInstance().constructCollectionType(List.class, Animal.class));
    } catch (IOException e) {
      throw new RuntimeException("Error al cargar los animales", e);
    }
  }

  public void guardarAnimales(List<Animal> animales) {
    try {
      File file = new File(filePath);
      file.getParentFile().mkdirs();
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, animales);
    } catch (IOException e) {
      throw new RuntimeException("Error al escribir el archivo JSON", e);
    }
  }
}
