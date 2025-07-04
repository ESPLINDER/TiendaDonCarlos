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
        <link rel="Stylesheet" href="<%= contextPath %>/Estilos/contenido.css">
        <link rel="stylesheet" href="<%= contextPath %>/Estilos/navbarEstilo.css">
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
                        <form id="pagosForm" action="<%= contextPath %>/PagosController">
                            <input type="hidden" name="menu" value="Pagos">
                            <input type="hidden" name="accion" value="Agregar">
                            <div class="form-group">
                                <label for="fkIdCredito">Credito a Pagar</label>
                                <select name="fkIdCredito" class="form-control" id="fkIdCredito" required>
                                    <option value="">Seleccione el Credito</option>
                                    <option value="Administrador">Administrador</option>
                                    <option value="Empleado">Empleado</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="nomUsuario">N</label>
                                <input name="nomUsuario" type="text" class="form-control" id="nomUsuario" required>
                            </div>
                            <div class="form-group">
                                <label for="apeUsuario">Apellido</label>
                                <input name="apeUsuario" type="text" class="form-control" id="apeUsuario" required>
                            </div>
                            <div class="form-group">
                                <label for="emaUsuario">Correo Electrónico</label>
                                <input name="emaUsuario" type="email" class="form-control" id="emaUsuario" required>
                            </div>
                            <div class="form-group">
                                <label for="passUsuario">Contraseña</label>
                                <input name="passUsuario" type="password" class="form-control" id="passUsuario" required>
                            </div>
                            
                            <div class="card-title mb-0 text-center">
                                <button type="submit" class="btn btn-primary me-2 mt-2" >
                                    Crear Pago
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
