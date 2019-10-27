package base;

public class Dimensoes {

	private double largura;
	private double comprimento;
	private double altura;

	public Dimensoes(double largura, double comprimento, double altura) {
		super();
		this.largura = largura;
		this.comprimento = comprimento;
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public double getComprimento() {
		return comprimento;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

}
