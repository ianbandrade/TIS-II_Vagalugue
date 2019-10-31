package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import classes.Dimensoes;
import classes.Localizacao;
import classes.Usuario;
import classes.Vaga;

public class VagaDAO implements DAO<Vaga, String> {

	@Override
	public Vaga get(String chave) {
		Vaga vaga = null;
		String idSTR = null;

		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("vaga.txt"))) {

			while ((idSTR = buffer_entrada.readLine()) != null) {
				if (chave.equals(idSTR)) {
					vaga = new Vaga();
					vaga.setIndicador(idSTR);
					vaga.setLocatario(new Usuario(buffer_entrada.readLine(), buffer_entrada.readLine()));
					vaga.setFoto(buffer_entrada.readLine());
					vaga.setDescricao(buffer_entrada.readLine());
					vaga.setDimensoes(new Dimensoes(Double.parseDouble(buffer_entrada.readLine()),
							Double.parseDouble(buffer_entrada.readLine()),
							Double.parseDouble(buffer_entrada.readLine())));
					vaga.setLocalizacao(new Localizacao(buffer_entrada.readLine(), buffer_entrada.readLine(),
							Integer.parseInt(buffer_entrada.readLine()), buffer_entrada.readLine(),
							buffer_entrada.readLine(), buffer_entrada.readLine()));
				} else {
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
					buffer_entrada.readLine();
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler a Vaga '" + idSTR + "' do disco rígido!");
			vaga = null;
			e.printStackTrace();
		}
		return vaga;
	}

	@Override
	public void add(Vaga p) {
		Vaga v = (Vaga) p;
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("vaga.txt", true))) {
			String separadorDeLinha = System.getProperty("line.separator");
			buffer_saida.write(v.getIndicador() + separadorDeLinha);
			buffer_saida.write(v.getLocatario().getNome() + separadorDeLinha);
			buffer_saida.write(v.getLocatario().getSobrenome() + separadorDeLinha);
			buffer_saida.write(v.getFoto() + separadorDeLinha);
			buffer_saida.write(v.getDescricao() + separadorDeLinha);
			buffer_saida.write(v.getDimensoes().getComprimento() + separadorDeLinha);
			buffer_saida.write(v.getDimensoes().getLargura() + separadorDeLinha);
			buffer_saida.write(v.getDimensoes().getAltura() + separadorDeLinha);
			buffer_saida.write(v.getLocalizacao().getCep() + separadorDeLinha);
			buffer_saida.write(v.getLocalizacao().getEndereco() + separadorDeLinha);
			buffer_saida.write(v.getLocalizacao().getNumero() + separadorDeLinha);
			buffer_saida.write(v.getLocalizacao().getBairro() + separadorDeLinha);
			buffer_saida.write(v.getLocalizacao().getCidade() + separadorDeLinha);
			buffer_saida.write(v.getLocalizacao().getEstado() + separadorDeLinha);
			buffer_saida.flush();

		} catch (Exception e) {
			System.out.println("ERRO ao gravar a Vaga '" + v.getIndicador() + "' no disco!");
			e.printStackTrace();
		}

	}

	@Override
	public void update(Vaga p) {
		List<Vaga> vagas = getAll();
		Vaga novaVaga = vagas.stream().filter(vaga -> vaga.getIndicador().equals(p.getIndicador())).collect(Collectors.toList()).get(0);
		int index = vagas.indexOf(novaVaga);
		if (index != -1) {
			vagas.set(index, p);
		}
		saveToFile(vagas);

	}

	@Override
	public void remove(Vaga p) {
		List<Vaga> vagas = getAll();
		vagas.removeIf((vaga) -> p.getIndicador().equals(vaga.getIndicador()));
		saveToFile(vagas);
	}

	@Override
	public List<Vaga> getAll() {
		List<Vaga> vagas = new ArrayList<Vaga>();
		Vaga vaga = null;
		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("vaga.txt"))) {
			String idSTR;

			while ((idSTR = buffer_entrada.readLine()) != null) {
				vaga = new Vaga();
				vaga.setIndicador(idSTR);
				vaga.setLocatario(new Usuario(buffer_entrada.readLine(), buffer_entrada.readLine()));
				vaga.setFoto(buffer_entrada.readLine());
				vaga.setDescricao(buffer_entrada.readLine());
				vaga.setDimensoes(new Dimensoes(Double.parseDouble(buffer_entrada.readLine()),
						Double.parseDouble(buffer_entrada.readLine()), Double.parseDouble(buffer_entrada.readLine())));
				vaga.setLocalizacao(new Localizacao(buffer_entrada.readLine(), buffer_entrada.readLine(),
						Integer.parseInt(buffer_entrada.readLine()), buffer_entrada.readLine(),
						buffer_entrada.readLine(), buffer_entrada.readLine()));
				vagas.add(vaga);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler as Vagas do disco rígido!");
			e.printStackTrace();
		}
		return vagas;
	}

	private void saveToFile(List<Vaga> vagas) {
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("vaga.txt", false))) {

			String separadorDeLinha = System.getProperty("line.separator");
			for (Vaga v : vagas) {
				buffer_saida.write(v.getIndicador() + separadorDeLinha);
				buffer_saida.write(v.getLocatario().getNome() + separadorDeLinha);
				buffer_saida.write(v.getLocatario().getSobrenome() + separadorDeLinha);
				buffer_saida.write(v.getFoto() + separadorDeLinha);
				buffer_saida.write(v.getDescricao() + separadorDeLinha);
				buffer_saida.write((int) v.getDimensoes().getComprimento() + separadorDeLinha);
				buffer_saida.write((int) v.getDimensoes().getLargura() + separadorDeLinha);
				buffer_saida.write((int) v.getDimensoes().getAltura() + separadorDeLinha);
				buffer_saida.write(v.getLocalizacao().getCep() + separadorDeLinha);
				buffer_saida.write(v.getLocalizacao().getEndereco() + separadorDeLinha);
				buffer_saida.write(v.getLocalizacao().getNumero() + separadorDeLinha);
				buffer_saida.write(v.getLocalizacao().getBairro() + separadorDeLinha);
				buffer_saida.write(v.getLocalizacao().getCidade() + separadorDeLinha);
				buffer_saida.write(v.getLocalizacao().getEstado() + separadorDeLinha);
				buffer_saida.flush();
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar Vagas no disco!");
			e.printStackTrace();
		}
	}

}
