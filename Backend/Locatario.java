import org.json.JSONException;
import org.json.JSONObject;

public class Locatario {
	
	private String nome;
	private String sobrenome;
	
	public Locatario(String nome, String sobrenome) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("Nome", nome);
		json.put("Sobrenome", sobrenome);
		return json;
	}
	
}
