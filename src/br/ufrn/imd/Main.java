package br.ufrn.imd;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("visao/TelaPrincipal.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Media Player");
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
