package es.cic.curso25.proy014.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "coche")
public class Coche {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "marca")
    private String marca;

    @ManyToOne
    @JoinColumn(name = "garaje_id")
    private Garaje garaje;

    @OneToOne(mappedBy = "coche")
    private Plaza plaza;

    @OneToOne(optional = true)
    @JoinColumn(name = "multa_id")
    private Multa multa;


    //SETTERS Y GETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Garaje getGaraje() {
        return garaje;
    }

    public void setGaraje(Garaje garaje) {
        this.garaje = garaje;
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        result = prime * result + ((garaje == null) ? 0 : garaje.hashCode());
        result = prime * result + ((plaza == null) ? 0 : plaza.hashCode());
        result = prime * result + ((multa == null) ? 0 : multa.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coche other = (Coche) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        if (garaje == null) {
            if (other.garaje != null)
                return false;
        } else if (!garaje.equals(other.garaje))
            return false;
        if (plaza == null) {
            if (other.plaza != null)
                return false;
        } else if (!plaza.equals(other.plaza))
            return false;
        if (multa == null) {
            if (other.multa != null)
                return false;
        } else if (!multa.equals(other.multa))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Coche [id=" + id + ", marca=" + marca + ", garaje=" + garaje + ", plaza=" + plaza + ", multa=" + multa
                + "]";
    }


    
    
}
