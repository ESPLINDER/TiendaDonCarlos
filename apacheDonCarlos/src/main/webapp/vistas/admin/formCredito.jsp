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
                <form action="<%= contextPath%>/CreditoController">
                    <div class="form-label row p-3 px-5">
                        <div class="col-sm-5 mb-3 clientes">
                            <label for="idCliente">Cliente</label>
                            <select name="fk_idCliente" class="form-control" id="Cliente" onchange="TraerCliente()">
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
                    </div>

                    <div class="table-container mt-5">
                        <table class="table table-borderless mb-0">
                            <thead class="tabla">
                                <tr>
                                    <th>#</th>
                                    <th>Descripción</th>
                                    <th>Cant</th>
                                    <th>Valor Unitario</th>
                                    <th>Valor Total</th>
                                </tr>
                            </thead>
                            <tbody id="tabla-productos">
                                <tr>
                                    <td class="row-number">1</td>
                                    <td>
                                        <select name="fk_idProducto" class="form-control select-producto" onchange="actualizarValorUnitario(this)">
                                            <option value="0" data-precio="0">Seleccionar producto</option>
                                            <% for (Modelo.Producto p : listaProductos) {%>
                                            <option value="<%= p.getIdProducto()%>" data-precio="<%= p.getValProducto()%>">
                                                <%= p.getNomProducto()%>
                                            </option>
                                            <% }%>
                                        </select>
                                    </td>
                                    <td>
                                        <input name="cantidad" type="number" class="form-control input-cantidad" min="1" oninput="actualizarTotal(this)">
                                    </td>
                                    <td>
                                        <input type="number" class="form-control input-valor-unitario" value="0" readonly>
                                    </td>
                                    <td>
                                        <input name="totalPrecio" type="number" class="form-control input-total" value="0" readonly>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <!-- Botón para agregar fila -->
                        <button type="button" class="btn btn-success mt-2" onclick="agregarFila()">Agregar producto</button>
                    </div>
                    <div class="mx-auto row pagoTotal mt-3 mb-3">
                        <div class="col-md-7">
                            <div class="py-3">
                                <h4>Forma de pago</h4>
                            </div>
                            <div class="py-2 row ceparador">
                                <div class="col-md-5 text-end my-auto"><label for="venCredito">Vencimiento del credito</label></div>
                                <div class="col-md-3">
                                    <select name="plazo" id="venCredito" class="form-select">
                                        <option value="0">Hoy</option>
                                        <option value="15">a 15 dias</option>
                                        <option value="30">a 30 dias</option>
                                        <option value="60">a 60 dias</option>
                                        <option value="90">a 90 dias</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5 total py-3 ps-5">
                            <strong>Total general: </strong><br>
                            <span id="total-general">$0</span>
                            <input type="hidden" name="montoCredito" id="input-total-general">
                        </div>
                    </div>
                    <div class="justify-content-center d-flex">
                        <button type="button" class="btn cancelar ms-auto">Cancelar</button>
                        <input name="accion" value="guardar" type="hidden">
                        <button type="submit" class="btn guardar me-auto ms-2">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
    crossorigin="anonymous"></script>
    <script src="<%= request.getContextPath()%>/script/formCredito.js"></script>
</html>
