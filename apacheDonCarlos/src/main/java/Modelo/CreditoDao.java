package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author William
 */
public class CreditoDao {

    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    // Obtener el último ID de crédito
    public String idUltimoCredito() {
        String ultimoId = "";
        String sql = "SELECT idCredito FROM credito ORDER BY idCredito DESC LIMIT 1";

        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                ultimoId = rs.getString("idCredito");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener último id de crédito: " + e.getMessage());
        }

        return ultimoId;
    }
    
    public void Guardar(Credito credito){
        String sql = "INSERT INTO credito(idCredito, fk_idCliente, fk_idUsuario, montoCredito, emiCredito, venCredito) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);

            ps.setString(1, credito.getIdCredito());
            ps.setInt(2, credito.getFk_idCliente());
            ps.setInt(3, credito.getFk_idUsuario());
            ps.setInt(4, credito.getMontoCredito());
            ps.setDate(5, java.sql.Date.valueOf(credito.getEmiCredito()));
            ps.setDate(6, java.sql.Date.valueOf(credito.getVenCredito()));
            
            System.out.println("Consulta guardar credito: "+ps);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al guardar credito: " + e.getMessage());
        }
    }
}