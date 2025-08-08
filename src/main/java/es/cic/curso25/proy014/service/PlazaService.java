package es.cic.curso25.proy014.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy014.exception.IdNotFoundException;
import es.cic.curso25.proy014.model.Plaza;
import es.cic.curso25.proy014.repository.PlazaRepository;

@Service
public class PlazaService {

    @Autowired
    private PlazaRepository plazaRepository;

    public Plaza create(Plaza plaza){
        return plazaRepository.save(plaza);
    }

    public Plaza getPlaza(Long id){
        return plazaRepository.findById(id).orElse(null);
    }

    public void deletePlaza(Long id){
        plazaRepository.deleteById(id);
    }

    public Plaza updatePlaza(Plaza plaza){
        if (!plazaRepository.existsById(plaza.getId())) {
            throw new IdNotFoundException(plaza.getId());
        }
        return plazaRepository.save(plaza);
    }


    
}