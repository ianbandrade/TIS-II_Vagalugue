package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Dimensoes {
	
	private double comprimento;
	private double largura;
	private double altura;

	public Dimensoes( double comprimento, double largura, double altura) {
		super();
		this.comprimento = comprimento;
		this.largura = largura;
		this.altura = altura;
	}

	public double getComprimento() {
		return comprimento;
	}

	public double getLargura() {
		return largura;
	}

	public double getAltura() {
		return altura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "Dimensoes [comprimento=" + comprimento + ", largura=" + largura + ", altura=" + altura + "]";
	}

	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("Largura", largura);
		json.put("Comprimento", comprimento);
		json.put("Altura", altura);
		return json;
	}

}
