$(document).ready(function () {
  $(".ui.form").form({
    fields: {
      email: {
        identifier: "email",
        rules: [
          {
            type: "empty",
            prompt: "Por favor insira um e-mail"
          },
          {
            type: "email",
            prompt: "Por favor insira um e-mail válido"
          }
        ]
      },
      password: {
        identifier: "senha",
        rules: [
          {
            type: "empty",
            prompt: "Por favor insira uma senha"
          },
          {
            type: "length[6]",
            prompt: "Sua senha deve conter no mínimo 6 caracteres"
          }
        ]
      }
    }
  });

  function limpa_form() {
    email = $('#email').val("");
    senha = $('#senha').val("");
  }

  $("#form-login").submit(async function (event) {
    event.preventDefault();
    let email = $('#email').val();
    let senha = $('#senha').val();

    let data = {
      email,
      senha
    }

    try {
      let response = await $.post('http://localhost:880/login', data)
      alert("FORM ENVIADO");
      limpa_form();
      console.log(response);
    } catch (error) {
      console.log(error)
    }
  })

  $('#submit-form').click(function () {
    $('.required-input').each(function () {
      if (!$(this).find('input').val()) {
        $(this).addClass("ui error");
      }
    });
  });

  //Volta a cor dos campos para a normal se preenchidos
  $('.required-input').find('input').blur(function () {
    $('.required-input').each(function () {
      if ($(this).find('input').val()) {
        $(this).removeClass("ui error");
      }
    });
  });

});
