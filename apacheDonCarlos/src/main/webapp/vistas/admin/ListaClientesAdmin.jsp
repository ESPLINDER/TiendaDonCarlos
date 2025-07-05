<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Cliente"%>
<%
    String contextPath = request.getContextPath();
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("lista_cli");
    Cliente clienteEditar = (Cliente) request.getAttribute("mostrar_cliente");
    Cliente clienteEncontrado = (Cliente) request.getAttribute("cliente_encontrado");
    if (clienteEditar == null) {
        clienteEditar = new Cliente();
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="<%= contextPath %>/Estilos/contenido.css">
    <link rel="stylesheet" href="<%= contextPath %>/Estilos/navbarEstilo.css">
</head>
<body>

    <%-- Sidebar/Navbar --%>
    <%@ include file="../../plantillas/DashAdminController.jsp" %>

    <div class="container my-5">
        <div class="card">
            <div class="card-header bg-primary text-white text-center">
                <h2>Creación de Usuarios</h2>
            </div>
            <div class="card-body">
                <%-- Mensajes --%>
                <% if (request.getAttribute("mensaje") != null) { %>
                    <div class="alert alert-success"><%= request.getAttribute("mensaje") %></div>
                <% } %>
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                <% } %>

                <%-- Formulario Registro/Actualización --%>
                <h4><%= (clienteEditar.getIdCliente() == 0) ? "Registrar Cliente" : "Actualizar Cliente" %></h4>
                <form action="ClienteController" method="GET" class="row g-3">
                    <input type="hidden" name="menu" value="Clientes"/>
                    <input type="hidden" name="accion" value="<%= (clienteEditar.getIdCliente() == 0) ? "Agregar" : "Actualizar" %>"/>

                    <div class="col-md-4">
                        <label class="form-label">ID Cliente</label>
                        <input type="number" class="form-control" name="idCliente" value="<%= clienteEditar.getIdCliente() %>"
                        <%= (clienteEditar.getIdCliente() != 0) ? "readonly" : "" %> required/>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Tipo Documento</label>
                        <input type="text" class="form-control" name="tipDocumento" value="<%= clienteEditar.getTipoDocumento() %>" required/>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Nombre</label>
                        <input type="text" class="form-control" name="nomCliente" value="<%= clienteEditar.getNomCliente() %>" required/>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Apellido</label>
                        <input type="text" class="form-control" name="apeCliente" value="<%= clienteEditar.getApeCliente() %>" required/>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Email</label>
                        <input type="email" class="form-control" name="emaCliente" value="<%= clienteEditar.getEmaCliente() %>" required/>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Dirección</label>
                        <input type="text" class="form-control" name="domCliente" value="<%= clienteEditar.getDomCliente() %>" required/>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Teléfono</label>
                        <input type="number" class="form-control" name="telCliente" value="<%= clienteEditar.getTelCliente() %>" required/>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Categoría Crediticia</label>
                        <select name="catCredito" class="form-select" required>
                            <option value="">Seleccione</option>
                            <option value="A" <%= "A".equals(clienteEditar.getCatCredito()) ? "selected" : "" %>>A</option>
                            <option value="B" <%= "B".equals(clienteEditar.getCatCredito()) ? "selected" : "" %>>B</option>
                            <option value="C" <%= "C".equals(clienteEditar.getCatCredito()) ? "selected" : "" %>>C</option>
                        </select>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">Límite de Crédito</label>
                        <input type="number" class="form-control" name="limCredito" value="<%= clienteEditar.getLimCredito() %>" required/>
                    </div>

                    <div class="col-12">
                        <button type="submit" class="btn btn-success">
                            <%= (clienteEditar.getIdCliente() == 0) ? "Registrar" : "Actualizar" %>
                        </button>
                    </div>
                </form>

                <%-- Formulario Buscar --%>
                <h4 class="mt-4">Buscar Cliente</h4>
                <form action="ClienteController" method="GET" class="row g-3">
                    <input type="hidden" name="menu" value="Clientes"/>
                    <input type="hidden" name="accion" value="Buscar"/>
                    <div class="col-md-4">
                        <label class="form-label">ID Cliente</label>
                        <input type="number" class="form-control" name="idCliente" required/>
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </form>

                <% if (clienteEncontrado != null && clienteEncontrado.getIdCliente() != 0) { %>
                    <div class="mt-3">
                        <h5>Resultado Búsqueda:</h5>
                        <p>ID: <%= clienteEncontrado.getIdCliente() %></p>
                        <p>Nombre: <%= clienteEncontrado.getNomCliente() %> <%= clienteEncontrado.getApeCliente() %></p>
                        <p>Email: <%= clienteEncontrado.getEmaCliente() %></p>
                    </div>
                <% } %>

                <%-- Tabla Listado --%>
                <h4 class="mt-4">Lista de Clientes</h4>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Tipo Doc</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Email</th>
                                <th>Dirección</th>
                                <th>Teléfono</th>
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
                                    <td><%= c.getLimCredito() %></td>
                                    <td>
                                        <a href="ClienteController?menu=Clientes&accion=Editar&idCliente=<%= c.getIdCliente() %>"
                                           class="btn btn-sm btn-warning">Editar</a>
                                        <a href="ClienteController?menu=Clientes&accion=Eliminar&idCliente=<%= c.getIdCliente() %>"
                                           class="btn btn-sm btn-danger">Eliminar</a>
                                        <button type="button" class="btn btn-sm btn-info"
                                                onclick="mostrarCategoria('<%= c.getCatCredito() %>')">
                                            Historial
                                        </button>
                                    </td>
                                </tr>
                            <% }
                            } else { %>
                                <tr><td colspan="10" class="text-center">No hay clientes registrados.</td></tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal para mostrar categoría -->
    <div class="modal fade" id="modalHistorial" tabindex="-1" aria-labelledby="modalHistorialLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-primary text-white">
            <h5 class="modal-title" id="modalHistorialLabel">Categoría Crediticia- Ultima actualizacion crediticia</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
          </div>
          <div class="modal-body">
            <p id="categoriaTexto"></p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    <script>
      function mostrarCategoria(categoria) {
        document.getElementById("categoriaTexto").innerText = "La categoría crediticia actual es: " + categoria;
        var myModal = new bootstrap.Modal(document.getElementById('modalHistorial'));
        myModal.show();
      }
    </script>
</body>
</html>

