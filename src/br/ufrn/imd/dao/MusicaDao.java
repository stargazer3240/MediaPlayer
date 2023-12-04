package br.ufrn.imd.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import br.ufrn.imd.modelo.Musica;

public class MusicaDao {
	private static MusicaDao mDao;
	private ArrayList<Musica> musicas = new ArrayList<>();

	public static MusicaDao getInstance() {
		if (mDao == null) {
			mDao = new MusicaDao();
		}
		return mDao;
	}

	public void adicionarMusica(Path p) {
		Musica m = new Musica();
		m.setNome(p.getFileName().toString());

		try {
			File f = p.toFile();
			m.setArquivo(f);
			if (!checarMusica(m)) {
				musicas.add(m);
			}
		} catch (NullPointerException e) {
			System.out.println("File not found!");
		}
		salvarMusicas();
	}

	private void salvarMusicas() {
		Path dest = Path.of("./data/musicas.txt");
		ArrayList<String> output = formarOutput();
		try {
			Files.write(dest, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<String> formarOutput() {
		ArrayList<String> output = new ArrayList<>();
		for (Musica m : musicas) {
			output.add(m.toString());
		}
		return output;
	}

	private boolean checarMusica(Musica teste) {
		for (Musica m : musicas) {
			if (m.equals(teste)) {
				return true;
			}
		}
		return false;
	}
}
