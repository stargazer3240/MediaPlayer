module MediaPlayer {
	requires javafx.fxml;
	requires transitive javafx.graphics;
	
	opens br.ufrn.imd to javafx.fxml;
	//opens br.ufrn.imd.controle to javafx.fxml;
	exports br.ufrn.imd;
}
