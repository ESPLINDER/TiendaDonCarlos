package Modelo;

import java.time.LocalDate;

/**
 *
 * @author AndresM
 */
public class Pagos {

    int idPago;
    int fkIdCredito;
    int montoPago;
    String tipoPago;
    LocalDate fechaPago;

    public Pagos() {
    }

    public Pagos(int idPago, int fkIdCredito, int montoPago, String tipoPago, LocalDate fechaPago) {
        this.idPago = idPago;
        this.fkIdCredito = fkIdCredito;
        this.montoPago = montoPago;
        this.tipoPago = tipoPago;
        this.fechaPago = fechaPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public int getFkIdCredito() {
        return fkIdCredito;
    }

    public int getMontoPago() {
        return montoPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public void setFkIdCredito(int fkIdCredito) {
        this.fkIdCredito = fkIdCredito;
    }

    public void setMontoPago(int montoPago) {
        this.montoPago = montoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Pagos{" + "idPago=" + idPago + ", fkIdCredito=" + fkIdCredito + ", montoPago=" + montoPago + ", tipoPago=" + tipoPago + ", fechaPago=" + fechaPago + '}';
    }
}
