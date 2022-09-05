var usuarios;

window.addEventListener("load", () => {
    let usuario = JSON.parse(sessionStorage.getItem("usuario"));

    adicionarCamposLista();
});

function adicionarCamposLista() {

    let data = getUsuarios();
    let lista = JSON.parse(data);

    usuarios = [];

    lista.forEach(elemento => {
        usuarios.push({
            nome: elemento.nome,
            email: elemento.email,
            cargo: elemento.cargo
        });
    });

    exibirUsuarios();
}

function getUsuarios() {
    let url = "https://segundo-bimestre-java.herokuapp.com/api/usuarios";
    let request = new XMLHttpRequest();
    request.open("GET", url, false);
    request.send();
    return request.responseText;
}

function sair($event) {

    event.preventDefault();

    sessionStorage.clear();

    window.location.replace("../../index.jsp");
}


function atualizarCargo(pos) {
    let url = "https://segundo-bimestre-java.herokuapp.com/api/usuarios/atualizar/cargo/" + usuarios[pos].email;
    fetch(url,{
        method: 'PUT',
        headers:{
            'Content-Type':'application/json'
        },
    })
        .then((res) => {
            console.log(res);
            location.reload();
        })
    .catch((error) => console.log(error));

}

function deletar(pos) {
    let url = "https://segundo-bimestre-java.herokuapp.com/api/usuarios/deletar/" + usuarios[pos].email;

    fetch(url,{
        method: 'DELETE',
        headers:{
            'Content-Type':'application/json'
        },
    })
        .then((res) => {
            console.log(res.status);
            location.reload();
        })
        .catch((error) => console.log(error));


}



function exibirUsuarios() {
    let elemento = "";
    let x1 = "<td>";
    let x2 = "</td>";
    for (let i = 0; i < usuarios.length; i++) {
        let cargoNome;

        let usuario = JSON.parse(sessionStorage.getItem("usuario"));
        let email = usuarios[i].email.toString();

        if (usuarios[i].email !== usuario.email) {
            if (usuarios[i].cargo === 1) {
                cargoNome = "administrador";
            } else if (usuarios[i].cargo === 0) {
                cargoNome = "default";
            } else {
                cargoNome = "jornalista";
            }

            elemento += "<tr>";
            elemento += x1 + usuarios[i].nome + x2;
            elemento += x1 + email + x2;
            elemento += x1 + cargoNome + x2;
            elemento +=
                x1 + "<button onClick='atualizarCargo(" + i + ")'>Atualizar Cargo</button>" + x2;
            elemento +=
                x1 + "<button onClick='deletar(" + i + ")'>Excluir</button>" + x2;
            elemento += "</tr>";
        }
    }

    let mostrar = document.getElementById("usuarios");
    mostrar.innerHTML = elemento;


}
