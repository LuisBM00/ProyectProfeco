
package profeco.negocio.app.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import profeco.negocio.ds.Conexion;
import profeco.negocio.dto.Cliente;



public class ClienteServices {
    
     Conexion cn;
    public ClienteServices() {
        cn = Conexion.getConexionInstance();
    }
    
    public Cliente[] Listar(String nombre) {
        
        Cliente[] arr = null;
        try {
            
            PreparedStatement comando = cn.prepareStatement("SELECT * FROM dbo.[Clientes] where NombreCliente like ?");//cn.prepareStatement("SELECT COUNT(*) FROM dbo.[Clientes]");
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
                arr = new Cliente[total];
                comando = cn.prepareStatement("SELECT * FROM dbo.[Clientes] where NombreCliente like ?");
                comando.setString(1, nombre+"%");
                rs = comando.executeQuery();
                int i = 0;
                while (rs.next()) {
                    arr[i] = new Cliente();
                    arr[i].setIdCliente(rs.getString(1));                   
                    arr[i].setNombreCliente(rs.getString(2));
                    arr[i].setCorreo(rs.getString(3));
                    arr[i].setSexo(rs.getBoolean(4));
                    arr[i].setEdad(rs.getByte(5));
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
    
    public boolean Agregar(Cliente agg) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("insert into dbo.Clientes values(?, ?, ?, ?, ?)");
            comando.setString(1, agg.getIdCliente());                   
            comando.setString(2, agg.getNombreCliente());
            comando.setString(3, agg.getCorreo());
            comando.setBoolean(4, agg.getSexo());
            comando.setByte(5,agg.getEdad());
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
    
    public boolean Modificar(Cliente mod) {
        try {
            
            PreparedStatement comando =  cn.prepareStatement("update dbo.Clientes set idCliente = ?, NombreCliente = ?, Correo = ?, Sexo = ?, Edad = ? where idCliente = ? ");
            comando.setString(7, mod.getIdClienteAnterior());
            comando.setString(1, mod.getIdCliente());
            comando.setString(2, mod.getNombreCliente());
            comando.setString(3, mod.getCorreo());
            comando.setBoolean(4, mod.getSexo());
            comando.setByte(5, mod.getEdad());
            
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
    public boolean Eliminar(Cliente del) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("delete from dbo.Clientes where idCliente = ?");
            comando.setString(1, del.getIdCliente());

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
    
    public Cliente Buscar(String id){
        
        Cliente[]arr = Listar("");
        Cliente ObjSuper = null;
         for (Cliente arr1 : arr) {
             if (id.equals(arr1.getIdCliente())) {
                 ObjSuper = arr1;
             }
         }
        
        return ObjSuper;
    }
    
  
}
