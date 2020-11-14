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

public class Servidor extends Observable implements Runnable {

    private int puerto;
    private String consulta, into, tabla;
    private String nombre, descripcion, precio, impuesto, categoria, estado, inventario;
    private String idArti;
    private String idCante;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        Consultas consultas = new Consultas();
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream input;

        try {

            servidor = new ServerSocket(puerto);
            while (true) {

                sc = servidor.accept();
                input = new DataInputStream(sc.getInputStream());

                String mensaje = input.readUTF();
               

                String[] info = mensaje.split("_");

                consulta = info[0];
                

                if (consulta.equals("insert")) {
                    into = info[1];
                    tabla = info[2];
                    
                    if (tabla.equals("articulo")) {
                        System.out.println("entro articulo");
                        nombre = info[3];
                        descripcion = info[4];
                        precio = info[5];
                        impuesto = info[6];
                        categoria = info[7];
                        estado = info[8];
                        inventario = info[9];

                        consultas.insertar(nombre, descripcion, precio, impuesto, categoria, estado, inventario);
                        consultas.ObtenerArt();

                    } else if (tabla.equals("categoria")) {
                 
                        categoria = info[3];
                        //System.out.println(categoria);
                        consultas.InsertCategoria(categoria);
                        consultas.ObtenerCategoria();
                   // consultas.ObtenerArt();

                    } else {
                        JOptionPane.showInputDialog(null, "La tabla digitada no existe");
                    }

                } else if (consulta.equals("select")) {
                    System.out.println("entro a select");
                    consultas.ObtenerCategoria();
                   // consultas.ObtenerArt();

                } else {

                }

                sc.close();

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

}
