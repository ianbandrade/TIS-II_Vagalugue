import org.json.JSONException;
import org.json.JSONObject;

public class Dimensoes {

	private double largura;
	private double comprimento;
	private double altura;

	public Dimensoes(double largura, double comprimento, double altura) {
		super();
		this.largura = largura;
		this.comprimento = comprimento;
		this.altura = altura;
	}

	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("Largura", largura);
		json.put("Comprimento", comprimento);
		json.put("Altura", altura);
		return json;
	}

}
