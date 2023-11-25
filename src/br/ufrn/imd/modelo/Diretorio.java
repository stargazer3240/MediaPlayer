package br.ufrn.imd.modelo;

import java.nio.file.Path;

public class Diretorio {
	private String nome;
	private Path caminho;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Path getCaminho() {
		return caminho;
	}

	public void setCaminho(Path caminho) {
		this.caminho = caminho;
	}

}
