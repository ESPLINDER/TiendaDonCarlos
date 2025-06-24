package Modelo;
/**
 * @author william el pro
 */
public class Usuario {
    private int idUsuario;
    private String emaUsuario;
    private String passUsuario;
    private String nomUsuario;
    private String apeUsuario;
    private String rolUsuario;

    public Usuario() {
    }
    public Usuario(int idUsuario, String emaUsuario, String passUsuario, String nomUsuario, String apeUsuario) {
        this.idUsuario = idUsuario;
        this.emaUsuario = emaUsuario;
        this.passUsuario = passUsuario;
        this.nomUsuario = nomUsuario;
        this.apeUsuario = apeUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getEmaUsuario() {
        return emaUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public String getApeUsuario() {
        return apeUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setEmaUsuario(String emaUsuario) {
        this.emaUsuario = emaUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public void setApeUsuario(String apeUsuario) {
        this.apeUsuario = apeUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", emaUsuario=" + emaUsuario + ", passUsuario=" + passUsuario + ", nomUsuario=" + nomUsuario + ", apeUsuario=" + apeUsuario + ", rolUsuario=" + rolUsuario + '}';
    }
}
