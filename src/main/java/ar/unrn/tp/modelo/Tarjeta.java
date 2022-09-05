package ar.unrn.tp.modelo;

public class Tarjeta {

    private int numero;
    private TipoTarjeta tipo;


    public Tarjeta(int numero, TipoTarjeta tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public TipoTarjeta tipoTarjeta(){
        return tipo;
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
