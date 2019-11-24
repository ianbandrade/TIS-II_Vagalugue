// $(document).ready(function () {
//   $(".ui.form").form({
//     fields: {
//       email: {
//         identifier: "email",
//         rules: [{
//             type: "empty",
//             prompt: "Por favor insira um e-mail"
//           },
//           {
//             type: "email",
//             prompt: "Por favor insira um e-mail válido"
//           }
//         ]
//       },
//       password: {
//         identifier: "senha",
//         rules: [{
//           type: "empty",
//           prompt: "Por favor insira uma senha"
//         }]
//       }
//     }
//   });
// });

function limpa_form() {
  $('#email').val("")
  $('#senha').val("")
}

$('#submit-btn').click(async function (event) {
  alert("AAAAAAAAA")
  event.preventDefault();
  let email = $('#email').val()
  let senha = $('#senha').val()
  console.log(email,senha)
  try {
    let response = await $.getJSON("http://localhost:880/login?email=" + email + "&senha=" + senha, data);
    limpa_form();
    console.log(response)
    if (response.Login == true)
      alert("logado")
    else
      alert("nao logado")
    console.log(response);
  } catch (error) {
    console.log(error);
  }

});