package br.ufrn.imd.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import br.ufrn.imd.modelo.Playlist;

public class PlaylistDao {
	private static PlaylistDao pDao;
	private ArrayList<Playlist> playlists = new ArrayList<>();

	public static PlaylistDao getInstance() {
		if (pDao == null) {
			pDao = new PlaylistDao();
		}
		return pDao;
	}

	public void limparPlaylists() {
		salvarPlaylists();
		playlists.clear();
	}

	public void adicionarPlaylist(String nome) {
		if (!checarPlaylist(nome)) {
			playlists.add(new Playlist(nome));
		}
		salvarPlaylists();
	}

	public void adicionarMusica(File arquivo, String playlist) {
		for (Playlist p : playlists) {
			if (p.getNome().equals(playlist)) {
				p.adicionarMusica(arquivo);
			}
		}
	}

	private boolean checarPlaylist(String nome) {
		for (Playlist p : playlists) {
			if (p.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}

	private void salvarPlaylists() {
		Path dest;
		UsuarioDao uDao = UsuarioDao.getInstance();
		Integer id = uDao.identificarId();
		for (Playlist p : playlists) {
			dest = Path.of("./data", "playlist_ID" + id + "_" + p.getNome());
			try {
				Files.write(dest, formarOutput(p));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private ArrayList<String> formarOutput(Playlist p) {
		ArrayList<String> output = new ArrayList<>();
		for (File f : p.getFiles()) {
			output.add(f.toURI().toString());
		}
		return output;
	}
}
