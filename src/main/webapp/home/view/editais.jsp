<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="../../assets/css/editais.css">
    <script src="../../assets/js/editais.js"></script>
    <script>
        window.addEventListener("load", () => {
            let usuario = JSON.parse(sessionStorage.getItem("usuario"));

            if (usuario.cargo === 1 || usuario.cargo === "1") {
                document.getElementById("liAdmin").style.display = "block";
                document.getElementById("btadd").style.display = "block";
            } else {
                document.getElementById("liAdmin").style.display = "none";
                document.getElementById("btadd").style.display = "none";
            }

            if (usuario.cargo === 2 || usuario.cargo === "2") {
                document.getElementById("btadd").style.display = "block";
            } else {
                document.getElementById("btadd").style.display = "none";
            }
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
                    <a id="inicio" class="nav-link" aria-current="page" href="home.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a id="noticias" class="nav-link" href="noticias.jsp">Noticias</a>
                </li>
                <li class="nav-item">
                    <a id="editais" class="nav-link active" href="editais.jsp">Editais</a>
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

<h3>
    Editais
</h3>

<div class="row me-auto mb-2 mb-lg-0" id="list" style="padding-left: 20px">
</div>

<div class="d-grid gap-2 mt-2">
    <!-- Button trigger modal -->
    <button type="button" id="btadd" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Adicionar
    </button>
</div>

<!-- Modal Inserção -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"> Novo edital
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form name="formInsert" id="formInsert">
                    <div class="form-group">
                        <label for="inNome" class="col-form-label">Nome:</label>
                        <input type="text" class="form-control" style="font-size: 1.05rem" id="inNome"
                               name="inNome">
                    </div>
                    <div class="form-group">
                        <label for="inDescricao" class="col-form-label">Descrição:</label>
                        <textarea class="form-control" style="font-size: 1.05rem" id="inDescricao"
                                  name="inDescricao"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                <button type="submit" class="btn btn-primary" onclick="adicionarEdital(event)" data-bs-dismiss="modal">
                    Enviar
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalEdit" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalEditTitle"> Editar edital
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form name="formEdit" id="formEdit">
                    <div class="form-group">
                        <label for="inIdEdit" class="col-form-label">Id:</label>
                        <input type="text" class="form-control" style="font-size: 1.05rem" id="inIdEdit"
                               name="inIdEdit" disabled>
                    </div>
                    <div class="form-group">
                        <label for="inNomeEdit" class="col-form-label">Nome:</label>
                        <input type="text" class="form-control" style="font-size: 1.05rem" id="inNomeEdit"
                               name="inNomeEdit">
                    </div>
                    <div class="form-group">
                        <label for="inDescricaoEdit" class="col-form-label">Descrição:</label>
                        <textarea class="form-control" style="font-size: 1.05rem" id="inDescricaoEdit"
                                  name="inDescricaoEdit"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                <button type="submit" class="btn btn-primary" onclick="enviarEditalAtualizado(event)"
                        data-bs-dismiss="modal">
                    Atualizar
                </button>
            </div>
        </div>
    </div>
</div>

<div class="container hero">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous">
    </script>
</div>
</body>

</html>
