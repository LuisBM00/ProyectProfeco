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

import profeco.negocio.app.dto.Multa;

/**
 *
 * @author Luis Barroso
 */
public class MultaService {

    Conexion cn;

    public MultaService() {
        cn = Conexion.getConexionInstance();
    }

    public Multa[] Listar(int idSupermercado) {

        Multa[] arr = null;
        try {
            System.out.println(idSupermercado);
            PreparedStatement comando = cn.prepareStatement("SELECT * FROM dbo.[Multas]");//cn.prepareStatement("SELECT COUNT(*) FROM dbo.[Clientes]");
            
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
                arr = new Multa[total];
                comando = cn.prepareStatement("SELECT * FROM dbo.[Multas]");
                
                rs = comando.executeQuery();
                int i = 0;
                while (rs.next()) {
                    arr[i] = new Multa();
                    arr[i].setIdMulta(rs.getInt(1));
                    arr[i].setIdSupermercado(rs.getInt(2));
                    arr[i].setTipoSancion(rs.getString(3));
                    arr[i].setNumInconsistencias(rs.getInt(4));

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

    public boolean Agregar(Multa agg) {
        try {

            PreparedStatement comando = cn.prepareStatement("insert into [Multas] values(?,?,?)");          
            comando.setInt(1, agg.getIdSupermercado());
            comando.setString(2, agg.getTipoSancion());
            comando.setInt(3, agg.getNumInconsistencias());
           

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

//    public boolean Modificar(ListaArticulosXSuper mod) {
//        try {
//
//            PreparedStatement comando = cn.prepareStatement("update [Multas] set  PrecioActual= ?, PrecioAnterior=?, EstaDescuento=? where idLista = ? ");
//            comando.setDouble(1, mod.getPrecioActual());
//            comando.setDouble(2, mod.getPrecioInicial());
//            comando.setByte(3, mod.getEstaDescuento());
//            comando.setInt(4, mod.getIdLista());
//
//            try {
//                comando.executeQuery();
//            } catch (SQLException ex) {
//                return false;
//            }
//            return true;
//        } catch (SQLException ex) {
//            return false;
//        }
//    }

    public boolean Eliminar(Multa del) {
        try {

            PreparedStatement comando = cn.prepareStatement("delete from [Multas] where idMulta = ?");
            comando.setInt(1, del.getIdMulta());

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

    public Multa Buscar(int id) {

        Multa[] arr = Listar(id);
        Multa ObjSuper = null;
        for (Multa arr1 : arr) {
            if (id == arr1.getIdMulta()) {
                ObjSuper = arr1;
            }
        }

        return ObjSuper;
    }

}
