package base;

import java.io.Serializable;

public class Locatario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String numConta;
	private Endereco endereco;
	private Vaga vaga; 
	
	public Locatario(Usuario usuario, String numConta, Endereco endereco) {
		this.usuario = usuario;
		this.numConta = numConta;
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	
}
