
package profeco.negocio.app.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import profeco.negocio.app.ds.Conexion;
import profeco.negocio.app.dto.Usuario;


public class UsuarioServices {
     Conexion cn;
    public UsuarioServices() {
        cn = Conexion.getConexionInstance();
    }
    
    public Usuario[] Listar(String nombre) {
        
        Usuario[] arr = null;
        try {
            
            PreparedStatement comando = cn.prepareStatement("SELECT * FROM dbo.[Usuarios] where idUsuario like ?");//cn.prepareStatement("SELECT COUNT(*) FROM dbo.[Clientes]");
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
                arr = new Usuario[total];
                comando = cn.prepareStatement("SELECT * FROM dbo.[Usuarios] where idUsuario like ?");
                comando.setString(1, nombre+"%");
                rs = comando.executeQuery();
                int i = 0;
                while (rs.next()) {
                    arr[i] = new Usuario();
                    arr[i].setIdUsuario(rs.getString(1));
                    arr[i].setIdTipoUsuario(rs.getInt(2));
                    arr[i].setIdCOS(rs.getInt(3));
                    arr[i].setNombre(rs.getString(4));
                    arr[i].setCorreo(rs.getString(5));
                    arr[i].setContrasena(rs.getString(6));
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
    
    public boolean Agregar(Usuario agg) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("insert into dbo.Usuarios values(?, ?, ?, ?, ?, ?)");
            comando.setString(1, agg.getIdUsuario());         
            comando.setInt(2, agg.getIdTipoUsuario());
            comando.setInt(3, agg.getIdCOS());
            comando.setString(4, agg.getNombre());
            comando.setString(5, agg.getCorreo());
            comando.setString(6, agg.getContrasena());
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
    
    public boolean Modificar(Usuario mod) {
        try {
            
            PreparedStatement comando =  cn.prepareStatement("update dbo.Usuarios set idUsuario = ?, idTipoUsuario = ?, idCOS = ?, Nombre = ?, Correo = ?, Contrasena = ? where idSupermercado = ? ");
            comando.setString(7, mod.getIdUsuarioAnterior());
            comando.setString(1, mod.getIdUsuario());
            comando.setInt(2, mod.getIdTipoUsuario());
            comando.setInt(3, mod.getIdCOS());
            comando.setString(4, mod.getNombre());
            comando.setString(5, mod.getCorreo());
            comando.setString(6, mod.getContrasena());
            
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
    public boolean Eliminar(Usuario del) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("delete from dbo.Usuarios where idUsuario = ?");
            comando.setString(1, del.getIdUsuario());

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
    
    public Usuario Buscar(String id){
        
        Usuario[]arr = Listar("");
        Usuario ObjSuper = null;
         for (Usuario arr1 : arr) {
             if (id.equals(arr1.getIdUsuario())) {
                 ObjSuper = arr1;
             }
         }
        
        return ObjSuper;
    }
    
    
    
}
