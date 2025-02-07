package org.martin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.martin.models.Acuatico;
import org.martin.models.Animal;
import org.martin.models.Terrestre;
import org.martin.models.Volador;

class JsonDatabaseTest {

  private JsonDatabase jsonDatabase;
  private static final String TEST_FILE_PATH = "src/test/resources/test_animales.json";

  @BeforeEach
  void setUp() {
    jsonDatabase = new JsonDatabase(TEST_FILE_PATH);
  }

  @AfterEach
  void tearDown() {
    limpiarArchivo();
  }

  private void limpiarArchivo() {
    File file = new File(TEST_FILE_PATH);
    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  void cargarAnimales_CuandoArchivoNoExiste_DeberiaRetornarListaVacia() {
    List<Animal> animales = jsonDatabase.cargarAnimales();
    assertTrue(animales.isEmpty(), "Debe devolver una lista vacía si el archivo no existe");
  }

  @Test
  void guardarYcargarAnimales_DeberiaMantenerLosDatos() {
    List<Animal> animales = List.of(
        new Terrestre("Perro", "guau"),
        new Acuatico("Pez", "glub"),
        new Volador("Aguila", "pío")
    );

    jsonDatabase.guardarAnimales(animales);
    List<Animal> cargados = jsonDatabase.cargarAnimales();

    assertEquals(animales.size(), cargados.size(), "La cantidad de animales debe coincidir");

    for (int i = 0; i < animales.size(); i++) {
      assertEquals(animales.get(i).getNombre(), cargados.get(i).getNombre(),
          "El nombre debe coincidir");
      assertEquals(animales.get(i).getOnomatopeya(), cargados.get(i).getOnomatopeya(),
          "El sonido debe coincidir");
      assertEquals(animales.get(i).getTipo(), cargados.get(i).getTipo(), "El tipo debe coincidir");
    }
  }
}