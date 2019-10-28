package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import base.Vaga;

public class VagaDAO implements DAO<Vaga, String> {
	private List<Vaga> vagas;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public VagaDAO(String filename) throws IOException {

		vagas = new ArrayList<Vaga>();

		file = new File(filename);
		readFromFile();
	}

	public void add(Vaga produto) {
		try {
			vagas.add(produto);
			saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o produto '" + produto.getDescricao() + "' no disco!");
			e.printStackTrace();
		}
	}

	public Vaga get(String chave) {
		Vaga produto = null;

		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				produto = (Vaga) inputFile.readObject();

				if (chave.equals(produto.getDescricao())) {
					return produto;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o produto '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	public void update(Vaga atual, Vaga novo) {

		vagas.remove(atual);
		vagas.add(novo);
		saveToFile();

	}

	public void remove(Vaga p) {
		int index = vagas.indexOf(p);
		if (index != -1) {
			vagas.remove(index);
			saveToFile();
		}
	}

	public List<Vaga> getAll() {
		return vagas;
	}

	private List<Vaga> readFromFile() {
		Vaga produto = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				produto = (Vaga) inputFile.readObject();
				vagas.add(produto);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler produto no disco!");
			e.printStackTrace();
		}
		return vagas;
	}

	private void saveToFile() {
		try {
			close();
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Vaga produto : vagas) {
				outputFile.writeObject(produto);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar produto no disco!");
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
