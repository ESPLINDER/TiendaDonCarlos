-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-07-2025 a las 14:04:20
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `el_vecino_amigo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `idCliente` int(12) NOT NULL,
  `tipDocumento` varchar(3) NOT NULL,
  `nomCliente` varchar(30) NOT NULL,
  `ApeCliente` varchar(30) NOT NULL,
  `emaCliente` varchar(70) NOT NULL,
  `domCliente` varchar(40) NOT NULL,
  `telCliente` int(12) NOT NULL,
  `catCredito` enum('A','B','C') NOT NULL DEFAULT 'C',
  `limCredito` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idCliente`, `tipDocumento`, `nomCliente`, `ApeCliente`, `emaCliente`, `domCliente`, `telCliente`, `catCredito`, `limCredito`) VALUES
(1, 'DNI', 'Juan', 'Pérez', 'juan.perez@example.com', 'Calle Falsa 123', 123456789, 'A', 10000),
(2, 'DNI', 'Ana', 'Gómez', 'ana.gomez@example.com', 'Avenida Siempre Viva 742', 987654321, 'B', 5000),
(3, 'DNI', 'Luis', 'Martínez', 'luis.martinez@example.com', 'Calle de la Paz 456', 456789123, 'C', 3000),
(4, 'DNI', 'María', 'López', 'maria.lopez@example.com', 'Calle 7', 321654987, 'A', 8000),
(5, 'DNI', 'Carlos', 'Sánchez', 'carlos.sanchez@example.com', 'Calle 8', 654987321, 'B', 6000),
(6, 'DNI', 'Laura', 'Fernández', 'laura.fernandez@example.com', 'Calle 9', 987321654, 'C', 2000),
(7, 'DNI', 'Pedro', 'Gutiérrez', 'pedro.gutierrez@example.com', 'Calle 10', 321987654, 'A', 9000),
(8, 'DNI', 'Sofía', 'Torres', 'sofia.torres@example.com', 'Calle 11', 654321987, 'B', 7000),
(9, 'DNI', 'Andrés', 'Ramírez', 'andres.ramirez@example.com', 'Calle 12', 789654123, 'C', 4000),
(10, 'DNI', 'Isabel', 'Hernández', 'isabel.hernandez@example.com', 'Calle 13', 159753486, 'A', 11000),
(11, 'DNI', 'Fernando', 'Díaz', 'fernando.diaz@example.com', 'Calle 14', 123456789, 'B', 5000),
(12, 'DNI', 'Claudia', 'Jiménez', 'claudia.jimenez@example.com', 'Calle 15', 987654321, 'C', 3000),
(13, 'DNI', 'Ricardo', 'Morales', 'ricardo.morales@example.com', 'Calle 16', 456789123, 'A', 8000),
(14, 'DNI', 'Patricia', 'Cruz', 'patricia.cruz@example.com', 'Calle 17', 321654987, 'B', 6000),
(15, 'DNI', 'Jorge', 'Salazar', 'jorge.salazar@example.com', 'Calle 18', 654987321, 'C', 2000),
(16, 'DNI', 'Verónica', 'Pérez', 'veronica.perez@example.com', 'Calle 19', 987321654, 'A', 9000),
(17, 'DNI', 'Esteban', 'Rojas', 'esteban.rojas@example.com', 'Calle 20', 321987654, 'B', 7000),
(18, 'DNI', 'Nadia', 'Vásquez', 'nadia.vasquez@example.com', 'Calle 21', 654321987, 'C', 4000),
(19, 'DNI', 'Raúl', 'Mendoza', 'raul.mendoza@example.com', 'Calle 22', 789654123, 'A', 11000),
(20, 'DNI', 'Santiago', 'González', 'santiago.gonzalez@example.com', 'Calle 23', 159753486, 'B', 5000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credito`
--

CREATE TABLE `credito` (
  `idCredito` varchar(9) NOT NULL,
  `fk_idCliente` int(12) NOT NULL,
  `fk_idUsuario` int(12) NOT NULL,
  `montoCredito` int(8) NOT NULL,
  `emiCredito` date NOT NULL,
  `venCredito` date NOT NULL,
  `pagoCredito` enum('Sin pagar','Pago parcial','Pagado') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `credito`
--

INSERT INTO `credito` (`idCredito`, `fk_idCliente`, `fk_idUsuario`, `montoCredito`, `emiCredito`, `venCredito`, `pagoCredito`) VALUES
('FC000001', 1, 1, 100000, '2025-07-05', '2025-07-19', 'Sin pagar'),
('FC00002', 1, 1, 10000, '2025-07-05', '2025-07-20', 'Sin pagar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_credito`
--

CREATE TABLE `detalle_credito` (
  `idDetCredito` int(8) NOT NULL,
  `fk_idProducto` int(4) NOT NULL,
  `fk_idCredito` varchar(8) NOT NULL,
  `cantidad` int(6) NOT NULL,
  `totalPrecio` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalle_credito`
--

INSERT INTO `detalle_credito` (`idDetCredito`, `fk_idProducto`, `fk_idCredito`, `cantidad`, `totalPrecio`) VALUES
(2, 16, 'FC00002', 2, 4000),
(3, 19, 'FC00002', 2, 6000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `idPago` int(8) NOT NULL,
  `fkIdCredito` varchar(8) NOT NULL,
  `montoPago` int(8) NOT NULL,
  `tipoPago` enum('Parcial','Total') NOT NULL,
  `fechaPago` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(4) NOT NULL,
  `nomProducto` varchar(40) NOT NULL,
  `valProducto` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `nomProducto`, `valProducto`) VALUES
(1, 'Pan tajado', 3500),
(2, 'Leche entera', 4200),
(3, 'Arroz x500g', 2800),
(4, 'Azúcar x500g', 2700),
(5, 'Sal refinada', 1500),
(6, 'Huevos x6', 5000),
(7, 'Café molido x125g', 4500),
(8, 'Chocolate en pastillas', 3200),
(9, 'Aceite vegetal x500ml', 6300),
(10, 'Pasta espagueti', 2300),
(11, 'Fríjoles x500g', 3700),
(12, 'Lentejas x500g', 3600),
(13, 'Atún en lata', 5800),
(14, 'Galletas surtidas', 2500),
(15, 'Gaseosa 600ml', 2800),
(16, 'Agua embotellada 600ml', 2000),
(17, 'Jugo en caja', 2200),
(18, 'Jabón de ropa barra', 1800),
(19, 'Detergente x250g', 3000),
(20, 'Papel higiénico x2', 4200),
(21, 'Desodorante', 5800),
(22, 'Champú sachet', 1500),
(23, 'Avena en polvo', 3100),
(24, 'Harina de maíz', 3500),
(25, 'Mayonesa pequeña', 2600),
(26, 'Salsa de tomate', 2900),
(27, 'Cubitos de caldo', 1200),
(28, 'Chocorramo', 2200),
(29, 'Arequipe pequeño', 2400),
(30, 'Velas pequeñas x2', 1000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(12) NOT NULL,
  `emaUsuario` varchar(40) NOT NULL,
  `passUsuario` varchar(3) NOT NULL,
  `nomUsuario` varchar(30) NOT NULL,
  `apeUsuario` varchar(30) NOT NULL,
  `rolUsuario` enum('Administrador','Empleado') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `emaUsuario`, `passUsuario`, `nomUsuario`, `apeUsuario`, `rolUsuario`) VALUES
(1, 'administrador@gmail.com', '123', 'administrador', 'usuario', 'Administrador'),
(2, 'empleado@gmail.com', '123', 'empleado', 'usuario', 'Empleado');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `credito`
--
ALTER TABLE `credito`
  ADD PRIMARY KEY (`idCredito`),
  ADD KEY `fk_idCliente` (`fk_idCliente`),
  ADD KEY `fk_idUsuario` (`fk_idUsuario`);

--
-- Indices de la tabla `detalle_credito`
--
ALTER TABLE `detalle_credito`
  ADD PRIMARY KEY (`idDetCredito`),
  ADD KEY `fk_idProducto` (`fk_idProducto`),
  ADD KEY `fk_idCredito` (`fk_idCredito`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`idPago`),
  ADD KEY `fk_idCredito` (`fkIdCredito`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `detalle_credito`
--
ALTER TABLE `detalle_credito`
  MODIFY `idDetCredito` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `idPago` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1029;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idProducto` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `credito`
--
ALTER TABLE `credito`
  ADD CONSTRAINT `credito_ibfk_1` FOREIGN KEY (`fk_idCliente`) REFERENCES `clientes` (`idCliente`),
  ADD CONSTRAINT `credito_ibfk_2` FOREIGN KEY (`fk_idUsuario`) REFERENCES `usuarios` (`idUsuario`);

--
-- Filtros para la tabla `detalle_credito`
--
ALTER TABLE `detalle_credito`
  ADD CONSTRAINT `detalle_credito_ibfk_1` FOREIGN KEY (`fk_idProducto`) REFERENCES `producto` (`idProducto`),
  ADD CONSTRAINT `detalle_credito_ibfk_2` FOREIGN KEY (`fk_idCredito`) REFERENCES `credito` (`idCredito`);

--
-- Filtros para la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`fkIdCredito`) REFERENCES `credito` (`idCredito`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
