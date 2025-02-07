package org.martin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.martin.models.Acuatico;
import org.martin.models.Animal;
import org.martin.models.Terrestre;
import org.martin.models.Volador;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

  @InjectMocks
  private AnimalService animalService;
  @Mock
  private JsonDatabase jsonDatabase;
  private List<Animal> animales;

  @BeforeEach
  public void setUp() {
    animales = new ArrayList<>();
    animales.add(new Terrestre("León", "Roar"));
    animales.add(new Acuatico("Delfín", "Clic"));
    animales.add(new Volador("Águila", "Screech"));
  }

  @Test
  public void obtenerAnimales_debeRetornarListaDeAnimales() {
    when(jsonDatabase.cargarAnimales()).thenReturn(animales);

    List<Animal> animalesObtenidos = animalService.obtenerAnimales();

    assertNotNull(animalesObtenidos);
    assertEquals(3, animalesObtenidos.size());
    assertEquals("León", animalesObtenidos.get(0).getNombre());
  }

  @Test
  public void mostrarAnimalesAgrupados_debeMostrarAnimalesAgrupadosPorTipo() {
    when(jsonDatabase.cargarAnimales()).thenReturn(animales);
    animalService.mostrarAnimalesAgrupados();

    verify(jsonDatabase, times(2)).cargarAnimales();
  }

}