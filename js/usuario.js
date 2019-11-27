$(document).ready(function () {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['dayGrid'],
        locale: 'pt-br',
        time_zone: 'local',
        aspectRatio: 3.3,
        height: 'auto'
    });
    calendar.render();

    var calendarListEl = document.getElementById('calendar-list');

    var calendarList = new FullCalendar.Calendar(calendarListEl, {
        plugins: ['dayGrid', 'list'],
        locale: 'pt-br',
        time_zone: 'local',
        defaultView: 'listWeek',
        height: 'auto',
    });
    calendarList.render();


    async function listar() {
        let response = await $.getJSON("http://127.0.0.1:880/vagas/pesquisar/usuario?email=" +
            localStorage
            .getItem(
                "email"))
        response = response.vagas
        response.forEach(element => {
            calendarList.addEventSource([{
                title: element.Indicador + " (" + element.Localizacao
                    .Endereco + ", " +
                    element.Localizacao.Numero + ")",
                start: element.Data_inicio,
                end: element.Data_fim
            }])
            calendar.addEventSource([{
                title: element.Indicador,
                start: element.Data_inicio,
                end: element.Data_fim
            }])
        });
    }

    listar()

})