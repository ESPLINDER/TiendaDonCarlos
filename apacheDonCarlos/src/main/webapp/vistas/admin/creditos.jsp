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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.22.2/dist/sweetalert2.min.css">
        <link rel="Stylesheet" href="<%= contextPath%>/Estilos/contenido.css">
    </head>
    <body>
        <%@ include file="../../plantillas/DashAdminController.jsp" %>
        <div class="contenido container mx-auto">
            <h1>Lista de Usuarios</h1>
            <button class="boton-agregar"><a class="nav-link active" href="<%= contextPath%>/CreditoController?accion=PrepararCredito">Nueva Factura</a></button>
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
                            <td><%= c.getIdCredito()%></td>
                            <td><%= c.getUsuario()%></td>
                            <td><%= c.getCliente()%></td>
                            <td><%= c.getMontoCredito()%></td>
                            <td><%= c.getEmiCredito()%></td>
                            <td><%= c.getVenCredito()%></td>
                            <td><%= c.getPagoCredito()%></td>
                            <td><button class="boton boton-pagar"><a href="<%= contextPath%>/ANDRES SE ENCARGA DE ESTO">Registrar pago</a></button>
                                <button class="boton boton-editar"><a href="<%= contextPath%>/CreditoController?accion=Editar&idCredito=<%=c.getIdCredito()%>">Editar</a></button>
                                <button class="boton boton-eliminar" data-bs-toggle="modal" data-bs-target="#eliminarRutina_<%=c.getIdCredito()%>">Eliminar</button></td>
                        </tr>
                    <div class="modal fade" id="eliminarRutina_<%=c.getIdCredito()%>" aria-labelledby="titulos" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <h5 class="modal-title" id="titulos">ESTAS A PUNTO DE ELIMINAR UNA FACTURA!</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                                </div>

                                <div class="modal-body">
                                    <form id="formEliminar_<%=c.getIdCredito()%>" action="<%=contextPath%>/CreditoController">
                                        <input type="hidden" name="idCredito" value="<%=c.getIdCredito()%>">
                                        <div class="form-group">
                                            Si eliminas esta factura, no podras recuperarla posteriormente.
                                        </div>
                                    </form>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                    <button type="submit" name="accion" value="Eliminar" class="btn boton-eliminar" form="formEliminar_<%=c.getIdCredito()%>">Eliminar Definitivamente</button>
                                </div>

                            </div>
                        </div>
                    </div>
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
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
