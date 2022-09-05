var editais;

window.addEventListener("load", () => {
    adicionarCamposLista();
});

function adicionarCamposLista() {

    let data = getEditais();
    let lista = JSON.parse(data);

    editais = [];

    lista.forEach(elemento => {
        editais.push({
            id: elemento.id,
            nome: elemento.nome,
            descricao: elemento.descricao,
        });
    });

    exibirEditais();
}

function getEditais() {
    let url = "https://segundo-bimestre-java.herokuapp.com/api/editais";
    let request = new XMLHttpRequest();
    request.open("GET", url, false);
    request.send();
    return request.responseText;
}

function exibirEditais() {
    let elemento = "";
    for (let i = 0; i < editais.length; i++) {
        let usuario = JSON.parse(sessionStorage.getItem("usuario"));

        elemento += "<div style='flex:0 0 auto; width:50%; margin-bottom: 20px'>";
        elemento += "<div style='background-color: rgb(174, 230, 179);  border: 1px solid rgba(0, 0, 0, 0.175); border-radius: 0.375rem'>";
        elemento += "<div style='flex:1 1 auto; border-radius: 10px;'>";
        elemento += "<p style='margin-top: 4px; font-size: 1.50rem; padding-left: 20px'>" + editais[i].nome + "</p>";
        elemento += "<h5 style='font-weight: 500; line-height: 1.2; margin-top: 0; margin-bottom: 0.5rem; font-size: 1.25rem; padding-left: 20px'>" + editais[i].descricao + "</h5>";
        if ((usuario.cargo === "2" || usuario.cargo === "1") || (usuario.cargo === 2 || usuario.cargo === 1)) {
            elemento += "<div style='text-align: center'>";
            elemento += "<button data-bs-toggle='modal' data-bs-target='#modalEdit' style='background-color: #198754; " +
                "border: 1px solid #198754; border-radius: 0.5rem; color: white; padding: 15px 20px; text-align: center; " +
                "text-decoration: none; display: inline-block; font-size: 16px; margin-right: 4px; margin-bottom: 4px;' onClick='atualizarEdital(" + i + ")'>Editar</button>";
            elemento += "<button  style='background-color: #f44336; " +
                "border: 1px solid #198754; border-radius: 0.5rem; color: white; padding: 15px 20px; text-align: center; " +
                "text-decoration: none; display: inline-block; font-size: 16px; margin-left: 4px; margin-bottom: 4px;' onClick='deletarEdital(" + i + ")'>Apagar</button>";
            elemento += "</div>";
        }
        elemento += "</div>";
        elemento += "</div>";
        elemento += "</div>";
    }

    let mostrar = document.getElementById("list");
    mostrar.innerHTML = elemento;


}

function adicionarEdital($event) {

    event.preventDefault();

    const formData = new FormData(document.querySelector("[name='formInsert']"));

    let dados = {
        "nome": formData.get("inNome"),
        "descricao": formData.get("inDescricao")
    };


    let url = "https://segundo-bimestre-java.herokuapp.com/api/editais/cadastrar";

    fetch(url, {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(dados)
    })
        .then((res) => {
            if (res.ok) {
                adicionarCamposLista();
            } else {
                console.log("Falha!");
            }
            return res;
        })
        .then((res) => res.json())
        .catch((error) => console.log(error));
}

function atualizarEdital(pos) {

    let edital = editais[pos];

    sessionStorage.setItem("id_usuario", edital.id)

    document.querySelector("[name='inIdEdit']").value = edital.id;

    document.querySelector("[name='inNomeEdit']").value = edital.nome;

    let descricao = document.getElementById("inDescricaoEdit");
    descricao.innerHTML = edital.descricao;

}

function enviarEditalAtualizado($event) {

    event.preventDefault();

    const formData = new FormData(document.querySelector("[name='formEdit']"));

    let dados = {
        "nome": formData.get("inNomeEdit"),
        "descricao": formData.get("inDescricaoEdit")
    };

    console.log(dados);


    let url = "https://segundo-bimestre-java.herokuapp.com/api/editais/atualizar/" + sessionStorage.getItem("id_usuario");
    console.log(url);
    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dados)
    })
        .then((res) => {
            console.log(res);
            location.reload();
        })
        .catch((error) => console.log(error));
}

function deletarEdital(pos) {
    let url = "https://segundo-bimestre-java.herokuapp.com/api/editais/deletar/" + editais[pos].id;

    fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then((res) => {
            console.log(res.status);
            location.reload();
        })
        .catch((error) => console.log(error));

}

function sair($event) {

    event.preventDefault();

    sessionStorage.clear();

    window.location.replace("../../index.jsp");
}
