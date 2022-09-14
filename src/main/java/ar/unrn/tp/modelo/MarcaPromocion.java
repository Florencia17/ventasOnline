package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class MarcaPromocion extends Promocion<Marca> {

    @Id
    @GeneratedValue
    private long id;
    private Marca marca;


    public MarcaPromocion(boolean estado, LocalDate fechaInicio, LocalDate fechaFin, Marca marca) {
        super(estado, fechaInicio, fechaFin, marca);
        this.marca = marca;
    }



    @Override
    public double descuento() {
        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(this.fechaFin()) || hoy.isBefore(this.fechaInicio())){
            return 0;
        }
        return 0.05;
    }

    public Marca marca(){
        return marca;
    }


   /* @Override
    public String toString() {
        return "MarcaPromocion{ " + marca +" }";
    }*/
}
