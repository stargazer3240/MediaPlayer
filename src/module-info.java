module MediaPlayer {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	
	opens br.ufrn.imd to javafx.fxml;
	//opens br.ufrn.imd.controle to javafx.fxml;
	exports br.ufrn.imd;
	// exports br.ufrn.imd.controle;
	exports br.ufrn.imd.dao;
	exports br.ufrn.imd.modelo;
}
