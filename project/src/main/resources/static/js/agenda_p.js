const daysTag = document.querySelector(".days"),
  currentDate = document.querySelector(".current-date"),
  prevNextIcon = document.querySelectorAll(".icons span"),
  datetimeInput = document.querySelector('input[type="datetime-local"]');

// Obtener la nueva fecha, el año y el mes actual
let date = new Date(),
  currYear = date.getFullYear(),
  currMonth = date.getMonth();

// Almacenar el nombre completo de todos los meses en un array
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

// Supongamos que fechasAgendadas contiene fechas en formato 'YYYY-MM-DD'
const fechasAgendadas = [];


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
    let isAgendado = fechasAgendadas.includes(
      `${currYear}-${currMonth + 1}-${i < 10 ? "0" + i : i}`
    )
      ? "agendado"
      : "";
    liTag += `<li class="${isToday} ${isAgendado}" onclick="setDate(${i})">${i}</li>`;
  }

  for (let i = lastDayofMonth; i < 6; i++) {
    liTag += `<li class="inactive">${i - lastDayofMonth + 1}</li>`;
  }
  currentDate.innerText = `${months[currMonth]} ${currYear}`;
  daysTag.innerHTML = liTag;
};
renderCalendar();

// Función para establecer la fecha cuando se hace clic en un día
function setDate(day) {
  // Obtener la entrada datetime-local
  let inputDatetime = document.querySelector('input[type="datetime-local"]');
  // Crear una nueva fecha
  let selectedDate = new Date(currYear, currMonth, day);
  // Formatear fecha y hora para datetime-local
  let formattedDate = selectedDate.toISOString().substring(0, 16);
  // Establecer el valor de la entrada
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

// Función para limpiar los campos de entrada
function limpiarCampos() {
  // Limpiar los campos utilizando los IDs asignados
  document.getElementById('empleado').value = '';
  document.getElementById('inicio_fh').value = '';
  document.getElementById('direccion').value = '';   
  document.getElementById('servicio').value = '';   
  document.getElementById('paciente').value = '';   
  document.getElementById('horario').value = '';   
}

document.getElementById('btnCancelar').onclick = limpiarCampos;
