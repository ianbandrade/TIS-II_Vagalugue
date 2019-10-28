package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Locador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String numCartao;
	static List<Vaga> vagasAlugadas = new ArrayList<Vaga>();
		
	public Locador(Usuario usuario, String numCartao, Endereco endereco) {
		this.usuario = usuario;
		this.numCartao = numCartao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getnumCartao() {
		return numCartao;
	}

	public void setnumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
		
}
