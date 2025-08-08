package es.cic.curso25.proy014.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso25.proy014.model.Garaje;
import es.cic.curso25.proy014.service.GarajeService;

@RestController
@RequestMapping("Garaje")
public class GarajeController {

    @Autowired
    private GarajeService garajeService;


    @PostMapping
    public Garaje create(@RequestBody Garaje garaje){

        return garajeService.create(garaje);

    }
}
