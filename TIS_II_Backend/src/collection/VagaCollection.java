package collection;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import classes.Vaga;
import dao.VagaDAO;

public class VagaCollection {
	private Collection<Vaga> vagas = new ArrayList<Vaga>();

	public VagaCollection() {
		VagaDAO vagaDAO = new VagaDAO();
		vagas = vagaDAO.getAll();
	}

	public List<Vaga> getVagasNaoAlugadas() {
		return vagas.stream().filter(vaga -> vaga.isAlugada().equals(false)).collect(Collectors.toList());
	}

	public List<Vaga> getVagasAlugadas() {
		vagas.stream().forEach(vaga -> System.out.println(vaga.isAlugada()));
		return vagas.stream().filter(vaga -> vaga.isAlugada().equals(true)).collect(Collectors.toList());
	}

	public List<Vaga> getVagasPesquisaRua(String rua) {
		return vagas.stream().filter(vaga -> vaga.getLocalizacao().getEndereco().equalsIgnoreCase(rua)).collect(Collectors.toList());
	}
	
	public List<Vaga> getVagasPesquisaUsuario(String usuario) {
		return vagas.stream().filter(vaga -> vaga.getAlugadaPor().equalsIgnoreCase(usuario)).collect(Collectors.toList());
	}

}