package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class UsuarioVIP extends Usuario {
	private ArrayList<Playlist> playlists = new ArrayList<>();

	public void adicionarPlaylist(Playlist p) {
		playlists.add(p);
	}

	public void removerPlaylist(Playlist p) {
		playlists.remove(p);
	}
}
