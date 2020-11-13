/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoll.servidor;

import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author User
 */
public class Consultas {
        ConexionBD conexion = new ConexionBD();
        Connection con = conexion.getConnection();

    public void insertar(String nombre, String descripcion, String precio, String impuesto, String categoria, String estado, String inventario) {
        

        try {
            CallableStatement cs = con.prepareCall("{call SP_In_articulo(?,?,?,?,?,?,?)}");
            cs.setString(1, nombre);
            cs.setString(2, descripcion);
            cs.setString(3, precio);
            cs.setString(4, impuesto);
            cs.setString(5, categoria);
            cs.setString(6, estado);
            cs.setString(7, inventario);

            cs.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }

    }
    
    public void InsertCategoria(String nombrecat){
        try{
            CallableStatement cs2 = con.prepareCall("{call SP_In_categoria(?)}");
            cs2.setString(1,nombrecat);
            cs2.executeUpdate();
        } catch(Exception ex){
            System.out.println(ex.fillInStackTrace());
        }
        
        
    }

    public Consultas() {
    }

}
