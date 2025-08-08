package es.cic.curso25.proy014.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proy014.model.Coche;
import es.cic.curso25.proy014.model.Garaje;
import es.cic.curso25.proy014.model.Plaza;
import es.cic.curso25.proy014.repository.CocheRepository;
import es.cic.curso25.proy014.repository.GarajeRepository;
import es.cic.curso25.proy014.repository.PlazaRepository;


@SpringBootTest
@Transactional
public class GarajeServiceIntegrationTest {

      @Autowired
    private GarajeService garajeService;

    @Autowired
    private GarajeRepository garajeRepository;

    @Autowired
    private PlazaRepository plazaRepository;

    @Autowired
    private CocheRepository cocheRepository;


    @Test
    public void testCreateGaraje() {
        Garaje garaje = new Garaje();
        garaje.setPlazas(Collections.emptyList());
        garaje.setCoches(Collections.emptyList());

        Garaje creado = garajeService.create(garaje);

        assertNotNull(creado.getId());
        assertEquals(0, creado.getPlazas().size());
        assertEquals(0, creado.getCoches().size());
    }

    @Test
    public void testGetGaraje() {
        Garaje garaje = new Garaje();
        garaje.setPlazas(Collections.emptyList());
        garaje.setCoches(Collections.emptyList());
        garaje = garajeRepository.save(garaje);

        Garaje recuperado = garajeService.getGaraje(garaje.getId());

        assertNotNull(recuperado);
        assertEquals(garaje.getId(), recuperado.getId());
    }


    @Test
    public void testDeleteGaraje() {
        Garaje garaje = new Garaje();
        garaje.setPlazas(Collections.emptyList());
        garaje.setCoches(Collections.emptyList());
        garaje = garajeRepository.save(garaje);

        garajeService.deleteGaraje(garaje.getId());

        assertFalse(garajeRepository.existsById(garaje.getId()));
    }

    @Test
    public void testDeletePlaza() {
        // Crear garaje
        Garaje garaje = new Garaje();
        garaje.setPlazas(Collections.emptyList());
        garaje.setCoches(Collections.emptyList());
        garaje = garajeService.create(garaje);

        // Crear plaza
        Plaza plaza = new Plaza();
        plaza.setNumeroPlaza(1);
        plaza.setGaraje(garaje);
        plaza = plazaRepository.save(plaza);

        // Crear coche y asignarlo a plaza
        Coche coche = new Coche();
        coche.setMarca("Toyota");

        coche = garajeService.setCocheAPlaza(garaje.getId(), plaza.getId(), coche);

        // Verificar que coche y plaza están guardados
        assertTrue(plazaRepository.existsById(plaza.getId()));
        assertTrue(cocheRepository.existsById(coche.getId()));

        // Eliminar plaza
        garajeService.deletePlaza(plaza.getId());

        // Verificar que la plaza ha sido eliminada
        assertFalse(plazaRepository.existsById(plaza.getId()));

        // Verificar que el coche también ha sido eliminado (por cascade + orphanRemoval)
        assertFalse(cocheRepository.existsById(coche.getId()));
    }

    @Test
    public void testSetCocheAPlaza() {
        // Crear garaje
        Garaje garaje = new Garaje();
        garaje = garajeRepository.save(garaje);

        // Crear plaza
        Plaza plaza = new Plaza();
        plaza.setNumeroPlaza(1);
        plaza.setGaraje(garaje);
        plaza = plazaRepository.save(plaza);

        // Crear coche
        Coche coche = new Coche();
        coche.setMarca("Renault");

        Coche asignado = garajeService.setCocheAPlaza(garaje.getId(), plaza.getId(), coche);

        assertNotNull(asignado.getId());
        assertEquals("Renault", asignado.getMarca());
        assertEquals(plaza.getId(), asignado.getPlaza().getId());
        assertEquals(garaje.getId(), asignado.getGaraje().getId());

        // Verifica que la plaza tiene al coche asignado
        Plaza plazaActualizada = plazaRepository.findById(plaza.getId()).orElseThrow();
        assertNotNull(plazaActualizada.getCoche());
        assertEquals(asignado.getId(), plazaActualizada.getCoche().getId());
    }




}

