package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;

    protected Categoria() {

    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

 /*  @Override
    public String toString() {
        return "Categoria: " + nombre;
    }*/
}
