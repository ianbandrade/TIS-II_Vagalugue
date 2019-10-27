package base;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

	//Os demais m�todos e vari�veis devem ser colocados quando for desenvolver o cadastro de usu�rio
	static List<Usuario> usuarios = new ArrayList<Usuario>(); // Essa ser� a lista que armazenar� todos os cadastros de
	private String nome;
	private String telefone;
	private String senha;
	private String email;

	public Usuario(String nome, String telefone, String email, String senha) {
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.nome = nome;
	}
	
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public String getNome() {
		return nome;
	}
	
//	public JSONObject verificacaoDeLogin(Request request) {
//
//		usuarios.add(new Usuario("Username", "(XX) XXXX-XXXX", "user@gmail.com", "123456")); // Suposi��o de cadastro para teste de login
//
//		String email;
//		String senha;
//
//		Usuario u = null;
//
//		Query query = request.getQuery();
//
//		email = query.get("email");
//		senha = query.get("senha");
//
//		u = new Usuario(email, senha);
//
//		return toJson(u, email, senha);
//	}
//
//	private static JSONObject toJson(Usuario u, String email, String senha) throws JSONException {
//		JSONObject json = new JSONObject();
//		boolean eLoginValido = false;
//		eLoginValido = eLoginValido(email, senha);
//		json.put("Login", eLoginValido);
//		return json;
//	}
//	
	public static boolean eLoginValido(String email, String senha) {
		boolean x = false;
		for (Usuario u : usuarios) {
			x = u.getEmail().equals(email) && u.getSenha().equals(senha);
			if (x)
				break;
		}
		return x;

	}

}
