package ar.unrn.tp.modelo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Tienda {
    @Id
    @GeneratedValue
    private Long id;
    // listas con el registro historico de cada tipo de promociones
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<MarcaPromocion> marcaPromociones;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<TarjetaPromocion> tarjetaPromociones;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Venta> ventaList;

    public Tienda() {
        this.ventaList = new ArrayList<>();
        this.marcaPromociones = new ArrayList<>();
        this.tarjetaPromociones = new ArrayList<>();
    }

    public void agregarVenta(Venta venta) {
        this.ventaList.add(venta);
    }

    public List<Venta> verVentasRealizadas() {
        return ventaList;
    }


    //para actulizar las promociones
    public void setMarcaPromocion(MarcaPromocion marcaPromocion) {

        if (marcaPromocion == null)
            throw new RuntimeException("La promocion no puede ser vacia");

        if (!marcaPromocion.fechaValida())
            throw new RuntimeException("No se puede crear una promocion con una fecha de finalizacion ya expirada.");

        if (marcaPromociones.isEmpty()) {
            this.marcaPromociones.add(marcaPromocion);
        } else {
            this.marcaPromociones.get(marcaPromociones.size() - 1).setEstado();
            this.marcaPromociones.add(marcaPromocion);

        }

    }

    public void setTarjetaPromocion(TarjetaPromocion tarjetaPromocion) {

        System.out.println(tarjetaPromocion.toString());
        if (tarjetaPromocion == null)
            throw new RuntimeException("La promocion no puede ser vacia");

        if (!tarjetaPromocion.fechaValida())
            throw new RuntimeException("No se puede crear una promocion con una fecha de finalizacion ya expirada.");

        if (this.tarjetaPromociones.isEmpty()) {
            this.tarjetaPromociones.add(tarjetaPromocion);
        } else {
            this.tarjetaPromociones.get(tarjetaPromociones.size() - 1).setEstado();
            this.tarjetaPromociones.add(tarjetaPromocion);
        }
    }


    //retornar la promocion de marca vigente
    public MarcaPromocion MarcaPromocionVigente() {
        return this.marcaPromociones.get(marcaPromociones.size() - 1);
    }

    //retornar la promocion de tarjeta vigente
    public TarjetaPromocion TarjetaPromocionVigente() {
        return this.tarjetaPromociones.get(tarjetaPromociones.size() - 1);
    }

    public List<MarcaPromocion> marcaPromocionList() {
        return marcaPromociones;
    }

    public List<TarjetaPromocion> tarjetaPromocionList() {
        return tarjetaPromociones;
    }

 /* @Override
    public String toString() {
        return "Tienda{" +
                "marcaPromociones=" + marcaPromociones +
                ", tarjetaPromociones=" + tarjetaPromociones +
                ", ventaList=" + ventaList +
                '}';
    }
*/
}
