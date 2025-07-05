package Modelo;

import java.time.LocalDate;

/**
 * @author William
 */
public class Credito {
    private String idCredito;
    private int fk_idCliente;
    private int fk_idUsuario; // El que crea la factura
    private int montoCredito;
    private LocalDate emiCredito;
    private LocalDate venCredito;

    public Credito() {
    }

    public Credito(String idCredito, int fk_idCliente, int fk_idUsuario, int montoCredito, LocalDate emiCredito, LocalDate venCredito) {
        this.idCredito = idCredito;
        this.fk_idCliente = fk_idCliente;
        this.fk_idUsuario = fk_idUsuario;
        this.montoCredito = montoCredito;
        this.emiCredito = emiCredito;
        this.venCredito = venCredito;
    }

    public String getIdCredito() {
        return idCredito;
    }

    public int getFk_idCliente() {
        return fk_idCliente;
    }

    public int getFk_idUsuario() {
        return fk_idUsuario;
    }

    public int getMontoCredito() {
        return montoCredito;
    }

    public LocalDate getEmiCredito() {
        return emiCredito;
    }

    public LocalDate getVenCredito() {
        return venCredito;
    }

    public void setIdCredito(String idCredito) {
        this.idCredito = idCredito;
    }

    public void setFk_idCliente(int fk_idCliente) {
        this.fk_idCliente = fk_idCliente;
    }

    public void setFk_idUsuario(int fk_idUsuario) {
        this.fk_idUsuario = fk_idUsuario;
    }

    public void setMontoCredito(int montoCredito) {
        this.montoCredito = montoCredito;
    }

    public void setEmiCredito(LocalDate emiCredito) {
        this.emiCredito = emiCredito;
    }

    public void setVenCredito(LocalDate venCredito) {
        this.venCredito = venCredito;
    }

    @Override
    public String toString() {
        return "Credito{" + "idCredito=" + idCredito + ", fk_idCliente=" + fk_idCliente + ", fk_idUsuario=" + fk_idUsuario + ", montoCredito=" + montoCredito + ", emiCredito=" + emiCredito + ", venCredito=" + venCredito + '}';
    }
}
