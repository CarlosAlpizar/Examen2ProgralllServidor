/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoll.servidor;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import javax.swing.JOptionPane;

public class Servidor extends Observable implements Runnable {

    private int puerto;
    private String consulta, into, tabla;
    private String nombre, descripcion, precio, impuesto, categoria, estado, inventario;
    private String idArti;
    private String idCante;
    private String mensaje;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {

        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream input;

        try {
            servidor = new ServerSocket(puerto);
            while (true) {

                sc = servidor.accept();
                input = new DataInputStream(sc.getInputStream());

                this.mensaje = input.readUTF();
                System.out.println(mensaje);
                Thread hilo = new Thread(new Procesos(mensaje));
                hilo.start();

                sc.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

}
