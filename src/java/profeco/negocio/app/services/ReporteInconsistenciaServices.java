package profeco.negocio.app.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import profeco.negocio.app.ds.Conexion;
import profeco.negocio.app.dto.ReporteInconsistencia;
import profeco.negocio.app.dto.Supermercado;

public class ReporteInconsistenciaServices {

    Conexion cn;

    public ReporteInconsistenciaServices() {
        cn = Conexion.getConexionInstance();
    }

    public ReporteInconsistencia[] Listar(int idSupermercado) {

        ReporteInconsistencia[] arr = null;
        try {

            PreparedStatement comando = cn.prepareStatement("SELECT * FROM dbo.[ReportesInconsistencias] where idSupermercado = ?");//cn.prepareStatement("SELECT COUNT(*) FROM dbo.[Clientes]");
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
                arr = new ReporteInconsistencia[total];
                comando = cn.prepareStatement("SELECT * FROM dbo.[ReportesInconsistencias] where idSupermercado = ?");
                comando.setInt(1, idSupermercado);
                rs = comando.executeQuery();
                int i = 0;
                while (rs.next()) {
                    arr[i] = new ReporteInconsistencia();
                    arr[i].setIdReporte(rs.getInt(1));
                    arr[i].setIdCliente(rs.getString(2));
                    arr[i].setIdSupermercado(rs.getInt(3));
                    arr[i].setIdArticulo(rs.getInt(4));
                    arr[i].setPrecioPublicado(rs.getDouble(5));
                    arr[i].setPrecioReal(rs.getDouble(6));
                    arr[i].setComentarios(rs.getString(7));
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

    public boolean Agregar(ReporteInconsistencia agg) {
        try {

            PreparedStatement comando = cn.prepareStatement("insert into dbo.ReportesInconsistencias values(?, ?,?,?,?,?)");
            comando.setString(1, agg.getIdCliente());
            comando.setInt(2, agg.getIdSupermercado());
            comando.setInt(3, agg.getIdArticulo());
            comando.setDouble(4, agg.getPrecioPublicado());
            comando.setDouble(5, agg.getPrecioReal());
            comando.setString(6, agg.getComentarios());

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

            PreparedStatement comando = cn.prepareStatement("update dbo.Supermercado set NombreSupermercado = ?, Direccion = ? where idSupermercado = ? ");
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

    public int CantidadInconsistencias(int idSupermercado) {
        try {

            try {
//                ResultSet RS = comando.getGeneratedKeys();
//                RS.next();
//                int NF = RS.getInt(1);
//                arrCli = new strCliente[NF];
                ///////comando = cn.prepareStatement("SELECT * FROM dbo.[Clientes]");
                //comando.setString(6,"000010");
                ResultSet rs = null;
//                while(rs.next()){   
//                int num=rs.getInt(1);  
//                }           
                int cantidad = 0;
                PreparedStatement comando = cn.prepareStatement("select Count(idSupermercado) as cantidad from dbo.ReportesInconsistencias where idSupermercado = ? group by idSupermercado");
                comando.setInt(1, idSupermercado);
                rs = comando.executeQuery();
                int i = 0;
                while (rs.next()) {
                    cantidad = rs.getInt(1);
                }
                return cantidad;
            } catch (SQLException E) {
                System.out.println(E.toString());
            }

        } catch (Exception E) {
            System.out.println(E.toString());
        }

        return 0;
    }

    public ReporteInconsistencia Buscar(int id) {

        ReporteInconsistencia[] arr = Listar(id);
        ReporteInconsistencia ObjSuper = null;
        for (ReporteInconsistencia arr1 : arr) {
            if (id == arr1.getIdSupermercado()) {
                ObjSuper = arr1;
            }
        }

        return ObjSuper;
    }
}
