package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.time.ZoneId;

public class TarjetaPromocion extends Promocion<TipoTarjeta>{

    private TipoTarjeta tarjeta;

    public TarjetaPromocion(boolean estado, LocalDate fechaInicio, LocalDate fechaFin, TipoTarjeta tarjeta) {
        super(estado, fechaInicio, fechaFin, tarjeta);
        this.tarjeta = tarjeta;
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
