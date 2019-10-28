package base;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;
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
	
	@Override
	public String toString() {
		return "Nome: " +nome+ " - Telefone: " +telefone;
		
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

}
