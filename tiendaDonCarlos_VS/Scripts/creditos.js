document.addEventListener("DOMContentLoaded", function () {
  // Base de datos unificada con clientes y sus contactos
  const clientes = [
    { nombre: "CRISTIAN DUVAN VASQUEZ MOYANO", id: "1030690985", correo: "duvan@correo.com" },
    { nombre: "CRISTIAN CAMILO GIL GONZALEZ", id: "1000469592", correo: "camilo@correo.com" },
    { nombre: "CRISTIAN ROA CONSTRUCCIONES SAS", id: "901651152", correo: "roa@correo.com" },
    { nombre: "Cristobal Gil Buitrago Gil", id: "74356721", correo: "gil@correo.com" },
    { nombre: "CRISTOBAL QUINTERO GIRALDO", id: "19409107", correo: "quintero@correo.com" },
    { nombre: "VICARIA EPISCOPAL TERRITORIAL DE CRISTO SACERDOTES", id: "800200820", correo: "vicaria@correo.com" },
    { nombre: "CRISTIAN IBAÑEZ PERALTA", id: "1015409548", correo: "ibanez@correo.com" }
  ];

  // Elementos del DOM
  const inputCliente = document.getElementById("idCliente");
  const inputContacto = document.getElementById("emaCliente");
  const resultado = document.getElementById("resultado");
  const fechaDisplay = document.getElementById("fechaElaboracion");
  const fechaInput = document.getElementById("emiCredito");
  const btnCalendario = document.getElementById("btnCalendario");
  const fechaSelector = document.getElementById("fechaSelector");

  // Establecer fecha actual al cargar la página
  function establecerFechaActual() {
    const ahora = new Date();
    const fechaFormateada = ahora.toLocaleDateString('es-CO');
    fechaDisplay.value = fechaFormateada; // Cambié textContent por value
    fechaInput.value = ahora.toISOString().split('T')[0]; // Formato YYYY-MM-DD
    fechaSelector.value = ahora.toISOString().split('T')[0]; // Sincronizar selector
  }

  // Función para actualizar la fecha mostrada
  function actualizarFecha(fechaISO) {
    const fecha = new Date(fechaISO + 'T00:00:00'); // Evitar problemas de zona horaria
    const fechaFormateada = fecha.toLocaleDateString('es-CO');
    fechaDisplay.value = fechaFormateada; // Cambié textContent por value
    fechaInput.value = fechaISO;
  }

  // Event listener para el botón del calendario
  btnCalendario.addEventListener("click", () => {
    fechaSelector.showPicker(); // Abre el selector de fecha nativo
  });

  // Event listener para cuando se cambia la fecha
  fechaSelector.addEventListener("change", () => {
    if (fechaSelector.value) {
      actualizarFecha(fechaSelector.value);
    }
  });

  // Ocultar selector si se hace clic fuera
  document.addEventListener("click", (e) => {
    if (!resultado.contains(e.target) && e.target !== inputCliente) {
      resultado.style.display = "none";
    }
  });

  // Ejecutar al cargar
  establecerFechaActual();

  inputCliente.addEventListener("input", () => {
    const valor = inputCliente.value.toLowerCase();
    resultado.innerHTML = "";

    if (valor.trim() === "") {
      resultado.style.display = "none";
      // Limpiar contacto si se borra el cliente
      inputContacto.value = "";
      return;
    }

    const filtrados = clientes.filter(c => c.nombre.toLowerCase().includes(valor));

    filtrados.forEach(c => {
      const div = document.createElement("div");
      div.textContent = `${c.nombre} (${c.id})`;
      div.onclick = () => {
        inputCliente.value = c.nombre;
        inputContacto.value = c.correo; // Llenar automáticamente el contacto
        resultado.style.display = "none";
      };
      resultado.appendChild(div);
    });

    const crearNuevo = document.createElement("div");
    crearNuevo.id = "crear-nuevo";
    crearNuevo.textContent = "+ Crear nuevo";
    crearNuevo.onclick = () => {
      alert("Abrir formulario de creación de cliente");
      resultado.style.display = "none";
    };
    resultado.appendChild(crearNuevo);

    resultado.style.display = "block";
  });

  document.addEventListener("click", (e) => {
    if (!resultado.contains(e.target) && e.target !== inputCliente) {
      resultado.style.display = "none";
    }
  });
});