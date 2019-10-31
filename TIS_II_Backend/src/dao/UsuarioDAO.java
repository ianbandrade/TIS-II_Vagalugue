package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import classes.Usuario;

public class UsuarioDAO implements DAO<Usuario, String> {

	@Override
	public Usuario get(String chave) {
		Usuario usuario = null;
		String idSTR = null;

		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("usuario.txt"))) {

			while ((idSTR = buffer_entrada.readLine()) != null) {
				if (chave.equals(idSTR)) {
					usuario = new Usuario();
					usuario.setEmail(idSTR);
					usuario.setNome(buffer_entrada.readLine());
					usuario.setSobrenome(buffer_entrada.readLine());
					usuario.setSenha(buffer_entrada.readLine());
				} else {
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler a Usuario '" + idSTR + "' do disco rígido!");
			usuario = null;
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public void add(Usuario p) {
		Usuario u = (Usuario) p;
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("usuario.txt", true))) {
			String separadorDeLinha = System.getProperty("line.separator");
			buffer_saida.write(u.getEmail() + separadorDeLinha);
			buffer_saida.write(u.getNome() + separadorDeLinha);
			buffer_saida.write(u.getSobrenome() + separadorDeLinha);
			buffer_saida.write(u.getSenha() + separadorDeLinha);
			buffer_saida.flush();

		} catch (Exception e) {
			System.out.println("ERRO ao gravar a Usuario '" + u.getEmail() + "' no disco!");
			e.printStackTrace();
		}

	}

	@Override
	public void update(Usuario p) {
		List<Usuario> usuarios = getAll();
		Usuario novoUsuario = usuarios.stream().filter(usuario -> usuario.getEmail().equals(p.getEmail())).collect(Collectors.toList()).get(0);
		int index = usuarios.indexOf(novoUsuario);
		if (index != -1) {
			usuarios.set(index, p);
		}
		saveToFile(usuarios);

	}

	@Override
	public void remove(Usuario p) {
		List<Usuario> usuarios = getAll();
//		int index = usuarios.indexOf(p);
//		if (index != -1) {
//			usuarios.remove(index);
//		}
		usuarios.removeIf((usuario) -> p.getEmail().equals(usuario.getEmail()));
		saveToFile(usuarios);
	}

	@Override
	public List<Usuario> getAll() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario = null;
		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("usuario.txt"))) {
			String idSTR;

			while ((idSTR = buffer_entrada.readLine()) != null) {
				usuario = new Usuario();
				usuario.setEmail(idSTR);
				usuario.setNome(buffer_entrada.readLine());
				usuario.setSobrenome(buffer_entrada.readLine());
				usuario.setSenha(buffer_entrada.readLine());
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler os Usuarios do disco rígido!");
			e.printStackTrace();
		}
		return usuarios;
	}

	private void saveToFile(List<Usuario> usuarios) {
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("usuario.txt", false))) {

			String separadorDeLinha = System.getProperty("line.separator");
			for (Usuario u : usuarios) {
				buffer_saida.write(u.getEmail() + separadorDeLinha);
				buffer_saida.write(u.getNome() + separadorDeLinha);
				buffer_saida.write(u.getSobrenome() + separadorDeLinha);
				buffer_saida.write(u.getSenha() + separadorDeLinha);
				buffer_saida.flush();
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar Usuarios no disco!");
			e.printStackTrace();
		}
	}

}
