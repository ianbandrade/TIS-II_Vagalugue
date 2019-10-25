import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
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

			// Recupera a URL e o método utilizado.
			String path = request.getPath().getPath();
			System.out.println(path);
			String method = request.getMethod();
			JSONObject mensagem;

			System.out.println(method);

			// Verifica qual ação está sendo chamada
			if (path.equalsIgnoreCase("/login") && "POST".equals(method)) {
				mensagem = tisService.verificarLogin(request);

				this.enviaResposta(Status.CREATED, response, mensagem);

			} else if (path.equalsIgnoreCase("/vaga") && "POST".equals(method)) {
				mensagem = tisService.adicionar(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			} else if (path.equalsIgnoreCase("/vagas")) {
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

	private void enviaResposta(Status status, Response response, JSONArray json) throws Exception {

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
		tisService = new TisService();

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
