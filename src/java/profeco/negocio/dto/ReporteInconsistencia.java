/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profeco.negocio.dto;

/**
 *
 * @author Luis Barroso
 */
public class ReporteInconsistencia {
    
    private int idReporte;
    private int idCliente;
    private int idSupermercado;
    private int idArticulo;
    
    private double PrecioPublicado;
    private double PrecioReal;
    
    private String Comentarios;

    public int getIdReporte() {
        return idReporte;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdSupermercado() {
        return idSupermercado;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public double getPrecioPublicado() {
        return PrecioPublicado;
    }

    public double getPrecioReal() {
        return PrecioReal;
    }

    public String getComentarios() {
        return Comentarios;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdSupermercado(int idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public void setPrecioPublicado(double PrecioPublicado) {
        this.PrecioPublicado = PrecioPublicado;
    }

    public void setPrecioReal(double PrecioReal) {
        this.PrecioReal = PrecioReal;
    }

    public void setComentarios(String Comentarios) {
        this.Comentarios = Comentarios;
    }
    
    
    
}
