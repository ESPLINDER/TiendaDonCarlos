package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.CreditoDao;
import Modelo.ProductoDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * @author William
 */
@WebServlet(name = "CreditoController", urlPatterns = {"/CreditoController"})
public class CreditoController extends HttpServlet {

    CreditoDao cre_dao = new CreditoDao();
    ClienteDao cli_dao = new ClienteDao();
    ProductoDao pro_dao = new ProductoDao();
    Cliente cli = new Cliente();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "PrepararCredito":
                this.PrepararCredito(request, response);
                break;
        }
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
        
        session.setAttribute("idFactura", prefijo+nuevoNumeroStr);
        session.setAttribute("listaClientes", listaClientes);
        session.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("vistas/admin/formCredito.jsp").forward(request, response);
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
