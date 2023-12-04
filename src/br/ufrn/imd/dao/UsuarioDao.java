package br.ufrn.imd.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.modelo.UsuarioVIP;

public class UsuarioDao {
	private static UsuarioDao uDao;
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private Usuario atual;
	private static Integer ultimoId = 0;

	public enum TipoUsuario {
		COMUM, VIP, NULO
	}

	public static UsuarioDao getInstance() {
		if (uDao == null) {
			uDao = new UsuarioDao();
		}
		return uDao;
	}

	public void limparAtual() {
		atual = null;
	}

	public void criarUsuario(String nome, String senha, TipoUsuario t) {
		Usuario u = null;
		if (t.equals(TipoUsuario.VIP)) {
			u = new UsuarioVIP();
		} else if (t.equals(TipoUsuario.COMUM)) {
			u = new Usuario();
		}
		if (u != null) {
			u.setId(recuperarId());
			u.setLogin(nome);
			u.setSenha(senha);
			adicionarUsuario(u);
		}
	}

	private static Integer recuperarId() {
		ultimoId++;
		return ultimoId;
	}

	private void adicionarUsuario(Usuario u) {
		usuarios.add(u);
		salvarUsuarios();
	}

	private void salvarUsuarios() {
		criarDiretorioData();
		Path dest = Path.of("./data/usuarios.txt");
		ArrayList<String> output = formarOutput();
		try {
			Files.write(dest, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<String> formarOutput() {
		ArrayList<String> output = new ArrayList<>();
		for (Usuario u : usuarios) {
			output.add(u.toString());
		}
		return output;
	}

	private void criarDiretorioData() {
		File novoDir = new File("./data");
		if (!novoDir.exists()) {
			novoDir.mkdir();
		}
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
		if (atual != null) {
			if (atual instanceof UsuarioVIP) {
				return TipoUsuario.VIP;
			} else {
				return TipoUsuario.COMUM;
			}
		}
		return TipoUsuario.NULO;
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

	public void recuperarUsuarios() {

	}
}
