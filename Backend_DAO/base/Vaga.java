package base;

public class Vaga {

	private Locatario locatario;

	private String indicador;
	private String foto;
	private String descricao;
	private String precoDiaria;
	private Dimensoes dimensoes;
	private Endereco localizacao;

	public Vaga(Locatario locatario, String indicador, String foto, String descricao, Dimensoes dimensoes,
			Endereco localizacao) {
		super();
		this.setLocatario(locatario);
		this.indicador = indicador;
		this.foto = foto;
		this.descricao = descricao;
		this.dimensoes = dimensoes;
		this.localizacao = localizacao;
	}

	public String getPrecoDiaria() {
		return precoDiaria;
	}

	public void setPrecoDiaria(String precoDiaria) {
		this.precoDiaria = precoDiaria;
	}

	public Dimensoes getDimensoes() {
		return dimensoes;
	}

	public void setDimensoes(Dimensoes dimensoes) {
		this.dimensoes = dimensoes;
	}

	public Endereco getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Endereco localizacao) {
		this.localizacao = localizacao;
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

	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

}
