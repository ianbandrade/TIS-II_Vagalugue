package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import base.Locador;

public class LocadorDAO implements DAO<Locador, String> {

	private static List<Locador> locadores;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public LocadorDAO(String filename) throws IOException {

		locadores = new ArrayList<Locador>();

		file = new File(filename);
		readFromFile();
	}

	public void add(Locador Locador) {
		try {
			locadores.add(Locador);
			saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o Locador '" + Locador.getUsuario().getNome() + "' no disco!");
			e.printStackTrace();
		}
	}

	public Locador get(String nome) {
		Locador Locador = null;

		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				Locador = (Locador) inputFile.readObject();

				if (nome.equals(Locador.getUsuario().getNome())) {
					return Locador;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o Locador '" + nome + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	public void update(Locador atual, Locador novo) {

		locadores.remove(atual);
		locadores.add(novo);
		saveToFile();

	}

	public void remove(Locador p) {
		int index = locadores.indexOf(p);
		if (index != -1) {
			locadores.remove(index);
			saveToFile();
		}
	}

	public List<Locador> getAll() {
		return locadores;
	}

	private List<Locador> readFromFile() {
		Locador Locador = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				Locador = (Locador) inputFile.readObject();
				locadores.add(Locador);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler Locador no disco!");
			e.printStackTrace();
		}
		return locadores;
	}

	private void saveToFile() {
		try {
			close();
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Locador Locador : locadores) {
				outputFile.writeObject(Locador);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar Locador no disco!");
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
