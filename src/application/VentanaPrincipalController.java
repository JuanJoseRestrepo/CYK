package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.CYK;

public class VentanaPrincipalController {

	@FXML
	private TextArea gramatica;

	@FXML
	private TextField cadena;

	@FXML
	private Button check;

	private String[][] grammar;

	private CYK cyk;

	public void checkButton() {

		Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
		mensaje.setHeaderText(null);
		
		int tam = 0;
		System.out.println("hola puta");
		
		if (gramatica.getText() != "" && cadena.getText() != "") {

			String[] sistem = gramatica.getText().split("\n");

			for (int i = 0; i < sistem.length - 1; i++) {

				String[] firstSplit = sistem[i].split(":");
				String[] secondSplit = firstSplit[1].split("|");

				String[] inicial = new String[secondSplit.length + 1];
				inicial[0] = firstSplit[0];

				for (int k = 0; k < secondSplit.length; k++) {
					inicial[k + 1] = secondSplit[k];
				}

				tam = inicial.length;
				
				for (int j = 0; j < inicial.length; j++) {
					grammar = new String[sistem.length - 1][inicial.length];
					grammar[i][j] = inicial[j];
				}

			}
			
			cyk = new CYK(grammar, cadena.getText());
			
			
			mensaje.setTitle("Resultado");
			mensaje.setContentText("La gramática " + cyk.fillMatrixAndAnswer() + " el lenguaje.");
			mensaje.show();
			
		}else {
			mensaje.setTitle("Error");
			mensaje.setContentText("Todos los espacios deben ser llenados");
			mensaje.show();
		}
		
	}

}
