
package profeco.negocio.dto;


public class Supermercado {
    
    private int idSupermercado;
    private String NombreSupermercado;
    private String Direccion;

    public Supermercado() {
    }

    public Supermercado(int idSupermercado, String NombreSupermercado, String Direccion) {
        this.idSupermercado = idSupermercado;
        this.NombreSupermercado = NombreSupermercado;
        this.Direccion = Direccion;
    }
    
    public int getIdSupermercado() {
        return idSupermercado;
    }

    public String getNombreSupermercado() {
        return NombreSupermercado;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setIdSupermercado(int idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    public void setNombreSupermercado(String NombreSupermercado) {
        this.NombreSupermercado = NombreSupermercado;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
    
    
    
    
    
}
