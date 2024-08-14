// Obtener elementos HTML
const imagenInput = document.getElementById("imagenInput");
const imagenPreview = document.getElementById("imagenPreview");
const medicinesTable = document.getElementById("medicinesTable");

// Escuchar cambios en el campo de carga de imagen
imagenInput.addEventListener("change", function () {
    const file = imagenInput.files[0];

    // Verificar si se seleccionó un archivo
    if (file) {
        // Crear un objeto FileReader para leer el archivo
        const reader = new FileReader();

        // Definir una función para cuando se cargue el archivo
        reader.onload = function (e) {
            // Asignar la imagen cargada a la previsualización
            imagenPreview.src = e.target.result;
            imagenPreview.style.display = "block";
        };

        // Leer el archivo como una URL de datos
        reader.readAsDataURL(file);
    } else {
        // Ocultar la previsualización si no se selecciona ningún archivo
        imagenPreview.src = "";
        imagenPreview.style.display = "none";
    }
});

// Esta función se ejecutará cuando se haga clic en el botón "Agregar"
function agregarFila() {
    // Obtener la tabla y su cuerpo (tbody)
    const table = document.getElementById("medicinesTable");
    const tbody = table.querySelector("tbody");

    // Crear una nueva fila
    const newRow = document.createElement("tr");

    // Crear celdas para los inputs y el botón
    const medicamentoCell = document.createElement("td");
    // const dosisCell = document.createElement("td");
    const buttonCell = document.createElement("td");

    // Crear inputs para medicamento y dosis
    const medicamentoInput = document.createElement("input");
    medicamentoInput.type = "text";
    medicamentoInput.name = "medicamento[]";
    medicamentoInput.placeholder = "Nombre del medicamento";

    // const dosisInput = document.createElement("input");
    // dosisInput.type = "text";
    // dosisInput.name = "dosis[]";
    // dosisInput.placeholder = "Dosis";
    // dosisInput.inputmode = "numeric";

    // Crear el botón para eliminar la fila
    const deleteButton = document.createElement("button");
    deleteButton.type = "button";
    deleteButton.textContent = "Eliminar";

    // Agregar un evento de clic al botón para eliminar la fila
    deleteButton.addEventListener("click", function () {
        // Eliminar la fila al hacer clic en el botón
        table.deleteRow(newRow.rowIndex);
    });

    // Agregar los inputs y el botón a las celdas correspondientes
    medicamentoCell.appendChild(medicamentoInput);
    // dosisCell.appendChild(dosisInput);
    buttonCell.appendChild(deleteButton);

    // Agregar las celdas a la nueva fila
    newRow.appendChild(medicamentoCell);
    // newRow.appendChild(dosisCell);
    newRow.appendChild(buttonCell);

    // Agregar la nueva fila al tbody de la tabla
    tbody.appendChild(newRow);
}

function actualizarFechaHora() {
    const ahora = new Date(); 

    // Formatea la fecha como "DD/MM/YYYY"
    const fecha = ahora.toLocaleDateString('es', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
    });

    let horas = ahora.getHours();
    const minutos = ahora.getMinutes();
    const esAM = horas < 12;
    if (horas === 0) {
        horas = 12; 
    } else if (horas > 12) {
        horas -= 12; 
    }

    const minutosFormateados = minutos < 10 ? `0${minutos}` : minutos;
    const hora = `${horas}:${minutosFormateados} ${esAM ? 'a.m.' : 'p.m.'}`;

    document.getElementById('fechaNota').textContent = fecha;
    document.getElementById('horaNota').textContent = hora;
}

actualizarFechaHora();

setInterval(actualizarFechaHora, 60000);



