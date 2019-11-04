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

	public static void main(String[] args) {
//		Vaga v = new Vaga();
//		VagaDAO vd = new VagaDAO();
//		v.setIndicador("1");
//		v.setAlugada(true);
//		vd.add(v);
//		v.setIndicador("2");
//		v.setAlugada(false);
//		vd.add(v);
//		v.setIndicador("3");
//		v.setAlugada(true);
//		vd.add(v);
		VagaCollection vc = new VagaCollection(); //Deve ser declarado por ultimo
		System.out.println(vc.getVagasAlugadas());
		System.out.println(vc.getVagasNaoAlugadas());

	}
}