package br.ufrn.imd.controle;

import java.io.IOException;

import br.ufrn.imd.Main;
import br.ufrn.imd.dao.UsuarioDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

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

	private enum TipoFeedback {
		VALIDO, INVALIDO
	}

	@FXML
	public void abrirTelaLogin() throws IOException {
		Main.trocarTela("TelaLogin");
	}

	@FXML
	public void checarCredenciais() {
		UsuarioDao uDao = UsuarioDao.getInstance();
		String nome = txtUsuario.getText();
		String senha = txtSenha.getText();
		boolean existente = uDao.checarCredenciais(nome, senha);
		if (!existente) {
			cadastrarUsuario(nome, senha);
		} else {
			gerarFeedback(TipoFeedback.INVALIDO);
		}
	}

	private void cadastrarUsuario(String nome, String senha) {
		UsuarioDao uDao = UsuarioDao.getInstance();
		UsuarioDao.TipoUsuario tipo = UsuarioDao.TipoUsuario.COMUM;
		if (btnVIP.isSelected()) {
			tipo = UsuarioDao.TipoUsuario.VIP;
		}
		if (!nome.isBlank() && !senha.isBlank()) {
			uDao.criarUsuario(nome, senha, tipo);
			gerarFeedback(TipoFeedback.VALIDO);
		}
	}

	private void gerarFeedback(TipoFeedback t) {
		lblFeedback.setVisible(true);
		if (t.equals(TipoFeedback.VALIDO)) {
			gerarFeedbackValido();
		} else if (t.equals(TipoFeedback.INVALIDO)) {
			gerarFeedbackInvalido();
		}
	}

	private void gerarFeedbackValido() {
		lblFeedback.setText("Account created with success.");
		lblFeedback.setTextFill(Paint.valueOf("green"));
	}

	private void gerarFeedbackInvalido() {
		lblFeedback.setText("Account already exists!");
		lblFeedback.setTextFill(Paint.valueOf("red"));
	}
}
