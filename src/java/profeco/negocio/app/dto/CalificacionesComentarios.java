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
public class CalificacionesComentarios {
    
    private int idCalificacion;
    private int idCliente;
    private int idSupermercado;
    private float Calificacion;
    private String Comentario;

    public CalificacionesComentarios() {
    }

    public CalificacionesComentarios(int idCalificacion, int idCliente, int idSupermercado, float Calificacion, String Comentario) {
        this.idCalificacion = idCalificacion;
        this.idCliente = idCliente;
        this.idSupermercado = idSupermercado;
        this.Calificacion = Calificacion;
        this.Comentario = Comentario;
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdSupermercado() {
        return idSupermercado;
    }

    public float getCalificacion() {
        return Calificacion;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdSupermercado(int idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    public void setCalificacion(float Calificacion) {
        this.Calificacion = Calificacion;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }
    
    
    
}
