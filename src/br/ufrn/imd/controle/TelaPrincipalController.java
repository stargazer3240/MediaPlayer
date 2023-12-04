package br.ufrn.imd.controle;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import br.ufrn.imd.Main;
import br.ufrn.imd.dao.DiretorioDao;
import br.ufrn.imd.dao.MusicaDao;
import br.ufrn.imd.dao.UsuarioDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;

public class TelaPrincipalController{

	protected Media media;
	protected MediaPlayer mediaPlayer;
	protected int songNumber;

	@FXML
	protected Button btnPause;

	@FXML
	protected Button btnStop;

	@FXML
	protected Label lblSongDuration;

	@FXML
	protected Slider sSongProgress;

	@FXML
	protected Slider sSongVolume;

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
	
	@FXML
	protected Label lblSong;

	protected DirectoryChooser directoryChooser = new DirectoryChooser();

	@FXML
    protected void fazerLogout(ActionEvent event) throws IOException {
		if(mediaPlayer != null) {
			musicStop();
			mediaPlayer.dispose();
		}
		Main.trocarTela("TelaLogin");
    }
	
	@FXML
	protected void adicionarDiretorio() {
		File selectedDirectory = directoryChooser.showDialog(Main.getStage());
		if (selectedDirectory != null) {
			selectedDirectory.getAbsolutePath();
		}
		DiretorioDao dDao = DiretorioDao.getInstance();
		UsuarioDao uDao = UsuarioDao.getInstance();
		Integer id = uDao.identificarId();
		dDao.adicionarDiretorio(id, selectedDirectory);
		uDao.adicionarDiretorio(selectedDirectory);
		listarDiretorios(selectedDirectory);
	}

	protected void listarDiretorios(File d) {
		if (!listFolders.getItems().contains(d.getAbsolutePath())) {
			listFolders.getItems().add(d.getAbsolutePath());
		}
	}

	@FXML
	protected void atualizarSongs() {
		if (!listFolders.getItems().isEmpty()) {
			String caminho = listFolders.getSelectionModel().selectedItemProperty().get();
			listarMusicasDiretorio(caminho);
		}
	}

