$(".special.cards .image").dimmer({
  on: "hover"
});

var req = new XMLHttpRequest();
var url = "http://127.0.0.1:880/vagas";

get();

async function get() {
  try {
    let response = await $.getJSON("http://127.0.0.1:880/vagas");
    let vagas = "";
    response.vagas.forEach(element => {
      vagas += `<div class="ui stackable grid container">
    <div class="four wide column">
      <div class="ui card">
        <div class="ui slide masked reveal image">
          <img src="imgs/vaga.JPG" class="visible content">
          <img src="imgs/vaga2.jpg" class="hidden content">
        </div>
        <div class="content">
          <a class="header" >${element.Localizacao.Numero}</a>
          <div class="meta">
            <span class="description">Breve descricao</span>
          </div>
        </div>
        <div class="extra content">
          <a>
            <i class="table icon"></i>
            Média
          </a>
          <br>
          <a>
            <i class="phone icon"></i>
            (DD) XXXXX-XXXX
          </a>
          <br>
          <a>
            <i class="dollar sign icon"></i>
            15,00/dia
          </a>
        </div>
      </div>
    </div>
  </div>
  </div>`;
    });
    document.getElementById("vagas").innerHTML = vagas;
  } catch (errors) {
    console.log(errors);
  }
}
