package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase DAO para la gestión de clientes
 * @author SDC
 */
public class ClienteDao {
    
    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    // Listar todos los clientes
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY idCliente";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setTipoDocumento(rs.getString("tipDocumento"));
                cliente.setNomCliente(rs.getString("nomCliente"));
                cliente.setApeCliente(rs.getString("ApeCliente"));
                cliente.setEmaCliente(rs.getString("emaCliente"));
                cliente.setDomCliente(rs.getString("domCliente"));
                cliente.setTelCliente(rs.getInt("telCliente"));
                cliente.setCatCredito(rs.getString("catCredito"));
                cliente.setLimCredito(rs.getInt("limCredito"));
                lista.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }
    
    // Listar cliente por ID
    public Cliente ListarId(int id) {
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM clientes WHERE idCliente = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setTipoDocumento(rs.getString("tipDocumento"));
                cliente.setNomCliente(rs.getString("nomCliente"));
                cliente.setApeCliente(rs.getString("ApeCliente"));
                cliente.setEmaCliente(rs.getString("emaCliente"));
                cliente.setDomCliente(rs.getString("domCliente"));
                cliente.setTelCliente(rs.getInt("telCliente"));
                cliente.setCatCredito(rs.getString("catCredito"));
                cliente.setLimCredito(rs.getInt("limCredito"));
            }
        } catch (Exception e) {
            System.out.println("Error al listar cliente por ID: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return cliente;
    }
    
    // Agregar nuevo cliente
    public int Agregar(Cliente cliente) {
        int resultado = 0;
        String sql = "INSERT INTO clientes (idCliente, tipDocumento, nomCliente, ApeCliente, emaCliente, domCliente, telCliente, catCredito, limCredito) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getTipoDocumento());
            ps.setString(3, cliente.getNomCliente());
            ps.setString(4, cliente.getApeCliente());
            ps.setString(5, cliente.getEmaCliente());
            ps.setString(6, cliente.getDomCliente());
            ps.setInt(7, cliente.getTelCliente());
            ps.setString(8, cliente.getCatCredito());
            ps.setInt(9, cliente.getLimCredito());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return resultado;
    }
    
    // Actualizar cliente
    public int Actualizar(Cliente cliente) {
        int resultado = 0;
        String sql = "UPDATE clientes SET tipDocumento=?, nomCliente=?, ApeCliente=?, emaCliente=?, domCliente=?, telCliente=?, catCredito=?, limCredito=? WHERE idCliente=?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getTipoDocumento());
            ps.setString(2, cliente.getNomCliente());
            ps.setString(3, cliente.getApeCliente());
            ps.setString(4, cliente.getEmaCliente());
            ps.setString(5, cliente.getDomCliente());
            ps.setInt(6, cliente.getTelCliente());
            ps.setString(7, cliente.getCatCredito());
            ps.setInt(8, cliente.getLimCredito());
            ps.setInt(9, cliente.getIdCliente());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return resultado;
    }
    
    // Eliminar cliente
    public int Eliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM clientes WHERE idCliente = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return resultado;
    }
    
    // Buscar cliente por documento
    public Cliente BuscarPorDocumento(int idCliente) {
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM clientes WHERE idCliente = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setTipoDocumento(rs.getString("tipDocumento"));
                cliente.setNomCliente(rs.getString("nomCliente"));
                cliente.setApeCliente(rs.getString("ApeCliente"));
                cliente.setEmaCliente(rs.getString("emaCliente"));
                cliente.setDomCliente(rs.getString("domCliente"));
                cliente.setTelCliente(rs.getInt("telCliente"));
                cliente.setCatCredito(rs.getString("catCredito"));
                cliente.setLimCredito(rs.getInt("limCredito"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por documento: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return cliente;
    }
    
    // Obtener historial crediticio (créditos del cliente)
    public List<Object[]> obtenerHistorialCrediticio(int idCliente) {
        List<Object[]> historial = new ArrayList<>();
        String sql = "SELECT c.idCredito, c.montoCredito, c.emiCredito, c.venCredito, " +
                     "COALESCE(SUM(p.montoPago), 0) as totalPagado, " +
                     "(c.montoCredito - COALESCE(SUM(p.montoPago), 0)) as saldoPendiente " +
                     "FROM credito c " +
                     "LEFT JOIN pagos p ON c.idCredito = p.fk_idCredito " +
                     "WHERE c.fk_idCliente = ? " +
                     "GROUP BY c.idCredito " +
                     "ORDER BY c.emiCredito DESC";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getString("idCredito");
                fila[1] = rs.getInt("montoCredito");
                fila[2] = rs.getDate("emiCredito");
                fila[3] = rs.getDate("venCredito");
                fila[4] = rs.getInt("totalPagado");
                fila[5] = rs.getInt("saldoPendiente");
                historial.add(fila);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener historial crediticio: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return historial;
    }
    
    // Validar si existe un cliente con el ID dado
    public boolean existeCliente(int idCliente) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM clientes WHERE idCliente = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al validar existencia del cliente: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return existe;
    }
    
    // Cerrar conexiones para evitar memory leaks
    private void cerrarConexiones() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexiones: " + e.getMessage());
        }
    }
}