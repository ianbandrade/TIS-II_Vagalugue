$(document).ready(function () {
    if (localStorage.getItem("nome") != null) {
        $('.right.menu').html(
            `<a href ="usuario.html"><h4 id="first-menu">Bem Vindo, ${localStorage.getItem("nome")}</h4></a> 
    <button id="sair-btn-first" class="ui large basic button" onclick="sair()">
    Sair 
    <i class="icon sign-out"></i>
    </button>`)
        $('.right.item').html(
            `<a href = "usuario.html"><h4 id ="second-menu">Bem Vindo, ${localStorage.getItem("nome")}</h4></a>
    <button id="sair-btn-second" class="ui large button" onclick="sair()">
    Sair 
    <i class="icon sign-out"></i>
    </button>`)
    }
})

function sair() {
    localStorage.clear();
    location.reload();
}