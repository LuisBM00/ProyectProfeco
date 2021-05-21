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
public class Multa {
    private int idMulta;
    private int idSupermercado; 
    private String TipoSancion;
    private int NumInconsistencias;
   

    public Multa() {
    }

    public Multa(int idMulta, int idSupermercado, int NumInconsistencias, String TipoSancion) {
        this.idMulta = idMulta;
        this.idSupermercado = idSupermercado;
        this.NumInconsistencias = NumInconsistencias;
        this.TipoSancion = TipoSancion;
        
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

    
    
}
