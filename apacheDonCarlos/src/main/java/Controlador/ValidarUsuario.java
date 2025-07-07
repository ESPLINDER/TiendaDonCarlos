package Controlador;

import Modelo.UsuarioDao;
import Modelo.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author William
 */

@WebServlet("/ValidarUsuario")
public class ValidarUsuario extends HttpServlet {
    
    UsuarioDao u_dao = new UsuarioDao();
    Usuario usu = new Usuario();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion"); //name del boton ingresar/registrar
        if (accion.equalsIgnoreCase("Ingresar")){
            
            //al hacer click en el boton con value ingresar
            String user = request.getParameter("emaUsuario"); //name del formulario campo usuario
            String pass = request.getParameter("passUsuario"); // campo contrasena
            System.out.println("user: "+user+" pass: "+pass);
            try {
                usu = u_dao.Validar(user, pass);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ValidarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (usu.getEmaUsuario() != null && usu.getPassUsuario() != null) {
                
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usu);
                
                request.setAttribute("usuario", usu); //vamos a la pagina donde se autentica el usuario
                if (usu.getRolUsuario().equals("Administrador")) {
                    request.getRequestDispatcher("vistas/admin/ListaUsuariosAdmin.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("vistas/empleado/IndexEmpleado.jsp").forward(request, response);
                }
            } else {
                System.out.println("Contraseña o usuario incorrectos");
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Ingrese Usuario y Contraseña");
            request.getRequestDispatcher("../../index.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
