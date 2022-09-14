package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.ProductoInterfaz;
import ar.unrn.tp.modelo.Categoria;
import ar.unrn.tp.modelo.Marca;
import ar.unrn.tp.modelo.Producto;

import javax.persistence.*;
import java.util.List;

public class ProductoServicio implements ProductoInterfaz {

    @Override
    public void crearProducto(String codigo, String descripcion, float precio, Long idCategoria, Long idMarca){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            TypedQuery<Producto> qp = em.createQuery("select p from Producto p where p.codigo=:codigo", Producto.class);
            qp.setParameter("codigo", codigo);
            if (!qp.getResultList().isEmpty()) {
                throw new RuntimeException("El codigo del producto ya existe");
            }

            TypedQuery<Marca> q = em.createQuery("select m from Marca m where m.id=:id", Marca.class);
            q.setParameter("id", idMarca);
            Marca marca = q.getSingleResult();
            if (marca == null) {
                throw new RuntimeException("La marca no existe");
            }

            TypedQuery<Categoria> q1 = em.createQuery("select c from Categoria c where c.id=:id", Categoria.class);
            q1.setParameter("id", idCategoria);
            Categoria categoria = q1.getSingleResult();
            if (categoria == null) {
                throw new RuntimeException("La categoria no existe");
            }
            Producto p = new Producto(codigo, precio, descripcion, categoria, marca);
            em.persist(p);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }

    @Override
    public void modificarProducto(Long idProducto, String codigo, String descripcion, float precio, Long idCategoria,
                                   Long idMarca){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Producto> productos;
        try {
            tx.begin();

            TypedQuery<Producto> q = em.createQuery("select p from Producto p where p.id=:id", Producto.class);
            q.setParameter("id", idProducto);
            productos = q.getResultList();
            if (productos.isEmpty())
                throw new RuntimeException("El producto no esta registrado.");

            Producto producto = em.find(Producto.class, idProducto);

            TypedQuery<Producto> qp = em.createQuery("select p from Producto p where p.codigo=:codigo", Producto.class);
            qp.setParameter("codigo", codigo);
            if (!qp.getResultList().isEmpty() && !producto.codigo().equals(codigo)) {
                throw new RuntimeException("Ya Existe este codigo de producto");
            }

            Marca marca = em.find(Marca.class, idMarca);
            Categoria categoria = em.find(Categoria.class, idCategoria);

            producto.setCategoria(categoria);
            producto.setCodigo(codigo);
            producto.setDescripcion(descripcion);
            producto.setMarca(marca);
            producto.setPrecio(precio);


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

    @Override
    public List listarProductos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            TypedQuery<Producto> q = em.createQuery("select p from Producto p", Producto.class);

            List<Producto> productos = q.getResultList();
            return productos;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }


    public void crearMarca(String nombre) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();
            Marca m = new Marca(nombre);
            em.persist(m);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }

    public void crearCategoria(String nombre) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();
            Categoria c = new Categoria(nombre);
            em.persist(c);
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






