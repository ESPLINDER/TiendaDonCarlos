<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulario Usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="Stylesheet" href="<%= contextPath %>/Estilos/contenido.css">
        <link rel="stylesheet" href="<%= contextPath %>/Estilos/navbarEstilo.css">
    </head>
    <body>
        <%--Aqui Insertamos el dasboard--%>
        <%@ include file="../../plantillas/DashAdminController.jsp" %>
        
        <%--Aqui empieza el contenido--%>
        <div class="row contenido justify-content-center">
            <div class="col-md-5">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h1 class="card-title mb-0 text-center">Creacion de Usuarios</h1>
                    </div>
                    <div class="card-body">
                        <form id="userForm" action="<%= contextPath %>/UsuarioController">
                            <input type="hidden" name="menu" value="Usuarios">
                            <input type="hidden" name="accion" value="Agregar">
                            <div class="form-group">
                                <label for="nomUsuario">Numero de documento</label>
                                <input name="idUsuario" type="number" class="form-control" id="idUsuario" required>
                            </div>
                            <div class="form-group">
                                <label for="nomUsuario">Nombre</label>
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
                            <div class="form-group">
                                <label for="rolUsuario">Rol</label>
                                <select name="rolUsuario" class="form-control" id="rolUsuario" required>
                                    <option value="">Seleccione un rol</option>
                                    <option value="Administrador">Administrador</option>
                                    <option value="Empleado">Empleado</option>
                                </select>
                            </div>
                            <div class="card-title mb-0 text-center">
                                <button type="submit" class="btn btn-primary me-2 mt-2" >
                                    Crear usuario
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
