module MediaPlayer {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;
	requires javafx.media;
	
	opens br.ufrn.imd to javafx.fxml;
	opens br.ufrn.imd.controle to javafx.fxml;
	exports br.ufrn.imd;
}
