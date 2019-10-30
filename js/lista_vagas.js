async function reply_click(clicked_id) {
  try {
    console.log(clicked_id);
    $(() => {
      $(".ui.test.modal")
        .modal({
          closable: false,
          inverted: true
        })
        .modal("attach events", `#${clicked_id}`, "show");
    });
  } catch (errors) {
    console.log(errors);
  }
}

// A função de pegar o id é essa:
// function reply_click(clicked_id) {
//   console.log(clicked_id);
// }

// A função de dar o "show" no modal:
// $(() => {
//   $(".ui.test.modal")
//     .modal({
//       closable: false,
//       inverted: true
//     })
//     .modal("attach events", `#${clicked_id}`, "show");
// });

get();

async function get() {
  try {
    let response = await $.getJSON("http://127.0.0.1:880/vagas");

    console.log(response);

    let vagas = "";
    response.vagas.forEach((element, index) => {
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
          <button class="btn card_btn" id="${index}">Alugar</button>
        </div>
      </div>
    </li>
    <div class="ui test modal" id="">
    <div class="ui icon header">
      <i class="user outline icon"></i>
    </div>
    <div class="content" class="ui grid">

      <h2>Locador: </h2>
      <p>Nome: Miguel Rodrigues</p>
      <p>Indicador da vaga: 402B</p>
      <h2>Localização: </h2>
      <p>Rua: Cláudio Manoel, 546</p>
      <p>Bairro: Savassi</p>
      <p>Cidade: Belo Horizonte</p>
      <p>Estado: Minas Gerais</p>
      <h2>Dimensões:</h2>
      <p>Altura: 4m</p>
      <p>Largura: 6m</p>
      <p>Comprimento: 7m</p>
      <p class="confirmacao">Confirmar aluguel da vaga?</p>

    </div>
    <div class="actions">
      <div class="ui red cancel inverted button">
        <i class="remove icon"></i>
        Cancelar
      </div>
      <div class="ui green ok inverted button">
        <i class="checkmark icon"></i>
        Alugar
      </div>
    </div>
  </div>`;
    });

    document.getElementById("vagas").innerHTML += vagas;
  } catch (errors) {
    console.log(errors);
  }
}
