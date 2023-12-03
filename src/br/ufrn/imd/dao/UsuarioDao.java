package br.ufrn.imd.dao;

import java.io.File;
import java.util.ArrayList;

import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.modelo.UsuarioVIP;

public class UsuarioDao {
	private static UsuarioDao uDao;
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private Usuario atual;
	private static Integer ultimoId = 0;

	public enum TipoUsuario {
		COMUM, VIP
	}

	public static UsuarioDao getInstance() {
		if (uDao == null) {
			uDao = new UsuarioDao();
		}
		return uDao;
	}

	public void criarUsuario(String nome, String senha, TipoUsuario t) {
		Usuario u;
		if (t.equals(TipoUsuario.VIP)) {
			u = new UsuarioVIP();
		} else {
			u = new Usuario();
		}
		u.setId(recuperarId());
		u.setLogin(nome);
		u.setSenha(senha);
		adicionarUsuario(u);
	}

	private static Integer recuperarId() {
		ultimoId++;
		return ultimoId;
	}

	private void adicionarUsuario(Usuario u) {
		usuarios.add(u);
	}

	public boolean checarCredenciais(String login, String senha) {
		for (Usuario u : usuarios) {
			if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
				atual = u;
				return true;
			}
		}
		return false;
	}

	public TipoUsuario identificarTipo() {
		if (atual instanceof UsuarioVIP) {
			return TipoUsuario.VIP;
		} else {
			return TipoUsuario.COMUM;
		}
	}

	public Integer identificarId() {
		return atual.getId();
	}

	public void adicionarDiretorio(File d) {
		if (!checarDiretorio(d)) {
			atual.adicionarDiretorio(d);
		}
	}

	private boolean checarDiretorio(File teste) {
		for (File d : atual.getDiretorios()) {
			if (d.equals(teste)) {
				return true;
			}
		}
		return false;
	}
}
