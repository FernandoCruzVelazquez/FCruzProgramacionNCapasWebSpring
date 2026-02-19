console.log("JS cargado correctamente");

function cargarUsuario(btn) {

    const sexo = btn.dataset.sexo;
    const idRol = btn.dataset.idrol;

    console.log("sexo:", sexo);
    console.log("rol:", idRol);


    document.getElementById("sexoM").checked = false;
    document.getElementById("sexoF").checked = false;

    if (sexo === "M") {
        document.getElementById("sexoM").checked = true;
    } else if (sexo === "F") {
        document.getElementById("sexoF").checked = true;
    }


    const selectRol = document.getElementById("rol");
    selectRol.value = idRol;


    selectRol.dispatchEvent(new Event("change"));


    document.getElementById("idUsuario").value = btn.dataset.id;
    document.getElementById("userName").value = btn.dataset.username;
    document.getElementById("nombre").value = btn.dataset.nombre;
    document.getElementById("apellidoPaterno").value = btn.dataset.appaterno;
    document.getElementById("apellidoMaterno").value = btn.dataset.apmaterno;
    document.getElementById("email").value = btn.dataset.email;
    document.getElementById("telefono").value = btn.dataset.telefono;
    document.getElementById("celular").value = btn.dataset.celular;
    document.getElementById("CURP").value = btn.dataset.curp;
    document.getElementById("fechaNacimiento").value = btn.dataset.fechanacimiento;
    document.getElementById("password").value = btn.dataset.password;
}




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