package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TarjetaPromocion extends Promocion<TipoTarjeta>{

    private TipoTarjeta tarjeta;


    public TarjetaPromocion(boolean estado, LocalDate fechaInicio, LocalDate fechaFin, TipoTarjeta tarjeta) {
        super(estado, fechaInicio, fechaFin, tarjeta);
        System.out.println(fechaInicio + "tp");
        System.out.println(fechaFin + "tp" );
        this.tarjeta = tarjeta;
    }

    protected TarjetaPromocion() {
        super();

    }

    @Override
    public double descuento() {
        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(this.fechaFin()) || hoy.isBefore(this.fechaInicio())){
            return 0;
        }
        return 0.08;
    }


    public TipoTarjeta tarjeta(){
        return tarjeta;
    }

    /*@Override
    public String toString() {
        return "TarjetaPromocion{ "+ tarjeta +" }";
    }*/
}
