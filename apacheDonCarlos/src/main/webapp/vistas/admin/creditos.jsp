<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Creditos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="Stylesheet" href="<%= contextPath %>/Estilos/contenido.css">
    </head>
    <body>
        <%@ include file="../../plantillas/DashAdminController.jsp" %>
        <div class="contenido container mx-auto">
            <h1>Lista de Usuarios</h1>
            <button class="boton-agregar"><a class="nav-link active" href="<%= contextPath %>/CreditoController?accion=PrepararCredito">Nueva Factura</a></button>
            <div class="tabla">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Elaborada</th>
                            <th>Cliente</th>
                            <th>Valor de la factura</th>
                            <th>Fecha de emision</th>
                            <th>Vencimiento</th>
                            <th>Estado del pago</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Modelo.Credito> lista_cre = (List<Modelo.Credito>) request.getAttribute("lista_cre");
                            if (lista_cre != null && !lista_cre.isEmpty()) {
                                for (Modelo.Credito c : lista_cre) {
                        %>
                        <tr>
                            <td><%= c.getIdCredito() %></td>
                            <td><%= c.getUsuario() %></td>
                            <td><%= c.getCliente() %></td>
                            <td><%= c.getMontoCredito() %></td>
                            <td><%= c.getEmiCredito() %></td>
                            <td><%= c.getVenCredito() %></td>
                            <td><%= c.getPagoCredito() %></td>
                            <td><button class="boton boton-editar"><a href="<%= contextPath %>/">Editar</a></button>
                                <button class="boton boton-eliminar"><a href="<%= contextPath %>/">Eliminar</a></button>
                                <button class="boton boton-pagar"><a href="<%= contextPath %>/">Registrar pago</a></button></td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="2">No hay creditos registrados</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
    </body>
</html>
