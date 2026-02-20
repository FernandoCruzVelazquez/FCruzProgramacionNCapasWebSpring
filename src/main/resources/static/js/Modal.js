console.log("JS cargado correctamente");

function cargarUsuario(btn) {

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


    let idRol = btn.dataset.idrol;
    document.getElementById("rol").value = idRol;


    let sexo = btn.dataset.sexo.trim();

    if (sexo === "M") {
        document.getElementById("sexoM").checked = true;
    } else if (sexo === "F") {
        document.getElementById("sexoF").checked = true;
    }
}

async function cargarSelect(url, selectId, idSeleccionar = null) {
    try {
        console.log(`Solicitando: ${url}`); 
        const response = await fetch(url);
        const result = await response.json();
        const select = document.getElementById(selectId);

        select.innerHTML = '<option value="">-- Seleccione --</option>';

        if (result.correct && result.objects) {
            result.objects.forEach(obj => {
                const option = document.createElement("option");
                
                const idKey = Object.keys(obj).find(key => key.toLowerCase().includes("id"));
                
                option.value = obj[idKey];
                option.text = obj.nombre;
                
                if (idSeleccionar && obj[idKey] == idSeleccionar) {
                    option.selected = true;
                }
                select.appendChild(option);
            });
            console.log(`Cargado ${selectId} con ${result.objects.length} elementos`);
        }
    } catch (error) {
        console.error(`Error en fetch para ${selectId}:`, error);
    }
}

document.querySelectorAll('.btnEditarDireccion').forEach(button => {
    button.addEventListener('click', async () => {

        console.log($(button).data("idpais"));
        console.log($(button).data("idestado"));
        const idPais = button.getAttribute('data-idpais');
        const idEstado = button.getAttribute('data-idestado');
        const idMunicipio = button.getAttribute('data-idmunicipio');
        const idColonia = button.getAttribute('data-idcolonia');

        console.log("Valores recibidos del botón:", {idPais, idEstado, idMunicipio, idColonia});

        document.getElementById('direccionId').value = button.getAttribute('data-id');
        document.getElementById('direccionCalle').value = button.getAttribute('data-calle');
        document.getElementById('direccionNoExt').value = button.getAttribute('data-noext');
        document.getElementById('direccionNoInt').value = button.getAttribute('data-noint');
        document.getElementById('direccionCP').value = button.getAttribute('data-cp');

        const selectPais = document.getElementById('direccionPais');
        selectPais.value = idPais;

        if (idPais && idPais !== "null") {
            await cargarSelect(`/Usuario/getEstadosByPais/${idPais}`, 'direccionEstado', idEstado);
            
            if (idEstado && idEstado !== "null") {
                await cargarSelect(`/Usuario/getMunicipioByEstado/${idEstado}`, 'direccionMunicipio', idMunicipio);
                
                if (idMunicipio && idMunicipio !== "null") {
                    await cargarSelect(`/Usuario/getColoniaByMunicipios/${idMunicipio}`, 'direccionColonia', idColonia);
                }
            }
        }
    });
});

document.getElementById('direccionPais').addEventListener('change', function() {
    const idPais = this.value;
    document.getElementById('direccionEstado').innerHTML = '<option value="">-- Seleccione --</option>';
    document.getElementById('direccionMunicipio').innerHTML = '<option value="">-- Seleccione --</option>';
    document.getElementById('direccionColonia').innerHTML = '<option value="">-- Seleccione --</option>';
    if (idPais) cargarSelect(`/Usuario/getEstadosByPais/${idPais}`, 'direccionEstado');
});

document.getElementById('direccionEstado').addEventListener('change', function() {
    const idEstado = this.value;
    document.getElementById('direccionMunicipio').innerHTML = '<option value="">-- Seleccione --</option>';
    document.getElementById('direccionColonia').innerHTML = '<option value="">-- Seleccione --</option>';
    if (idEstado) cargarSelect(`/Usuario/getMunicipioByEstado/${idEstado}`, 'direccionMunicipio');
});

document.getElementById('direccionMunicipio').addEventListener('change', function() {
    const idMunicipio = this.value;
    document.getElementById('direccionColonia').innerHTML = '<option value="">-- Seleccione --</option>';
    if (idMunicipio) cargarSelect(`/Usuario/getColoniaByMunicipios/${idMunicipio}`, 'direccionColonia');
});

document.getElementById('direccionColonia').addEventListener('change', async function() {
    const idColonia = this.value;
    const idMunicipio = document.getElementById('direccionMunicipio').value;
    if (idColonia && idMunicipio) {
        const response = await fetch(`/Usuario/getColoniaByMunicipios/${idMunicipio}`);
        const result = await response.json();
        const colonia = result.objects.find(c => c.idColonia == idColonia);
        document.getElementById('direccionCP').value = colonia ? colonia.codigoPostal : "";
    }
});