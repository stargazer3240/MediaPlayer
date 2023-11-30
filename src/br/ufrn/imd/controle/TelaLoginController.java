package br.ufrn.imd.controle;

import java.io.IOException;

import br.ufrn.imd.Main;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLoginController {

	@FXML
	private Button btnCadastro;

	@FXML
	private Button btnLogin;

	@FXML
	private Label lblFeedback;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private TextField txtUsuario;

	@FXML
	public void abrirTelaCadastro() throws IOException {
		Main.setRoot("TelaCadastro");
	}

}
