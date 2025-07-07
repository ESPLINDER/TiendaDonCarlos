package Controlador;

import Modelo.Credito;
import Modelo.CreditoDao;
import Modelo.Pagos;
import Modelo.PagosDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Rojas
 */
public class CalcularMontoRestante extends HttpServlet {

    PagosDao pagoDao = new PagosDao();
    CreditoDao creditoDao = new CreditoDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idCredito = request.getParameter("idCredito");

        System.out.println("el id de credito es:" + idCredito);
        // Suponiendo que tienes estos métodos:
        int montoTotal = creditoDao.obtenerMontoCredito(idCredito); // del crédito
        int pagosRealizados = pagoDao.obtenerSumaPagos(idCredito);  // suma de pagos

        int restante = montoTotal - pagosRealizados;

        System.out.println("Monto total: " + montoTotal);
        System.out.println("Pagos realizados: " + pagosRealizados);
        System.out.println("Restante: " + restante);

        response.setContentType("application/json");
        response.getWriter().write("{\"restante\": " + restante + "}");
    }
}
