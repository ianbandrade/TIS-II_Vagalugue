async function reply_click(clicked_id) {
  try {
    console.log(clicked_id);
    $(() => {
      $(`#modal_${clicked_id}`)
        .modal({
          closable: false,
          inverted: true
        })
        .modal("show");
    });
  } catch (errors) {
    console.log(errors);
  }
}

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
          <button class="btn card_btn" id="btn_${index}" onclick="reply_click(this.id)">Alugar</button>
        </div>
      </div>
    </li>
    <div class="ui test modal" id="modal_btn_${index}">
    <div class="ui icon header">
      <i class="user outline icon"></i>
    </div>
    <div class="content" class="ui grid">

      <h2>Locador: </h2>
      <p>Nome: ${element["Locatario"]["Nome"]} ${element["Locatario"]["Sobrenome"]}</p>
      <p>Indicador da vaga:  ${element["Indicador"]}</p>
      <h2>Localização:</h2>
      <p>Rua: ${element["Localizacao"]["Endereco"]}, ${element["Localizacao"]["Numero"]}</p>
      <p>Bairro: ${element["Localizacao"]["Bairro"]}</p>
      <p>Cidade: ${element["Localizacao"]["Cidade"]}</p>
      <p>Estado: ${element["Localizacao"]["Estado"]}</p>
      <h2>Dimensões:</h2>
      <p>Altura: ${element["Dimensoes"]["Altura"]}m</p>
      <p>Largura: ${element["Dimensoes"]["Largura"]}m</p>
      <p>Comprimento: ${element["Dimensoes"]["Comprimento"]}m</p>
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
