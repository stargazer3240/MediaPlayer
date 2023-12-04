
package br.ufrn.imd.controle;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

public class TelaPrincipalVIPController extends TelaPrincipalController {
	@FXML
	private ListView<String> listPlaylists;

	@FXML
	private MenuItem mItViewDirs;

	@FXML
	private MenuItem mItViewPlaylists;

	@FXML
	private MenuItem mItAdicionarPlaylist;

	@FXML
	private void atualizarSongsPlaylist() {
		if (!listPlaylists.getItems().isEmpty()) {
			String caminho = listPlaylists.getSelectionModel().selectedItemProperty().get();
			listarMusicasPlaylist(caminho);
		}
	}

	private void listarMusicasPlaylist(String caminho) {

	}
}
