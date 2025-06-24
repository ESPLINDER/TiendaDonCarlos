<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Modelo.Usuario" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="Stylesheet" href="Estilos/contenido.css">
        <link rel="stylesheet" href="Estilos/navbarEstilo.css">
    </head>
    <body>
        <%--Aqui Insertamos el dasboard--%>
        <%@ include file="../plantillas/DashAdminController.jsp" %>

        <%--Aqui empieza el contenido--%>
        <div class="contenido">
            <h1>Lista de Usuarios</h1>
            <button class="boton-agregar"><a class="nav-link active" href="vistas/formAgregarUsuario.jsp">Agregar</a></button>
            <div class="tabla">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Email</th>
                            <th>Rol</th>
                            <th>Opciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Modelo.Usuario> lista_usu = (List<Modelo.Usuario>) request.getAttribute("lista_usu");
                            if (lista_usu != null && !lista_usu.isEmpty()) {
                                for (Modelo.Usuario u : lista_usu) {
                        %>
                        <tr>
                            <td><%= u.getIdUsuario()%></td>
                            <td><%= u.getNomUsuario()%></td>
                            <td><%= u.getApeUsuario()%></td>
                            <td><%= u.getEmaUsuario()%></td>
                            <td><%= u.getRolUsuario()%></td>
                            <td><button class="boton boton-editar"><a href="UsuarioController?menu=Usuarios&accion=Editar&idUsuario=<%= u.getIdUsuario()%>">Editar</a></button>
                                <button class="boton boton-eliminar"><a href="UsuarioController?menu=Usuarios&accion=Eliminar&idUsuario=<%= u.getIdUsuario()%>">Eliminar</button></td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="2">No hay usuarios registrados</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div>
                <% Modelo.Usuario usuarioEditar = (Usuario) request.getAttribute("mostrar_usuario"); 
                String idValue = (usuarioEditar != null) ? String.valueOf(usuarioEditar.getIdUsuario()) : "";
                String nomValue = (usuarioEditar != null) ? String.valueOf(usuarioEditar.getNomUsuario()) : "";
                String apeValue = (usuarioEditar != null) ? String.valueOf(usuarioEditar.getApeUsuario()) : "";
                String emaValue = (usuarioEditar != null) ? String.valueOf(usuarioEditar.getEmaUsuario()) : "";
                String passValue = (usuarioEditar != null) ? String.valueOf(usuarioEditar.getPassUsuario()) : "";
                String rolValue = (usuarioEditar != null) ? String.valueOf(usuarioEditar.getRolUsuario()) : "";
                %>
                <!-- Modal -->
                <div class="modal fade" id="editarUsuario" aria-labelledby="editarUsuarioLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">

                            <div class="modal-header">
                                <h5 class="modal-title" id="editarUsuarioLabel">Editar Usuario</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                            </div>

                            <div class="modal-body">
                                <form id="edicionUsuario" action="UsuarioController">
                                    <input type="hidden" name="menu" value="Usuarios">
                                    <input type="hidden" name="accion" value="Actualizar">
                                    <div class="form-group">
                                        <label for="nomUsuario">Numero de documento</label>
                                        <input name="idUsuario" type="text" class="form-control" id="idUsuario" value="<%=idValue%>" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="nomUsuario">Nombre</label>
                                        <input name="nomUsuario" type="text" class="form-control" id="nomUsuario" value="<%=nomValue%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="apeUsuario">Apellido</label>
                                        <input name="apeUsuario" type="text" class="form-control" id="apeUsuario" value="<%=apeValue%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="emaUsuario">Correo Electrónico</label>
                                        <input name="emaUsuario" type="email" class="form-control" id="emaUsuario" value="<%=emaValue%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="passUsuario">Contraseña</label>
                                        <input name="passUsuario" type="text" class="form-control" id="passUsuario" value="<%=passValue%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="rolUsuario">Rol</label>
                                        <select name="rolUsuario" class="form-control" id="rolUsuario" required>
                                            <option value="<%=rolValue%>"><%=rolValue%></option>
                                            <option value="Administrador">Administrador</option>
                                            <option value="Empleado">Empleado</option>
                                        </select>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-primary" form="edicionUsuario">Guardar cambios</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
        <% if (usuarioEditar != null) {
            System.out.println("el objeto no esta vacio");%>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var modalElement = document.getElementById('editarUsuario');
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
