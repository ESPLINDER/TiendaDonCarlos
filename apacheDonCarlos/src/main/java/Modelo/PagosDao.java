package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rojas
 */
public class PagosDao {

    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    int r; //Valor que se retorna al agregar un nuevo registro

    public List<Pagos> listar() {
        String sql = "SELECT * FROM Pagos";
        List<Pagos> lista = new ArrayList<>();

        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pagos pag = new Pagos();
                pag.setIdPago(rs.getInt(1));
                pag.setFkIdCredito(rs.getString(2));
                pag.setMontoPago(rs.getInt(3));
                pag.setTipoPago(rs.getString(4));
                pag.setFechaPago(rs.getObject(5, LocalDate.class));
                lista.add(pag);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al generar lista de Pagos");
        }
        return lista;
    }

    public Pagos listarId(int idPago) {
        Pagos pag = new Pagos();
        String sql = "SELECT * FROM Pagos WHERE idPago = ?";

        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPago);
            rs = ps.executeQuery();

            while (rs.next()) {
                pag.setIdPago(rs.getInt("idPago"));
                pag.setFkIdCredito(rs.getString("fkIdCredito"));
                pag.setMontoPago(rs.getInt("montoPago"));
                pag.setTipoPago(rs.getString("tipoPago"));
                pag.setFechaPago(rs.getObject("fechaPago", LocalDate.class));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al generar lista de Pagos");
        }
        return pag;
    }

    public int Agregar(Pagos pago) {
        String sql = "INSERT INTO pagos(fkIdCredito, montoPago, tipoPago, fechaPago) VALUES (?, ?, ?, ?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);

            ps.setString(1, pago.getFkIdCredito());
            ps.setInt(2, pago.getMontoPago());
            ps.setString(3, pago.getTipoPago());
            LocalDate fecha = pago.getFechaPago();
            ps.setDate(4, java.sql.Date.valueOf(fecha));
            r = ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al agregar Pago: " + e.getMessage());
        }
        return r;
    }

    public int Actualizar(Pagos pago) {
        String sql = "UPDATE pagos SET montoPago = ?, tipoPago = ? WHERE idPago = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, pago.getMontoPago());
            ps.setString(2, pago.getTipoPago());
            ps.setInt(3, pago.getIdPago());
            r = ps.executeUpdate();

            if (r > 0) {
                System.out.println("Pago actualizado correctamente");
            } else {
                System.out.println("No se pudo actualizar el pago");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al actualizar pago: " + e.getMessage());
        }
        return r;
    }

    public int obtenerSumaPagos(String idCredito) {
        int suma = 0;
        String sql = "SELECT SUM(montoPago) AS totalPagado FROM pagos WHERE fkIdCredito = ?";
        try (Connection conn = cn.Conexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idCredito);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                suma = rs.getInt("totalPagado");
                if (rs.wasNull()) {
                    suma = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suma;
    }

}
