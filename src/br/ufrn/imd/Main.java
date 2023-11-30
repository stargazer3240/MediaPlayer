package br.ufrn.imd;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws IOException {
		stage = primaryStage;
		Scene scene = new Scene(loadFXML("TelaLogin"));
		stage.setScene(scene);
		stage.setTitle("Media Player");
		stage.setResizable(false);
		stage.show();
	}

	public static void trocarTela(String fxml) throws IOException {
		Scene scene = new Scene(loadFXML(fxml));
		stage.setScene(scene);
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("visao/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}
}
