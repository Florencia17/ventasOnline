package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tarjeta {
    @Id
    @GeneratedValue
    private Long id;
    private int numero;
    private TipoTarjeta tipo;


    public Tarjeta(int numero, TipoTarjeta tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public TipoTarjeta tipoTarjeta(){
        return tipo;
    }

    public Long getId() {
        return id;
    }

   /* public int getNumero() {
        return numero;
    }*/



   /* @Override
    public String toString() {
        return "Tarjeta{" +
                "numero=" + numero +
                ", tipo=" + tipo +
                '}';
    }*/
}
