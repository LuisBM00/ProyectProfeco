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
public class Multa {
     private int idMulta;
    private int idSupermercado;
    private int NumInconsistencias;
    private String TipoSancion;
    private double Sancion;

    public Multa() {
    }

    public Multa(int idMulta, int idSupermercado, int NumInconsistencias, String TipoSancion, double Sancion) {
        this.idMulta = idMulta;
        this.idSupermercado = idSupermercado;
        this.NumInconsistencias = NumInconsistencias;
        this.TipoSancion = TipoSancion;
        this.Sancion = Sancion;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public int getIdSupermercado() {
        return idSupermercado;
    }

    public int getNumInconsistencias() {
        return NumInconsistencias;
    }

    public String getTipoSancion() {
        return TipoSancion;
    }

    public double getSancion() {
        return Sancion;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public void setIdSupermercado(int idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    public void setNumInconsistencias(int NumInconsistencias) {
        this.NumInconsistencias = NumInconsistencias;
    }

    public void setTipoSancion(String TipoSancion) {
        this.TipoSancion = TipoSancion;
    }

    public void setSancion(double Sancion) {
        this.Sancion = Sancion;
    }
    
    
}
