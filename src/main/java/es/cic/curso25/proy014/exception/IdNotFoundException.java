package es.cic.curso25.proy014.exception;

public class IdNotFoundException  extends RuntimeException{

    public IdNotFoundException(Long id) {
        super("No se encontró el garaje con ID: " + id);
    }
}
