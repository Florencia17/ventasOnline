import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ar.unrn.tp.modelo.Tarjeta;
import ar.unrn.tp.modelo.TipoTarjeta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import ar.unrn.tp.modelo.Cliente;

class ClienteTest {

    //todos los casos de prueba:
    public static Object[] parametros() {
        return new Object[][] {
                { "", "Malacarne", "00000000", "flor@gmail.com" },  //sin nombre
                { "Flor", "", "00000000", "flor@gmail.com" },   //sin apellido
                { "Flor", "Malacarne", "", "flor@gmail.com" },     //sin dni
                { "Flor", "Malacarne", "00000000", "flor@gmail" }, //email invalido
                { "Flor", "Malacarne", "00000000", "" },          //sin email
                { "Flor", "Malacarne", "000", "flor@gmail.com" },  //dni invalido
        };
    }

    @ParameterizedTest
    @MethodSource("parametros")
    void crearCliente(String nombre, String apellido, String dni, String email) {
        assertThrows(RuntimeException.class, () -> new Cliente(nombre, apellido, dni, email));
    }

    @Test
    public void agregarTarjeta(){
        Cliente cliente =  new Cliente("Florencia", "Malacarne", "12345678", "flor@gmail.com");
        Tarjeta tarjeta = new Tarjeta(12, TipoTarjeta.MASTERCARD);
        cliente.agregarTarjeta(tarjeta);
        assertEquals(1, cliente.getTarjetas().size());
    }

}
