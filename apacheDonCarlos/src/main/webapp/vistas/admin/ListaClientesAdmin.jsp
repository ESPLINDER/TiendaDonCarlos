<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Cliente"%>
<%
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("lista_cli");
    Cliente clienteEditar = (Cliente) request.getAttribute("mostrar_cliente");
    Cliente clienteEncontrado = (Cliente) request.getAttribute("cliente_encontrado");

    if (clienteEditar == null) {
        clienteEditar = new Cliente();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Clientes</title>
</head>
<body>
    <h1>Gestión de Clientes</h1>

    <!-- Mensajes -->
    <% if (request.getAttribute("mensaje") != null) { %>
        <p style="color: green;"><%= request.getAttribute("mensaje") %></p>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <!-- Formulario Registro / Actualización -->
    <h2><%= (clienteEditar.getIdCliente() == 0) ? "Registrar Cliente" : "Actualizar Cliente" %></h2>
    <form action="ClienteController" method="GET">
        <input type="hidden" name="menu" value="Clientes"/>
        <input type="hidden" name="accion" value="<%= (clienteEditar.getIdCliente() == 0) ? "Agregar" : "Actualizar" %>"/>
        
        <label>ID Cliente:</label>
        <input type="number" name="idCliente" value="<%= clienteEditar.getIdCliente() %>" <%= (clienteEditar.getIdCliente() != 0) ? "readonly" : "" %> required/><br/>

        <label>Tipo Documento:</label>
        <input type="text" name="tipDocumento" value="<%= clienteEditar.getTipoDocumento() %>" required/><br/>

        <label>Nombre:</label>
        <input type="text" name="nomCliente" value="<%= clienteEditar.getNomCliente() %>" required/><br/>

        <label>Apellido:</label>
        <input type="text" name="apeCliente" value="<%= clienteEditar.getApeCliente() %>" required/><br/>

        <label>Email:</label>
        <input type="email" name="emaCliente" value="<%= clienteEditar.getEmaCliente() %>" required/><br/>

        <label>Dirección:</label>
        <input type="text" name="domCliente" value="<%= clienteEditar.getDomCliente() %>" required/><br/>

        <label>Teléfono:</label>
        <input type="number" name="telCliente" value="<%= clienteEditar.getTelCliente() %>" required/><br/>

        <label>Categoría Crediticia:</label>
        <select name="catCredito" required>
            <option value="">Seleccione</option>
            <option value="A" <%= "A".equals(clienteEditar.getCatCredito()) ? "selected" : "" %>>A</option>
            <option value="B" <%= "B".equals(clienteEditar.getCatCredito()) ? "selected" : "" %>>B</option>
            <option value="C" <%= "C".equals(clienteEditar.getCatCredito()) ? "selected" : "" %>>C</option>
        </select><br/>

        <label>Límite de Crédito:</label>
        <input type="number" name="limCredito" value="<%= clienteEditar.getLimCredito() %>" required/><br/>

        <button type="submit"><%= (clienteEditar.getIdCliente() == 0) ? "Registrar" : "Actualizar" %></button>
    </form>

    <!-- Formulario Buscar -->
    <h2>Buscar Cliente</h2>
    <form action="ClienteController" method="GET">
        <input type="hidden" name="menu" value="Clientes"/>
        <input type="hidden" name="accion" value="Buscar"/>
        <label>ID Cliente:</label>
        <input type="number" name="idCliente" required/>
        <button type="submit">Buscar</button>
    </form>

    <% if (clienteEncontrado != null && clienteEncontrado.getIdCliente() != 0) { %>
        <h3>Resultado Búsqueda:</h3>
        <p>ID: <%= clienteEncontrado.getIdCliente() %></p>
        <p>Nombre: <%= clienteEncontrado.getNomCliente() %> <%= clienteEncontrado.getApeCliente() %></p>
        <p>Email: <%= clienteEncontrado.getEmaCliente() %></p>
    <% } %>

    <!-- Tabla Listado -->
    <h2>Lista de Clientes</h2>
    <table border="1">
        <thead>
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
                        <a href="ClienteController?menu=Clientes&accion=Editar&idCliente=<%= c.getIdCliente() %>">Editar</a> |
                        <a href="ClienteController?menu=Clientes&accion=Eliminar&idCliente=<%= c.getIdCliente() %>">Eliminar</a> |
                        <a href="ClienteController?menu=Clientes&accion=Consultar&idCliente=<%= c.getIdCliente() %>">Historial</a>
                    </td>
                </tr>
            <% }
            } else { %>
                <tr><td colspan="10">No hay clientes registrados.</td></tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
