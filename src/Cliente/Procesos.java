package Cliente;

import Datos.Consultas;

public class Procesos implements Runnable {

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    String mensaje = "";
    private int puerto;
    //  private String consulta, into, tabla;
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

        // puerto - accion - tabla - info
        if (info[1].equals("insert")) {

            if (info[2].equals("categoria")) {
                categoria = info[3];

                consultas.InsertCategoria(categoria);

            } else if (info[2].equals("articulo")) {
                nombre = info[3];
                descripcion = info[4];
                precio = info[5];
                impuesto = info[6];
                categoria = info[7];
                estado = info[8];
                inventario = info[9];
                consultas.insertar(nombre, descripcion, precio, impuesto, categoria, estado, inventario);

            }

        } else if (info[0].equals("refresh")) {
            String msj = consultas.ObtenerArt() + "/" + consultas.ObtenerCategoria();

            RemitenteServidor.enviar("localhost", Integer.parseInt(info[1]), msj);

        } // puerto - update - tabla - info
        else if (info[1].equals("update")) {
            if (info[2].equals("articulo")) {
                int idArtis = Integer.parseInt(info[3]);
                nombre = info[4];
                descripcion = info[5];
                precio = info[6];
                impuesto = info[7];
                categoria = info[8];
                estado = info[9];
                inventario = info[10];
                consultas.ModificarArt(idArtis, nombre, descripcion, precio, impuesto, categoria, estado, inventario);

            }

        } else if (info[1].equals("delete")) {
            System.out.println("entro delete");
            if (info[2].equals("articulo")) {

                idArti = info[3];
                consultas.EliminarArt(idArti);
            } else if (info[2].equals("categoria")) {
                consultas.EliminarCat(info[3]);

            }

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
