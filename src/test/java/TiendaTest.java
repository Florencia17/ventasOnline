
import ar.unrn.tp.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TiendaTest {

    private Tienda tienda;
    private Cliente cliente;
    private Tarjeta tarjeta;
    private Carrito carrito;
    private Marca marcaGarnier, marcaArcor, marcaCocacola;
    private Categoria categoria, categoria2;
    private Producto producto1, producto2, producto3, producto4;

    private LocalDate  fecha2DiasAntes, fecha2DiasDesp;


    @BeforeEach
    public void before() {

        tienda = new Tienda();
        carrito = new Carrito();

        cliente = new Cliente("Florencia", "Malacarne", "12345678", "flor@gmail.com");
        tarjeta = new Tarjeta(1111, TipoTarjeta.MASTERCARD);

        marcaGarnier = new Marca("Garnier");
        marcaArcor = new Marca("Oreo");
        marcaCocacola = new Marca("CocaCola");

        categoria = new Categoria("shampoo");
        categoria2 = new Categoria("gaseosa");

        producto1 = new Producto("1", 100, "shampooSolido", categoria, marcaGarnier);
        producto2 = new Producto("2", 34, "gomitas", categoria2, marcaArcor);
        producto3 = new Producto("3", 6, "cola", categoria2, marcaCocacola);
        producto4 = new Producto("4", 100, "shampooSeco", categoria, marcaGarnier);

        carrito.agregarProductoAlCarrito(producto1);
        carrito.agregarProductoAlCarrito(producto2);
        carrito.agregarProductoAlCarrito(producto3);
        carrito.agregarProductoAlCarrito(producto4);


        fecha2DiasAntes = LocalDate.now().minusDays(2);
        fecha2DiasDesp = LocalDate.now().plusDays(2);



    }

    @Test
    public void registrarVenta() {


        tienda.setMarcaPromocion(new MarcaPromocion(true,
                fecha2DiasAntes,
                fecha2DiasDesp, marcaGarnier));
        tienda.setTarjetaPromocion(new TarjetaPromocion(true,
                fecha2DiasAntes,
                fecha2DiasDesp, TipoTarjeta.MASTERCARD));


        tienda.agregarVenta(carrito.pagar(cliente,
                tienda.MarcaPromocionVigente(), tienda.TarjetaPromocionVigente(), tarjeta));

        assertEquals(1, tienda.verVentasRealizadas().size());


    }

    //REGISTRO DE PROMOCION CON FECHAS VALIDAS

    @Test
    public void registrarPromocionMarcaNueva() {
        tienda.setMarcaPromocion(new MarcaPromocion(true,
                fecha2DiasAntes, fecha2DiasDesp, marcaGarnier));

        assertEquals(1, tienda.marcaPromocionList().size());

    }

    @Test
    public void registrarPromocionTarjetaNueva() {
        tienda.setTarjetaPromocion(new TarjetaPromocion(true,
                fecha2DiasAntes, fecha2DiasDesp, TipoTarjeta.MASTERCARD));

        assertEquals(1, tienda.tarjetaPromocionList().size());
    }

    @Test
    public void registrarPromociones(){
        tienda.setMarcaPromocion(new MarcaPromocion(true,
                fecha2DiasAntes,
                fecha2DiasDesp, marcaArcor));
        tienda.setTarjetaPromocion(new TarjetaPromocion(true,
                fecha2DiasAntes,
                fecha2DiasDesp, TipoTarjeta.MACRO));

        tienda.setMarcaPromocion(new MarcaPromocion(true,
                fecha2DiasAntes,
                fecha2DiasDesp, marcaArcor));
        tienda.setTarjetaPromocion(new TarjetaPromocion(true,
                fecha2DiasAntes,
                fecha2DiasDesp, TipoTarjeta.MASTERCARD));

        assertEquals(2, tienda.marcaPromocionList().size());
        assertEquals(2, tienda.tarjetaPromocionList().size());
    }

    //REGISTRO DE PROMOCION INVALIDAS
    @Test
    public void registrarPromocionMarcaNuevaConFechaInvalida() {
        //  fecha de finalizacion previo al dia de hoy

        assertThrows(RuntimeException.class, () -> tienda.setMarcaPromocion(new MarcaPromocion(true,
                fecha2DiasAntes, LocalDate.now().minusDays(1), marcaGarnier)));

    }

    @Test
    public void registrarPromocionTarjetaNuevaConFechaInvalida() {
        //  fecha de finalizacion previo al dia de hoy

        assertThrows(RuntimeException.class, () -> tienda.setTarjetaPromocion(new TarjetaPromocion(true,
                fecha2DiasAntes, LocalDate.now().minusDays(1), TipoTarjeta.MASTERCARD)));

    }

    @Test
    public void registrarPromocionTarjetaNuevaConFechasInvertidas() {


        assertThrows(RuntimeException.class, () -> tienda.setTarjetaPromocion(new TarjetaPromocion(true,
                fecha2DiasDesp, fecha2DiasAntes, TipoTarjeta.MASTERCARD)));

    }

    @Test
    public void registrarPromocionMarcaNuevaVacia() {

        assertThrows(RuntimeException.class, () -> tienda.setMarcaPromocion(null));

    }

    @Test
    public void registrarPromocionTarjetaNuevaVacia() {

        assertThrows(RuntimeException.class, () -> tienda.setTarjetaPromocion(null));

    }


}