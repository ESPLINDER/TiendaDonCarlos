<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Modelo.Pagos" %>
<%
    String contextPath = request.getContextPath();

    List<Modelo.Pagos> lista_pagos = (List<Modelo.Pagos>) request.getAttribute("lista_Pagos");

    String mensajeExito = (String) session.getAttribute("mensajeExito");
    String mensajeError = (String) session.getAttribute("mensajeError");

    // Eliminar después de mostrar para que no reaparezca
    session.removeAttribute("mensajeExito");
    session.removeAttribute("mensajeError");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion de Pagos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.22.2/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.22.2/dist/sweetalert2.all.min.js"></script>
        <link rel="Stylesheet" href="<%= contextPath%>/Estilos/contenido.css">
        <link rel="stylesheet" href="<%= contextPath%>/Estilos/navbarEstilo.css">
    </head>
    <body>
        <% if (mensajeExito != null) {%>
        <script>
            Swal.fire({
                icon: 'success',
                title: '¡Éxito!',
                text: '<%= mensajeExito%>'
            });
        </script>
        <% } else if (mensajeError != null) {%>
        <script>
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: '<%= mensajeError%>'
            });
        </script>
        <% }%>
        <%--Aqui Insertamos el dasboard--%>
        <%@ include file="../../plantillas/DashAdminController.jsp" %>

        <%--Aqui empieza el contenido--%>
        <div class="contenido">
            <h1>Lista de Pagos</h1>
            <button class="boton-agregar"><a class="nav-link active" href="<%= contextPath%>/PagosController?menu=Pagos&accion=ListarCreditos">Crear Pago</a></button>
            <div class="tabla">
                <table>
                    <thead>
                        <tr>
                            <th>ID Pago</th>
                            <th>ID Credito</th>
                            <th>Valor del Pago</th>
                            <th>Tipo del Pago</th>
                            <th>Fecha del Pago</th>
                            <th>Opciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (lista_pagos != null && !lista_pagos.isEmpty()) {
                                for (Modelo.Pagos pago : lista_pagos) {
                        %>
                        <tr>
                            <td><%= pago.getIdPago()%></td>
                            <td><%= pago.getFkIdCredito()%></td>
                            <td><%= pago.getMontoPago()%></td>
                            <td><%= pago.getTipoPago()%></td>
                            <td><%= pago.getFechaPago()%></td>
                            <td><button class="boton boton-editar"><a href="<%= contextPath%>/PagosController?menu=Pagos&accion=Editar&idPago=<%= pago.getIdPago()%>">Editar Pago</a></button></td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="2">No hay pagos registrados</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>


            <div>
                <% Modelo.Pagos PagoEditar = (Pagos) request.getAttribute("mostrar_pago");
                    String idValue = (PagoEditar != null) ? String.valueOf(PagoEditar.getIdPago()) : "";
                    String monValue = (PagoEditar != null) ? String.valueOf(PagoEditar.getMontoPago()) : "";
                    String tipValue = (PagoEditar != null) ? String.valueOf(PagoEditar.getTipoPago()) : "";
                %>
                <!-- Modal -->
                <div class="modal fade" id="editarPago" aria-labelledby="editarPagoLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="editarPagoLabel">Editar Pago</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                            </div>
                            <div class="modal-body">

                                <form id="edicionPago" action="PagosController">
                                    <input type="hidden" name="menu" value="Pagos">
                                    <input type="hidden" name="accion" value="Actualizar">
                                    <div class="form-group">
                                        <input name="idPago" id="idPago" type="hidden" class="form-control" value="<%=idValue%>">
                                    </div>
                                    <div class="form-group">
                                        <label for="montoPago">Monto del Pago</label>
                                        <input name="montoPago" id="montoPago" type="number" class="form-control" value="<%= monValue%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="tipoPago">Tipo de Pago</label>
                                        <select name="tipoPago" id="tipoPago"  class="form-control" required>
                                            <option value="Parcial" <%= "Parcial".equals(tipValue) ? "selected" : ""%>>Parcial</option>
                                            <option value="Total" <%= "Total".equals(tipValue) ? "selected" : ""%>>Total</option>
                                        </select>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-primary" form="edicionPago">Guardar cambios</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
        <% if (PagoEditar != null) {
                System.out.println("el objeto no esta vacio");%>
        <script>
        document.addEventListener('DOMContentLoaded', function () {
            var modalElement = document.getElementById('editarPago');
            if (modalElement) {
                var modal = new bootstrap.Modal(modalElement);
                modal.show();
            }
        });
        </script>
        <%} else {
            System.out.println("el objeto esta vacio");
        %>  <script></script> <%
            }%>
    </body>
</html>
