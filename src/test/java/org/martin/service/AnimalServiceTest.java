package org.martin.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.martin.models.Acuatico;
import org.martin.models.Animal;
import org.martin.models.Terrestre;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

  @InjectMocks
  private AnimalService animalService;
  @Mock
  private JsonDatabase jsonDatabase;

  @Test
  void obtenerAnimales_DeberiaRetornarListaDesdeJsonDatabase() throws IOException {
    when(jsonDatabase.cargarAnimales()).thenReturn(List.of());
    List<Animal> animales = animalService.obtenerAnimales();
    verify(jsonDatabase, times(1)).cargarAnimales();
    assertNotNull(animales);
  }

  @Test
  void agregarAnimal_DeberiaGuardarAnimalEnJsonDatabase() {
    List<Animal> listaExistente = new ArrayList<>();
    listaExistente.add(new Acuatico("Delfin", "Chirrido"));
    when(jsonDatabase.cargarAnimales()).thenReturn(listaExistente);
    animalService.agregarAnimal("Tigre", "terrestre", "Rugido");
    List<Animal> listaEsperada = new ArrayList<>(listaExistente);
    listaEsperada.add(new Terrestre("Tigre", "Rugido"));
    verify(jsonDatabase).guardarAnimales(listaEsperada);
  }
}