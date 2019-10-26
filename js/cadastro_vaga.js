$(document).ready(function() {
  var foto_vaga = "";

  //mascaras
  $("#cep").mask("00000-000");
  $("#numero").mask("000000");

  //Converter imagem para Base64
  $("#foto-vaga").on("change", function() {
    var file = $("#foto-vaga")[0].files[0];
    var reader = new FileReader();

    reader.addEventListener(
      "load",
      function() {
        foto_vaga = reader.result;
      },
      false
    );

    if (file) {
      reader.readAsDataURL(file);
    }
  });

  function limpa_form() {
    nome = $("#nome").val("");
    sobrenome = $("#sobrenome").val("");
    indicador = $("#indicador").val("");
    descricao = $("#descricao").val("");
    largura = $("#largura").val("");
    comprimento = $("#comprimento").val("");
    altura = $("#altura").val("");
    cep = $("#cep").val("");
    endereco = $("#endereco").val("");
    numero = $("#numero").val("");
    bairro = $("#bairro").val("");
    cidade = $("#cidade").val("");
    estado = $("#estado-input").val("");
    foto_vaga = "";
  }

  $("#form-vaga").submit(async function(event) {
    event.preventDefault();
    let nome = $("#nome").val();
    let sobrenome = $("#sobrenome").val();
    let indicador = $("#indicador").val();
    let descricao = $("#descricao").val();
    let largura = $("#largura").val();
    let comprimento = $("#comprimento").val();
    let altura = $("#altura").val();
    let cep = $("#cep").val();
    let endereco = $("#endereco").val();
    let numero = $("#numero").val();
    let bairro = $("#bairro").val();
    let cidade = $("#cidade").val();
    let estado = $("#estado-input").val();

    let data = {
      nome,
      sobrenome,
      indicador,
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

    try {
      let response = await $.post("http://localhost:880/vaga", data);
      alert("Form Enviado");
      limpa_form();
      limpa_form_cep();
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  });

  $("#submit-form").click(function() {
    $(".required-input").each(function() {
      if (
        !$(this)
          .find("input")
          .val()
      ) {
        $(this).addClass("ui error");
      }
    });
    if (
      !$(".required-select")
        .find("select")
        .val()
    ) {
      $(".required-select").addClass("ui error");
    }
  });

  //Volta a cor dos campos para a normal se preenchidos
  $(".required-input")
    .find("input")
    .blur(function() {
      $(".required-input").each(function() {
        if (
          $(this)
            .find("input")
            .val()
        ) {
          $(this).removeClass("ui error");
        }
      });
    });
  $(".required-select").click(function() {
    if (
      $(".required-select")
        .find("select")
        .val()
    ) {
      $(".required-select").removeClass("ui error");
    }
  });

  function limpa_form_cep() {
    $(".ui.input.loading").removeClass("left icon");
    $(".search.icon.form").addClass("hide");
    $("#endereco").val("");
    $("#bairro").val("");
    $("#cidade").val("");
    $("#estado").val("");
    $("#estado-input").val("");
    $("#endereco").prop("readOnly", false);
    $("#bairro").prop("readOnly", false);
    $("#cidade").prop("readOnly", false);
    $("#estado-select").removeClass("hide");
    $("#estado-input").addClass("hide");
  }

  $("#cep").blur(function() {
    var cep = $(this)
      .val()
      .replace(/\D/g, "");

    if (cep != "") {
      var validacep = /^[0-9]{8}$/;

      if (validacep.test(cep)) {
        $(".ui.input.loading").addClass("left icon");
        $(".search.icon.form").removeClass("hide");
        $("#endereco").val("...");
        $("#bairro").val("...");
        $("#cidade").val("...");
        $("#estado").val("...");

        $.getJSON(
          "https://viacep.com.br/ws/" + cep + "/json/?callback=?",
          function(dados) {
            if (!("erro" in dados)) {
              $(".ui.input.loading").removeClass("left icon");
              $(".search.icon.form").addClass("hide");
              $("#endereco").val(dados.logradouro);
              $("#bairro").val(dados.bairro);
              $("#cidade").val(dados.localidade);
              $("#estado").val(dados.uf);
              $("#cep-field").removeClass("ui input error");
              if (dados.logradouro != "") $("#endereco").prop("readOnly", true);
              if (dados.bairro != "") $("#bairro").prop("readOnly", true);
              if (dados.localidade != "") $("#cidade").prop("readOnly", true);
              if (dados.uf != "") $("#estado-select").addClass("hide");
              $("#estado-input")
                .removeClass("hide")
                .val($("#estado :selected").text());
            } else {
              limpa_form_cep();
              alert("CEP não encontrado!");
              $("#cep-field").addClass("ui error");
              $("#cep").val("");
            }
          }
        );
      } else {
        limpa_form_cep();
        alert("CEP inválido!");
        $("#cep-field").addClass("ui error");
        $("#cep").val("");
      }
    } else {
      limpa_form_cep();
    }
  });
});
