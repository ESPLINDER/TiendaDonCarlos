package Modelo;
/**
 * @author SantiagoDC
 */
public class Cliente {
    private int idCliente;
    private String tipoDocumento;
    private String nomCliente;
    private String apeCliente;
    private String emaCliente;
    private String domCliente;
    private int telCliente;
    private String[] catCredito = {"A", "B", "C"};
    private int limCredito;

    public Cliente() {
    }

    public Cliente(int idCliente, String tipoDocumento, String nomCliente, String apeCliente, String emaCliente, String domCliente, int telCliente, String catCredito, int limCredito) {
        this.idCliente = idCliente;
        this.tipoDocumento = tipoDocumento;
        this.nomCliente = nomCliente;
        this.apeCliente = apeCliente;
        this.emaCliente = emaCliente;
        this.domCliente = domCliente;
        this.telCliente = telCliente;
        this.catCredito = new String[] {catCredito};
        this.limCredito = limCredito;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public String getApeCliente() {
        return apeCliente;
    }

    public String getEmaCliente() {
        return emaCliente;
    }

    public String getDomCliente() {
        return domCliente;
    }

    public int getTelCliente() {
        return telCliente;
    }

    public String[] getCatCredito() {
        return catCredito;
    }

    public int getLimCredito() {
        return limCredito;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public void setApeCliente(String apeCliente) {
        this.apeCliente = apeCliente;
    }

    public void setEmaCliente(String emaCliente) {
        this.emaCliente = emaCliente;
    }

    public void setDomCliente(String domCliente) {
        this.domCliente = domCliente;
    }

    public void setTelCliente(int telCliente) {
        this.telCliente = telCliente;
    }

    public void setCatCredito(String[] catCredito) {
        this.catCredito = catCredito;
    }

    public void setLimCredito(int limCredito) {
        this.limCredito = limCredito;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", tipoDocumento=" + tipoDocumento + ", nomCliente=" + nomCliente + ", apeCliente=" + apeCliente + ", emaCliente=" + emaCliente + ", domCliente=" + domCliente + ", telCliente=" + telCliente + ", catCredito=" + catCredito + ", limCredito=" + limCredito + '}';
    }
}