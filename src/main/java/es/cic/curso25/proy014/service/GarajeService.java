package es.cic.curso25.proy014.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy014.exception.IdNotFoundException;
import es.cic.curso25.proy014.model.Coche;
import es.cic.curso25.proy014.model.Garaje;
import es.cic.curso25.proy014.model.Plaza;
import es.cic.curso25.proy014.repository.CocheRepository;
import es.cic.curso25.proy014.repository.GarajeRepository;
import es.cic.curso25.proy014.repository.PlazaRepository;

@Service
public class GarajeService {


    @Autowired
    private GarajeRepository garajeRepository;

    @Autowired
    private PlazaRepository plazaRepository;

    @Autowired
    private CocheRepository cocheRepository;




    public Garaje create(Garaje garaje){
        return garajeRepository.save(garaje);
    }

    public Garaje getGaraje(Long id){
        return garajeRepository.findById(id).orElse(null);
    }

    public void deleteGaraje(Long id){
        garajeRepository.deleteById(id);
    }
    
    public Garaje updatGaraje(Garaje garaje) {

        if (!garajeRepository.existsById(garaje.getId())) {
            throw new IdNotFoundException(garaje.getId());
        }
        return garajeRepository.save(garaje);
    }


    public Coche setCocheAPlaza(Long garajeId, Long plazaId, Coche coche) {
        //Buscamos el garaje y la plaza
    Garaje garaje = garajeRepository.findById(garajeId)
        .orElseThrow(() -> new IdNotFoundException(garajeId));
    Plaza plaza = plazaRepository.findById(plazaId)
        .orElseThrow(() -> new IdNotFoundException(plazaId));
        //Miramos si la plaza pertenece al garaje
    if (!plaza.getGaraje().getId().equals(garajeId)) {
        throw new IllegalArgumentException("La plaza no pertenece al garaje");
    }
    //asignamos la plaza y el garaje al coche
    coche.setPlaza(plaza);
    coche.setGaraje(garaje);
    //asignamos el coche a la plaza
    plaza.setCoche(coche);
    //guardamos
    cocheRepository.save(coche);
    plazaRepository.save(plaza);
    return coche;
    }


    public void deletePlaza(Long plazaId) {
    Plaza plaza = plazaRepository.findById(plazaId)
        .orElseThrow(() -> new IdNotFoundException(plazaId));

    plazaRepository.delete(plaza);
    }



}
