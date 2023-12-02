package br.ufrn.imd.controle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

public class TelaPrincipalController {
	@FXML
	protected Button btnNext;

	@FXML
	protected Button btnPlay;

	@FXML
	protected Button btnPrevious;

	@FXML
	protected ListView<String> listFolders;

	@FXML
	protected ListView<String> listSongs;

	@FXML
	protected MenuItem mItLogout;

	@FXML
	protected MenuItem mItQuit;
}
