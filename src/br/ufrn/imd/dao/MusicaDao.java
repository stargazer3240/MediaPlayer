package br.ufrn.imd.dao;

import java.io.File;
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

	}

	public ArrayList<Musica> getMusicas() {
		return musicas;
	}
	
//	public File encontrarMusica(String musica) {
//		for(Musica m: musicas) {
//			if()
//		}
//	}

	private boolean checarMusica(Musica teste) {
		for (Musica m : musicas) {
			if (m.getNome().equals(teste.getNome())
					&& m.getArquivo().getAbsolutePath().equals(teste.getArquivo().getAbsolutePath())) {
				return true;
			}
		}
		return false;
	}
}
