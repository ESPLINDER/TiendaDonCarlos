
<%
    Modelo.Usuario usuario = (Modelo.Usuario) session.getAttribute("usuario");
    if (usuario != null) {
%>
<link rel="stylesheet" href="<%= contextPath %>/Estilos/navbarEstilo.css">
<header>
    <div class="left">
        <div class="brand">
            <i class="bi bi-shop logo"></i>
            <span class="name">El Vecino Amigo</span>
        </div>
    </div>
    <div class="right">
        <a href="<%= request.getContextPath()%>/CerrarSesion">
            <span class="names">Cerrar Sesión</span>
        </a>
        <a href="<%= contextPath %>/ClienteController?menu=Clientes&accion=Listar">
            <i class="bi bi-plus"></i>
            <span class="names">Cliente</span>
        </a>
        <a href="<%= contextPath %>/UsuarioController?menu=accion&accion=Agregar">
            <i class="bi bi-plus"></i>
            <span class="names">Usuario</span>
        </a>
        <a href="#">
            <i class="bi bi-plus"></i>
            <span class="names">Producto</span>
        </a>
        <img src="<%= contextPath %>/recursos/admin.jpg" alt="usuario" class="user">
        <span class="names"><%= usuario.getNomUsuario()%> | <%= usuario.getRolUsuario()%></span>
    </div>
</header>
<div class="sidebar">
    <ul>
        <li>
            <a href="<%= contextPath %>/UsuarioController?menu=Usuarios&accion=Listar">
                <i class="bi bi-people"></i>
                <span class="names">Usuarios</span>
            </a>
        </li>
        <li>
            <a href="<%= contextPath %>/PagosController?menu=Pagos&accion=Listar">
                <i class="bi bi-cash-stack"></i>
                <span class="names">Pagos</span>
            </a>
        </li>
        <li>
            <a href="<%= contextPath %>/CreditoController?accion=Listar">
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
            <a href="<%= contextPath %>/ClienteController?menu=Clientes&accion=Listar">
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
<%}%>