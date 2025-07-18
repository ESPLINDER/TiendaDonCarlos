package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.Credito;
import Modelo.CreditoDao;
import Modelo.Detalle_Credito;
import Modelo.Detalle_CreditoDao;
import Modelo.ProductoDao;
import Modelo.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 * @author William
 */
@WebServlet(name = "CreditoController", urlPatterns = {"/CreditoController"})
public class CreditoController extends HttpServlet {

    CreditoDao cre_dao = new CreditoDao();
    Credito credito = new Credito();
    ClienteDao cli_dao = new ClienteDao();
    ProductoDao pro_dao = new ProductoDao();
    Usuario usuario = new Usuario();
    Detalle_Credito detCre = new Detalle_Credito();
    Detalle_CreditoDao detCreDao = new Detalle_CreditoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "Editar":
                this.Editar(request, response);
                break;
            case "Actualizar":
                this.Actualizar(request, response);
                break;
            case "Eliminar":
                this.Delete(request, response);
                break;
            case "Listar":
                    List lista_cre = cre_dao.listar();
                    request.setAttribute("lista_cre", lista_cre);
                    request.getRequestDispatcher("vistas/admin/creditos.jsp").forward(request, response);
                break;
            case "PrepararCredito":
                this.PrepararCredito(request, response);
                break;
            case "guardar":
                this.Guardar(request, response);
                break;
        }
    }
    
    protected void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idCredito = request.getParameter("idCredito");
            detCreDao.Delete(idCredito);
            cre_dao.Delete(idCredito);

            request.getRequestDispatcher("CreditoController?accion=Listar").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println("Error al eliminar credito");
        }
    }
    
    protected void Editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String idCredito = request.getParameter("idCredito");
        List listaClientes = cli_dao.listar();
        List listaProductos = pro_dao.listar();
        credito = cre_dao.BuscarId(idCredito);
        
        List listaDetalleCredito = detCreDao.listar(idCredito);
        
        session.setAttribute("credito", credito);
        session.setAttribute("listaDetalleCredito", listaDetalleCredito);
        session.setAttribute("listaClientes", listaClientes);
        session.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("vistas/admin/editarCredito.jsp").forward(request, response);
    }
    
    protected void Actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Credito credito = (Credito) session.getAttribute("credito");
        
        String MontoCredito = request.getParameter("montoCredito");
        int montoCredito = Integer.parseInt(MontoCredito);
        
        String plazoCredito = request.getParameter("plazo");
        int plazo = Integer.parseInt(plazoCredito);
        LocalDate vencimiento = credito.getEmiCredito().plusDays(plazo);

        String fk_idCliente = request.getParameter("fk_idCliente");

        credito.setFk_idCliente(Integer.parseInt(fk_idCliente));
        credito.setMontoCredito(montoCredito);
        credito.setVenCredito(vencimiento);

        cre_dao.Actualizar(credito);

        String[] idsProducto = request.getParameterValues("fk_idProducto");
        String[] cantidades = request.getParameterValues("cantidad");
        String[] totales = request.getParameterValues("totalPrecio");
        String[] IdDetalleCredito = request.getParameterValues("idDetalleCredito");

        for (int i = 0; i < idsProducto.length; i++) {
            int idProd = Integer.parseInt(idsProducto[i]);
            int cantidad = Integer.parseInt(cantidades[i]);
            int total = Integer.parseInt(totales[i]);
            int idDetalleCredito = (IdDetalleCredito[i] != null && !IdDetalleCredito[i].isEmpty()) ? Integer.parseInt(IdDetalleCredito[i]) : 0;
            
            detCre.setFk_idProducto(idProd);
            detCre.setFk_idCredito(credito.getIdCredito());
            detCre.setCantidad(cantidad);
            detCre.setTotalPrecio(total);
            if (idDetalleCredito == 0) {
                detCreDao.Guardar(detCre);
            } else {
                detCre.setIdDetCredito(idDetalleCredito);
                detCreDao.Actualizar(detCre);
            }
        }
        request.getRequestDispatcher("CreditoController?accion=Listar").forward(request, response);
    }

    protected void PrepararCredito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List listaClientes = cli_dao.listar();
        List listaProductos = pro_dao.listar();
        String ultimoIdCredito = cre_dao.idUltimoCredito();

        // Suponiendo que el formato es siempre "FC" seguido de 5 dígitos
        String prefijo = ultimoIdCredito.substring(0, 2); // "FC"
        String numeroStr = ultimoIdCredito.substring(2); // "00015"

        int numero = Integer.parseInt(numeroStr); // 15
        numero++; // 16

        // Formatear el nuevo número con ceros a la izquierda
        String nuevoNumeroStr = String.format("%05d", numero); // "00016"

        session.setAttribute("idFactura", prefijo + nuevoNumeroStr);
        session.setAttribute("listaClientes", listaClientes);
        session.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("vistas/admin/formCredito.jsp").forward(request, response);
    }

    protected void Guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String idCredito = (String) session.getAttribute("idFactura");
        String MontoCredito = request.getParameter("montoCredito");
        int montoCredito = Integer.parseInt(MontoCredito);
        String plazoCredito = request.getParameter("plazo");
        int plazo = Integer.parseInt(plazoCredito);
        LocalDate vencimiento = LocalDate.now().plusDays(plazo);

        String fk_idCliente = request.getParameter("fk_idCliente");
        usuario = (Usuario) session.getAttribute("usuario");

        credito.setFk_idCliente(Integer.parseInt(fk_idCliente));
        credito.setFk_idUsuario(usuario.getIdUsuario());
        credito.setMontoCredito(montoCredito);
        credito.setEmiCredito(LocalDate.now());
        credito.setVenCredito(vencimiento);
        credito.setIdCredito(idCredito);

        cre_dao.Guardar(credito);

        String[] idsProducto = request.getParameterValues("fk_idProducto");
        String[] cantidades = request.getParameterValues("cantidad");
        String[] totales = request.getParameterValues("totalPrecio");

        for (int i = 0; i < idsProducto.length; i++) {
            int idProd = Integer.parseInt(idsProducto[i]);
            int cantidad = Integer.parseInt(cantidades[i]);
            int total = Integer.parseInt(totales[i]);
            
            detCre.setFk_idProducto(idProd);
            detCre.setFk_idCredito(idCredito);
            detCre.setCantidad(cantidad);
            detCre.setTotalPrecio(total);
            
            detCreDao.Guardar(detCre);
        }
        request.getRequestDispatcher("CreditoController?accion=Listar").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
