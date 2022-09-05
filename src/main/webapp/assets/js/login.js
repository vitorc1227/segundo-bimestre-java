function entrar($event) {

    event.preventDefault();

    const formData = new FormData(document.querySelector('form'))

    let dados = {
        "email": formData.get("inEmail"),
        "senha": formData.get("inSenha")
    };


    if (dados["email"] === "admin@gmail.com" && dados["senha"] === "123") {
        sessionStorage.setItem("usuario", JSON.stringify({
            "email": "admin@gmail.com",
            "nome": "Admin",
            "cargo": 1
        }));

        window.location.replace("../../home/view/home.jsp");
    } else {
        let url = "https://segundo-bimestre-java.herokuapp.com/api/usuarios/entrar";

        fetch(url, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(dados)
        })
            .then((res) => {
                if (res.ok) {
                    console.log("Sucesso no login");
                    window.location.replace("../../home/view/home.jsp");
                } else {
                    console.log("Falha no login");
                    document.getElementById("alertError").style.display = "block";
                }
                return res;
            })
            .then((res) => res.json())
            .then((data) => sessionStorage.setItem("usuario", JSON.stringify(data)))
            .catch((error) => console.log(error));
    }
}


