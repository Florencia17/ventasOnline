package ar.unrn.tp.modelo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venta {
    @Id
    @GeneratedValue
    private Long id;
    private Cliente cliente;
    private EstadoVenta estadoVenta;
    private Tarjeta tarjeta;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Producto> productosVendidos;

    private double montoAbonado;

    protected Venta() {

    }


    public Venta(Cliente cliente, Tarjeta tarjeta, EstadoVenta estadoVenta, List<Producto> productosVendidos, double montoAbonado) {
        this.cliente = cliente;
        this.tarjeta = tarjeta;
        this.estadoVenta = estadoVenta;
        this.productosVendidos = new ArrayList<>();
        this.agregarProductos(productosVendidos);
        this.montoAbonado = montoAbonado;
    }

    private void agregarProductos(List<Producto> productos) {
        productos.forEach(producto ->
                this.productosVendidos.add(
                        new Producto(producto.codigo(), producto.precio(),
                                producto.descripcion(), producto.categoria(),
                                producto.marca())));

    }

    @Override
    public String toString() {
        return "Venta{" +
                "cliente=" + cliente +
                ", estadoVenta=" + estadoVenta +
                ", tarjeta=" + tarjeta +
                ", productosVendidos=" + productosVendidos +
                ", montoAbonado=" + montoAbonado +
                '}';
    }

}
