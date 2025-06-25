<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pagina_Principal</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="<%= contextPath %>/Estilos/navbarEstilo.css">
    </head>
    <body>
        <header>
            <div class="left">
                <div class="brand">
                    <i class="bi bi-shop logo"></i>
                    <span class="name">El Vecino Amigo</span>
                </div>
            </div>
            <div class="right">
                <a href="index.jsp">
                    <i class="bi bi-arrow-return-left"></i>
                    <span class="names">Regresar</span>
                </a>
                <a href="#">
                    <i class="bi bi-plus"></i>
                    <span class="names">Cliente</span>
                </a>
                <a href="#">
                    <i class="bi bi-plus"></i>
                    <span class="names">Credito</span>
                </a>
                <div class="right">
                    <a href="#" class="d-flex align-items-center">
                        <i class="bi bi-people"></i>
                        <span class="names">Clientes</span>
                    </a>
                </div>
                <img src="recursos/empleado.jpg" alt="usuario" class="user">
                <span class="names">Empleado</span>
            </div>
        </header>
        <div class="sidebar">
            <ul>
                <li>
                    <a href="#">
                        <i class="bi bi-cash-stack"></i>
                        <span class="names">Pagos</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="bi bi-coin"></i>
                        <span class="names">Creditos</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="bi bi-basket"></i>
                        <span class="names">Productos</span>
                    </a>
                </li>
                <li>
                <body>
                    <a href="#">
                        <i class="bi bi-people"></i>
                        <span class="names">clientes</span>
                    </a>
                    </li>
                <li>
                    <a href="#">
                        <i class="bi bi-exclamation-circle"></i>
                        <span class="names">reportes</span>
                    </a>
                </li>
            </ul>
        </div>
    </body>
</html>
