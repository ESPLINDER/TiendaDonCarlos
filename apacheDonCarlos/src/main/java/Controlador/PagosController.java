package Controlador;

import Modelo.Pagos;
import Modelo.PagosDao;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Rojas
 */
public class PagosController extends HttpServlet {

    Pagos pago = new Pagos();
    PagosDao pagoDao = new PagosDao();
    int doc;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (menu.equals("Pagos")) {
            switch (accion) {
                case "Listar":
                    List listaPagos = pagoDao.listar();
                    request.setAttribute("lista_Pagos", listaPagos);

                    if ("Administrador".equals(usuario.getRolUsuario())) {
                        request.getRequestDispatcher("vistas/admin/ListaPagosAdmin.jsp").forward(request, response);
                        System.out.println("Lista Pagos Administrador");
                    } else if ("Empleado".equals(usuario.getRolUsuario())) {
                        request.getRequestDispatcher("vistas/empleado/ListaPagosEmpleado.jsp").forward(request, response);
                        System.out.println("Lista Pagos Empleado");
                    } else {
                        System.out.println("Rol desconocido");
                    }

                    break;
                case "ListarId":
                    List listaPagosId = pagoDao.listar();
                    request.setAttribute("lista_Pagos", listaPagosId);

                    if ("Administrador".equals(usuario.getRolUsuario())) {
                        request.getRequestDispatcher("vistas/admin/ListaPagosAdmin.jsp").forward(request, response);
                        System.out.println("Lista Pagos Administrador");
                    } else if ("Empleado".equals(usuario.getRolUsuario())) {
                        request.getRequestDispatcher("vistas/empleado/ListaPagosEmpleado.jsp").forward(request, response);
                        System.out.println("Lista Pagos Empleado");
                    } else {
                        System.out.println("Rol desconocido");
                    }
                    break;
                case "Editar":
                    this.EditarPago(request, response);
                    break;
                case "Actualizar":
                    this.ActualizarPago(request, response);
                    break;
                case "Eliminar":

                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int fkIdCredito = Integer.parseInt(request.getParameter("fkIdCredito"));
            int montoPago = Integer.parseInt(request.getParameter("montoPago"));
            String tipoPago = request.getParameter("tipoPago");
            LocalDate fechaPago = LocalDate.now();

            pago.setFkIdCredito(fkIdCredito);
            pago.setMontoPago(montoPago);
            pago.setTipoPago(tipoPago);
            pago.setFechaPago(fechaPago);
            pagoDao.Agregar(pago);

            System.out.println("Ejecucion de metodo Agregar");
            request.getRequestDispatcher("PagosController?menu=Pagos&accion=Actualizar").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar Pago");
        }
    }

    protected void EditarPago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doc = Integer.parseInt(request.getParameter("idPago")); //variable para capturar el id de del usuario a editar
            Pagos pag = pagoDao.listarId(doc);
            request.setAttribute("mostrar_pago", pag);
            request.getRequestDispatcher("PagosController?menu=Pagos&accion=Listar").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    protected void ActualizarPago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPago = Integer.parseInt(request.getParameter("idPago"));
            int montoPago = Integer.parseInt(request.getParameter("montoPago"));
            String tipoPago = request.getParameter("tipoPago");

            pago.setIdPago(idPago);
            pago.setMontoPago(montoPago);
            pago.setTipoPago(tipoPago);

            int filasActualizadas = pagoDao.Actualizar(pago);
            boolean actualizado = filasActualizadas > 0;

            HttpSession session = request.getSession();

            if (actualizado) {
                session.setAttribute("mensajeExito", "¡Pago actualizado correctamente!");
            } else {
                session.setAttribute("mensajeError", "No se ha podido actualizar ningún pago.");
            }
            request.getRequestDispatcher("PagosController?menu=Pagos&accion=Listar").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println("Error al actualizar Pago");
        }
    }

    @Override
    public String getServletInfo() {
        return "Este es el servlet de pagos";
    }

}
