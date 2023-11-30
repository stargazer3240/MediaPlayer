package br.ufrn.imd.controle;

import java.io.IOException;

import br.ufrn.imd.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class TelaCadastroController {
	@FXML
	private Button btnCadastro;

	@FXML
	private RadioButton btnComum;

	@FXML
	private RadioButton btnVIP;

	@FXML
	private Button btnVoltar;

	@FXML
	private Label lblFeedback;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private TextField txtUsuario;

	@FXML
	public void abrirTelaLogin() throws IOException {
		Main.trocarTela("TelaLogin");
	}
}
