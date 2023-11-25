package br.ufrn.imd.modelo;

import java.util.ArrayList;

/** 
 * Uma classe que representa uma playlist.
 * 
 * Uma playlist contém músicas e é usada por usuários VIP.
 * @see UsuarioVIP
 */
public class Playlist {
	private ArrayList<Musica> musicas = new ArrayList<>();

	/**
	 * Adiciona uma música m ao ArrayList musicas.
	 * @see musicas
	 * @param m a música a ser adicionada.
	 */
	public void adicionarMusica(Musica m) {
		musicas.add(m);
	}

	/**
	 * Remove uma música m do ArrayList musicas.
	 * @see musicas
	 * @param m a música a ser removida.
	 */
	public void removerMusica(Musica m) {
		musicas.remove(m);
	}
}
