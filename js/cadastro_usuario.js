$(document).ready(function () {
    $('#telefone').mask("0000-0000")

    function limpa_form() {
        $('#nome').val("")
        $('#sobrenome').val("")
        $('#email').val("")
        $('#telefone').val("")
        $('#senha').val("")
        $('#senha-confirmacao').val("")
    }
    $("#form-usuario").submit(async function (event) {
        event.preventDefault();
        if ($('#senha').val() == $('#senha-confirmacao').val()) {
            let nome = $('#nome').val()
            let sobrenome = $('#sobrenome').val()
            let email = $('#email').val()
            let telefone = $('#telefone').val()
            let senha = $('#senha-confirmacao').val()

            let data = {
                nome,
                sobrenome,
                email,
                telefone,
                senha
            };

            try {
                let response = await $.post("http://localhost:880/usuario", data);
                alert("Form Enviado");
                limpa_form();
                console.log(response);
            } catch (error) {
                console.log(error);
            }
        } else {
            alert("Senhas não coincidem")
            $('#senha-confirmacao').parent().addClass("ui error")
        }
    });

    $('#senha-confirmacao').on('input', function () {
        if ($(this).val() != "") {
            if ($(this).val() != $('#senha').val()) {
                $(this).parent().addClass("ui error")
                $('#aviso-senha').removeClass("hidden")
            } else {
                $(this).parent().removeClass("ui error")
                $('#aviso-senha').addClass("hidden")
            }
        }
    })

    $('#submit-btn').click(function () {
        $(".required-input").each(function () {
            if (
                !$(this)
                .find("input")
                .val()
            ) {
                $(this).addClass("ui error");
            }
        });

        // Volta a cor dos campos para a normal se preenchidos
        $(".required-input")
            .find("input")
            .blur(function () {
                $(".required-input").each(function () {
                    if (
                        $(this)
                        .find("input")
                        .val()
                    ) {
                        $(this).removeClass("ui error");
                    }
                });
            });
    })
})