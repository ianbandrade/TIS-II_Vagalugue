import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public final class TisService {

	static List<Usuario> usuarios = new ArrayList<Usuario>(); //Essa será a lista que armazenará todos os cadastros de usuário

	public JSONObject verificarLogin(Request request) {
		
		usuarios.add(new Usuario("user@gmail.com", "123456")); //Suposição de cadastro para teste de login

		String email;
		String senha;

		Usuario u = null;

		Query query = request.getQuery();

		email = query.get("email");
		senha = query.get("senha");

		u = new Usuario(email, senha);

		return toJson(u, email, senha);
	}

	private static JSONObject toJson(Usuario u, String email, String senha) throws JSONException {
		JSONObject json = new JSONObject();
		boolean eLoginValido = false;
		eLoginValido = saoIguais(email, senha);
		json.put("Login", eLoginValido);
		return json;
	}

	public static boolean saoIguais(String email, String senha) {
		boolean x = false;
		for (Usuario u : usuarios) {
			x = u.getEmail().equals(email) && u.getSenha().equals(senha);
			if (x)
				break;
		}
		return x;

	}
}
