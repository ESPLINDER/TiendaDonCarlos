<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Cliente"%>
<%
    String contextPath = request.getContextPath();
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("lista_cli");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Clientes - Empleado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container my-5">

    <h2>Lista de Clientes</h2>

    <!-- Formulario de búsqueda -->
    <form action="ClienteController" method="GET" class="row g-3 mb-4">
        <input type="hidden" name="menu" value="Clientes"/>
        <input type="hidden" name="accion" value="BuscarEmpleado"/>
        <div class="col-md-4">
            <input type="text" class="form-control" name="filtro" placeholder="Buscar por ID o Nombre" required>
        </div>
        <div class="col-md-2">
            <button type="submit" class="btn btn-primary">Buscar</button>
        </div>
    </form>

    <!-- Tabla de clientes -->
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Tipo Doc</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th>Dirección</th>
                    <th>Teléfono</th>
                    <th>Categoría</th>
                    <th>Límite Crédito</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% if (listaClientes != null) {
                    for (Cliente c : listaClientes) { %>
                    <tr>
                        <td><%= c.getIdCliente() %></td>
                        <td><%= c.getTipoDocumento() %></td>
                        <td><%= c.getNomCliente() %></td>
                        <td><%= c.getApeCliente() %></td>
                        <td><%= c.getEmaCliente() %></td>
                        <td><%= c.getDomCliente() %></td>
                        <td><%= c.getTelCliente() %></td>
                        <td><%= c.getCatCredito() %></td>
                        <td><%= c.getLimCredito() %></td>
                        <td>
                            <a href="ClienteController?menu=Clientes&accion=Editar&idCliente=<%= c.getIdCliente() %>" class="btn btn-sm btn-warning">Editar</a>
                            <a href="#" class="btn btn-sm btn-info" data-bs-toggle="modal" data-bs-target="#historialModal<%= c.getIdCliente() %>">Historial</a>

                            <!-- Modal historial -->
                            <div class="modal fade" id="historialModal<%= c.getIdCliente() %>" tabindex="-1" aria-labelledby="historialLabel<%= c.getIdCliente() %>" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="historialLabel<%= c.getIdCliente() %>">Historial Crediticio</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                                        </div>
                                        <div class="modal-body">
                                            <p>La categoría actual de este cliente es: <strong><%= c.getCatCredito() %></strong></p>
                                            <!-- Aquí podrías expandir a mostrar historial real -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                <% }
                } else { %>
                    <tr><td colspan="10" class="text-center">No hay clientes registrados.</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
