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
	
	private List<Locador> Locadores;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public LocadorDAO(String filename) throws IOException {

		Locadores = new ArrayList<Locador>();

		file = new File(filename);
		readFromFile();
	}

	public void add(Locador Locador) {
		try {
			Locadores.add(Locador);
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

	public void update(Locador p) {
		int index = Locadores.indexOf(p);
		if (index != -1) {
			Locadores.set(index, p);
			saveToFile();
		}
	}

	public void remove(Locador p) {
		int index = Locadores.indexOf(p);
		if (index != -1) {
			Locadores.remove(index);
			saveToFile();
		}
	}

	public List<Locador> getAll() {
		return Locadores;
	}

	private List<Locador> readFromFile() {
		Locador Locador = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				Locador = (Locador) inputFile.readObject();
				Locadores.add(Locador);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler Locador no disco!");
			e.printStackTrace();
		}
		return Locadores;
	}

	private void saveToFile() {
		try {
			close();
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Locador Locador : Locadores) {
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
