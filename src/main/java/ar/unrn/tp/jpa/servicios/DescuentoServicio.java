package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.DescuentoInterfaz;
import ar.unrn.tp.modelo.*;

import javax.persistence.*;
import java.time.LocalDate;

public class DescuentoServicio implements DescuentoInterfaz {

    @Override
    public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde,
                                         LocalDate fechaHasta, float porcentaje){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // hacer validacion de fechas

//            System.out.println(fechaDesde + "desde");
//            System.out.println(fechaHasta + "hasta");
            Tienda tienda = em.find(Tienda.class, 8L);
            tienda.setTarjetaPromocion(new TarjetaPromocion(true,
                    fechaDesde, fechaHasta, TipoTarjeta.valueOf((marcaTarjeta).toUpperCase())));
//            System.out.println(tienda.tarjetaPromocionList());



            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();

            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
            if (emf.isOpen())
                emf.close();
        }

    }

    public void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate
            fechaHasta, float porcentaje){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Marca marca;
        try {
            tx.begin();
            // validar que las fechas no se superpongan

            TypedQuery<Marca> qm = em.createQuery("select m from Marca m where m.nombre=:nombre", Marca.class);
            qm.setParameter("nombre", marcaProducto);
            marca = qm.getSingleResult();
            if (marca == null)
                throw new RuntimeException("La marca ingresada no existe.");

            Tienda tienda = em.find(Tienda.class, 8);
            tienda.setMarcaPromocion(new MarcaPromocion(true, fechaDesde, fechaHasta, marca));
            System.out.println(tienda);


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
            if (emf.isOpen())
                emf.close();
        }
    }

    public void crearTienda() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();
            Tienda t = new Tienda();
            em.persist(t);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }

}
