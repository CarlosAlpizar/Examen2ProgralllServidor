package Datos;

import Datos.ConexionBD;
import com.mysql.cj.protocol.Resultset;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import Cliente.RemitenteServidor;

public class Consultas {

    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.getConnection();
    String mensaje = "";

    public void insertar(String nombre, String descripcion, String precio, String impuesto, String categoria, String estado, String inventario) {
        String select = "select idcategoria from categoria where nombre = '" + categoria + "'";
        Statement st;
        String idcategoria = null;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                idcategoria = rs.getString(1);

            }

        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }

        try {
            CallableStatement cs = con.prepareCall("{call SP_In_articulo(?,?,?,?,?,?,?)}");
            cs.setString(1, nombre);
            cs.setString(2, descripcion);
            cs.setString(3, precio);
            cs.setString(4, impuesto);
            cs.setString(5, idcategoria);
            cs.setString(6, estado);
            cs.setString(7, inventario);

            cs.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }

    }

    public void ObtenerArt(int puerto) {
        String select = "select * from articulo";
        Statement st;
        //  String[] dato = new String[2];
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                mensaje += rs.getString(1) + "_" + rs.getString(2) + "_" + rs.getString(3) + "_" + rs.getString(4) + "_" + rs.getString(5) + "_"
                        + rs.getString(6) + "_" + rs.getString(7) + "_" + rs.getString(8) + ";";

            }
            System.out.println(mensaje);
            RemitenteServidor.enviar("localhost", puerto, mensaje);
            mensaje = "";
        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
    }
    


    ////////////////////// categorias
    public void InsertCategoria(String nombrecat) {
        try {
            CallableStatement cs2 = con.prepareCall("{call SP_In_categoria(?)}");
            cs2.setString(1, nombrecat);

            cs2.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }

    }

    public void ObtenerCategoria(int puerto) {
        String select = "select * from categoria";
        Statement st;

        //String[] dato = new String[2];
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            mensaje += "C%";
            while (rs.next()) {
                mensaje += rs.getString(1) + "_" + rs.getString(2) + ";";
            }
            System.out.println(mensaje);
            RemitenteServidor.enviar("localhost", puerto, mensaje);
            mensaje = "";
        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
    }

    public Consultas() {
    }

}
