
package profeco.negocio.app.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import profeco.negocio.app.ds.Conexion;
import profeco.negocio.app.dto.ListaArticulosXSuper;

public class ListaArticulosXSuperServices {
    
    Conexion cn;
    public ListaArticulosXSuperServices() {
        cn = Conexion.getConexionInstance();
    }
    
    public ListaArticulosXSuper[] Listar(int idSupermercado) {
        
        ListaArticulosXSuper[] arr = null;
        try {
            System.out.println(idSupermercado);
            PreparedStatement comando = cn.prepareStatement("SELECT * FROM dbo.[ArticulosxSupermercado] where idSupermercado= ?");//cn.prepareStatement("SELECT COUNT(*) FROM dbo.[Clientes]");
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
                arr = new ListaArticulosXSuper[total];
                comando = cn.prepareStatement("SELECT * FROM dbo.[ArticulosxSupermercado] where idSupermercado= ?");
                comando.setInt(1, idSupermercado);
                rs = comando.executeQuery();
                int i = 0;
                while (rs.next()) {
                    arr[i] = new ListaArticulosXSuper();
                     arr[i].setIdLista(rs.getInt(1));
                    arr[i].setIdSupermercado(rs.getInt(2));
                     arr[i].setIdArticulo(rs.getInt(3));
                    arr[i].setCantidad(rs.getInt(4));    
                      
                      arr[i].setPrecioActual(rs.getDouble(5));
                      arr[i].setPrecioInicial(rs.getDouble(6));
                       arr[i].setEstaDescuento(rs.getByte(7));
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
    
    public boolean Agregar(ListaArticulosXSuper agg) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("insert into [ArticulosxSupermercado] values(?,?,?,?,?,?)");
            comando.setInt(1, agg.getIdSupermercado());         
            comando.setInt(2, agg.getIdArticulo());
            comando.setInt(3, agg.getCantidad());
            comando.setDouble(4, agg.getPrecioActual());
            comando.setDouble(5,agg.getPrecioInicial());
            comando.setByte(6, agg.getEstaDescuento());
           
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
    
    public boolean Modificar(ListaArticulosXSuper mod) {
        try {
            
            PreparedStatement comando =  cn.prepareStatement("update [ArticulosxSupermercado] set  PrecioActual= ?, PrecioAnterior=?, EstaDescuento=? where idLista = ? ");
                        comando.setDouble(1,mod.getPrecioActual());
            comando.setDouble(2,mod.getPrecioInicial());
            comando.setByte(3, mod.getEstaDescuento());
            comando.setInt(4, mod.getIdLista());
            
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
    public boolean Eliminar(ListaArticulosXSuper del) {
        try {
            
            PreparedStatement comando = cn.prepareStatement("delete from [ArticulosxSupermercado] where idSupermercado = ?");
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
    
    public ListaArticulosXSuper Buscar(int id){
        
        ListaArticulosXSuper[]arr = Listar(id);
        ListaArticulosXSuper ObjSuper = null;
         for (ListaArticulosXSuper arr1 : arr) {
             if (id == arr1.getIdSupermercado()) {
                 ObjSuper = arr1;
             }
         }
        
        return ObjSuper;
    }
}
