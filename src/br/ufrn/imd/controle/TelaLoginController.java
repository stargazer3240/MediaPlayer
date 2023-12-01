package br.ufrn.imd.controle;

import java.io.IOException;

import br.ufrn.imd.Main;
import br.ufrn.imd.dao.UsuarioDao;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class TelaLoginController {
	private UsuarioDao uDao = UsuarioDao.getInstance();

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
	public void logar() throws IOException {
		String login = txtUsuario.getText();
		String senha = txtSenha.getText();
		boolean validado = uDao.checarCredenciais(login, senha);
		if (validado) {
			UsuarioDao.tipoUsuario tipo = uDao.identificarTipo();
			if (tipo == UsuarioDao.tipoUsuario.COMUM) {
				abrirTelaPrincipal();
			} else {
				abrirTelaPrincipalVip();
			}
		} else {
			gerarfeedbackInvalido();
		}
	}

	@FXML
	public void abrirTelaCadastro() throws IOException {
		Main.trocarTela("TelaCadastro");
	}

	private void gerarfeedbackInvalido() {
		lblFeedback.setText("Invalid login, try again!");
		lblFeedback.setTextFill(Paint.valueOf("red"));
		lblFeedback.setVisible(true);
	}

	private void abrirTelaPrincipal() throws IOException {
		Main.trocarTela("TelaPrincipal");
	}

	private void abrirTelaPrincipalVip() throws IOException {
		Main.trocarTela("TelaPrincipalVIP");
	}
}
