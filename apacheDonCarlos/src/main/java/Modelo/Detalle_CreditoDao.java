package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author William
 */
public class Detalle_CreditoDao {
    
    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public void Guardar(Detalle_Credito detCredito){
        String sql = "INSERT INTO detalle_credito(fk_idProducto, fk_idCredito, cantidad, totalPrecio) VALUES (?, ?, ?, ?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, detCredito.getFk_idProducto());
            ps.setString(2, detCredito.getFk_idCredito());
            ps.setInt(3, detCredito.getCantidad());
            ps.setInt(4, detCredito.getTotalPrecio());
            
            System.out.println("Consulta guardar detalle credito: "+ps);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al guardar detalle credito: " + e.getMessage());
        }
    }
}
