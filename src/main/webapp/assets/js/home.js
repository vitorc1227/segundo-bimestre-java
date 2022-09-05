function sair($event) {

    event.preventDefault();

    sessionStorage.clear();

    window.location.replace("../../index.jsp");
}
