package br.ufrn.imd.modelo;

import java.io.File;
import java.util.ArrayList;

public class Playlist {
	private String nome;
	private ArrayList<Musica> musicas = new ArrayList<>();

	public Playlist(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Musica> getMusicas() {
		return musicas;
	}

	public ArrayList<File> getFiles() {
		ArrayList<File> files = new ArrayList<>();
		for (Musica m : musicas) {
			files.add(m.getArquivo());
		}
		return files;
	}

	public void adicionarMusica(Musica m) {
		musicas.add(m);
	}

	public void adicionarMusica(File f) {
		Musica m = new Musica();
		m.setNome(f.getName());
		m.setArquivo(f);
	}

	public void removerMusica(Musica m) {
		musicas.remove(m);
	}

	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		for (Musica m : musicas) {
			bld.append(m + "\n");
		}
		return bld.toString();
	}
}
