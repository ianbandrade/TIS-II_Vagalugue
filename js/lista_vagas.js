$(document).ready(function () {
  $('#input_pesquisa').on('keypress', function (e) {
    if (e.which == 13) {
      this.blur();
    }
  }).blur(async function () {
    try {
      $('.btn_listar').removeClass('active');
      $('#btn_listas_todas').addClass('active');
      let input_pesquisa = $('#input_pesquisa').val()
      if (input_pesquisa != "") {
        listar("http://127.0.0.1:880/vagas/pesquisar/rua?input_pesquisa=" + input_pesquisa);
      } else {
        listar("http://127.0.0.1:880/vagas")
      }
    } catch (errors) {
      console.log(errors);
    }
  })

})


async function reply_click(clicked_id) {
  try {
    $(() => {
      $(`#modal_${clicked_id}`)
        .modal({
          closable: false,
          inverted: true,
          onApprove: async function () {

            $(`#modal2_${clicked_id}`)
              .modal({
                closable: false,
                inverted: true,
                onApprove: async function () {

                  let response = await $.getJSON("http://127.0.0.1:880/vagas");

                  let nome = response.vagas[clicked_id.replace("btn_", "")].Locatario.Nome
                  let sobrenome = response.vagas[clicked_id.replace("btn_", "")].Locatario.Sobrenome
                  let indicador_vaga = response.vagas[clicked_id.replace("btn_", "")].Indicador
                  let descricao = response.vagas[clicked_id.replace("btn_", "")].Descricao
                  let largura = response.vagas[clicked_id.replace("btn_", "")].Dimensoes.Largura
                  let comprimento = response.vagas[clicked_id.replace("btn_", "")].Dimensoes.Comprimento
                  let altura = response.vagas[clicked_id.replace("btn_", "")].Dimensoes.Altura
                  let cep = response.vagas[clicked_id.replace("btn_", "")].Localizacao.Cep
                  let endereco = response.vagas[clicked_id.replace("btn_", "")].Localizacao.Endereco
                  let numero = response.vagas[clicked_id.replace("btn_", "")].Localizacao.Numero
                  let bairro = response.vagas[clicked_id.replace("btn_", "")].Localizacao.Bairro
                  let cidade = response.vagas[clicked_id.replace("btn_", "")].Localizacao.Cidade
                  let estado = response.vagas[clicked_id.replace("btn_", "")].Localizacao.Estado
                  let foto_vaga = response.vagas[clicked_id.replace("btn_", "")].Foto
                  let data_inicio = $(`#data-inicio_${clicked_id.replace("btn_", "")}`).val()
                  let data_fim = $(`#data-fim_${clicked_id.replace("btn_", "")}`).val()
                  let alugada_por = localStorage.getItem("email")

                  let data = {
                    nome,
                    sobrenome,
                    indicador_vaga,
                    foto_vaga,
                    descricao,
                    largura,
                    comprimento,
                    altura,
                    cep,
                    endereco,
                    numero,
                    bairro,
                    cidade,
                    estado,
                    data_inicio,
                    data_fim,
                    alugada_por
                  };

                  await $.post("http://localhost:880/alugar", data);
                  alert("Vaga alugada com sucesso!");
                  location.reload(true);
                },
                onDeny: function () {
                  // alert('Rejeitar');
                }
              })
              .modal("show");
          },
          onDeny: function () {
            // alert('Rejeitar');
          }
        })
        .modal("show");
    });
  } catch (errors) {
    console.log(errors);
  }
}

listar("http://127.0.0.1:880/vagas");

async function getCoordenadas(Endereco) {
  return new Promise((resolve, reject) => {
    $.getJSON(
      "https://api.mapbox.com/geocoding/v5/mapbox.places/" + Endereco +
      ".json?country=BR&access_token=pk.eyJ1IjoiaWFuZ3VlbG1hbiIsImEiOiJjazJjY2JmaXcxeHN3M2hvamozNGsxazF5In0.xA8KBv93NZZAu44gw_fc3A",
      function (dados) {
        let coordenadas = dados.features[0].geometry.coordinates
        resolve(coordenadas)
      }
    )
  });
}

