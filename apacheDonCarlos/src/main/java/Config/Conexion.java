package Config;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * @author William
 */
public class Conexion {

    Connection conn;
    String url = "jdbc:mysql://localhost:3306/el_vecino_amigo";
    String user = "root";
    String pass = "";

    public Connection Conexion() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion Exitosa");
        } catch (Exception e) {
            System.out.println("Error en conexion");
        }
        return conn;
    }
}