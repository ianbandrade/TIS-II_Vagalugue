$(document).ready(function () {
    var url = window.location.pathname
    var filename = url.substring(url.lastIndexOf('/') + 1).replace(".html","")

    $("#index").removeClass("active")
    $("#lista_vagas").removeClass("active")  
    $("#cadastro_vaga").removeClass("active")
    $("#mapa_vagas").removeClass("active")
    $("#desempenho").removeClass("active")
    document.getElementById(filename).classList.add("active")

    $(".ui.sidebar").sidebar("attach events", ".toc.item");

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