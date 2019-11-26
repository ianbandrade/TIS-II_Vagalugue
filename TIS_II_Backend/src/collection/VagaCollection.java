package collection;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import classes.Vaga;
import dao.VagaDAO;

public class VagaCollection {
	private static Collection<Vaga> vagas = new ArrayList<Vaga>();

	public VagaCollection() {
		VagaDAO vagaDAO = new VagaDAO();
		vagas = vagaDAO.getAll();
	}

	public List<Vaga> getVagasNaoAlugadas() {
		return vagas.stream().filter(vaga -> vaga.isAlugada().equals(false)).collect(Collectors.toList());
	}

	public List<Vaga> getVagasAlugadas() {
		return vagas.stream().filter(vaga -> vaga.isAlugada().equals(true)).collect(Collectors.toList());
	}

	public List<Vaga> getVagasPesquisaRua(String rua) {
		return vagas.stream().filter(vaga -> vaga.getLocalizacao().getEndereco().equalsIgnoreCase(rua))
				.collect(Collectors.toList());
	}

	public List<Vaga> getVagasPesquisaUsuario(String usuario) {
		return vagas.stream().filter(vaga -> vaga.getAlugadaPor().equalsIgnoreCase(usuario))
				.collect(Collectors.toList());
	}

	public long getPercentualAlugadas() {
		return Math.round(((double) getVagasAlugadas().stream().count() / vagas.stream().count()) * 100.0);
	}

	public long getTaxaRetorno() {
		List<String> emails = new ArrayList<String>();
		vagas.stream().filter(v -> !v.getAlugadaPor().equals("null")).forEach(v -> emails.add(v.getAlugadaPor()));
		return Math.round(
				((double) emails.stream().filter(x -> Collections.frequency(emails, x) > 1).count() / emails.size())
						* 100);
	}

	public Double getTempoMedio() {
		List<Long> tempos = new ArrayList<Long>();
		vagas.stream().filter(v -> v.isAlugada()).forEach(v -> tempos.add(
				LocalDateTime.parse(v.getDataInicio()).until(LocalDateTime.parse(v.getDataFim()), ChronoUnit.HOURS)));
		return tempos.stream().mapToDouble(x -> x).average().orElse(-1);
	}

	public int getAlugueis30Dias() {
		return (int) vagas.stream().filter(v -> v.isAlugada())
				.filter(v -> LocalDateTime.parse(v.getDataInicio()).until(LocalDateTime.now(), ChronoUnit.DAYS) <= 30)
				.count();
	}
	
	public HashMap<String, Integer> getVagasPorBairro() {
		List<String> bairros = new ArrayList<String>();
		HashMap<String, Integer> bairroVagas = new HashMap <String, Integer>();
		vagas.stream().forEach(v -> bairros.add(v.getLocalizacao().getBairro()));
		bairros.forEach(b -> bairroVagas.put(b,Collections.frequency(bairros, b)));
		System.out.println(bairroVagas);
		return bairroVagas;
	}

}