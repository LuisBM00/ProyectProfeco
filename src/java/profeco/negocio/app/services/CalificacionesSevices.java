/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profeco.negocio.app.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import profeco.negocio.app.ds.Conexion;

import profeco.negocio.app.dto.CalificacionesComentarios;

/**
 *
 * @author Carlo
 */
public class CalificacionesSevices {
     Conexion cn;
    public CalificacionesSevices() {
        cn = Conexion.getConexionInstance();
    }
    
    public CalificacionesComentarios[] Listar(int idSupermercado) {
        
        CalificacionesComentarios[] arr = null;
        try {
            
            PreparedStatement comando = cn.prepareStatement("SELECT * FROM dbo.[CalificacionesComentarios] where idSupermercado = ?");//cn.prepareStatement("SELECT COUNT(*) FROM dbo.[Clientes]");
            comando.setInt(1, idSupermercado);
            try {
//                ResultSet RS = comando.getGeneratedKeys();
//                RS.next();
//                int NF = RS.getInt(1);
//                arrCli = new strCliente[NF];
                ///////comando = cn.prepareStatement("SELECT * FROM dbo.[Clientes]");
                //comando.setString(6,"000010");
                ResultSet rs = comando.executeQuery();
//                while(rs.next()){   
//                int num=rs.getInt(1);  
//                } 
                int total = 0;
                while (rs.next()) {
                    //Obtienes la data que necesitas...
                    total++;
                }
                arr = new CalificacionesComentarios[total];
                comando = cn.prepareStatement("SELECT * FROM dbo.[CalificacionesComentarios] where idSupermercado= ?");
                comando.setInt(1, idSupermercado);
                rs = comando.executeQuery();
                int i = 0;
                while (rs.next()) {
                    arr[i] = new CalificacionesComentarios();
                    arr[i].setIdCalificacion(rs.getInt(1));
                     arr[i].setIdCliente(rs.getString(2));
                    arr[i].setIdSupermercado(rs.getInt(3));
                    arr[i].setCalificacion(rs.getInt(4));
                    arr[i].setComentario(rs.getString(5));
                   
                    i++;
                }
                return arr;
            } catch (SQLException E) {
                System.out.println(E.toString());
            }

        } catch (Exception E) {
            System.out.println(E.toString());
        }

        return arr;
    }
    
    public boolean Agregar(CalificacionesComentarios agg) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("insert into dbo.[CalificacionesComentarios] values(?, ?, ?, ?)");
            comando.setString(1, agg.getIdCliente());         
            comando.setInt(2, agg.getIdSupermercado());
            comando.setInt(3, (int) agg.getCalificacion());
            comando.setString(4, agg.getComentario());
        
            try {
                comando.executeQuery();
                System.out.println("paso");
                
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
   
    
    
    
    
}
