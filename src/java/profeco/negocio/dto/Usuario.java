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
public class Usuario {
    private String idUsuario;
    private int idTipoUsuario;
    private int idCOS;
    private String Nombre;
    private String Correo;
    private String Contrasena;
    private String idUsuarioAnterior;

    public Usuario(String idUsuario, int idTipoUsuario, int idCOS, String Nombre, String Correo, String Contrasena) {
        this.idUsuario = idUsuario;
        this.idTipoUsuario = idTipoUsuario;
        this.idCOS = idCOS;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Contrasena = Contrasena;
    }

    public Usuario() {
    }

    public void setIdUsuarioAnterior(String idUsuarioAnterior) {
        this.idUsuarioAnterior = idUsuarioAnterior;
    }

    public String getIdUsuarioAnterior() {
        return idUsuarioAnterior;
    }
    
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public int getIdCOS() {
        return idCOS;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public void setIdCOS(int idCOS) {
        this.idCOS = idCOS;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }
    
    
}
