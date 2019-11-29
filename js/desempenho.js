$(document).ready(function () {
    $.getJSON(
        "http://191.235.95.241:8080/desempenho/percentualalugadas",
        function (dados) {
            let porcentagem = dados["Porcentagem"];
            $('#vagas-alugadas').html(porcentagem + "%")
        })

    $.getJSON(
        "http://191.235.95.241:8080/desempenho/taxaretorno",
        function (dados) {
            let taxa = dados["Taxa Retorno"];
            $('#taxa-retorno').html(taxa + "%")
        })

    $.getJSON(
        "http://191.235.95.241:8080/desempenho/tempomedio",
        function (dados) {
            let tempo = dados["Tempo Medio"];
            $('#tempo-medio').html(Math.round( tempo * 10 ) / 10 + " h")
        })

    $.getJSON(
        "http://191.235.95.241:8080/desempenho/alugueis30dias",
        function (dados) {
            let alugueis = dados["Alugueis"];
            $('#alugueis-30dias').html(alugueis)
        })

    $.getJSON(
        "http://191.235.95.241:8080/desempenho/vagasporbairro",
        function (dados) {
            var lista = "";
            Object.keys(dados).forEach(element => {
                addData(chartGraph, element, dados[element])
            });
        })
})

function addData(chart, label, data) {
    chart.data.labels.push(label);
    chart.data.datasets.forEach((dataset) => {
        dataset.data.push(data);
    });
    chart.update();
}