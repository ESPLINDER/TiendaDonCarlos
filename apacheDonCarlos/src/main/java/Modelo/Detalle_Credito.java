package Modelo;

/**
 * @author William
 */
public class Detalle_Credito {
    private int idDetCredito;
    private int fk_idProducto;
    private String fk_idCredito;
    private int cantidad;
    private int totalPrecio;
    
    private String nomProducto;

    public Detalle_Credito() {
    }

    public Detalle_Credito(int idDetCredito, int fk_idProducto, String fk_idCredito, int cantidad, int totalPrecio, String nomProducto) {
        this.idDetCredito = idDetCredito;
        this.fk_idProducto = fk_idProducto;
        this.fk_idCredito = fk_idCredito;
        this.cantidad = cantidad;
        this.totalPrecio = totalPrecio;
        this.nomProducto = nomProducto;
    }

    public int getIdDetCredito() {
        return idDetCredito;
    }

    public int getFk_idProducto() {
        return fk_idProducto;
    }

    public String getFk_idCredito() {
        return fk_idCredito;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getTotalPrecio() {
        return totalPrecio;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setIdDetCredito(int idDetCredito) {
        this.idDetCredito = idDetCredito;
    }

    public void setFk_idProducto(int fk_idProducto) {
        this.fk_idProducto = fk_idProducto;
    }

    public void setFk_idCredito(String fk_idCredito) {
        this.fk_idCredito = fk_idCredito;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setTotalPrecio(int totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    @Override
    public String toString() {
        return "Detalle_Credito{" + "idDetCredito=" + idDetCredito + ", fk_idProducto=" + fk_idProducto + ", fk_idCredito=" + fk_idCredito + ", cantidad=" + cantidad + ", totalPrecio=" + totalPrecio + ", nomProducto=" + nomProducto + '}';
    }
}
