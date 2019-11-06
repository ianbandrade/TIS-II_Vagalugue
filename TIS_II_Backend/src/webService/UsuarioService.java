package webService;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import classes.Usuario;
import dao.UsuarioDAO;

public final class UsuarioService {

	public JSONObject verificarLogin(Request request) {

		boolean eLoginValido = false;

		Usuario queryUsuario = queryUsuario(request);

		JSONObject json = new JSONObject();
		// JSONArray jsonArray = new JSONArray();

		List<Usuario> usuarios = new ArrayList<Usuario>();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarios = usuarioDAO.getAll();

		for (Usuario u : usuarios) {
			if (u.getEmail().equals(queryUsuario.getEmail()) && u.getSenha().equals(queryUsuario.getSenha()))
				eLoginValido = true;
		}

		json.put("Login", eLoginValido);
		return json;

	}

	public JSONObject adicionar(Request request) {
		Usuario usuario = new Usuario();
		usuario = queryUsuario(request);
		System.out.println(usuario);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.add(usuario);

		return usuario.getJson();
	}

	public Usuario queryUsuario(Request request) {
		String nome;
		String sobrenome;

		String email;
		String senha;

		Usuario usuario = null;

		Query query = request.getQuery();

		nome = query.get("nome");
		sobrenome = query.get("sobrenome");
		email = query.get("email");
		senha = query.get("senha");

		usuario = new Usuario(nome, sobrenome, email, senha);

		return usuario;
	}
}
