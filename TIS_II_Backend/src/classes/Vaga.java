package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Vaga {

	private Usuario locatario;

	private String indicador;
	private String foto;
	private String descricao;

	private Dimensoes dimensoes;

	private Localizacao localizacao;

	private Boolean alugada = false;

	public Vaga() {
		super();
		this.locatario = new Usuario();
		this.indicador = null;
		this.foto = null;
		this.descricao = null;
		this.dimensoes = new Dimensoes(0, 0, 0);
		this.localizacao = new Localizacao(null, null, 0, null, null, null);	
	}

	public Vaga(Usuario locatario, String indicador, String foto, String descricao, Dimensoes dimensoes,
			Localizacao localizacao) {
		super();
		this.locatario = locatario;
		this.indicador = indicador;
		this.foto = foto;
		this.descricao = descricao;
		this.dimensoes = dimensoes;
		this.localizacao = localizacao;
	}

	public Usuario getLocatario() {
		return locatario;
	}

	public JSONObject getLocatarioJson() {
		return locatario.toJson();
	}

	public String getIndicador() {
		return indicador;
	}

	public String getFoto() {
		return foto;
	}

	public String getDescricao() {
		return descricao;
	}

	public Dimensoes getDimensoes() {
		return dimensoes;
	}

	public JSONObject getDimensoesJson() {
		return dimensoes.toJson();
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public JSONObject getLocalizacaoJson() {
		return localizacao.toJson();
	}

	public Boolean isAlugada() {
		return alugada;
	}

	public void setLocatario(Usuario locatario) {
		this.locatario = locatario;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDimensoes(Dimensoes dimensoes) {
		this.dimensoes = dimensoes;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public void setAlugada(Boolean alugada) {
		this.alugada = alugada;
	}

	@Override
	public String toString() {
		return "Vaga [locatario=" + locatario + ", indicador=" + indicador + ", foto=" + foto + ", descricao="
				+ descricao + ", dimensoes=" + dimensoes + ", localizacao=" + localizacao + ", alugada=" + alugada
				+ "]";
	}

	public JSONObject getJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("Locatario", getLocatarioJson());
		json.put("Indicador", getIndicador());
		json.put("Foto", getFoto());
		json.put("Descricao", getDescricao());
		json.put("Dimensoes", getDimensoesJson());
		json.put("Localizacao", getLocalizacaoJson());
		json.put("Alugada", isAlugada());
		return json;
	}

}
