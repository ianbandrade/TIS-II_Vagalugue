package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import base.Usuario;

public class UsuarioDAO implements DAO<Usuario, String>{

	private static List<Usuario> usuarios;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public UsuarioDAO(String filename) throws IOException {

		usuarios = new ArrayList<Usuario>();

		file = new File(filename);
		readFromFile();

	}

	public void add(Usuario usuario) {
		try {
			usuarios.add(usuario);
			saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o usuario '" + usuario.getNome() + "' no disco!");
			e.printStackTrace();
		}
	}

	public static boolean eLoginValido(String email, String senha) {
		boolean x = false;

		for (Usuario u : usuarios) {
			x = u.getEmail().equals(email) && u.getSenha().equals(senha);
			if (x)
				break;
		}

		return x;
	}

	public Usuario get(String email) {
		Usuario usuario = null;

		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				usuario = (Usuario) inputFile.readObject();

				if (email.equals(usuario.getNome())) {
					return usuario;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o usuario '" + email + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	public void update(Usuario p) {
		int index = usuarios.indexOf(p);
		if (index != -1) {
			usuarios.set(index, p);
			saveToFile();
		}
	}

	public void remove(Usuario p) {
		int index = usuarios.indexOf(p);
		if (index != -1) {
			usuarios.remove(index);
			saveToFile();
		}
	}

	public List<Usuario> getAll() {
		return usuarios;
	}

	private List<Usuario> readFromFile() {
		Usuario usuario = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				usuario = (Usuario) inputFile.readObject();
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler usuario no disco!");
			e.printStackTrace();
		}
		return usuarios;
	}

	private void saveToFile() {
		try {
			close();
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Usuario usuario : usuarios) {
				outputFile.writeObject(usuario);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar usuario no disco!");
			e.printStackTrace();
		}
	}

	private void close() throws IOException {
		if (outputFile != null) {
			outputFile.close();
			fos.close();
			outputFile = null;
			fos = null;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.close();
	}

}
