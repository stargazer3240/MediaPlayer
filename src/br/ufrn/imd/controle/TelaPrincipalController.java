package br.ufrn.imd.controle;

import java.io.File;
import java.io.IOException;

import br.ufrn.imd.Main;
import br.ufrn.imd.dao.DiretorioDao;
import br.ufrn.imd.dao.DiretorioDao.Pair;
import br.ufrn.imd.dao.UsuarioDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;

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

	@FXML
	protected MenuItem mItAdicionarPasta;

	protected DirectoryChooser directoryChooser = new DirectoryChooser();

	@FXML
	void adicionarPasta() {
		File selectedDirectory = directoryChooser.showDialog(Main.getStage());
		if (selectedDirectory != null) {
			selectedDirectory.getAbsolutePath();
		}
		DiretorioDao dDao = DiretorioDao.getInstance();
		UsuarioDao uDao = UsuarioDao.getInstance();
		Integer id = uDao.identificarId();
		dDao.adicionarDiretorio(id, selectedDirectory);
		
		listFolders.getItems().clear();
		for(Pair it : dDao.getDiretorios())
		{
			listFolders.getItems().add(it.getDiretorio().toString());
		}
	}

	@FXML
	void sair() {
		System.exit(0);
	}
}
