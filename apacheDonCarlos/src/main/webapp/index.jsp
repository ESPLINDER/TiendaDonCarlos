<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - El Vecino Amigo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="Estilos/login.css">
</head>
<body> 
    <div class="login-container">
        <!-- Panel del formulario (izquierda) -->
        <div class="form-panel">
            <div class="logo-section">
                <div class="logo-icon">
                    <i class="bi bi-shop"></i>
                </div>
                <div class="brand-name">El Vecino Amigo</div>
                <div class="welcome-text">Ingresa a tu cuenta</div>
            </div>

            <form id="userForm" method="POST" action="ValidarUsuario">
                <div class="form-group">
                    <label for="emaUsuario" class="form-label">Correo Electrónico</label>
                    <input type="email" class="form-control" id="emaUsuario" name="emaUsuario" required placeholder="correo@ejemplo.com">
                </div>

                <div class="form-group">
                    <label for="passUsuario" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="passUsuario" name="passUsuario" required placeholder="Tu contraseña">
                </div>

                <button type="submit" name="accion" value="Ingresar" class="btn-login">
                    Iniciar Sesión
                </button>
            </form>
        </div>

        <!-- Panel de bienvenida (derecha) -->
        <div class="welcome-panel">
            <div class="welcome-content">
                <div class="welcome-illustration">
                    <i class="bi bi-cart-check"></i>
                </div>
                <h1 class="welcome-title">¡Bienvenido de Vuelta!</h1>
                <p class="welcome-subtitle">
                    Accede a tu cuenta para continuar haciendo credito para que te endeudes en tu tienda de confianza del barrio
                </p>
            </div>
            <div class="website-url">www.elvecinoamigo.com</div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
