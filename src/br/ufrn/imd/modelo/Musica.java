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
		return arquivo.toURI().toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Musica)) {
			return false;
		}
		Musica c = (Musica) o;
		return arquivo.toURI().equals(c.getArquivo().toURI());
	}
}