async function listar(url) {
  try {
    let response = await $.getJSON(url);
    let vagas = "";
    if (response.vagas.length == 0) {
      document.getElementById("vagas").innerHTML = "<br><br><br><h2 style='color: #f79307; height: 470px; width: 100%; text-align: center; vertical-align: middle'>Não existem vagas cadastradas nesta categoria</h2>";
    } else {
      response.vagas.forEach(async (element, index) => {
        let endereco = element.Localizacao.Numero + ", " + element.Localizacao.Endereco + ", " + element.Localizacao.Bairro + ", " +
          element.Localizacao.Cidade + ", " + element.Localizacao.Estado + ", " + element.Localizacao.Cep + ", Brazil"
        coordenadas = await getCoordenadas(endereco)
        if (!element.Alugada && localStorage.getItem("email") != null) {
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
        element.Dimensoes.Largura
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
      <section style="display: flex">
        <div style="flex-grow: 1">
          <h2>Locador: </h2>
          <p>Nome: ${element["Locatario"]["Nome"]} ${element["Locatario"]["Sobrenome"]}</p>
          <p>Indicador da vaga:  ${element["Indicador"]}</p>
          <h2>Localização: </h2>
          <p>Rua: ${element["Localizacao"]["Endereco"]}, ${element["Localizacao"]["Numero"]}</p>
          <p>Bairro: ${element["Localizacao"]["Bairro"]}</p>
          <p>Cidade: ${element["Localizacao"]["Cidade"]}</p>
          <p>Estado: ${element["Localizacao"]["Estado"]}</p>
          <h2>Dimensões:</h2>
          <p>Altura: ${element["Dimensoes"]["Altura"]}m</p>
          <p>Largura: ${element["Dimensoes"]["Largura"]}m</p>
          <p>Comprimento: ${element["Dimensoes"]["Comprimento"]}m</p>
        </div>
        <div>
          <img src="https://api.mapbox.com/styles/v1/mapbox/outdoors-v11/static/pin-s(${coordenadas[0]},${coordenadas[1]})/${coordenadas[0]},${coordenadas[1]},16,0,0/450x450?access_token=pk.eyJ1IjoiaWFuZ3VlbG1hbiIsImEiOiJjazJjY2JmaXcxeHN3M2hvamozNGsxazF5In0.xA8KBv93NZZAu44gw_fc3A">
        </div>
      </section>

    </div>
    <div class="actions" style="text-align: center">
    <div class="ui red cancel inverted button">
        <i class="remove icon"></i>
        Cancelar
      </div>
      <div class="ui green ok inverted button">
        <i class="checkmark icon"></i>
        Alugar
      </div>
    </div>
  </div>
  <div class="ui test modal" id="modal2_btn_${index}">
  <h3>Selecione o tempo de locação da vaga</h3>
  <input id="data-inicio_${index}" type="datetime-local"">
  <input id="data-fim_${index}" type="datetime-local">
  <div class="actions" style="text-align: center">
    <p class="confirmacao" style="text-align: center">Confirmar aluguel da vaga?</p>
    <div class="ui red cancel inverted button">
        <i class="remove icon"></i>
        Cancelar
      </div>
      <div class="ui green ok inverted button">
        <i class="checkmark icon"></i>
        Alugar
      </div>
    </div>
  </div>`
        } else if (!element.Alugada) {
          vagas += 
          `<li class="cards_item">
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
            element.Dimensoes.Largura
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
          <section style="display: flex">
            <div style="flex-grow: 1">
              <h2>Locador: </h2>
              <p>Nome: ${element["Locatario"]["Nome"]} ${element["Locatario"]["Sobrenome"]}</p>
              <p>Indicador da vaga:  ${element["Indicador"]}</p>
              <h2>Localização: </h2>
              <p>Rua: ${element["Localizacao"]["Endereco"]}, ${element["Localizacao"]["Numero"]}</p>
              <p>Bairro: ${element["Localizacao"]["Bairro"]}</p>
              <p>Cidade: ${element["Localizacao"]["Cidade"]}</p>
              <p>Estado: ${element["Localizacao"]["Estado"]}</p>
              <h2>Dimensões:</h2>
              <p>Altura: ${element["Dimensoes"]["Altura"]}m</p>
              <p>Largura: ${element["Dimensoes"]["Largura"]}m</p>
              <p>Comprimento: ${element["Dimensoes"]["Comprimento"]}m</p>
            </div>
            <div>
              <img src="https://api.mapbox.com/styles/v1/mapbox/outdoors-v11/static/pin-s(${coordenadas[0]},${coordenadas[1]})/${coordenadas[0]},${coordenadas[1]},16,0,0/450x450?access_token=pk.eyJ1IjoiaWFuZ3VlbG1hbiIsImEiOiJjazJjY2JmaXcxeHN3M2hvamozNGsxazF5In0.xA8KBv93NZZAu44gw_fc3A">
            </div>
          </section>
    
        </div>
        <div class="actions" style="text-align: center">
        <div class="ui red cancel inverted button">
            <i class="remove icon"></i>
            Cancelar
          </div>
          <div class="ui green ok inverted disabled button">
            Faça Log-in para alugar
          </div>
        </div>
      </div>`
        } else {
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
        element.Dimensoes.Largura
      }m x ${element.Dimensoes.Altura}m </h5>
          <button class="btn card_btn ui disabled button" id="btn_${index}" onclick="reply_click(this.id)">Vaga já alugada</button>
        </div>
      </div>
    </li>`;
        }
        document.getElementById("vagas").innerHTML = vagas;
      })
    };
  } catch (errors) {
    console.log(errors);
  }
}