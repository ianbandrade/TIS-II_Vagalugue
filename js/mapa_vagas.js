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
resposta.vagas.forEach(element => {
    e = element
    element = element["Localizacao"]
    let endereco = element.Numero + ", " + element.Endereco + ", " + element.Bairro + ", " +
        element.Cidade + ", " + element.Estado + ", " + element.Cep + ", Brazil"
    console.log(endereco)
    addPin(endereco, e)
});
}
async function addPin(Endereco, Element) {
await $.getJSON(
    "https://api.mapbox.com/geocoding/v5/mapbox.places/" + Endereco +
    ".json?country=BR&access_token=pk.eyJ1IjoiaWFuZ3VlbG1hbiIsImEiOiJjazJjY2JmaXcxeHN3M2hvamozNGsxazF5In0.xA8KBv93NZZAu44gw_fc3A",
    function (dados) {
        let coordenadas = dados.features[0].geometry.coordinates
        console.log(coordenadas)
        var el = document.createElement('div'); //remover para tirar icone carro e voltar para pin
        el.className = 'marker'; //remover para tirar icone carro e voltar para pin
        new mapboxgl.Marker(el) //new mapboxgl.Marker() _ remover <<<<el>>>> para tirar icone carro e voltar para pin 
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
                ${Element.Dimensoes.Altura}m x ${Element.Dimensoes.Altura}m </h5>
                <button class="btn card_btn">Alugar</button>
                </div>
                </div>
                </li>`));
    }
);
}