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
    private String pagoCredito; //Sin pagar | Pago parcial | Pagado
    
    private String cliente;
    private String usuario;

    public Credito() {
    }

    public Credito(String idCredito, int fk_idCliente, int fk_idUsuario, int montoCredito, LocalDate emiCredito, LocalDate venCredito, String pagoCredito, String cliente, String usuario) {
        this.idCredito = idCredito;
        this.fk_idCliente = fk_idCliente;
        this.fk_idUsuario = fk_idUsuario;
        this.montoCredito = montoCredito;
        this.emiCredito = emiCredito;
        this.venCredito = venCredito;
        this.pagoCredito = pagoCredito;
        this.cliente = cliente;
        this.usuario = usuario;
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

    public String getPagoCredito() {
        return pagoCredito;
    }

    public String getCliente() {
        return cliente;
    }

    public String getUsuario() {
        return usuario;
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

    public void setPagoCredito(String pagoCredito) {
        this.pagoCredito = pagoCredito;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Credito{" + "idCredito=" + idCredito + ", fk_idCliente=" + fk_idCliente + ", fk_idUsuario=" + fk_idUsuario + ", montoCredito=" + montoCredito + ", emiCredito=" + emiCredito + ", venCredito=" + venCredito + ", pagoCredito=" + pagoCredito + ", cliente=" + cliente + ", usuario=" + usuario + '}';
    }
}
