package Modelo;

/**
 * @author adminsena
 */
public class Producto {
    private int idProducto;
    private String nomProducto;
    private int valProducto;

    public Producto() {
    }

    public Producto(int idProducto, String nomProducto, int valProducto) {
        this.idProducto = idProducto;
        this.nomProducto = nomProducto;
        this.valProducto = valProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public int getValProducto() {
        return valProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public void setValProducto(int valProducto) {
        this.valProducto = valProducto;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nomProducto=" + nomProducto + ", valProducto=" + valProducto + '}';
    }
}
