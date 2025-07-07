<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Pago</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="Stylesheet" href="<%= contextPath%>/Estilos/contenido.css">
        <link rel="stylesheet" href="<%= contextPath%>/Estilos/navbarEstilo.css">
    </head>
    <body>
        <%--Aqui Insertamos el dasboard--%>
        <%@ include file="../../plantillas/DashAdminHtml.jsp" %>

        <%--Aqui empieza el contenido--%>
        <div class="row contenido justify-content-center">
            <div class="col-md-5">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h1 class="card-title mb-0 text-center">Crear Nuevo Pago</h1>
                    </div>
                    <div class="card-body">
                        <form id="pagosForm" action="<%= contextPath%>/PagosController">
                            <input type="hidden" name="menu" value="Pagos">
                            <input type="hidden" name="accion" value="Agregar">
                            <div class="form-group">
                                <label for="fkIdCredito">Credito a Pagar</label>
                                <select name="fkIdCredito" class="form-control" id="fkIdCredito" required>
                                    <option value="">Seleccione el Crédito</option>
                                    <%
                                        List<Modelo.Credito> listaCreditos = (List<Modelo.Credito>) request.getAttribute("listaCreditos");
                                        if (listaCreditos != null) {
                                            for (Modelo.Credito credito : listaCreditos) {
                                    %>
                                    <option value="<%=credito.getIdCredito()%>">Crédito #<%= credito.getIdCredito()%> - <%= credito.getCliente()%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="tipoPago">Tipo de Pago</label>
                                <select name="tipoPago" class="form-control" id="tipoPago" required>
                                    <option value="">Seleccione Tipo de Pago</option>
                                    <option value="Parcial">Parcial</option>
                                    <option value="Total">Total</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="montoPago">Monto del Pago</label>
                                <input name="montoPago" id="montoPago" type="number" class="form-control" min="1" required>
                            </div>
                            <input name="pagoCredito" id="pagoCredito" type="hidden" value="Sin pagar">

                            <div class="card-title mb-0 text-center">
                                <button type="submit" class="btn btn-primary me-2 mt-2" >Crear Pago</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const creditoSelect = document.getElementById("fkIdCredito");
                const montoInput = document.getElementById("montoPago");
                const pagoCreditoInput = document.getElementById("pagoCredito");

                const contextPath = '<%= request.getContextPath()%>';

                creditoSelect.addEventListener("change", function () {
                    const idCredito = this.value;

                    if (idCredito !== "") {
                        fetch(`${contextPath}/CalcularMontoRestante?idCredito=encodeURIComponent(idCredito)`);
                        .then(res => res.json())
                                .then(data => {
                                    const restante = data.restante;

                                    montoInput.max = restante;
                                    montoInput.placeholder = `Máximo permitido: ${restante}`;

                                    // Asignar estado al input hidden
                                    if (restante <= 0) {
                                        pagoCreditoInput.value = "Pagado";
                                    } else {
                                        pagoCreditoInput.value = "Pago parcial";
                                    }
                                })
                                .catch(err => {
                                    console.error("Error al consultar el monto restante:", err);
                                    pagoCreditoInput.value = "Sin pagar"; // fallback
                                });
                    } else {
                        montoInput.removeAttribute("max");
                        montoInput.placeholder = "";
                        pagoCreditoInput.value = "Sin pagar";
                    }
                });
            });
        </script>
    </body>
</html>
