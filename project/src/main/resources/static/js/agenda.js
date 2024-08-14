const daysTag = document.querySelector(".days"),
currentDate = document.querySelector(".current-date"),
prevNextIcon = document.querySelectorAll(".icons span"),
datetimeInput = document.querySelector('input[type="datetime-local"]');

let date = new Date(),
currYear = date.getFullYear(),
currMonth = date.getMonth();

const months = [
"Enero",
"Febrero",
"Marzo",
"Abril",
"Mayo",
"Junio",
"Julio",
"Agosto",
"Septiembre",
"Octubre",
"Noviembre",
"Diciembre",
];

const renderCalendar = () => {
let firstDayofMonth = new Date(currYear, currMonth, 1).getDay(),
    lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate(),
    lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay(),
    lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate();
let liTag = "";

for (let i = firstDayofMonth; i > 0; i--) {
    liTag += `<li class="inactive">${lastDateofLastMonth - i + 1}</li>`;
}

for (let i = 1; i <= lastDateofMonth; i++) {
    let isToday =
        i === date.getDate() &&
        currMonth === new Date().getMonth() &&
        currYear === new Date().getFullYear()
            ? "active"
            : "";
    let isPastDay = new Date(currYear, currMonth, i) < new Date() ? "inactive" : "";
    liTag += `<li class="${isToday} ${isPastDay}" onclick="setDate(${i})">${i}</li>`;
}

for (let i = lastDayofMonth; i < 6; i++) {
    liTag += `<li class="inactive">${i - lastDayofMonth + 1}</li>`;
}
currentDate.innerText = `${months[currMonth]} ${currYear}`;
daysTag.innerHTML = liTag;
};
renderCalendar();

datetimeInput.min = new Date().toISOString().substring(0, 16);

function setDate(day) {
let inputDatetime = document.querySelector('input[type="datetime-local"]');
  
  // Crear una nueva fecha con la hora fija a las 07:00
let selectedDate = new Date(currYear, currMonth, day, 7, 0);

  // Validar si la hora seleccionada es después de las 7:00 a. m.
  if (selectedDate.getTime() < new Date().getTime()) {
    alert("Esta fecha no es valida, por favor seleccione un horario valido.");
    return;
  }

  // Formatear la fecha
  let formattedDate = selectedDate.toISOString().substring(0, 10) + "T07:00";
  inputDatetime.value = formattedDate;
}

prevNextIcon.forEach((icon) => {
icon.addEventListener("click", () => {
    currMonth = icon.id === "prev" ? currMonth - 1 : currMonth + 1;

    if (currMonth < 0 || currMonth > 11) {
        date = new Date(currYear, currMonth, new Date().getDate());
        currYear = date.getFullYear();
        currMonth = date.getMonth();
    } else {
        date = new Date();
    }
    renderCalendar();
});
});

function limpiarCampos() {
document.getElementById('empleado').value = '';
document.getElementById('Empleado').value='';
document.getElementById('Paciente').value='';
document.getElementById('inicio_fh').value = '';
document.getElementById('fin_fh').value = '';
document.getElementById('direccion').value = '';
document.getElementById('servicio').value = '';
document.getElementById('paciente').value = '';
document.getElementById('horario').value = '';
}

document.getElementById('btnCancelar').onclick = limpiarCampos;

document.addEventListener("DOMContentLoaded", function() {
    // Escuchar cambios en el select
    document.getElementById('empleado').addEventListener('change', function() {
        // Actualiza el campo de identificación del empleado
        document.getElementById('Empleado').value = this.value;
    });

    document.getElementById('paciente').addEventListener('change', function() {
        // Actualiza el campo de identificación del paciente
        document.getElementById('Paciente').value = this.value;
    });

    document.getElementById('Empleado').addEventListener('input', function() {
        const id = this.value;
        const select = document.getElementById('empleado');
        let found = false;

        for (let i = 0; i < select.options.length; i++) {
            if (select.options[i].value === id) {
                select.selectedIndex = i;
                found = true;
                break;
            }
        }

        if (!found) select.selectedIndex = 0;
    });

    document.getElementById('Paciente').addEventListener('input', function() {
        const id = this.value;
        const select = document.getElementById('paciente');
        let found = false;

        for (let i = 0; i < select.options.length; i++) {
            if (select.options[i].value === id) {
                select.selectedIndex = i;
                found = true;
                break;
            }
        }

        if (!found) select.selectedIndex = 0;
    });

});

function actualizarFechaFin() {
    var inicio = document.getElementById('inicio_fh').value;
    var tipoTurno = document.getElementById('horario').value;
    
    if (inicio) {
        var fechaInicio = new Date(inicio);
        var horas = 0;
        
        // Asignar horas basadas en el tipo de turno seleccionado
        if (tipoTurno === "500") {
            horas = 8;
        } else if (tipoTurno === "501") {
            horas = 12;
        } else if (tipoTurno === "502") {
            horas = 24;
        } else if (tipoTurno === "503") {
            horas = 48;
        }
        
        fechaInicio.setHours(fechaInicio.getHours() + horas);
        
        document.getElementById('fin_fh').value = formatearFechaISO(fechaInicio);
    }
}

function formatearFechaISO(fecha) {
    function pad(n) { return n < 10 ? '0' + n : n; }
    
    return fecha.getFullYear() +
        '-' + pad(fecha.getMonth() + 1) +
        '-' + pad(fecha.getDate()) +
        'T' + pad(fecha.getHours()) +
        ':' + pad(fecha.getMinutes());
}