package classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuario {

	private String nome;
    private String sobrenome;
    private String email;
    private String senha;
	
	public Usuario(String nome, String sobrenome) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = null;
		this.senha = null;
	}
	
	public Usuario() {
		super();
		this.nome = null;
		this.sobrenome = null;
		this.email = null;
		this.senha = null;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", senha=" + senha + "]";
	}

	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("Nome", nome);
		json.put("Sobrenome", sobrenome);
		return json;
	}
}
