package br.ufrn.imd.modelo;

import java.io.File;

public class Musica {
	private String nome;
	private File arquivo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public String toString() {
		return nome + " " + arquivo.toURI();
	}
}
