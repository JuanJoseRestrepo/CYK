package application;

import java.util.Iterator;

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

	
	//Este método está asignado en el botón check y prepara las entradas (gramática y cadena) para ser ingresadas en el algoritmo
	public void checkButton() {
		try {
			Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
			mensaje.setHeaderText(null);
			String[] firstSplit;
			String[] secondSplit;
			String[] inicial = null;
			
			if (gramatica.getText().length() != 0 && cadena.getText().length() != 0) {

				String[] sistem = gramatica.getText().split("\n");
				int col = 0;
				
				String[][] matriz;
				
				for (int i = 0; i < sistem.length; i++) {
					
					firstSplit = sistem[i].split(":");
					secondSplit = firstSplit[1].split("\\|");
					col = secondSplit.length + 1;	
					
				}
						matriz = new String[sistem.length][col];
				
				for (int i = 0; i < sistem.length; i++) {
					
					firstSplit = sistem[i].split(":");
					secondSplit = firstSplit[1].split("\\|");
						
					
					for(int n = 0; n < secondSplit.length+1;n++) {
						if(n == 0) {
							matriz[i][n] = firstSplit[n];
						}else {
							matriz[i][n] = secondSplit[n-1];
						}
					}
					
				}
				
				grammar = matriz;
				
				cyk = new CYK(grammar, cadena.getText());
				

				mensaje.setTitle("Resultado");
				mensaje.setContentText("La gramatica con la cadena:" + "[" + cadena.getText() + "]" + "  " + cyk.fillMatrixAndAnswer() + " el lenguaje.");
				mensaje.show();	
				gramatica.setText("");
				cadena.setText("");
					
			}else {
				mensaje.setTitle("Error");
				mensaje.setContentText("Todos los espacios deben ser llenados");
				mensaje.show();
			}	
		}catch(ArrayIndexOutOfBoundsException e) {
			Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
			mensaje.setHeaderText(null);
			mensaje.setTitle("Error");
			mensaje.setContentText( "\n" + "La cadena es muy grande o no es el formato solicitado" + "\n" + "Por favor vuelve a diligenciarlo" + "\n"); 
			cadena.setText("");
			gramatica.setText("");
			mensaje.show();	
		}
		
	}

}
