
package br.ufrn.imd.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map.Entry;

public class DiretorioDao {
	private static DiretorioDao dDao;
	private HashMap<Integer, File> diretorios = new HashMap<>();

	public static DiretorioDao getInstance() {
		if (dDao == null) {
			dDao = new DiretorioDao();
		}
		return dDao;
	}

	public void adicionarDiretorio(Integer id, File d) {
		diretorios.put(id, d);
	}

	public void salvarDiretorios() throws IOException {
		Path dest = Path.of("../../../../../data/diretorios.txt");
		for (Entry<Integer, File> e : diretorios.entrySet()) {
			String line = e.getKey() + " " + e.getValue().getAbsolutePath();
			Files.writeString(dest, line);
		}
	}
}
