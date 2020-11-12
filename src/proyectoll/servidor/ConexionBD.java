/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoll.servidor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Carlos Alpizar <carlosalpizarg@hotmail.com>
 */
public class ConexionBD {

    public Connection getConnection() {

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventos?useTimezone=true&serverTimezone=UTC", "root", "root");
            return con;

        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
            return null;
        }

    }

    public void ejecutar(String s) {

        Connection conection = getConnection();
        String consulta = "{call SP_In_articulo(?,?)}";
        try {

            CallableStatement cs = conection.prepareCall(consulta);
            cs.setString(1, "1");
            cs.setString(2, "21/04/2021 1:00");

            cs.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
