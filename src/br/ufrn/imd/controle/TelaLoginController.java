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
			UsuarioDao.TipoUsuario tipo = uDao.identificarTipo();
			if (tipo == UsuarioDao.TipoUsuario.COMUM) {
				abrirTela("TelaPrincipal");
			} else {
				abrirTela("TelaPrincipalVIP");
			}
		} else {
			gerarFeedbackInvalido();
		}
	}

	@FXML
	public void abrirTelaCadastro() throws IOException {
		Main.trocarTela("TelaCadastro");
	}

	private void gerarFeedbackInvalido() {
		lblFeedback.setText("Invalid login, try again!");
		lblFeedback.setTextFill(Paint.valueOf("red"));
		lblFeedback.setVisible(true);
	}

	private void abrirTela(String tela) throws IOException {
		Main.trocarTela(tela);
	}
}
