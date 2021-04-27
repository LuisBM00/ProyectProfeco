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
public class Cliente {
    
    
    private String idCliente;
    private String NombreCliente;
    private String Correo;
    private boolean Sexo;
    private byte Edad;
    private String idClienteAnterior;
    
    public Cliente() {
    }

    public Cliente(String idCliente, String NombreCliente, String Correo, boolean Sexo, byte Edad) {
        this.idCliente = idCliente;
        this.NombreCliente = NombreCliente;
        this.Correo = Correo;
        this.Sexo = Sexo;
        this.Edad = Edad;
    }

    public String getIdClienteAnterior() {
        return idClienteAnterior;
    }

    public void setIdClienteAnterior(String idClienteAnterior) {
        this.idClienteAnterior = idClienteAnterior;
    }

    
    public String getIdCliente() {
        return idCliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public String getCorreo() {
        return Correo;
    }

    public boolean getSexo() {
        return Sexo;
    }

    public byte getEdad() {
        return Edad;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public void setSexo(boolean Sexo) {
        this.Sexo = Sexo;
    }

    public void setEdad(byte Edad) {
        this.Edad = Edad;
    }
    
    
    
}
