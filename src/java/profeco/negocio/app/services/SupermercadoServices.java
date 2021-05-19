
package profeco.negocio.app.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import profeco.negocio.app.ds.Conexion;
import profeco.negocio.app.dto.Supermercado;

public class SupermercadoServices {
    
    Conexion cn;
    public SupermercadoServices() {
        cn = Conexion.getConexionInstance();
    }
    
    public Supermercado[] Listar(String nombre) {
        
        Supermercado[] arr = null;
        try {
            
            PreparedStatement comando = cn.prepareStatement("SELECT * FROM dbo.[Supermercado] where nombreSupermercado like ?");//cn.prepareStatement("SELECT COUNT(*) FROM dbo.[Clientes]");
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
                arr = new Supermercado[total];
                comando = cn.prepareStatement("SELECT * FROM dbo.[Supermercado] where nombreSupermercado like ?");
                comando.setString(1, nombre+"%");
                rs = comando.executeQuery();
                int i = 0;
                while (rs.next()) {
                    arr[i] = new Supermercado();
                    arr[i].setIdSupermercado(rs.getInt(1));
                    arr[i].setNombreSupermercado(rs.getString(2));
                    arr[i].setDireccion(rs.getString(3));                    
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
    
    public boolean Agregar(Supermercado agg) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("insert into dbo.Supermercado values(?, ?)");
            comando.setString(1, agg.getNombreSupermercado());         
            comando.setString(2, agg.getDireccion());   
           
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
    
    public boolean Modificar(Supermercado mod) {
        try {
            
            PreparedStatement comando =  cn.prepareStatement("update dbo.Supermercado set NombreSupermercado = ?, Direccion = ? where idSupermercado = ? ");
            comando.setInt(3, mod.getIdSupermercado());
            comando.setString(1, mod.getNombreSupermercado());
            comando.setString(2, mod.getDireccion());
            
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
    public boolean Eliminar(Supermercado del) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("delete from dbo.Supermercado where idSupermercado = ?");
            comando.setInt(1, del.getIdSupermercado());

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
    
    public Supermercado Buscar(int id){
        
        Supermercado[]arr = Listar("");
        Supermercado ObjSuper = null;
         for (Supermercado arr1 : arr) {
             if (id == arr1.getIdSupermercado()) {
                 ObjSuper = arr1;
             }
         }
        
        return ObjSuper;
    }
}
