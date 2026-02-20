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


async function cargarSelect(url, selectId, idKey, selectedId = null) {
    const select = document.getElementById(selectId);
    select.innerHTML = '<option value="">-- Seleccione --</option>';

    const response = await fetch(url);
    const data = await response.json();

    const lista = data.objects;
    if (!Array.isArray(lista)) return;

    lista.forEach(obj => {
        const option = document.createElement('option');

        option.value = obj[idKey];
        option.textContent = obj.Nombre;

        if (selectId === 'direccionColonia') {
            option.dataset.cp = obj.CodigoPostal;
        }

        if (String(option.value) === String(selectedId)) {
            option.selected = true;

            if (selectId === 'direccionColonia') {
                document.getElementById('direccionCP').value = obj.CodigoPostal;
            }
        }

        select.appendChild(option);
    });
}

document.getElementById('direccionColonia')
    .addEventListener('change', function () {

        const selectedOption = this.options[this.selectedIndex];

        if (selectedOption.dataset.cp) {
            document.getElementById('direccionCP').value =
                selectedOption.dataset.cp;
        } else {
            document.getElementById('direccionCP').value = '';
        }
});

document.querySelectorAll('.btnEditarDireccion').forEach(button => {
    button.addEventListener('click', async () => {

        const { idpais, idestado, idmunicipio, idcolonia } = button.dataset;

        document.getElementById('direccionId').value    = button.dataset.id;
        document.getElementById('direccionCalle').value = button.dataset.calle;
        document.getElementById('direccionNoExt').value = button.dataset.noext;
        document.getElementById('direccionNoInt').value = button.dataset.noint;
        document.getElementById('direccionCP').value    = button.dataset.cp;

        document.getElementById('direccionPais').value = idpais;

        if (idpais) {
            await cargarSelect(
                `/Usuario/getEstadosByPais/${idpais}`,
                'direccionEstado',
                'IdEstado',
                idestado
            );

            if (idestado) {
                await cargarSelect(
                    `/Usuario/getMunicipioByEstado/${idestado}`,
                    'direccionMunicipio',
                    'IdMunicipio',
                    idmunicipio
                );

                if (idmunicipio) {
                    await cargarSelect(
                        `/Usuario/getColoniaByMunicipios/${idmunicipio}`,
                        'direccionColonia',
                        'IdColonia',
                        idcolonia
                    );
                }
            }
        }
    });
});


document.getElementById('direccionPais').addEventListener('change', e => {
    limpiarSelect('direccionEstado');
    limpiarSelect('direccionMunicipio');
    limpiarSelect('direccionColonia');

    if (e.target.value) {
        cargarSelect(
            `/Usuario/getEstadosByPais/${e.target.value}`,
            'direccionEstado',
            'IdEstado'
        );
    }
});

document.getElementById('direccionEstado').addEventListener('change', e => {
    limpiarSelect('direccionMunicipio');
    limpiarSelect('direccionColonia');

    if (e.target.value) {
        cargarSelect(
            `/Usuario/getMunicipioByEstado/${e.target.value}`,
            'direccionMunicipio',
            'IdMunicipio'
        );
    }
});

document.getElementById('direccionMunicipio').addEventListener('change', e => {
    limpiarSelect('direccionColonia');

    if (e.target.value) {
        cargarSelect(
            `/Usuario/getColoniaByMunicipios/${e.target.value}`,
            'direccionColonia',
            'IdColonia'
        );
    }
});

function limpiarSelect(id) {
    document.getElementById(id).innerHTML =
        '<option value="">-- Seleccione --</option>';
}




function cargarIdUsuarioFoto(button) {
    let id = button.getAttribute("data-id");
    document.getElementById("fotoIdUsuario").value = id;
}

function previewImagen(event) {
    const reader = new FileReader();
    reader.onload = function () {
        const img = document.getElementById("previewNuevaFoto");
        img.src = reader.result;
        img.style.display = "block";
    }
    reader.readAsDataURL(event.target.files[0]);
}


document.getElementById('modalFoto').addEventListener('hidden.bs.modal', function () {

    document.querySelector('input[name="archivoFoto"]').value = "";

    const img = document.getElementById("previewNuevaFoto");
    img.src = "";
    img.style.display = "none";

    document.getElementById("fotoIdUsuario").value = "";

});

