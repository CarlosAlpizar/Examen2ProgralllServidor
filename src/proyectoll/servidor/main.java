/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoll.servidor;

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
