package br.ufrn.imd.dao;

import java.util.ArrayList;

import br.ufrn.imd.modelo.Usuario;

public class UsuarioDao {
	private static UsuarioDao uDao;
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private Usuario atual;

	public enum tipoUsuario {
		COMUM, VIP
	}

	public static UsuarioDao getInstance() {
		if (uDao == null) {
			uDao = new UsuarioDao();
		}
		return uDao;
	}

	public void adicionarUsuario(Usuario u) {
		usuarios.add(u);
	}

	public void removerUsuario(Usuario u) {
		usuarios.remove(u);
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

	public tipoUsuario identificarTipo() {
		if (atual instanceof Usuario) {
			return tipoUsuario.COMUM;
		} else {
			return tipoUsuario.VIP;
		}
	}

	public void adicionarDiretorios() {
		for (Usuario u : usuarios) {
			DiretorioDao dDao = DiretorioDao.getInstance();
			dDao.adicionarDiretorios(u.getId(), u.getDiretorios());
		}
	}
}
