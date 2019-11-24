package webService;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import classes.Dimensoes;
import classes.Localizacao;
import classes.Usuario;
import classes.Vaga;
import collection.VagaCollection;
import dao.VagaDAO;

public final class VagaService {

	public JSONObject adicionar(Request request) {
		Vaga vaga = new Vaga();
		vaga = queryVaga(request);
		System.out.println(vaga);
		VagaDAO vagaDAO = new VagaDAO();
		vagaDAO.add(vaga);

		return vaga.getJson();
	}

	public JSONObject listar() {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		List<Vaga> vagas = new ArrayList<Vaga>();
		VagaDAO vagaDAO = new VagaDAO();
		vagas = vagaDAO.getAll();

		for (Vaga v : vagas) {
			jsonArray.put(v.getJson());
		}
		json.put("vagas", jsonArray);
		return json;
	}

	public JSONObject listarAlugadas() {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		List<Vaga> vagas = new ArrayList<Vaga>();
		VagaCollection vagaCollection = new VagaCollection();
		vagas = vagaCollection.getVagasAlugadas();

		for (Vaga v : vagas) {
			jsonArray.put(v.getJson());
		}
		json.put("vagas", jsonArray);
		return json;
	}
	
	public JSONObject listarNaoAlugadas() {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		List<Vaga> vagas = new ArrayList<Vaga>();
		VagaCollection vagaCollection = new VagaCollection();
		vagas = vagaCollection.getVagasNaoAlugadas();

		for (Vaga v : vagas) {
			jsonArray.put(v.getJson());
		}
		json.put("vagas", jsonArray);
		return json;
	}
	
	public JSONObject listarPesquisaRua(Request request) {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		List<Vaga> vagas = new ArrayList<Vaga>();
		VagaCollection vagaCollection = new VagaCollection();
		Query query = request.getQuery();
		String rua = query.get("input_pesquisa");
		vagas = vagaCollection.getVagasPesquisaRua(rua);

		for (Vaga v : vagas) {
			jsonArray.put(v.getJson());
		}
		json.put("vagas", jsonArray);
		return json;
	}
	
	public JSONObject listarPesquisaUsuario(Request request) {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		List<Vaga> vagas = new ArrayList<Vaga>();
		VagaCollection vagaCollection = new VagaCollection();
		Query query = request.getQuery();
		String usuario = query.get("email");
		vagas = vagaCollection.getVagasPesquisaRua(usuario);

		for (Vaga v : vagas) {
			jsonArray.put(v.getJson());
		}
		json.put("vagas", jsonArray);
		return json;
	}
	
	public JSONObject alugar(Request request) {
		Vaga vaga = new Vaga();
		vaga = queryVaga(request);
		System.out.println(vaga);
		Query query = request.getQuery();
		String data_inicio = query.get("data_inicio");
		String data_fim = query.get("data_fim");
		String alugada_por = query.get("alugada_por");

		VagaDAO vagaDAO = new VagaDAO();

		vaga.setAlugada(true);
		vaga.setDataInicio(data_inicio);
		vaga.setDataFim(data_fim);
		vaga.setAlugadaPor(alugada_por);
		vagaDAO.update(vaga);
		
		return vaga.getJson();
	}

	public Vaga queryVaga(Request request) {
		String nome;
		String sobrenome;

		String indicador;
		String foto;
		String descricao;

		double largura;
		double comprimento;
		double altura;

		String cep;
		String endereco;
		int numero;
		String bairro;
		String cidade;
		String estado;

		Vaga vaga = null;

		Query query = request.getQuery();

		nome = query.get("nome");
		sobrenome = query.get("sobrenome");
		indicador = query.get("indicador_vaga");
		foto = query.get("foto_vaga");
		descricao = query.get("descricao");
		largura = Math.round(query.getFloat("largura") * 10) / 10.0;
		comprimento = Math.round(query.getFloat("comprimento") * 10) / 10.0;
		altura = Math.round(query.getFloat("altura") * 10) / 10.0;
		cep = query.get("cep");
		endereco = query.get("endereco");
		numero = query.getInteger("numero");
		bairro = query.get("bairro");
		cidade = query.get("cidade");
		estado = query.get("estado");

		vaga = new Vaga(new Usuario(nome, sobrenome), indicador, foto, descricao,
				new Dimensoes(largura, comprimento, altura),
				new Localizacao(cep, endereco, numero, bairro, cidade, estado));

		return vaga;
	}
}
