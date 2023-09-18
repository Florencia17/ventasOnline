package ar.unrn.tp.main;

import ar.unrn.tp.jpa.servicios.ClienteServicio;
import ar.unrn.tp.jpa.servicios.ProductoService;
import ar.unrn.tp.jpa.servicios.VentaServicio;
import ar.unrn.tp.ui.ProductosUI;

public class Main {
    public static void main(String[] args) {
//        ClienteServicio cs = new ClienteServicio();
//        cs.crearCliente("florencia", "malacarne", "41421287", "flor@gmail.com");
//        //crear otro cliente
//        cs.modificarCliente(1L, "f", "mala", "41421287", "flor@gmail.com");
//      cs.agregarTarjeta(1L, "1", "mastercard");
//        System.out.println(cs.listarTarjetas(1L));
//
//       ProductoServicio ps = new ProductoServicio();
//       ps.crearMarca("cocacola");
//        ps.crearCategoria("gaseosa");
//
//        ps.crearProducto("12", "bebida con gas", 200, 6L, 5L);
//
//        ps.modificarProducto(7L, "6", "bebida con gas", 250, 6L, 5L);
//        System.out.println(ps.listarProductos());

//        DescuentoServicio ds= new DescuentoServicio();
//        ds.crearTienda();
//       ds.crearDescuentoSobreTotal("MASTERCARD", LocalDate.now().minusDays(3),
//               LocalDate.now().plusDays(5), 0.8f);
//
//        ds.crearDescuento("cocacola", LocalDate.now().minusDays(2),
//                LocalDate.now().plusDays(10), 0.5f);


//        ps.crearMarca("garnier");
//        ps.crearCategoria("shampoo");

//        ps.crearProducto("12", "sin parabenos", 1000 , 13L, 12L);
//
//        VentaServicio vs = new VentaServicio();
//        List<Long> productos = new ArrayList<>();
//        productos.add(7L);
//        productos.add(14L);
////
////        vs.realizarVenta(1L,productos, 4L);
//
//        System.out.println(vs.calcularMonto(productos, 4L));
//        System.out.println(vs.ventas());

        ProductosUI ventanaProductos = new ProductosUI(new ProductoService(), new VentaServicio(), new ClienteServicio(), 1L );
        ventanaProductos.setVisible(true);

    }
}
