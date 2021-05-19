/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profeco.negocio.app.dto;

/**
 *
 * @author Luis Barroso
 */
public class ListaArticulosXSuper {
    
   private int idLista;
   private int idSupermercado;
   private int idArticulo;
   private int Cantidad;
   private double PrecioActual;
   private double PrecioInicial;
   private byte PorcentajeDescuento;

    public ListaArticulosXSuper() {
    }

    public ListaArticulosXSuper(int idLista, int idSupermercado, int idArticulo, int Cantidad, double PrecioActual, double PrecioInicial, byte PorcentajeDescuento) {
        this.idLista = idLista;
        this.idSupermercado = idSupermercado;
        this.idArticulo = idArticulo;
        this.Cantidad = Cantidad;
        this.PrecioActual = PrecioActual;
        this.PrecioInicial = PrecioInicial;
        this.PorcentajeDescuento = PorcentajeDescuento;
    }

    public int getIdLista() {
        return idLista;
    }

    public int getIdSupermercado() {
        return idSupermercado;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public double getPrecioActual() {
        return PrecioActual;
    }

    public double getPrecioInicial() {
        return PrecioInicial;
    }

    public byte getPorcentajeDescuento() {
        return PorcentajeDescuento;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public void setIdSupermercado(int idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public void setPrecioActual(double PrecioActual) {
        this.PrecioActual = PrecioActual;
    }

    public void setPrecioInicial(double PrecioInicial) {
        this.PrecioInicial = PrecioInicial;
    }

    public void setPorcentajeDescuento(byte PorcentajeDescuento) {
        this.PorcentajeDescuento = PorcentajeDescuento;
    }

    
    
   
   
    
    
}
