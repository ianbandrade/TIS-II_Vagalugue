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
