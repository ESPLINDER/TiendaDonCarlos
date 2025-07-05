package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author William
 */
public class ProductoDao {
    
    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    // Listar todos los clientes
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNomProducto(rs.getString("nomProducto"));
                producto.setValProducto(rs.getInt("valProducto"));
                lista.add(producto);
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }
}
