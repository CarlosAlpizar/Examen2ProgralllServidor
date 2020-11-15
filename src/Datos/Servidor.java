package Datos;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import Cliente.Procesos;

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
                System.out.println("mensaje entrante " + mensaje);
                Thread hilo = new Thread(new Procesos(mensaje));
                hilo.start();

                sc.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

}
