function TraerClienteNombre() {
    // 1. Obtener el valor seleccionado del ejercicio
    var clienteId = document.getElementById("nomCliente").value;

    // 2. Agregar o actualizar el parámetro fkIdEjercicio
    params.set("idCliente", clienteId);
    params.set("accion", "TraerCliente");

    // 3. Redirigir al controlador con los parámetros
    window.location.href = "<%= contextPath%>/CreditoController?" + params.toString();
}
function TraerClienteEmail() {
    var clienteId = document.getElementById("Emaliente").value;

    params.set("idCliente", clienteId);
    params.set("accion", "TraerCliente");

    window.location.href = "<%= contextPath%>/CreditoController?" + params.toString();
}

function actualizarValorUnitario(selectElement) {
    const fila = selectElement.closest('tr');
    const precio = selectElement.options[selectElement.selectedIndex].getAttribute('data-precio');
    const inputValor = fila.querySelector('.input-valor-unitario');
    const inputCantidad = fila.querySelector('.input-cantidad');
    const inputTotal = fila.querySelector('.input-total');

    inputValor.value = precio;

    if (inputCantidad.value) {
        inputTotal.value = precio * inputCantidad.value;
    }
    actualizarTotalGeneral();
}

function actualizarTotal(cantidadInput) {
    const fila = cantidadInput.closest('tr');
    const valorUnitario = fila.querySelector('.input-valor-unitario').value;
    const totalInput = fila.querySelector('.input-total');

    totalInput.value = valorUnitario * cantidadInput.value;
    actualizarTotalGeneral();
}

function agregarFila() {
    const tabla = document.getElementById('tabla-productos');
    const nuevaFila = tabla.rows[0].cloneNode(true);

    // Reiniciar los valores de los campos
    nuevaFila.querySelector('.select-producto').selectedIndex = 0;
    nuevaFila.querySelector('.input-cantidad').value = "";
    nuevaFila.querySelector('.input-valor-unitario').value = 0;
    nuevaFila.querySelector('.input-total').value = 0;

    // Asignar nuevo número de fila
    const totalFilas = tabla.rows.length + 1;
    const celdaNumero = nuevaFila.querySelector('.row-number');
    celdaNumero.innerText = totalFilas - 1;

    // Asegurarse de que el input oculto esté limpio y exista
    // Eliminar cualquier input oculto existente (por si clonó uno viejo)
    const inputExistente = celdaNumero.querySelector('input[name="idDetalleCredito"]');
    if (inputExistente) {
        inputExistente.remove();
    }

    // Crear nuevo input oculto
    const inputHidden = document.createElement("input");
    inputHidden.type = "hidden";
    inputHidden.name = "idDetalleCredito";
    inputHidden.value = ""; // Nuevo detalle, sin ID aún

    celdaNumero.appendChild(inputHidden);

    // Agregar la fila a la tabla
    tabla.appendChild(nuevaFila);

    // Actualizar total general
    actualizarTotalGeneral();
}

function actualizarTotalGeneral() {
    const filas = document.querySelectorAll('#tabla-productos tr');
    let total = 0;

    filas.forEach(fila => {
        const inputTotal = fila.querySelector('.input-total');
        if (inputTotal && !isNaN(parseFloat(inputTotal.value))) {
            total += parseFloat(inputTotal.value);
        }
    });

    // Mostrar el total visualmente
    const totalGeneralSpan = document.getElementById('total-general');
    totalGeneralSpan.textContent = `$${total.toLocaleString('es-CO')}`;

    // Guardar el total en el input oculto
    const inputTotalHidden = document.getElementById('input-total-general');
    inputTotalHidden.value = total;
}