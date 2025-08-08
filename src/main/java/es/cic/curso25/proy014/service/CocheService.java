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

    public Coche create(Coche coche){
        return cocheRepository.save(coche);
    }

    public Coche getCoche(Long id){
        return cocheRepository.findById(id).orElse(null);
    }

    public void deleteCoche(Long id){
        cocheRepository.deleteById(id);
    }

    public Coche updateCoche(Coche coche){
        if (!cocheRepository.existsById(coche.getId())) {
            throw new IdNotFoundException(coche.getId());
        }
        return cocheRepository.save(coche);
    }


    
}