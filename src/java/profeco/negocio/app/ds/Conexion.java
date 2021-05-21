/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profeco.negocio.app.ds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Luis Barroso
 */
public final class Conexion {
    private static Conexion con;
    private Connection cn;
    //molombi qlon de mrda

    private Conexion() {
        cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //Conexion a SQL
            cn = DriverManager.getConnection("jdbc:sqlserver://sql5104.site4now.net; database=db_a747f3_profecoapp; user=db_a747f3_profecoapp_admin ; password=adodesconectado1;");
            //jdbc:sqlserver//DESKTOP-UC0TM7V\\SQLEXPRESS;1433;databaseName=Motel;user=usuarioSQL;password=321;
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    public static Conexion getConexionInstance() {

        if (con == null) {
            con = new Conexion();
        } else {
            System.out.println("no se puede jijiji");
        }
        
        return con;
    }

   public  PreparedStatement prepareStatement(String exec_dbosp_TipoUsuarioMDF__) {
        PreparedStatement comando = null;
        try {
           comando = cn.prepareCall(exec_dbosp_TipoUsuarioMDF__);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
       
        return comando;
    }

    
    
}
