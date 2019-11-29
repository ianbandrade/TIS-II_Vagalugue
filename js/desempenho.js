$(document).ready(function () {
    $.getJSON(
        "http://localhost:880/desempenho/percentualalugadas",
        function (dados) {
            let porcentagem = dados["Porcentagem"];
            $('#vagas-alugadas').html(porcentagem + "%")
        })

    $.getJSON(
        "http://localhost:880/desempenho/taxaretorno",
        function (dados) {
            let taxa = dados["Taxa Retorno"];
            $('#taxa-retorno').html(taxa + "%")
        })

    $.getJSON(
        "http://localhost:880/desempenho/tempomedio",
        function (dados) {
            let tempo = dados["Tempo Medio"];
            $('#tempo-medio').html(Math.round( tempo * 10 ) / 10 + " h")
        })

    $.getJSON(
        "http://localhost:880/desempenho/alugueis30dias",
        function (dados) {
            let alugueis = dados["Alugueis"];
            $('#alugueis-30dias').html(alugueis)
        })

    $.getJSON(
        "http://localhost:880/desempenho/vagasporbairro",
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