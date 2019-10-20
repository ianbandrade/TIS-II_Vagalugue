import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class URLMetodo implements Container {

    static TisService tisService;

    public void handle(Request request, Response response) {
        try {
            // Recupera a URL e o m�todo utilizado.
            String path = request.getPath().getPath();
            System.out.println(path);
            String method = request.getMethod();
            JSONObject mensagem;

            System.out.println(method);
            // Verifica qual a��o est� sendo chamada

            if ( path.equalsIgnoreCase("/vaga") && "POST".equals(method) ) {
                // http://127.0.0.1:880/adicionarProduto?descricao=leite&preco=3.59&quant=10&tipo=2&dataFabricacao=2017-01-01
                mensagem = tisService.adicionar(request);
                this.enviaResposta(Status.CREATED, response, mensagem);

            } else if ( path.equalsIgnoreCase("/vagas") ) {
                // http://127.0.0.1:880/adicionarProduto?descricao=leite&preco=3.59&quant=10&tipo=2&dataFabricacao=2017-01-01
                mensagem = tisService.listar();
                this.enviaResposta(Status.CREATED, response, mensagem);

            } else {
                this.naoEncontrado(response, path);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void naoEncontrado(Response response, String path) throws Exception {
        JSONObject error = new JSONObject();
        error.put("error", "N�o encontrado.");
        error.put("path", path);
        enviaResposta(Status.NOT_FOUND, response, error);
    }

    private void enviaResposta(Status status, Response response, JSONObject json) throws Exception {

        long time = System.currentTimeMillis();

        response.setValue("Content-Type", "application/json");
        response.setValue("Server", "Controle de Estoque Service (1.0)");
        response.setValue("Access-Control-Allow-Origin", "*");
        response.setDate("Date", time);
        response.setDate("Last-Modified", time);
        response.setStatus(status);

        PrintStream body = response.getPrintStream();
        if ( json != null )
			try (OutputStreamWriter out = new OutputStreamWriter(
					response.getOutputStream(), StandardCharsets.UTF_8)) {
				out.write(json.toString());
			}
        body.close();

    }

    public static void main(String[] list) throws Exception {

        // Instancia o tisService Service
        tisService = new TisService();

        // Se voc� receber uma mensagem
        // "Address already in use: bind error",
        // tente mudar a porta.

        int porta = 880;

        // Configura uma conex�o soquete para o servidor HTTP.
        Container container = new URLMetodo();
        ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
        Connection conexao = new SocketConnection(servidor);
        SocketAddress endereco = new InetSocketAddress(porta);
        conexao.connect(endereco);

        System.out.println("Tecle ENTER para interromper o servidor...");
        System.in.read();

        conexao.close();
        servidor.stop();
        System.out.println("Servidor interrompido.");

    }

}
