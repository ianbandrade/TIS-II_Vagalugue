$(document).ready(function() {
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
});
