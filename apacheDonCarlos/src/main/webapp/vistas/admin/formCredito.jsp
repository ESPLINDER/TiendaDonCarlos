<%@page import="Modelo.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Creditos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="<%= contextPath%>/Estilos/formCredito.css">
        <link rel="stylesheet" href="../Scripts/creditos.js">
    </head>

    <body>
        <%@ include file="../../plantillas/DashAdminController.jsp" %>
        <%List<Modelo.Cliente> listaClientes = (List<Modelo.Cliente>) session.getAttribute("listaClientes");
            List<Modelo.Producto> listaProductos = (List<Modelo.Producto>) session.getAttribute("listaProductos");
            Cliente cliente = (Cliente) session.getAttribute("cliente");
            int selectClienteIdValue = (cliente != null) ? cliente.getIdCliente() : 0;
            String selectClienteValue = (cliente != null) ? cliente.getNomCliente() + " " + cliente.getApeCliente() : "Seleccione un cliente";
            String idCredito = (String) session.getAttribute("idFactura");
            String selectContactoValue = (cliente != null) ? cliente.getEmaCliente() : "Buscar por correo";%>
        <!-- Formulario de crear Créditos -->
        <div class="row my-5">
            <div class="contenedor col-md-6 mx-auto px-3 py-5">
                <h1>Nueva Factura Credito</h1>
                <form>
                    <div class="form-label row p-3 px-5">
                        <div class="col-sm-5 mb-3 clientes">
                            <label for="idCliente">Cliente</label>
                            <select class="form-control" id="nomCliente" onchange="TraerCliente()">
                                <option value="<%=selectClienteIdValue%>"><%=selectClienteValue%></option>
                                <%for (Modelo.Cliente c : listaClientes) {%>
                                <option value="<%= c.getIdCliente()%>"><%=c.getNomCliente() + " " + c.getApeCliente()%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-sm-5 mb-3">
                            <label for="numeroFactura">Número</label>
                            <div class="form-control-plaintext" id="numeroFacturaVisible"><%=idCredito%> (Numeración Automática)
                            </div>
                        </div>

                        <div class="col-sm-5 mb-3">
                            <label for="contactoCliente">Contacto</label>
                            <select class="form-control" id="emaCliente" onchange="TraerCliente()">
                                <option value="<%=selectClienteIdValue%>"><%=selectContactoValue%></option>
                                <%for (Modelo.Cliente c : listaClientes) {%>
                                <option value="<%= c.getIdCliente()%>"><%=c.getEmaCliente()%></option>
                                <%}%>
                            </select>
                        </div>
                    </div>

                    <div class="table-container mt-5">
                        <table class="table table-borderless mb-0">
                            <thead class="tabla">
                                <tr>
                                    <th>#</th>
                                    <th>Producto</th>
                                    <th>Descripción</th>
                                    <th>Cant</th>
                                    <th>Valor Unitario</th>
                                    <th>Valor Total</th>
                                    <th>- vacio -</th>
                                </tr>
                            </thead>
                            <tbody id="tabla-productos">
                                <tr id="fila1">
                                    <td class="row-number">1</td>
                                    <td style="position: relative;">
                                        <select name="producto" id="idProducto" class="form-control">
                                            <option value="0">Seleccionar producto</option>
                                            <option value="arroz">0001</option>
                                            <option value="leche">0002</option>
                                            <option value="queso">0003</option>
                                            <option value="pan">0004</option>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="producto" id="nomProducto" class="form-control">
                                            <option value="0">Seleccionar producto</option>
                                            <option value="arroz">Arroz</option>
                                            <option value="leche">Leche</option>
                                            <option value="queso">Queso</option>
                                            <option value="pan">Pan</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control" id="cantProducto" value="1" min="1"
                                               readonly>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control" id="valProducto" value="1000" readonly>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control" id="totalProducto" value="1000" readonly>
                                    </td>
                                    <td>
                                        <button class="btn-remove">🗑️</button>
                                    </td>
                                </tr>
                                <tr id="fila2">
                                    <td class="row-number">2</td>
                                    <td style="position: relative;">
                                        <select name="producto" id="idProducto" class="form-control">
                                            <option value="0">Seleccionar producto</option>
                                            <option value="arroz">0001</option>
                                            <option value="leche">0002</option>
                                            <option value="queso">0003</option>
                                            <option value="pan">0004</option>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="producto" id="nomProducto" class="form-control">
                                            <option value="0">Seleccionar producto</option>
                                            <option value="arroz">Arroz</option>
                                            <option value="leche">Leche</option>
                                            <option value="queso">Queso</option>
                                            <option value="pan">Pan</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control" id="cantProducto" value="7" min="1"
                                               readonly>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control" id="valProducto" value="2000" readonly>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control" id="totalProducto" value="14000" readonly>
                                    </td>
                                    <td>
                                        <button class="btn-remove">🗑️</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <button class="btn btn-success mt-1">
                        Agregar Producto
                    </button>
                    <div class="mx-auto row pagoTotal mt-3 mb-3">
                        <div class="col-md-7">
                            <div class="py-3">
                                <h4>Forma de pago</h4>
                            </div>
                            <div class="py-2 row ceparador">
                                <div class="col-md-4 text-end my-auto"><label for="venCredito">Vencimiento del
                                        credito</label></div>
                                <div class="col-md-2">
                                    <select name="plazo" id="venCredito" class="form-select">
                                        <option value="Hoy">Hoy</option>
                                        <option value="a 15 dias">a 15 dias</option>
                                        <option value="a 30 dias">a 30 dias</option>
                                        <option value="a 60 dias">a 60 dias</option>
                                        <option value="a 90 dias">a 90 dias</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="fechaElaboracion" readonly
                                               value="10/06/2025">
                                        <button type="button" class="btn" id="btnCalendario"
                                                title="Cambiar fecha">📅</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5 total py-3 ps-5">
                            <h3 class="mb-4">Total a pagar: </h3>$ 15000
                        </div>
                    </div>
                    <div class="justify-content-center d-flex">
                        <button type="button" class="btn cancelar ms-auto">Cancelar</button>
                        <button type="submit" class="btn guardar me-auto ms-2">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
    crossorigin="anonymous"></script>

    <script>
        function TraerClienteNombre() {
            // 1. Obtener el valor seleccionado del ejercicio
            var clienteId = document.getElementById("nomCliente").value;

            // 2. Agregar o actualizar el parámetro fkIdEjercicio
            params.set("idCliente", clienteId);
            params.set("accion", "TraerCliente");

            // 3. Redirigir al controlador con los parámetros
            window.location.href = "<%= contextPath%>/CreditoController?" + params.toString();
        }
        function TraerClienteEmail() {
            var clienteId = document.getElementById("Emaliente").value;

            params.set("idCliente", clienteId);
            params.set("accion", "TraerCliente");

            window.location.href = "<%= contextPath%>/CreditoController?" + params.toString();
        }
    </script>
</html>
