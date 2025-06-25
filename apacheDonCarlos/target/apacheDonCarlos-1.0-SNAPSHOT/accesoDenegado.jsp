<%
    String contextPath = request.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acceso denegado</title>
    </head>
    <body>
        <h1>Acceso Denegado</h1>
        <p>No tienes permiso para ver esta página.</p>
        <a href="<%= contextPath %>/IndexEmpleado.jsp">Volver</a>
    </body>
</html>
