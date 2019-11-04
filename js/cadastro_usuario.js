$(document).ready(function() {
    var foto_vaga = "";
  
    //mascaras
    $("#cep").mask("00000-000");
    $("#numero").mask("000000");
  
  
    function limpa_form() {
      nome = $("#nome").val("");
      sobrenome = $("#sobrenome").val("");
      logradouro = $("#logradouro").val("");
      numero = $("#numero").val("");
      complemento = $("#complemento").val("");
      estado = $("#estado").val("");
      cep = $("#cep").val("");
      senha = $("#senha").val("");
      password = $("#password").val("");
      deacordo = $("#deacordo").val("");
    }
  
    $("#form-usuario").submit(async function(event) {
      event.preventDefault();
      let nome = $("#nome").val("");
      let sobrenome = $("#sobrenome").val("");
      let logradouro = $("#logradouro").val("");
      let numero = $("#numero").val("");
      let complemento = $("#complemento").val("");
      let estado = $("#estado").val("");
      let cep = $("#cep").val("");
      let senha = $("#senha").val("");
      let password = $("#password").val("");
      let deacordo = $("#deacordo").val("");
      
      let data = {

        nome,
        sobrenome,
        logradouro,
        numero,
        complemento,
        estado,
        cep,
        senha,
        password,
        deacordo    
        
      };
  
      try {
        let response = await $.post("http://localhost:880/usuario", data);
        alert("Form Enviado");
        limpa_form();
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
  