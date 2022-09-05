function cadastrar($event) {

    event.preventDefault();

    let url = "https://segundo-bimestre-java.herokuapp.com/api/usuarios/cadastrar";

    const formData = new FormData(document.querySelector('form'));

    let dados = {
        "email": formData.get("inEmail"),
        "nome": formData.get("inNome"),
        "senha": formData.get("inSenha")
    };

    let dadosJson;

    try {
        dadosJson = JSON.stringify(dados);
    } catch ( error ) {
        console.log('Error parsing JSON:', error, data);
    }

    fetch(url, {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: dadosJson
    })
        .then((res) => {
            if (res.ok) {
                console.log("Sucesso no cadastro");
                window.location.replace("../../index.jsp");
            } else {
                alert("Falha no cadastro");
            }
            return res;
        })
        .then((res) => res.json())
        .then((data) => sessionStorage.setItem("usuario", JSON.stringify(data)))
        .catch((error) => {
            console.log(error);
        });


}
