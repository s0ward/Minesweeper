package application;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ScoresController {
	@FXML
	private Label lblScores;
	
	{Platform.runLater(new Runnable() {
		  @Override
		  public void run() {
			if(!Database.read().isEmpty())
		    lblScores.setText(Database.ranking(Database.read()));   
		  }
		});}
	
	
	public void okButton(ActionEvent e){
		 Node source = (Node) e.getSource();
		 Stage stage = (Stage) source.getScene().getWindow();
		 stage.close();
	    
		
	}
	
	public void resetScoresButton(ActionEvent e){
		Database.write("",false, false);
		lblScores.setText("");
	}
	

}
