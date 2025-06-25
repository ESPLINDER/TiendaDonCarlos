package Controlador;

/**
 * @author William
 */
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class FiltroSesion implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI();

        // Rutas permitidas sin sesión
        boolean esLogin = path.endsWith("index.jsp") || path.endsWith("ValidarUsuario");
        boolean esRecursoEstatico = path.contains("/Estilos") || path.contains("/js") || path.contains("/recursos");

        HttpSession sesion = req.getSession(false);
        boolean logueado = (sesion != null && sesion.getAttribute("usuario") != null);

        if (logueado) {
            // Control por rol
            Modelo.Usuario usuario = (Modelo.Usuario) sesion.getAttribute("usuario");

            if (path.contains("/vistas/admin/") && !usuario.getRolUsuario().equals("Administrador")) {
                res.sendRedirect(req.getContextPath() + "/accesoDenegado.jsp");
                return;
            }

            // Cabeceras para evitar caché
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            res.setHeader("Pragma", "no-cache"); // HTTP 1.0
            res.setDateHeader("Expires", 0); // Proxies

            chain.doFilter(request, response);

        } else if (esLogin || esRecursoEstatico) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
