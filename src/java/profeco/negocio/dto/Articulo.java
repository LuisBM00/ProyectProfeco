
package profeco.negocio.dto;


public class Articulo {
    
    private int idArticulo;
    private String NombreArticulo;
    private String Marca;
    private String TipoArticulo;

    public Articulo(int idArticulo, String NombreArticulo, String Marca, String TipoArticulo) {
        this.idArticulo = idArticulo;
        this.NombreArticulo = NombreArticulo;
        this.Marca = Marca;
        this.TipoArticulo = TipoArticulo;
    }

    public Articulo() {
    }
    
    

    public int getIdArticulo() {
        return idArticulo;
    }

    public String getNombreArticulo() {
        return NombreArticulo;
    }

    public String getMarca() {
        return Marca;
    }

    public String getTipoArticulo() {
        return TipoArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public void setNombreArticulo(String NombreArticulo) {
        this.NombreArticulo = NombreArticulo;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public void setTipoArticulo(String TipoArticulo) {
        this.TipoArticulo = TipoArticulo;
    }
    
    
    
}
