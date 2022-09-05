<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cadastro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="../../assets/css/cadastro.css">
    <script src="../../assets/js/cadastro.js"></script>
</head>
<body>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-9 col-md-8 col-lg-10 col-xl-6">
                <form method="post">
                    <div id="form" class="card text-black" style="border-radius: 1rem;">
                        <div class="card-body p-5 text-center">

                            <div class="mb-md-5 mt-md-4 pb-5">

                                <h2 id="title" class="fw-bold text-uppercase">Cadastro</h2>

                                <div class="form-outline form-white mb-4">
                                    <input type="text" class="form-control form-control-lg" id="inNome"
                                           name="inNome" placeholder="Nome" required="true">
                                </div>

                                <div class="form-outline form-white mb-4 ">
                                    <input type="email" class="form-control form-control-lg" id="inEmail"
                                           name="inEmail" placeholder="Email" required>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="password" class="form-control form-control-lg" id="inSenha"
                                           name="inSenha" placeholder="Senha" required>
                                </div>

                                <p id="xi"></p>

                                <button id="botao" class="btn btn-outline-dark btn-lg px-5" onclick="cadastrar(event)"
                                        type="submit">
                                    Cadastrar
                                </button>
                            </div>
                            <div>
                                <p class="mb-0">Já possui uma conta? <a href="../../index.jsp"
                                                                        class="text-black-50 fw-bold">Faça
                                    login.</a>
                                </p>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>

</body>

</html>
