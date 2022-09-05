<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="../../assets/css/home.css">
    <script>
        window.addEventListener("load", () => {
            let mensagem = document.getElementById("mensagem");
            let usuario = JSON.parse(sessionStorage.getItem("usuario"));
            let nome = usuario.nome;

            if (usuario.cargo === 1 || usuario.cargo === "1") {
                document.getElementById("liAdmin").style.display = "block";
            } else {
                document.getElementById("liAdmin").style.display = "none";
            }

            mensagem.innerHTML = "<h2>Olá, " + nome + "</h2>";
        });


        function sair($event) {

            event.preventDefault();

            sessionStorage.clear();

            window.location.replace("../../index.jsp");
        }
    </script>
</head>

<body>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="home.jsp">IFG</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a id="inicio" class="nav-link active" aria-current="page" href="home.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a id="noticias" class="nav-link" href="noticias.jsp">Noticias</a>
                </li>
                <li class="nav-item">
                    <a id="editais" class="nav-link" href="editais.jsp">Editais</a>
                </li>
                <li class="nav-item" id="liAdmin">
                    <a id="admin" class="nav-link" href="admin.jsp">Administração</a>
                </li>
            </ul>

            <button class="btn btn-outline-success btn-lg" onclick="sair(event)" type="button">
                Sair
            </button>

        </div>
    </div>
</nav>
<div id="mensagem">
</div>



<div class="container hero">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous">
    </script>
</div>
</body>

</html>
