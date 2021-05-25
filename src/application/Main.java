package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaPrincipal.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add("/application/application.css");
        Scene scene = new Scene(root);
        stage.setTitle("CYK");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/application/branch.jpg")); 
        stage.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
