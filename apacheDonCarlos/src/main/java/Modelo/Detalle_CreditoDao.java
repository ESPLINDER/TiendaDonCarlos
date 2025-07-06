package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author William
 */
public class Detalle_CreditoDao {
    
    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public void Delete(String fk_idCredito) {
        String sql = "DELETE FROM detalle_credito WHERE fk_idCredito = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, fk_idCredito);
            r = ps.executeUpdate();
            
            if (r > 0) {
                System.out.println("detalle Credito eliminado correctamente");
            } else {
                System.out.println("No se encontró el detalle credito con ID: " + fk_idCredito);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar detalle credito: " + e.getMessage());
        }
    }
    
    public void Actualizar(Detalle_Credito detCredito){
        String sql = "UPDATE detalle_credito SET fk_idProducto = ?, cantidad = ?, totalPrecio = ? WHERE fk_idCredito = ? AND idDetCredito = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, detCredito.getFk_idProducto());
            ps.setInt(2, detCredito.getCantidad());
            ps.setInt(3, detCredito.getTotalPrecio());
            ps.setString(4, detCredito.getFk_idCredito());
            ps.setInt(5, detCredito.getIdDetCredito());
            r = ps.executeUpdate();
            
            if (r > 0) {
                System.out.println("Detalle credito actualizado correctamente");
            } else {
                System.out.println("No se pudo actualizar el detalle");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }
    
    public List<Detalle_Credito> listar(String idCredito) {
        String sql = "SELECT idDetCredito, fk_idProducto, fk_idCredito, cantidad, totalPrecio, producto.nomProducto"
                + " FROM detalle_credito INNER JOIN producto on detalle_credito.fk_idProducto = producto.idProducto"
                + " WHERE fk_idCredito = ?";
        List<Detalle_Credito> lista = new ArrayList<>();
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idCredito);
            rs = ps.executeQuery();
            while (rs.next()) {
                Detalle_Credito detCre = new Detalle_Credito();
                detCre.setIdDetCredito(rs.getInt(1));
                detCre.setFk_idProducto(rs.getInt(2));
                detCre.setFk_idCredito(rs.getString(3));
                detCre.setCantidad(rs.getInt(4));
                detCre.setTotalPrecio(rs.getInt(5));
                detCre.setNomProducto(rs.getString(6));
                lista.add(detCre);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al generar lista de detalle creditos: " + e.getMessage());
        }
        return lista;
    }
    
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
