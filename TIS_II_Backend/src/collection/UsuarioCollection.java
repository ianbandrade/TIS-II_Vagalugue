package collection;

import java.util.ArrayList;
import java.util.Collection;

import classes.Usuario;
import dao.UsuarioDAO;

public class UsuarioCollection {
	Collection<Usuario> usuarios = new ArrayList<Usuario>();

	public UsuarioCollection() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarios = usuarioDAO.getAll();
	}
}
