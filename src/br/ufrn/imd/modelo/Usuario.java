package br.ufrn.imd.modelo;

import java.io.File;
import java.util.ArrayList;

public class Usuario {
	private Integer id;
	private String login;
	private String senha;
	private ArrayList<File> diretorios = new ArrayList<>();

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<File> getDiretorios() {
		return diretorios;
	}

	public void adicionarDiretorio(File d) {
		diretorios.add(d);
	}

	public void removerDiretorio(File d) {
		diretorios.remove(d);
	}
}
