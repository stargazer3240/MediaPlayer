package br.ufrn.imd.controle;

import java.io.IOException;

import br.ufrn.imd.Main;
import br.ufrn.imd.dao.UsuarioDao;
import br.ufrn.imd.dao.UsuarioDao.TipoUsuario;
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
		SUCESSO, USER_EXISTENTE, SENHA_INVALIDA, NOME_INVALIDO
	}

	@FXML
	public void abrirTelaLogin() throws IOException {
		Main.trocarTela("TelaLogin");
	}

	@FXML
	public void checarCredenciais() {
		UsuarioDao uDao = UsuarioDao.getInstance();
		String nome = txtUsuario.getText();
		if (nome.contains(" ") || nome.contains("|")) {
			gerarFeedback(TipoFeedback.NOME_INVALIDO);
			return;
		}
		String senha = txtSenha.getText();
		if (senha.contains(" ") || senha.contains("|")) {
			gerarFeedback(TipoFeedback.SENHA_INVALIDA);
			return;
		}
		boolean existente = uDao.checarCredenciais(nome, senha);
		if (!existente) {
			cadastrarUsuario(nome, senha);
		} else {
			gerarFeedback(TipoFeedback.USER_EXISTENTE);
		}
	}

	private void cadastrarUsuario(String nome, String senha) {
		UsuarioDao uDao = UsuarioDao.getInstance();
		TipoUsuario tipo = TipoUsuario.COMUM;
		if (btnVIP.isSelected()) {
			tipo = TipoUsuario.VIP;
		}
		if (!nome.isBlank() && !senha.isBlank()) {
			uDao.criarUsuario(nome, senha, tipo);
			gerarFeedback(TipoFeedback.SUCESSO);
		}
	}

	private void gerarFeedback(TipoFeedback t) {
		lblFeedback.setVisible(true);
		if (t.equals(TipoFeedback.SUCESSO)) {
			gerarFeedbackSucesso();
		} else if (t.equals(TipoFeedback.USER_EXISTENTE)) {
			gerarFeedbackUserExistente();
		} else if (t.equals(TipoFeedback.SENHA_INVALIDA)) {
			gerarFeedbackSenhaInvalida();
		} else if (t.equals(TipoFeedback.NOME_INVALIDO)) {
			gerarFeedbackNomeInvalido();
		}
	}

	private void gerarFeedbackSucesso() {
		lblFeedback.setText("Account created with success.");
		lblFeedback.setTextFill(Paint.valueOf("green"));
	}

	private void gerarFeedbackUserExistente() {
		lblFeedback.setText("Account already exists!");
		lblFeedback.setTextFill(Paint.valueOf("red"));
	}

	private void gerarFeedbackSenhaInvalida() {
		lblFeedback.setText("Invalid password!");
		lblFeedback.setTextFill(Paint.valueOf("red"));
	}

	private void gerarFeedbackNomeInvalido() {
		lblFeedback.setText("Invalid username!");
		lblFeedback.setTextFill(Paint.valueOf("red"));
	}
}
