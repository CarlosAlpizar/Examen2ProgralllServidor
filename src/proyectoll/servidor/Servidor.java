/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoll.servidor;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Alpizar <carlosalpizarg@hotmail.com>
 */
public class Servidor extends Observable implements Runnable {

    private int puerto;
    private String consulta;
    private String nombre, descripcion, precio, impuesto, categoria, estado, inventario;
    private String idArti;
    private String idCante;

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

                String mensaje = input.readUTF();
                System.out.println(mensaje);

                String[] info = mensaje.split("_");

                consulta = info[0];
                System.out.println(consulta);

                if (consulta.equals("insert")) {
                    String into = info[1];
                    String tabla = info[2];
                    System.out.println("entro");

                    if (tabla.equals("articulo")) {
                        System.out.println("entro2");
                        nombre = info[3];
                        System.out.println(nombre);

                    } else if (tabla == "categoria") {

                    } else {
                        JOptionPane.showInputDialog(null, "La tabla digitada no existe");
                    }

                } else if (consulta == "update") {

                } else {

                }

                sc.close();

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

}
