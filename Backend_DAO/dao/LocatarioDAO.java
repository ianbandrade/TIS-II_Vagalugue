package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import base.Locatario;

public class LocatarioDAO implements DAO<Locatario, String> {

	private List<Locatario> locatarios;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public LocatarioDAO(String filename) throws IOException {

		locatarios = new ArrayList<Locatario>();

		file = new File(filename);
		readFromFile();
	}

	public void add(Locatario locatario) {
		try {
			locatarios.add(locatario);
			saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o locatario '" + locatario.getUsuario().getNome() + "' no disco!");
			e.printStackTrace();
		}
	}

	public Locatario get(String nome) {
		Locatario locatario = null;

		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				locatario = (Locatario) inputFile.readObject();

				if (nome.equals(locatario.getUsuario().getNome())) {
					return locatario;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o locatario '" + nome + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	public void update(Locatario p) {
		int index = locatarios.indexOf(p);
		if (index != -1) {
			locatarios.set(index, p);
			saveToFile();
		}
	}

	public void remove(Locatario p) {
		int index = locatarios.indexOf(p);
		if (index != -1) {
			locatarios.remove(index);
			saveToFile();
		}
	}

	public List<Locatario> getAll() {
		return locatarios;
	}

	private List<Locatario> readFromFile() {
		Locatario locatario = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				locatario = (Locatario) inputFile.readObject();
				locatarios.add(locatario);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler locatario no disco!");
			e.printStackTrace();
		}
		return locatarios;
	}

	private void saveToFile() {
		try {
			close();
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Locatario locatario : locatarios) {
				outputFile.writeObject(locatario);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar locatario no disco!");
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
