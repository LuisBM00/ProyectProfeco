/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profeco.negocio.app.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import profeco.negocio.ds.Conexion;
import profeco.negocio.dto.Articulo;

/**
 *
 * @author Luis Barroso
 */
public class ArticuloServices {
     Conexion cn;
    public ArticuloServices() {
        cn = Conexion.getConexionInstance();
    }
    
    public Articulo[] Listar(String nombre) {
        
        Articulo[] arr = null;
        try {
            
            PreparedStatement comando = cn.prepareStatement("SELECT * FROM dbo.[Articulos] where NombreArticulo like ?");//cn.prepareStatement("SELECT COUNT(*) FROM dbo.[Clientes]");
            comando.setString(1, nombre+"%");
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
                arr = new Articulo[total];
                comando = cn.prepareStatement("SELECT * FROM dbo.[Articulos] where NombreArticulo like ?");
                comando.setString(1, nombre+"%");
                rs = comando.executeQuery();
                int i = 0;
                while (rs.next()) {
                    arr[i] = new Articulo();
                    arr[i].setIdArticulo(rs.getInt(1));                   
                    arr[i].setNombreArticulo(rs.getString(2));
                    arr[i].setMarca(rs.getString(3));              
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
    
    public boolean Agregar(Articulo agg) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("insert into dbo.Articulos values(?, ?)");                   
            comando.setString(1, agg.getNombreArticulo());
            comando.setString(2, agg.getMarca());
            
            try {
                comando.executeQuery();
            } catch (SQLException ex) {
                return false;
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean Modificar(Articulo mod) {
        try {
            
            PreparedStatement comando =  cn.prepareStatement("update dbo.Articulos set NombreArticulo = ?, Marca = ? ");         
            
            comando.setString(1, mod.getNombreArticulo());
            comando.setString(2, mod.getMarca());
            
            try {
                comando.executeQuery();
            } catch (SQLException ex) {
                return false;
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean Eliminar(Articulo del) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("delete from dbo.Articulos where idArticulo = ?");
            comando.setInt(1, del.getIdArticulo());

            try {
                comando.executeQuery();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false;
            }

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public Articulo Buscar(int id){
        
        Articulo[]arr = Listar("");
        Articulo ObjSuper = null;
         for (Articulo arr1 : arr) {
             if (id == arr1.getIdArticulo()) {
                 ObjSuper = arr1;
             }
         }
        
        return ObjSuper;
    }
}
