$(document).ready(function () {
    mapboxgl.accessToken =
        'pk.eyJ1IjoiaWFuZ3VlbG1hbiIsImEiOiJjazJjY2JmaXcxeHN3M2hvamozNGsxazF5In0.xA8KBv93NZZAu44gw_fc3A';
    var map = new mapboxgl.Map({
        container: 'map', // container id
        style: 'mapbox://styles/mapbox/streets-v11', // stylesheet location
        center: [-43.9286357, -19.9346282], // starting position [lng, lat]
        zoom: 15 // starting zoom   
    });

    getEnderecoVagas()

    async function getEnderecoVagas() {
        let resposta = await $.getJSON("http://127.0.0.1:880/vagas");
        resposta.vagas.forEach((element, index) => {
            e = element
            element = element["Localizacao"]
            let endereco = element.Numero + ", " + element.Endereco + ", " + element.Bairro + ", " +
                element.Cidade + ", " + element.Estado + ", " + element.Cep + ", Brazil"
            addPin(endereco, e, index)
        });
    }
    async function addPin(Endereco, Element, index) {
        await $.getJSON(
            "https://api.mapbox.com/geocoding/v5/mapbox.places/" + Endereco +
            ".json?country=BR&access_token=pk.eyJ1IjoiaWFuZ3VlbG1hbiIsImEiOiJjazJjY2JmaXcxeHN3M2hvamozNGsxazF5In0.xA8KBv93NZZAu44gw_fc3A",
            function (dados) {
                let coordenadas = dados.features[0].geometry.coordinates;
                if (!Element.Alugada) {
                    new mapboxgl
                        .Marker()
                        .setLngLat(coordenadas)
                        .addTo(map)
                        .setPopup(new mapboxgl.Popup({
                            offset: 25
                        }).setHTML(
                            `<li class="cards_item">
                    <div class="card">
                    <div class="card_image"><img class="card-img" src="${Element["Foto"]}" width="220"></div>
                    <div class="card_content">
                    <h3 class="card_title">${Element["Localizacao"]["Endereco"]}, 
                    ${Element.Localizacao.Numero}</h3>
                    <p class="card_text">${Element["Descricao"]}
                    </p><h5>Dimensoes: ${Element.Dimensoes.Comprimento}m x 
                    ${Element.Dimensoes.Largura}m x ${Element.Dimensoes.Altura}m </h5>
                    <button class="btn card_btn ui button centered" id="btn_${index}" onclick="reply_click(this.id)">Alugar</button>
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
                        <p>Nome: ${Element["Locatario"]["Nome"]} ${Element["Locatario"]["Sobrenome"]}</p>
                        <p>Indicador da vaga:  ${Element["Indicador"]}</p>
                        <h2>Localização:</h2>
                        <p>Rua: ${Element["Localizacao"]["Endereco"]}, ${Element["Localizacao"]["Numero"]}</p>
                        <p>Bairro: ${Element["Localizacao"]["Bairro"]}</p>
                        <p>Cidade: ${Element["Localizacao"]["Cidade"]}</p>
                        <p>Estado: ${Element["Localizacao"]["Estado"]}</p>
                        <h2>Dimensões:</h2>
                        <p>Altura: ${Element["Dimensoes"]["Altura"]}m</p>
                        <p>Largura: ${Element["Dimensoes"]["Largura"]}m</p>
                        <p>Comprimento: ${Element["Dimensoes"]["Comprimento"]}m</p>
                        </div>
                        <div>
                        <img src="https://api.mapbox.com/styles/v1/mapbox/outdoors-v11/static/pin-s(${coordenadas[0]},${coordenadas[1]})/${coordenadas[0]},${coordenadas[1]},16,0,0/450x450?access_token=pk.eyJ1IjoiaWFuZ3VlbG1hbiIsImEiOiJjazJjY2JmaXcxeHN3M2hvamozNGsxazF5In0.xA8KBv93NZZAu44gw_fc3A">
                        </div>
                    </section>

                    </div>
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
                </div>`))
                } else {
                    new mapboxgl
                        .Marker()
                        .setLngLat(coordenadas)
                        .addTo(map)
                        .setPopup(new mapboxgl.Popup({
                            offset: 25
                        }).setHTML(
                            `<section class="cards_item">
                    <div class="card">
                    <div class="card_image"><img class="card-img" src="${Element["Foto"]}" width="220"></div>
                    <div class="card_content">
                    <h3 class="card_title">${Element["Localizacao"]["Endereco"]}, 
                    ${Element.Localizacao.Numero}</h3>
                    <p class="card_text">${Element["Descricao"]}
                    </p><h5>Dimensoes: ${Element.Dimensoes.Comprimento}m x 
                    ${Element.Dimensoes.Largura}m x ${Element.Dimensoes.Altura}m </h5>
                    <button class="btn card_btn ui disabled button centered" id="btn_${index}" onclick="reply_click(this.id)">Vaga já alugada...</button>
                    </div>
                    </div>
                    </section>
                    `))
                }
            }
        );
    }
})

async function reply_click(clicked_id) {
    try {
        $(() => {
            $(`#modal_${clicked_id}`)
                .modal({
                    closable: false,
                    inverted: true,
                    onApprove: async function () {

                        let response = await $.getJSON("http://127.0.0.1:880/vagas");

                        let nome = response.vagas[clicked_id.replace("btn_", "")]
                            .Locatario.Nome
                        let sobrenome = response.vagas[clicked_id.replace("btn_", "")]
                            .Locatario.Sobrenome
                        let indicador_vaga = response.vagas[clicked_id.replace("btn_",
                            "")].Indicador
                        let descricao = response.vagas[clicked_id.replace("btn_", "")]
                            .Descricao
                        let largura = response.vagas[clicked_id.replace("btn_", "")]
                            .Dimensoes.Largura
                        let comprimento = response.vagas[clicked_id.replace("btn_", "")]
                            .Dimensoes.Comprimento
                        let altura = response.vagas[clicked_id.replace("btn_", "")]
                            .Dimensoes.Altura
                        let cep = response.vagas[clicked_id.replace("btn_", "")]
                            .Localizacao.Cep
                        let endereco = response.vagas[clicked_id.replace("btn_", "")]
                            .Localizacao.Endereco
                        let numero = response.vagas[clicked_id.replace("btn_", "")]
                            .Localizacao.Numero
                        let bairro = response.vagas[clicked_id.replace("btn_", "")]
                            .Localizacao.Bairro
                        let cidade = response.vagas[clicked_id.replace("btn_", "")]
                            .Localizacao.Cidade
                        let estado = response.vagas[clicked_id.replace("btn_", "")]
                            .Localizacao.Estado
                        let foto_vaga = response.vagas[clicked_id.replace("btn_", "")]
                            .Foto

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
                            estado
                        };

                        await $.post("http://localhost:880/alugar", data);
                        alert("Vaga alugada com sucesso!")
                        location.reload(true);  
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