	protected void listarMusicasDiretorio(String caminho) {
		MusicaDao mDao = MusicaDao.getInstance();
		listSongs.getItems().clear();
		try (DirectoryStream<Path> dir = Files.newDirectoryStream(Path.of(caminho), "*.mp3")) {
			for (Path p : dir) {
				listSongs.getItems().add(p.getFileName().toString());
				mDao.adicionarMusica(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void sair() {
		System.exit(0);
	}

	protected void update() {
		mediaPlayer.setAutoPlay(true);
		
		mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
			sSongProgress.setValue(newValue.toSeconds());
			int hour = (int) sSongProgress.getValue() / (60 * 60);
			int min = ((int) sSongProgress.getValue() / (60)) - (hour * 60);
			int sec = ((int) sSongProgress.getValue() / (1)) - (min * 60);
			DecimalFormat formatter = new DecimalFormat("00");
			String hourFormatString = formatter.format(hour);
			String minFormatString = formatter.format(min);
			String secFormatString = formatter.format(sec);
			lblSongDuration.setText("Duration: " + hourFormatString + ":" + minFormatString + ":" + secFormatString);
		}));

		mediaPlayer.setOnReady(() -> {
			mediaPlayer.setVolume(sSongVolume.getValue() * 0.01);
			Duration totalDuration = media.getDuration();
			sSongProgress.setMax(totalDuration.toSeconds());
			lblSong.setText(listSongs.getItems().get(songNumber).toString());
		});

		sSongVolume.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				mediaPlayer.setVolume(sSongVolume.getValue() * 0.01);
			}
		});

		mediaPlayer.setOnEndOfMedia(() -> {
			musicNext();
		});
	}

	@FXML
	protected void musicChangeProgress(MouseEvent event) {
		if (mediaPlayer != null) {
			mediaPlayer.seek(Duration.seconds(sSongProgress.getValue()));
		}
	}

	@FXML
	protected void musicChangeVolume(MouseEvent event) {
		if (mediaPlayer != null) {
			mediaPlayer.setVolume(sSongVolume.getValue() * 0.01);
		}
	}

	@FXML
	protected void musicNext(ActionEvent event) {
		musicNext();
	}
	
	protected void musicNext() {
		if(mediaPlayer != null) {
			if (!listFolders.getItems().isEmpty()) {
				if(songNumber < listSongs.getItems().size() - 1) {
					musicStop();
					songNumber++;
					String dir = listFolders.getSelectionModel().selectedItemProperty().get();
					String musica = listSongs.getItems().get(songNumber);
					Path p = Path.of(dir, musica);
					if (!musica.isBlank()) {
						media = new Media(p.toUri().toString());
						mediaPlayer = new MediaPlayer(media);
						sSongProgress.setValue(0);
						listSongs.getSelectionModel().select(songNumber);
						update();
					}
				}
				else {
					musicStop();
					songNumber = 0;
					String dir = listFolders.getSelectionModel().selectedItemProperty().get();
					String musica = listSongs.getItems().get(songNumber);
					Path p = Path.of(dir, musica);
					if (!musica.isBlank()) {
						media = new Media(p.toUri().toString());
						mediaPlayer = new MediaPlayer(media);
						sSongProgress.setValue(0);
						listSongs.getSelectionModel().select(songNumber);
						update();
					}
				}
			}
		}
	}

	@FXML
	protected void musicPlay(ActionEvent event) {
		if(mediaPlayer == null) {
			if (!listFolders.getItems().isEmpty()) {
				String dir = listFolders.getSelectionModel().selectedItemProperty().get();
				String musica = listSongs.getSelectionModel().getSelectedItem();
				Path p = Path.of(dir, musica);
				if (!musica.isBlank()) {
					media = new Media(p.toUri().toString());
					mediaPlayer = new MediaPlayer(media);
					sSongProgress.setValue(0);
					songNumber = listSongs.getSelectionModel().getSelectedIndex();
					mediaPlayer.play();
					listSongs.getSelectionModel().select(songNumber);
					update();
					return;
				}
			}
		}
		if (mediaPlayer.getStatus().equals(Status.PLAYING) && listSongs.getSelectionModel().getSelectedIndex() != songNumber){
			musicStop();
			if (!listFolders.getItems().isEmpty()) {
				String dir = listFolders.getSelectionModel().selectedItemProperty().get();
				String musica = listSongs.getSelectionModel().getSelectedItem();
				Path p = Path.of(dir, musica);
				if (!musica.isBlank()) {
					media = new Media(p.toUri().toString());
					mediaPlayer = new MediaPlayer(media);
					sSongProgress.setValue(0);
					songNumber = listSongs.getSelectionModel().getSelectedIndex();
					mediaPlayer.play();
					update();
					return;
				}
			}
		}

		mediaPlayer.play();

	}
	
	@FXML
	protected void musicPause(ActionEvent event) {
		if(mediaPlayer != null || !mediaPlayer.getStatus().equals(Status.PAUSED)) {
			listSongs.getSelectionModel().select(songNumber);
			mediaPlayer.pause();
    	}
	}

	@FXML
	protected void musicPrevious(ActionEvent event) {
		musicPrevious();
	}
	
	protected void musicPrevious() {
		if(mediaPlayer != null) {
			if (!listFolders.getItems().isEmpty()) {
				musicStop();
				if(songNumber > 0) {
					songNumber--;
					String dir = listFolders.getSelectionModel().selectedItemProperty().get();
					String musica = listSongs.getItems().get(songNumber);
					Path p = Path.of(dir, musica);
					if (!musica.isBlank()) {
						media = new Media(p.toUri().toString());
						mediaPlayer = new MediaPlayer(media);
						sSongProgress.setValue(0);
						listSongs.getSelectionModel().select(songNumber);
						update();
					}
				}
				else {
					songNumber = listSongs.getItems().size() - 1;
					String dir = listFolders.getSelectionModel().selectedItemProperty().get();
					String musica = listSongs.getItems().get(songNumber);
					Path p = Path.of(dir, musica);
					if (!musica.isBlank()) {
						media = new Media(p.toUri().toString());
						mediaPlayer = new MediaPlayer(media);
						sSongProgress.setValue(0);
						listSongs.getSelectionModel().select(songNumber);
						update();
					}
				}
			}
		}
	}

	@FXML
	protected void musicStop(ActionEvent event) {
		musicStop();
	}
	
	protected void musicStop() {
		if (!mediaPlayer.getStatus().equals(Status.STOPPED)) {
			mediaPlayer.stop();
			sSongProgress.setValue(0);
		}
	}

	
}
