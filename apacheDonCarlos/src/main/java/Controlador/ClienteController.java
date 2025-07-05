package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClienteController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClienteController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu != null && menu.equals("Clientes")) {
            switch (accion) {
                case "Listar":
                    List listaClientes = cli_dao.listar();
                    request.setAttribute("lista_cli", listaClientes);
                    request.getRequestDispatcher("vistas/admin/ListaClientesAdmin.jsp").forward(request, response);
                    break;
                case "Agregar":
                    this.AgregarCliente(request, response);
                    break;
                case "Editar":
                    this.EditarCliente(request, response);
                    break;
                case "Actualizar":
                    this.ActualizarCliente(request, response);
                    break;
                case "Eliminar":
                    this.EliminarCliente(request, response);
                    break;
                case "Consultar":
                    this.ConsultarHistorial(request, response);
                    break;
                case "Buscar":
                    this.BuscarCliente(request, response);
                    break;
                default:
                    request.getRequestDispatcher("vistas/admin/IndexAdmin.jsp").forward(request, response);
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

    // Métodos para gestión de clientes
    protected void AgregarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            // Validar que no exista el cliente
            if (cli_dao.existeCliente(Integer.parseInt(idCliente))) {
                request.setAttribute("error", "Ya existe un cliente con ese documento");
                request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
                return;
            }

            // Asignar valores al objeto cliente
            cli.setIdCliente(Integer.parseInt(idCliente));
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
                request.setAttribute("mensaje", "Cliente agregado exitosamente");
            } else {
                request.setAttribute("error", "Error al agregar el cliente");
            }
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            request.setAttribute("error", "Error en el registro: " + e.getMessage());
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        }
    }

    protected void EditarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doc = Integer.parseInt(request.getParameter("idCliente"));
            Cliente c = cli_dao.ListarId(doc);
            request.setAttribute("mostrar_cliente", c);
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error al cargar los datos del cliente");
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        }
    }

    protected void ActualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                request.setAttribute("mensaje", "Cliente actualizado exitosamente");
            } else {
                request.setAttribute("error", "Error al actualizar el cliente");
            }
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            request.setAttribute("error", "Error al actualizar: " + e.getMessage());
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        }
    }

    protected void EliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doc = Integer.parseInt(request.getParameter("idCliente"));
            int resultado = cli_dao.Eliminar(doc);
            if (resultado > 0) {
                request.setAttribute("mensaje", "Cliente eliminado exitosamente");
            } else {
                request.setAttribute("error", "Error al eliminar el cliente");
            }
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            request.setAttribute("error", "Error al eliminar: " + e.getMessage());
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        }
    }

    protected void ConsultarHistorial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            Cliente cliente = cli_dao.ListarId(idCliente);
            List<Object[]> historial = cli_dao.obtenerHistorialCrediticio(idCliente);
            
            request.setAttribute("cliente", cliente);
            request.setAttribute("historial", historial);
            request.getRequestDispatcher("vistas/admin/HistorialClienteAdmin.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error al consultar historial: " + e.getMessage());
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        }
    }

    protected void BuscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            Cliente cliente = cli_dao.BuscarPorDocumento(idCliente);
            
            if (cliente.getIdCliente() != 0) {
                request.setAttribute("cliente_encontrado", cliente);
                request.setAttribute("mensaje", "Cliente encontrado");
            } else {
                request.setAttribute("error", "Cliente no encontrado");
            }
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error en la búsqueda: " + e.getMessage());
            request.getRequestDispatcher("ClienteController?menu=Clientes&accion=Listar").forward(request, response);
        }
    }
}