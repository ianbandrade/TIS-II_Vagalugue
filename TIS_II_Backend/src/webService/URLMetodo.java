package webService;

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

	static VagaService vagaService;
	static UsuarioService usuarioService;

	public void handle(Request request, Response response) {
		try {
			String path = request.getPath().getPath();
			System.out.println(path);
			String method = request.getMethod();
			JSONObject mensagem;

			System.out.println(method);

			if (path.equalsIgnoreCase("/vaga") && "POST".equals(method)) {
				mensagem = vagaService.adicionar(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			} else if (path.equalsIgnoreCase("/vagas")) {
				mensagem = vagaService.listar();
				this.enviaResposta(Status.CREATED, response, mensagem);
			} else if (path.equalsIgnoreCase("/vagas/alugadas")) {
				mensagem = vagaService.listarAlugadas();
				this.enviaResposta(Status.CREATED, response, mensagem);
			} else if (path.equalsIgnoreCase("/vagas/naoalugadas")) {
				mensagem = vagaService.listarNaoAlugadas();
				this.enviaResposta(Status.CREATED, response, mensagem);
			} else if (path.equalsIgnoreCase("/vagas/pesquisar/rua")) {
				mensagem = vagaService.listarPesquisaRua(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			} else if (path.equalsIgnoreCase("/vagas/pesquisar/usuario")) {
				mensagem = vagaService.listarPesquisaRua(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			} else if (path.equalsIgnoreCase("/alugar")) {
				mensagem = vagaService.alugar(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}  else if (path.equalsIgnoreCase("/usuario")) {
				mensagem = usuarioService.adicionar(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}else if (path.equalsIgnoreCase("/login")) {
				mensagem = usuarioService.verificarLogin(request);
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
		error.put("error", "Nao encontrado.");
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
		if (json != null)
			try (OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8)) {
				out.write(json.toString());
			}
		body.close();
	}

	public static void main(String[] list) throws Exception {

		vagaService = new VagaService();
		usuarioService = new UsuarioService();

		int porta = 880;

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
