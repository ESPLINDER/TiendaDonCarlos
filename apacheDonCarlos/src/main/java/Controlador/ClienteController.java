package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * Controlador para la gestión de clientes
 * @author SDC
 */
@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet {

    ClienteDao cli_dao = new ClienteDao();
    Cliente cli = new Cliente();
    int doc;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("Servlet ClienteController");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu != null && menu.equals("Clientes")) {
            switch (accion) {
                case "Listar":
                    listarClientes(request, response);
                    break;
                case "Agregar":
                    agregarCliente(request, response);
                    break;
                case "Editar":
                    editarCliente(request, response);
                    break;
                case "Actualizar":
                    actualizarCliente(request, response);
                    break;
                case "Eliminar":
                    eliminarCliente(request, response);
                    break;
                case "Consultar":
                    consultarHistorial(request, response);
                    break;
                case "Buscar":
                    buscarCliente(request, response);
                    break;
                default:
                    response.sendRedirect("vistas/admin/IndexAdmin.jsp");
                    break;
            }
        } else {
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador para gestión de clientes";
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> listaClientes = cli_dao.listar();
        request.setAttribute("lista_cli", listaClientes);

        HttpSession session = request.getSession();
        String mensaje = (String) session.getAttribute("mensaje");
        String error = (String) session.getAttribute("error");

        if (mensaje != null) {
            request.setAttribute("mensaje", mensaje);
            session.removeAttribute("mensaje");
        }

        if (error != null) {
            request.setAttribute("error", error);
            session.removeAttribute("error");
        }

        request.getRequestDispatcher("vistas/admin/ListaClientesAdmin.jsp").forward(request, response);
    }

    private void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        try {
            String tipDocumento = request.getParameter("tipDocumento");
            String nomCliente = request.getParameter("nomCliente");
            String apeCliente = request.getParameter("apeCliente");
            String emaCliente = request.getParameter("emaCliente");
            String domCliente = request.getParameter("domCliente");
            String telCliente = request.getParameter("telCliente");
            String catCredito = request.getParameter("catCredito");
            String limCredito = request.getParameter("limCredito");

            cli.setTipoDocumento(tipDocumento);
            cli.setNomCliente(nomCliente);
            cli.setApeCliente(apeCliente);
            cli.setEmaCliente(emaCliente);
            cli.setDomCliente(domCliente);
            cli.setTelCliente(Integer.parseInt(telCliente));
            cli.setCatCredito(catCredito);
            cli.setLimCredito(Integer.parseInt(limCredito));

            int resultado = cli_dao.Agregar(cli);
            if (resultado > 0) {
                session.setAttribute("mensaje", "Cliente agregado exitosamente");
            } else {
                session.setAttribute("error", "Error al agregar el cliente");
            }
        } catch (Exception e) {
            session.setAttribute("error", "Error en el registro: " + e.getMessage());
        }

        response.sendRedirect("ClienteController?menu=Clientes&accion=Listar");
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            doc = Integer.parseInt(request.getParameter("idCliente"));
            Cliente c = cli_dao.ListarId(doc);
            request.setAttribute("mostrar_cliente", c);
        } catch (NumberFormatException e) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Error al cargar los datos del cliente");
        }
        listarClientes(request, response);
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        try {
            String idCliente = request.getParameter("idCliente");
            String tipDocumento = request.getParameter("tipDocumento");
            String nomCliente = request.getParameter("nomCliente");
            String apeCliente = request.getParameter("apeCliente");
            String emaCliente = request.getParameter("emaCliente");
            String domCliente = request.getParameter("domCliente");
            String telCliente = request.getParameter("telCliente");
            String catCredito = request.getParameter("catCredito");
            String limCredito = request.getParameter("limCredito");

            cli.setIdCliente(Integer.parseInt(idCliente));
            cli.setTipoDocumento(tipDocumento);
            cli.setNomCliente(nomCliente);
            cli.setApeCliente(apeCliente);
            cli.setEmaCliente(emaCliente);
            cli.setDomCliente(domCliente);
            cli.setTelCliente(Integer.parseInt(telCliente));
            cli.setCatCredito(catCredito);
            cli.setLimCredito(Integer.parseInt(limCredito));

            int resultado = cli_dao.Actualizar(cli);
            if (resultado > 0) {
                session.setAttribute("mensaje", "Cliente actualizado exitosamente");
            } else {
                session.setAttribute("error", "Error al actualizar el cliente");
            }
        } catch (Exception e) {
            session.setAttribute("error", "Error al actualizar: " + e.getMessage());
        }

        response.sendRedirect("ClienteController?menu=Clientes&accion=Listar");
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        try {
            doc = Integer.parseInt(request.getParameter("idCliente"));
            int resultado = cli_dao.Eliminar(doc);
            if (resultado > 0) {
                session.setAttribute("mensaje", "Cliente eliminado exitosamente");
            } else {
                session.setAttribute("error", "Error al eliminar el cliente");
            }
        } catch (Exception e) {
            session.setAttribute("error", "Error al eliminar: " + e.getMessage());
        }
        response.sendRedirect("ClienteController?menu=Clientes&accion=Listar");
    }

    private void consultarHistorial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            Cliente cliente = cli_dao.ListarId(idCliente);
            List<Object[]> historial = cli_dao.obtenerHistorialCrediticio(idCliente);

            request.setAttribute("cliente", cliente);
            request.setAttribute("historial", historial);
            request.getRequestDispatcher("vistas/admin/HistorialClienteAdmin.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Error al consultar historial: " + e.getMessage());
            response.sendRedirect("ClienteController?menu=Clientes&accion=Listar");
        }
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        try {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            Cliente cliente = cli_dao.BuscarPorDocumento(idCliente);

            if (cliente.getIdCliente() != 0) {
                request.setAttribute("cliente_encontrado", cliente);
                session.setAttribute("mensaje", "Cliente encontrado");
            } else {
                session.setAttribute("error", "Cliente no encontrado");
            }
        } catch (Exception e) {
            session.setAttribute("error", "Error en la búsqueda: " + e.getMessage());
        }
        listarClientes(request, response);
    }
}
