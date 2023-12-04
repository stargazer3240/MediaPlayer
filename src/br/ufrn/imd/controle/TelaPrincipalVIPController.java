
package br.ufrn.imd.controle;

import java.io.File;

import br.ufrn.imd.dao.PlaylistDao;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TelaPrincipalVIPController extends TelaPrincipalController {
	@FXML
	private ListView<String> listPlaylists;

	@FXML
	private RadioMenuItem mItViewDirs;

	@FXML
	private RadioMenuItem mItViewPlaylists;

	@FXML
	private MenuItem mItAdicionarPlaylist;

	@FXML
	private TextField txtPlaylistName;

	@FXML
	private void mostrarCampoPlaylist() {
		txtPlaylistName.setVisible(true);
		txtPlaylistName.setDisable(false);
	}

	@FXML
	private void criarPlaylist(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			String nome = txtPlaylistName.getText();
			if (!nome.contains(" ") && !nome.contains("|")) {
				PlaylistDao pDao = PlaylistDao.getInstance();
				pDao.adicionarPlaylist(nome);
				esconderCampoPlaylist();
				adicionarPlaylist(nome);
			}
		}
	}

	private void adicionarPlaylist(String nome) {
		if (!listPlaylists.getItems().contains(nome)) {
			listPlaylists.getItems().add(nome);
		}
	}

	private void esconderCampoPlaylist() {
		txtPlaylistName.setVisible(false);
		txtPlaylistName.setDisable(true);
	}

	@FXML
	private void atualizarSongsPlaylist() {
		if (!listPlaylists.getItems().isEmpty() && mItViewPlaylists.isSelected()) {
			String nome = listPlaylists.getSelectionModel().selectedItemProperty().get();
			listarMusicasPlaylist(nome);
		}
	}

	private void listarMusicasPlaylist(String nome) {
		PlaylistDao pDao = PlaylistDao.getInstance();
		listPlaylists.getItems().clear();
		for (File f : pDao.getMusicFiles(nome)) {
			listSongs.getItems().add(f.getName());
		}
	}

	@Override
	@FXML
	protected void atualizarSongsDiretorio() {
		if (!listFolders.getItems().isEmpty() && mItViewDirs.isSelected()) {
			String caminho = listFolders.getSelectionModel().selectedItemProperty().get();
			listarMusicasDiretorio(caminho);
		}
	}
}
