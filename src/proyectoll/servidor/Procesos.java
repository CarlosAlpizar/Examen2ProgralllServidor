package proyectoll.servidor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Procesos implements Runnable {

    /**
     * @return the puerto
     */
    public int getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    String mensaje = "";
    private int puerto;
    private String consulta, into, tabla;
    private String nombre, descripcion, precio, impuesto, categoria, estado, inventario;
    private String idArti;
    private String idCante;

    public Procesos(String msj) {

        this.mensaje = msj;
    }

    @Override
    public void run() {

        Consultas consultas = new Consultas();
        String[] info = mensaje.split("_");
        System.out.println(info[0]);

        // puerto - accion - tabla - info
        
        
        if (info[1].equals("insert")) {

            if (info[2].equals("categoria")) {
                categoria = info[3];
                
                consultas.InsertCategoria(categoria);
                consultas.ObtenerCategoria(puerto);
                
            } 
            else if (info[2].equals("articulo")) {
                System.out.println("as");
            }


        }
        else if (info[0].equals("refresh")){
            consultas.ObtenerCategoria(Integer.parseInt(info[1]));
            consultas.ObtenerArt(Integer.parseInt(info[1]));
        } 
    }
}

/*
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
 */
