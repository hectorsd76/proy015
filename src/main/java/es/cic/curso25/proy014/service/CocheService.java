package es.cic.curso25.proy014.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy014.exception.IdNotFoundException;
import es.cic.curso25.proy014.model.Coche;
import es.cic.curso25.proy014.repository.CocheRepository;

@Service
public class CocheService {

    @Autowired
    private CocheRepository cocheRepository;



    
}