package br.ufrn.imd.dao;

import java.util.ArrayList;

import br.ufrn.imd.modelo.Usuario;

public class UsuarioDao {
	private static UsuarioDao uDao;
	private ArrayList<Usuario> usuarios = new ArrayList<>();

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
}
