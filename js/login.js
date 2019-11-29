$(document).ready(function () {
  function limpa_form() {
    $('#email').val("")
    $('#senha').val("")
  }

  $('#submit-btn').click(async function (event) {
    event.preventDefault();
    let email = $('#email').val()
    let senha = $('#senha').val()

    try {
      let response = await $.getJSON("http://localhost:880/login?email=" + email + "&senha=" + senha);
      console.log(response)
      limpa_form();
      if (response.Login == true) {
        localStorage.setItem("email", response.Email)
        localStorage.setItem("nome", response.Nome)
        localStorage.setItem("sobrenome", response.Sobrenome)
        alert("logado")
        window.location.href = "index.html";
      } else
        alert("nao logado")
    } catch (error) {
      console.log(error);
    }
  });

  $('#senha').on('keypress', async function (e) {
    if (e.which == 13) {
      event.preventDefault();
      let email = $('#email').val()
      let senha = $('#senha').val()

      try {
        let response = await $.getJSON("http://localhost:880/login?email=" + email + "&senha=" + senha);
        console.log(response)
        limpa_form();
        if (response.Login == true) {
          localStorage.setItem("email", response.Email)
          localStorage.setItem("nome", response.Nome)
          localStorage.setItem("sobrenome", response.Sobrenome)
          alert("logado")
          window.location.href = "index.html";
        } else
          alert("nao logado")
      } catch (error) {
        console.log(error);
      }
    }
  });
})