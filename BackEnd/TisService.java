import org.json.JSONException;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public final class TisService {

	public JSONObject adicionar(Request request) {
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

		Vaga v = null;

		Query query = request.getQuery();

		nome = query.get("nome");
		sobrenome = query.get("sobrenome");
		indicador = query.get("indicador_vaga");
		foto = query.get("foto_vaga");
		descricao = query.get("descricao");
		largura = query.getFloat("largura");
		comprimento = query.getFloat("comprimento");
		altura = query.getFloat("altura");
		cep = query.get("cep");
		endereco = query.get("endereco");
		numero = query.getInteger("numero");
		bairro = query.get("bairro");
		cidade = query.get("cidade");
		estado = query.get("estado");

		v = new Vaga(new Locatario(nome, sobrenome), indicador, foto, descricao,
				new Dimensoes(largura, comprimento, altura),
				new Localizacao(cep, endereco, numero, bairro, cidade, estado));

		return toJson(v);

	}

	private JSONObject toJson(Vaga v) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("Locat�rio", v.getLocatarioJson());
		json.put("Indicador", v.getIndicador());
		json.put("Foto", v.getFoto());
		json.put("Descri��o", v.getDescricao());
		json.put("Dimens�es", v.getDimensoesJson());
		json.put("Localizacao", v.getLocalizacaoJson());
		return json;
	}

}