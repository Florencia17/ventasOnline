package ar.unrn.tp.modelo;


public class Producto {

    private String codigo;
    private String descripcion;
    private Categoria categoria;
    private double precio;
    private Marca marca;

    public Producto(String codigo, double precio, String descripcion, Categoria categoria, Marca marca) {

        if (esDatoVacio(codigo))
            throw new RuntimeException("El codigo debe ser valido");
        this.codigo = codigo;

        if (descripcion == null || descripcion.isEmpty())
            throw new RuntimeException("La descripcion debe ser valido");
        this.descripcion = descripcion;

        if (esDatoVacio(String.valueOf(precio)))
            throw new RuntimeException("El precio debe ser valido");
        this.precio = precio;

        if (esDatoNulo(categoria))
            throw new RuntimeException("La categoria debe ser valido");
        this.categoria = categoria;

        if (esDatoNulo(marca))
            throw new RuntimeException("La marca debe ser valido");
        this.marca = marca;
    }

    private boolean esDatoVacio(String dato) {
        return dato.equals("");
    }

    private boolean esDatoNulo(Object dato) {
        return dato == null;
    }


    public String codigo() {
        return codigo;
    }

    public String descripcion() {
        return descripcion;
    }

    public Marca marca() {
        return marca;
    }

    public Categoria categoria() {
        return categoria;
    }

    public double precio() {
        return precio;
    }

  /*  @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", " + categoria +
                ", precio=" + precio +
                ", " + marca +
                '}';
    }*/
}