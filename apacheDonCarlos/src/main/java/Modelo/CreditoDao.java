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
public class CreditoDao {

    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public void Delete(String idCredito) {
        String sql = "DELETE FROM credito WHERE idCredito = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, idCredito);
            r = ps.executeUpdate();
            
            if (r > 0) {
                System.out.println("Credito eliminado correctamente");
            } else {
                System.out.println("No se encontró el credito con ID: " + idCredito);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar rutina: " + e.getMessage());
        }
    }
    
    public Credito BuscarId(String id) {
        Credito cre = new Credito();
        String sql = "SELECT idCredito, fk_idCliente, fk_idUsuario, montoCredito, emiCredito, venCredito, pagoCredito, clientes.nomCliente, usuarios.nomUsuario"
                + " FROM credito INNER JOIN clientes on credito.fk_idCliente = clientes.idCliente INNER JOIN usuarios on credito.fk_idUsuario = usuarios.idUsuario"
                + " WHERE idCredito = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                cre.setIdCredito(rs.getString(1));
                cre.setFk_idCliente(rs.getInt(2));
                cre.setFk_idUsuario(rs.getInt(3));
                cre.setMontoCredito(rs.getInt(4));
                cre.setEmiCredito(rs.getDate(5).toLocalDate());
                cre.setVenCredito(rs.getDate(6).toLocalDate());
                cre.setPagoCredito(rs.getString(7));
                cre.setCliente(rs.getString(8));
                cre.setUsuario(rs.getString(9));
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar el usuario: " + e.getMessage());
        }
        return cre;
    }
    public void Actualizar(Credito credito){
        String sql = "UPDATE credito SET fk_idCliente = ?, fk_idUsuario = ?, montoCredito = ?, venCredito = ? WHERE idCredito = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, credito.getFk_idCliente());
            ps.setInt(2, credito.getFk_idUsuario());
            ps.setInt(3, credito.getMontoCredito());
            ps.setDate(4, java.sql.Date.valueOf(credito.getVenCredito()));
            ps.setString(5, credito.getIdCredito());
            r = ps.executeUpdate();
            
            if (r > 0) {
                System.out.println("credito actualizado correctamente");
            } else {
                System.out.println("No se pudo actualizar el credito");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }
    
    public List<Credito> listar() {
        String sql = "SELECT idCredito, fk_idCliente, fk_idUsuario, montoCredito, emiCredito, venCredito, pagoCredito, clientes.nomCliente, usuarios.nomUsuario"
                + " FROM credito INNER JOIN clientes on credito.fk_idCliente = clientes.idCliente INNER JOIN usuarios on credito.fk_idUsuario = usuarios.idUsuario";
        List<Credito> lista = new ArrayList<>();
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Credito cre = new Credito();
                cre.setIdCredito(rs.getString(1));
                cre.setFk_idCliente(rs.getInt(2));
                cre.setFk_idUsuario(rs.getInt(3));
                cre.setMontoCredito(rs.getInt(4));
                cre.setEmiCredito(rs.getDate(5).toLocalDate());
                cre.setVenCredito(rs.getDate(6).toLocalDate());
                cre.setPagoCredito(rs.getString(7));
                cre.setCliente(rs.getString(8));
                cre.setUsuario(rs.getString(9));
                lista.add(cre);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al generar lista de creditos: " + e.getMessage());
        }
        return lista;
    }
    
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