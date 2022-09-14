package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Marca {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;

    protected Marca() {

    }
    public Marca (String nombre){
        this.nombre = nombre;
    }


   /* @Override
    public String toString() {
        return "Marca:" + nombre ;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return Objects.equals(nombre, marca.nombre);
    }

}