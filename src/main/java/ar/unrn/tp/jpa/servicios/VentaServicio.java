package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.VentaInterface;
import ar.unrn.tp.modelo.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class VentaServicio implements VentaInterface {

    @Override
    public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();
            Cliente cliente = em.find(Cliente.class, idCliente);
            if (cliente == null) {
                throw new RuntimeException("El cliente no existe");
            }
            Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);
            if (!cliente.tieneTarjeta(tarjeta)) {
                throw new RuntimeException("La tarjeta no pertenece al cliente");
            }

            List<Producto> productosLista = new ArrayList<>();
            if (productos.isEmpty()) {
                throw new RuntimeException("La lista de productos no puede ser vacia");
            }
            for (Long p : productos) {
                Producto prod = em.find(Producto.class, p);
                if (prod == null) {
                    throw new RuntimeException("el producto de id: " + p + " no existe");
                }
                productosLista.add(prod);
            }

            Carrito carrito = new Carrito();
            for (Producto p : productosLista) {
                carrito.agregarProductoAlCarrito(p);
            }
            Tienda tienda = em.find(Tienda.class, 8L);
            Venta venta = carrito.pagar(cliente, tienda.MarcaPromocionVigente(), tienda.TarjetaPromocionVigente(), tarjeta);

            tienda.agregarVenta(venta);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }

    @Override
    public float calcularMonto(List<Long> productos, Long idTarjeta) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();

            Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);
            if (tarjeta == null) {
                throw new RuntimeException("La tarjeta no existe");
            }

            List<Producto> productosLista = new ArrayList<>();
            if (productos.isEmpty()) {
                throw new RuntimeException("La lista de productos no puede ser vacia");
            }
            for (Long p : productos) {
                Producto prod = em.find(Producto.class, p);
                if (prod == null) {
                    throw new RuntimeException("el producto de id: " + p + " no existe");
                }
                productosLista.add(prod);
            }

            Carrito carrito = new Carrito();
            for (Producto p : productosLista) {
                carrito.agregarProductoAlCarrito(p);
            }
            Tienda tienda = em.find(Tienda.class, 8L);

            return (float) carrito.calcularMontoCarrito(tienda.MarcaPromocionVigente(), tienda.TarjetaPromocionVigente(), tarjeta);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }

    @Override
    public List<Venta> ventas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            TypedQuery<Venta> q = em.createQuery("select v from Tienda t join t.ventas v", Venta.class);

            List<Venta> ventasList = q.getResultList();
            return ventasList;
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }
}
