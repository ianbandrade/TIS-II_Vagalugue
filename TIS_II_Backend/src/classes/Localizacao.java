package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Localizacao {
	
	private String cep;
	private String endereco;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;

	public Localizacao(String cep, String endereco, int numero, String bairro, String cidade, String estado) {
		super();
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public int getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Localizacao [cep=" + cep + ", endereco=" + endereco + ", numero=" + numero + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", estado=" + estado + "]";
	}

	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("Cep", cep);
		json.put("Endereco", endereco);
		json.put("Numero", numero);
		json.put("Bairro", bairro);
		json.put("Cidade", cidade);
		json.put("Estado", estado); 
		return json;
	}
}
