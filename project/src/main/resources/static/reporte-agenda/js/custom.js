$(document).ready(function() {
    var table = $('#example').DataTable({
        buttons: [
            {
                extend: 'excel',
                text: '<i class="fas fa-file-excel"></i>', 
                titleAttr: 'Exportar a Excel'
            },
            {
                extend: 'pdf',
                text: '<i class="fa-solid fa-file-pdf"></i> ', 
                titleAttr: 'Exportar a PDF'
            },
            {
                extend: 'print',
                text: '<i class="fas fa-print"></i> ', 
                titleAttr: 'Imprimir'
            }
        ]
    });

    table.buttons().container()
        .appendTo('#example_wrapper .col-md-6:eq(0)');
});

function eliminarReporte(id) {
    if (confirm('¿Estás seguro de querer eliminar este reporte?')) {
        var formData = new FormData();
        formData.append('id', id);
        
        fetch('/eliminarReporte', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (response.ok) {
                alert('Reporte eliminado correctamente.');
                window.location.reload(); // Recargar la página para reflejar los cambios
            } else {
                alert('Ocurrió un error al tratar de eliminar el reporte.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Ocurrió un error al tratar de eliminar el reporte.');
        });
    }
}


