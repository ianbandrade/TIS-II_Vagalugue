$(".special.cards .image").dimmer({
  on: "hover"
});

var req = new XMLHttpRequest();
var url = "http://127.0.0.1:880/vagas";

get();

async function get() {
  try {
    let response = await $.getJSON("http://127.0.0.1:880/vagas");

    console.log(response);

    let vagas = "";
    response.vagas.forEach(element => {
      vagas += `
      <li class="cards_item">
      <div class="card">
        <div class="card_image"><img class="card-img" src="${
          element["Foto"]
        }"></div>
        <div class="card_content">
          <h2 class="card_title">${element["Localizacao"]["Endereco"]}, ${
        element.Localizacao.Numero
      }</h2>
          <p class="card_text">${element["Descricao"]}
          </p><br><h5>Dimensoes: ${element.Dimensoes.Comprimento}m x ${
        element.Dimensoes.Altura
      }m x ${element.Dimensoes.Altura}m </h5>
          <button class="btn card_btn">Alugar</button>
        </div>
      </div>
    </li>`;
    });
    document.getElementById("vagas").innerHTML += vagas;
  } catch (errors) {
    console.log(errors);
  }
}
