console.log("JS cargado correctamente");
document.addEventListener("DOMContentLoaded", function () {

    document.querySelectorAll(".btnEditar").forEach(button => {
        button.addEventListener("click", function() {

            document.getElementById("editIdUsuario").value = this.dataset.id;
            document.getElementById("editNombre").value = this.dataset.nombre;
            document.getElementById("editApellidoPaterno").value = this.dataset.apellidopaterno;
            document.getElementById("editApellidoMaterno").value = this.dataset.apellidomaterno;
            document.getElementById("editFechaNacimiento").value = this.dataset.fechanacimiento;
            document.getElementById("editEmail").value = this.dataset.email;
            document.getElementById("editTelefono").value = this.dataset.telefono;
            document.getElementById("editCelular").value = this.dataset.celular;
            document.getElementById("editUsername").value = this.dataset.username;
            document.getElementById("editCurp").value = this.dataset.curp;
            
            let sexo = this.dataset.sexo;
            document.querySelectorAll('input[name="sexo"]').forEach(radio => {radio.checked = radio.value === sexo;});

        });
    });

    document.querySelectorAll('.btnEditarDireccion').forEach(button => {
        button.addEventListener('click', () => {
            document.getElementById('direccionId').value = button.getAttribute('data-id');
            document.getElementById('direccionIdUsuario').value = button.getAttribute('data-idusuario');
            document.getElementById('direccionCalle').value = button.getAttribute('data-calle');
            document.getElementById('direccionNoExt').value = button.getAttribute('data-noext');
            document.getElementById('direccionNoInt').value = button.getAttribute('data-noint');
            document.getElementById('direccionColonia').value = button.getAttribute('data-colonia');
            document.getElementById('direccionCP').value = button.getAttribute('data-cp');
            document.getElementById('direccionMunicipio').value = button.getAttribute('data-municipio');
            document.getElementById('direccionEstado').value = button.getAttribute('data-estado');
            document.getElementById('direccionPais').value = button.getAttribute('data-pais');
        });
    });


});
