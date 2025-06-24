/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author adminsena
 */
@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {

    UsuarioDao usu_dao = new UsuarioDao();
    Usuario usu = new Usuario();
    int doc;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Administrador")) {
            request.getRequestDispatcher("IndexAdmin.jsp");

        }
        if (menu.equals("Usuarios")) {
            switch (accion) {
                case "Listar":
                    List listaUsuarios = usu_dao.listar();
                    request.setAttribute("lista_usu", listaUsuarios);
                    request.getRequestDispatcher("vistas/ListaUsuariosAdmin.jsp").forward(request, response);
                    break;
                case "Agregar":
                    this.AgregarUsuario(request, response);
                    break;
                case "Editar":
                    this.EditarUsuario(request, response);
                    break;
                case "Actualizar":
                    this.ActualizarUsuario(request, response);
                    break;
                case "Eliminar":
                    this.EliminarUsuario(request, response);
                    break;
            }
        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    //Nuestros metodos
    protected void AgregarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idUsuario = request.getParameter("idUsuario"); //el request solo sirve con Strings
            String nomUsuario = request.getParameter("nomUsuario");
            String apeUsuario = request.getParameter("apeUsuario");
            String emaUsuario = request.getParameter("emaUsuario");
            String passUsuario = request.getParameter("passUsuario");
            String rolUsuario = request.getParameter("rolUsuario");

            //le damos los valores del formulario al objeto usu
            usu.setIdUsuario(Integer.parseInt(idUsuario));
            usu.setNomUsuario(nomUsuario);
            usu.setApeUsuario(apeUsuario);
            usu.setEmaUsuario(emaUsuario);
            usu.setPassUsuario(passUsuario);
            usu.setRolUsuario(rolUsuario);

            usu_dao.Agregar(usu);
            request.getRequestDispatcher("UsuarioController?menu=Usuarios&accion=Listar").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println("Error en el registro");
        }
    }

    protected void EditarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doc = Integer.parseInt(request.getParameter("idUsuario")); //variable para capturar el id de del usuario a editar
            Usuario u = usu_dao.ListarId(doc);
            request.setAttribute("mostrar_usuario", u);
            request.getRequestDispatcher("UsuarioController?menu=Usuarios&accion=Listar").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    protected void ActualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idUsuario = request.getParameter("idUsuario"); //el request solo sirve con Strings
            String nomUsuario = request.getParameter("nomUsuario");
            String apeUsuario = request.getParameter("apeUsuario");
            String emaUsuario = request.getParameter("emaUsuario");
            String passUsuario = request.getParameter("passUsuario");
            String rolUsuario = request.getParameter("rolUsuario");

            usu.setIdUsuario(Integer.parseInt(idUsuario));
            usu.setNomUsuario(nomUsuario);
            usu.setApeUsuario(apeUsuario);
            usu.setEmaUsuario(emaUsuario);
            usu.setPassUsuario(passUsuario);
            usu.setRolUsuario(rolUsuario);
            usu_dao.Actualizar(usu);
            request.getRequestDispatcher("UsuarioController?menu=Usuarios&accion=Listar").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println("Error al actualizar");
        }
    }
    
    protected void EliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        doc = Integer.parseInt(request.getParameter("idUsuario"));
        usu_dao.Eliminar(doc);
        request.getRequestDispatcher("UsuarioController?menu=Usuarios&accion=Listar").forward(request, response);
        }catch(ServletException | IOException | NumberFormatException e){
            System.out.println("Error al eliminar");
        }
    }
}
