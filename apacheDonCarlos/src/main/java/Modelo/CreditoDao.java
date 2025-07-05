package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}