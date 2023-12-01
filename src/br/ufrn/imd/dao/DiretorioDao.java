
package br.ufrn.imd.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import br.ufrn.imd.modelo.Diretorio;

public class DiretorioDao {
	private static DiretorioDao dDao;
	private HashMap<Integer, Diretorio> diretorios = new HashMap<>();

	public static DiretorioDao getInstance() {
		if (dDao == null) {
			dDao = new DiretorioDao();
		}
		return dDao;
	}

	void adicionarDiretorios(Integer id, ArrayList<Diretorio> dirs) {
		for (Diretorio d : dirs) {
			diretorios.put(id, d);
		}
	}

	public void salvarDiretorios() throws IOException {
		Path dest = Path.of("../../../../../data/diretorios.txt");
		for (Entry<Integer, Diretorio> e : diretorios.entrySet()) {
			String line = e.getKey() + " " + e.getValue().getCaminho().toAbsolutePath();
			Files.writeString(dest, line);
		}
	}
}
