<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="../../assets/css/home.css">
    <script src="../../assets/js/admin.js"></script>
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
                    <a id="editais" class="nav-link" href="editais.jsp">Editais</a>
                </li>
                <li class="nav-item">
                    <a id="admin" class="nav-link active">Administração</a>
                </li>
            </ul>

            <button class="btn btn-outline-success btn-lg" onclick="sair(event)" type="button">
                Sair
            </button>

        </div>
    </div>
</nav>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<div class="container">
    <div class="box">
        <div class="w-100 p-3" >

        </div>
        <blockquote class="blockquote text-center">
            <h2> Usuarios Cadastrados: </h2>
        </blockquote>


        <table class="table table-striped table-bordered table-hover table-dark" page-content pg-5 >
            <thead class="thead-dark">
            <tr>
                <th scope="col">Usuario</th>
                <th scope="col">Email </th>
                <th scope="col">Cargo </th>
                <th scope="col"> </th>
                <th scope="col"> </th>
            </tr>
            </thead>

            <tbody id="usuarios">

            </tbody>
        </table>
    </div>
</div>

</body>


</html>
