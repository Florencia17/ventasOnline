
//import ar.unrn.tp.main.modelo.*;
import ar.unrn.tp.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import  org.junit.jupiter.api.Test ;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class CarritoTest {

    private Tienda tienda;
    private Cliente cliente;
    private Tarjeta tarjeta;
    private Carrito carrito;
    private Marca marcaGarnier, marcaArcor, marcaCocaCola;
    private Categoria categoria, categoria2;
    private Producto producto1, producto2, producto3, producto4;
    private LocalDate fecha2DiasAntes, fecha2DiasDesp;

    @BeforeEach
    public void before() {

        tienda = new Tienda();
        carrito = new Carrito();

        cliente = new Cliente("Florencia", "Malacarne", "12345678", "flor@gmail.com");
        tarjeta = new Tarjeta(1111, TipoTarjeta.MASTERCARD);

        marcaGarnier = new Marca("Garnier");
        marcaArcor = new Marca("Arcor");
        marcaCocaCola = new Marca("CocaCola");

        categoria = new Categoria("shampooo");
        categoria2 = new Categoria("gaseosa");

        producto1 = new Producto("1", 100, "shampooSolido", categoria, marcaGarnier);
        producto2 = new Producto("2", 100, "shampooEnSeco", categoria, marcaGarnier);
        producto4 = new Producto("3", 34, "gomitas", categoria2, marcaArcor);
        producto3 = new Producto("4", 6, "cola", categoria2, marcaCocaCola);


        fecha2DiasAntes = LocalDate.now().minusDays(2);
        fecha2DiasDesp = LocalDate.now().plusDays(2);
    }

    @Test
    public void agregarProductoAlCarrito(){
        carrito.agregarProductoAlCarrito(producto1);
        assertEquals(1, carrito.productos().size());
    }
    @Test
    public void agregarProductoVacio(){
        assertThrows(RuntimeException.class, () ->  carrito.agregarProductoAlCarrito(null));
    }

    @Test
    public void descuentoDeProducto(){
        // Calcular el monto total, descuento vigente marca Garnier.
        // no descuento en tarjeta
        carrito.agregarProductoAlCarrito(producto1);
        carrito.agregarProductoAlCarrito(producto2);
        assertEquals(190, carrito.calcularMontoCarrito(
                new MarcaPromocion(true,
                        fecha2DiasAntes, fecha2DiasDesp, marcaGarnier),
                new TarjetaPromocion(true,
                        fecha2DiasAntes, fecha2DiasDesp, TipoTarjeta.MACRO),
                tarjeta //tarjeta de mast
        ));
    }

    @Test
    public void descuentoDeCompra(){
        // Calcular el monto total, descuento vigente tarjeta.
        // no descuento en productos
        carrito.agregarProductoAlCarrito(producto3);
        carrito.agregarProductoAlCarrito(producto4);
        assertEquals(36.8, carrito.calcularMontoCarrito(
                new MarcaPromocion(true,
                        fecha2DiasAntes, fecha2DiasDesp, marcaGarnier),
                new TarjetaPromocion(true,
                        fecha2DiasAntes, fecha2DiasDesp, TipoTarjeta.MASTERCARD),
                tarjeta //tarjeta de mast
        ));
    }

    @Test
    public void descuentoDeProductosYCompra(){
        // Calcular el monto total, dos descuentos vigentes,
        //marca Garnier y tarjeta de crédito MT.

        carrito.agregarProductoAlCarrito(producto1);
        carrito.agregarProductoAlCarrito(producto2);
        assertEquals(174.8, carrito.calcularMontoCarrito(
                new MarcaPromocion(true,
                        fecha2DiasAntes, fecha2DiasDesp, marcaGarnier),
                new TarjetaPromocion(true,
                        fecha2DiasAntes, fecha2DiasDesp, TipoTarjeta.MASTERCARD),
                tarjeta //tarjeta de mast
        ));
    }

    @Test
    public void descuentosCaducados(){
        // Calcular el monto total, sin descuentos vigentes (descuentos caducados).
        // marca Garbier y tarjeta de crédito MP.

        carrito.agregarProductoAlCarrito(producto1);
        carrito.agregarProductoAlCarrito(producto2);
        assertThrows(RuntimeException.class, () -> carrito.calcularMontoCarrito(
                new MarcaPromocion(true,
                        fecha2DiasAntes, LocalDate.now().minusDays(1), marcaGarnier),
                new TarjetaPromocion(true,
                        fecha2DiasAntes, LocalDate.now().minusDays(1), TipoTarjeta.MASTERCARD),
                tarjeta //tarjeta de mast
        ));
    }

    @Test
    public void pagarYRegistrarVenta(){


        assertEquals(Venta.class, carrito.pagar(cliente,
                new MarcaPromocion(true,
                        fecha2DiasAntes, fecha2DiasDesp, marcaGarnier),
                new TarjetaPromocion(true,
                        fecha2DiasAntes, fecha2DiasDesp, TipoTarjeta.MASTERCARD),
                tarjeta).getClass());

    }

    @Test
    public void pagarYRegistrarVentaSinTarjetaValida(){


        assertEquals(Venta.class, carrito.pagar(cliente,
                new MarcaPromocion(true,
                        fecha2DiasAntes, fecha2DiasDesp, marcaGarnier),
                new TarjetaPromocion(true,
                        fecha2DiasAntes, fecha2DiasDesp, TipoTarjeta.MASTERCARD),
                null).getClass());

    }







}