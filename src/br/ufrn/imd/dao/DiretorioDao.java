
package br.ufrn.imd.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import br.ufrn.imd.modelo.Diretorio;

public class DiretorioDao {
	private static DiretorioDao dDao;
	private ArrayList<Diretorio> diretorios = new ArrayList<>();

	public static DiretorioDao getInstance() {
		if (dDao == null) {
			dDao = new DiretorioDao();
		}
		return dDao;
	}

	public void limparDiretorios() {
		salvarDiretorios();
		diretorios.clear();
	}

	public void adicionarDiretorio(Integer id, File d) {
		Diretorio novo = new Diretorio(id, d);
		if (!checarDiretorio(novo)) {
			diretorios.add(novo);
		}
		salvarDiretorios();
	}

	private boolean checarDiretorio(Diretorio teste) {
		for (Diretorio d : diretorios) {
			if (teste.equals(d)) {
				return true;
			}
		}
		return false;
	}

	private void salvarDiretorios() {
		Path dest = Path.of("./data/diretorios.txt");
		ArrayList<String> output = formarOutput();
		try {
			Files.write(dest, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<String> formarOutput() {
		ArrayList<String> output = new ArrayList<>();
		for (Diretorio d : diretorios) {
			output.add(d.toString());
		}
		return output;
	}
}
