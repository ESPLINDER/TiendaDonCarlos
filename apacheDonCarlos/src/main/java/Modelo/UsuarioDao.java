package Modelo;

import Config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SDC
 */
public class UsuarioDao {

    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    int r; //Valor que se retorna al agregar un nuevo registro

    public Usuario Validar(String email, String pass) throws ClassNotFoundException {
        Usuario obj_usu = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE emaUsuario = ? AND passUsuario = ?;";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                obj_usu.setIdUsuario(rs.getInt("idUsuario"));
                obj_usu.setNomUsuario(rs.getString("nomUsuario"));
                obj_usu.setApeUsuario(rs.getString("apeUsuario"));
                obj_usu.setEmaUsuario(rs.getString("emaUsuario"));
                obj_usu.setPassUsuario(rs.getString("passUsuario"));
                obj_usu.setRolUsuario(rs.getString("rolUsuario"));
            }
        } catch (SQLException e) {
            System.out.println("Correo o contraseña invalidos");
        }
        return obj_usu;
    }

    public List<Usuario> listar() {
        String sql = "SELECT idUsuario, nomUsuario, apeUsuario, emaUsuario, rolUsuario FROM usuarios";
        List<Usuario> lista = new ArrayList<>();
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt(1));
                usu.setNomUsuario(rs.getString(2));
                usu.setApeUsuario(rs.getString(3));
                usu.setEmaUsuario(rs.getString(4));
                usu.setRolUsuario(rs.getString(5));
                lista.add(usu);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al generar lista de usuarios");
        }
        return lista;
    }

    public Usuario ListarId(int id) {
        Usuario usu = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                usu.setIdUsuario(rs.getInt("idUsuario"));
                usu.setNomUsuario(rs.getString("nomUsuario"));
                usu.setApeUsuario(rs.getString("apeUsuario"));
                usu.setEmaUsuario(rs.getString("emaUsuario"));
                usu.setPassUsuario(rs.getString("passUsuario"));
                usu.setRolUsuario(rs.getString("rolUsuario"));
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar el usuario: " + e.getMessage());
        }
        return usu;
    }

    public int Agregar(Usuario usuario) {
        String sql = "INSERT INTO usuarios(idUsuario, nomUsuario, apeUsuario, emaUsuario, passUsuario, rolUsuario) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getNomUsuario());
            ps.setString(3, usuario.getApeUsuario());
            ps.setString(4, usuario.getEmaUsuario());
            ps.setString(5, usuario.getPassUsuario());
            ps.setString(6, usuario.getRolUsuario());
            r = ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al agregar usuario: " + e.getMessage());
        }
        return r;
    }

    public int Actualizar(Usuario usu) {
        String sql = "UPDATE usuarios SET nomUsuario = ?, apeUsuario = ?, emaUsuario = ?, passUsuario = ?, rolUsuario = ? WHERE idUsuario = ?";
        System.out.println("Actualizando usuario con ID: " + usu.getIdUsuario());
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, usu.getNomUsuario());
            ps.setString(2, usu.getApeUsuario());
            ps.setString(3, usu.getEmaUsuario());
            ps.setString(4, usu.getPassUsuario());
            ps.setString(5, usu.getRolUsuario());
            ps.setInt(6, usu.getIdUsuario());
            r = ps.executeUpdate();
            
            if (r > 0) {
                System.out.println("Usuario actualizado correctamente");
            } else {
                System.out.println("No se pudo actualizar el usuario");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
        return r;
    }
    
    public int Eliminar(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            r = ps.executeUpdate();
            
            if (r > 0) {
                System.out.println("Usuario eliminado correctamente");
            } else {
                System.out.println("No se encontró el usuario con ID: " + idUsuario);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
        return r;
    }
}