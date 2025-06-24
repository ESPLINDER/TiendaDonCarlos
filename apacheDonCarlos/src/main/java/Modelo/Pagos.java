package Modelo;
/**
 *
 * @author AndresM
 */
public class Pagos {
    private int idPago;
    private int montoPago;
    private String[] tipoPago = {"Parcial", "Total"};
    
    public Pagos(){
    }

    public Pagos(int idPago, int montoPago) {
        this.idPago = idPago;
        this.montoPago = montoPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public int getMontoPago() {
        return montoPago;
    }

    public String[] getTipoPago() {
        return tipoPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public void setMontoPago(int montoPago) {
        this.montoPago = montoPago;
    }

    public void setTipoPago(String[] tipoPago) {
        this.tipoPago = tipoPago;
    }
}
