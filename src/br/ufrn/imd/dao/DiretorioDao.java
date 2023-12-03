
package br.ufrn.imd.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DiretorioDao {
	private static DiretorioDao dDao;
	private ArrayList<Pair> diretorios = new ArrayList<>();

	public class Pair {
		private final Integer id;
		private final File diretorio;

		public Pair(Integer id, File dir) {
			this.id = id;
			this.diretorio = dir;
		}

		public Integer getId() {
			return id;
		}

		public File getDiretorio() {
			return diretorio;
		}

		@Override
		public String toString() {
			return id + " " + diretorio.getAbsolutePath();
		}

		@Override
		public boolean equals(Object o) {
			if (o == this) {
				return true;
			}
			if (!(o instanceof Pair)) {
				return false;
			}
			Pair c = (Pair) o;
			return id.equals(c.id) && diretorio.getAbsolutePath().equals(c.diretorio.getAbsolutePath());
		}
	}

	public static DiretorioDao getInstance() {
		if (dDao == null) {
			dDao = new DiretorioDao();
		}
		return dDao;
	}

	public void adicionarDiretorio(Integer id, File d) {
		Pair novo = new Pair(id, d);
		if (!checarDiretorio(novo)) {
			diretorios.add(novo);
		}
		salvarDiretorios();
	}

	private boolean checarDiretorio(Pair teste) {
		for (Pair p : diretorios) {
			if (teste.equals(p)) {
				return true;
			}
		}
		return false;
	}

	public void salvarDiretorios() {
		criarDirData();
		Path dest = Path.of("./data/diretorios.txt");
		ArrayList<String> output = formarOutput();
		try {
			Files.write(dest, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void criarDirData() {
		File novoDir = new File("./data");
		if (!novoDir.exists()) {
			novoDir.mkdir();
		}
	}

	private ArrayList<String> formarOutput() {
		ArrayList<String> output = new ArrayList<>();
		for (Pair p : diretorios) {
			output.add(p.toString());
		}
		return output;
	}

	public ArrayList<Pair> getDiretorios() {
		return diretorios;
	}
	
	
}
