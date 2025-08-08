package es.cic.curso25.proy014.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proy014.model.Coche;
import es.cic.curso25.proy014.model.Garaje;
import es.cic.curso25.proy014.model.Plaza;

@SpringBootTest
public class GarajeControllerIntegrationTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GarajeController garajeController;

    
    @Test
    void testCrearGaraje(){

        Coche coche = new Coche();
        coche.setMarca("Honda");

        Plaza plaza = new Plaza();
        plaza.setNumeroPlaza(23);

        plaza.setCoche(coche);
        coche.setPlaza(plaza);


        Garaje garaje = new Garaje();
        garaje.setCoches(null);

    }
}
