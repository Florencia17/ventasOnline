package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
@Entity
public abstract class Promocion<P> {
    @Id
    @GeneratedValue
    private long id;
    private boolean estado;
    private Date fechaInicio;
    private Date fechaFin;
    private P tipo;

    public Promocion(boolean estado, LocalDate fechaInicio, LocalDate fechaFin, P tipo) {
        this.estado = estado;
        System.out.println(fechaInicio);
        System.out.println(fechaFin);
        if (this.validarFecha(fechaInicio, fechaFin)) {
            this.fechaInicio = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
            this.fechaFin = Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            throw new RuntimeException("Las fechas de la promocion no son validas." + fechaInicio + "|||||" + fechaFin);
        }

        System.out.println(this.fechaInicio);
        System.out.println(this.fechaFin);
        this.tipo = tipo;
    }

    protected Promocion() {

    }


    public void setEstado() {
        this.estado = !this.estado;
    }


    public LocalDate fechaInicio() {
        return fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate fechaFin() {
        return fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private boolean validarFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDate hoy = LocalDate.now();
        return (fechaInicio.isBefore(fechaFin) && hoy.isBefore(fechaFin));
    }

    public boolean fechaValida() {
        System.out.println(this.fechaInicio);
        System.out.println(this.fechaFin);
        LocalDate hoy = LocalDate.now();
        LocalDate inicio = this.fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = this.fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (inicio.isBefore(fin) && hoy.isBefore(fin)); //si se cumplen ambas y retorna true, es fecha valida
    }


    public abstract double descuento();

    @Override
    public String toString() {
        return "Datos" + " {" +
                "estado=" + estado +
                ", fechaInicio=" + fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() +
                ", fechaFin=" + fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() +
                '}';


    }
}


