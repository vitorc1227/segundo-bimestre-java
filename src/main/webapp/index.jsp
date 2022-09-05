<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/login.css">
    <script src="assets/js/login.js"></script>
    <script src="assets/js/nao_voltar.js"></script>
    <script>
        window.addEventListener("load", () => {
            document.getElementById("alertError").style.display = "none";
        });
    </script>
</head>

<body>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div id="form" class="card text-black" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <form method="post">
                            <div class="mb-md-5 mt-md-4 pb-5">

                                <h2 class="fw-bold mb-2 text-uppercase mb-4">Login</h2>

                                <div class="form-outline form-white mb-4 ">
                                    <input type="email" class="form-control form-control-lg" id="inEmail"
                                           name="inEmail" placeholder="Email">
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="password" class="form-control form-control-lg" id="inSenha"
                                           name="inSenha" placeholder="Senha">
                                </div>
                                <div>

                                    <div class="alert alert-danger" id="alertError" role="alert">
                                        Seu email ou senha estão errados!
                                    </div>

                                </div>

                                <p class="small mb-2 pb-lg-2"><a class="text-black-50" href="#!">Esqueceu a senha?</a>
                                </p>

                                <button style="border-radius: 20px;" class="btn btn-outline-dark btn-lg px-5"
                                        type="submit" onclick="entrar(event)" value="Login">Login
                                </button>

                            </div>
                        </form>
                        <div>
                            <p class="mb-0">Não possui uma conta? <a href="usuario/view/cadastro.jsp"
                                                                     class="text-black-50 fw-bold">Faça
                                uma.</a>
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>

</html>
