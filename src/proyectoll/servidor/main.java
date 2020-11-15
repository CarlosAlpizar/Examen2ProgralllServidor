package proyectoll.servidor;

import Datos.Servidor;

/**
 *
 * @author Carlos Alpizar <carlosalpizarg@hotmail.com>
 */
public class main {

    public static void main(String[] args) {

        Servidor server = new Servidor(9000);
        Thread hilo = new Thread(server);
        hilo.start();

    }

}
