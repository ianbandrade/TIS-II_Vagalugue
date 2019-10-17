import org.json.JSONObject;

public class Vaga {

	private Locatario locatario;

	private String indicador;
	private String foto;
	private String descricao;

	private Dimensoes dimensoes;

	private Localizacao localizacao;

	public Vaga(Locatario locatario, String indicador, String foto, String descricao, Dimensoes dimensoes,
			Localizacao localizacao) {
		super();
		this.locatario = locatario;
		this.indicador = indicador;
		this.foto = foto;
		this.descricao = descricao;
		this.dimensoes = dimensoes;
		this.localizacao = localizacao;
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

	public JSONObject getDimensoesJson() {
		return dimensoes.toJson();
	}

	public JSONObject getLocalizacaoJson() {
		return localizacao.toJson();
	}

}
