package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class Usuario {
	private Integer id;
	private String login;
	private String nome;
	private String senha;
	private ArrayList<Diretorio> diretorios = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void adicionarDiretorio(Diretorio d) {
		diretorios.add(d);
	}

	public void removerDiretorio(Diretorio d) {
		diretorios.remove(d);
	}
}
