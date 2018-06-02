package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private AnchorPane Anchor;
	@FXML
	private Button newGame;
	@FXML
	private Label lbl;

	private Board b;
	private boolean start=false;
	private int openedCells=0;
	private long startTime;    
	private long estimatedTime; 
	Stage scoresStage = new Stage();
	
	public Button posToButton(Position pos){
		ObservableList<Node> children = Anchor.getChildren();
		Button b = new Button();
		for (Node child : children) {
			if (child instanceof Button && !((Button) child).getId().equals("newGame")){
				String ij= pos.i+""+pos.j;
				String id = ((Button) child).getId();
				id=id.charAt(6)+""+id.charAt(7);

				if(ij.equals(id)) b=((Button) child);
			}
		}
		return b;
	}

	public void buttonNew(ActionEvent e){
		start=false;
		openedCells=0;
		startTime = System.nanoTime();
		lbl.setText("");


		ObservableList<Node> children = Anchor.getChildren();
		for (Node child : children) {
			if (child instanceof Button) {
				if(!((Button) child).getId().equals("newGame")){
					((Button) child).setText("");
					((Button) child).setStyle("fx-color: WHITE");
				}
			}
		}
	}


	public void buttonClick(ActionEvent e) {
		String cord = ((Button) e.getSource()).getId();
		int i = Integer.parseInt(cord.charAt(6)+"");
		int j = Integer.parseInt(cord.charAt(7)+"");
		Position pos = new Position(i,j);

		if(!start)	{
			b = new Board(pos.i,pos.j);
			start=true;
			startTime = System.nanoTime();
		}

		if(pos.isIn(b.mines)){

			((Button)e.getSource()).setText("\uD83D\uDC80");
			((Button)e.getSource()).setStyle("-fx-color: YELLOW;"+"-fx-font-size: 10;");
			lbl.setText("You lose!");


		}

		else{

			Integer numb = new Integer(b.countMines(pos));
			if(((Button) e.getSource()).getText()=="" || ((Button) e.getSource()).getText()=="\u2691") {
				((Button) e.getSource()).setText(numb.toString());
				openedCells++;
			}

			switch(numb){
			case 1:
				((Button) e.getSource()).setStyle("-fx-color: BLUE");
				break;
			case 2:
				((Button) e.getSource()).setStyle("-fx-color: GREEN");
				break;
			case 3:
				((Button) e.getSource()).setStyle("-fx-color: RED");
				break;
			case 4:
				((Button) e.getSource()).setStyle("-fx-color: PURPLE");
				break;
			case 5:
				((Button) e.getSource()).setStyle("-fx-color: MAROON");
				break;
			case 6:
				((Button) e.getSource()).setStyle("-fx-color: TURQUOISE");
				break;
			case 7:
				((Button) e.getSource()).setStyle("-fx-color: BLACK");
				break;
			case 8:
				((Button) e.getSource()).setStyle("-fx-color: GRAY");
				break;
			case 0:
				for(Position ps: b.fluidFill(pos)) {
					if(posToButton(ps).getText()=="" || posToButton(ps).getText()==	"\u2691"){
						posToButton(ps).setText("0");
						openedCells++;
					}
				}
				for(Position ps: b.border(b.fluidFill(pos))){
					if(posToButton(ps).getText()=="" || posToButton(ps).getText() == "\u2691"){
						posToButton(ps).setText(new Integer(b.countMines(ps)).toString());
						openedCells++;
					}
					switch(b.countMines(ps)){
					case 1:
						posToButton(ps).setStyle("-fx-color: BLUE");
						break;
					case 2:
						posToButton(ps).setStyle("-fx-color: GREEN");
						break;
					case 3:
						posToButton(ps).setStyle("-fx-color: RED");
						break;
					case 4:
						posToButton(ps).setStyle("-fx-color: PURPLE");
						break;
					case 5:
						posToButton(ps).setStyle("-fx-color: MAROON");
						break;
					case 6:
						posToButton(ps).setStyle("-fx-color: TURQUOISE");
						break;
					case 7:
						posToButton(ps).setStyle("-fx-color: BLACK");
						break;
					case 8:
						posToButton(ps).setStyle("-fx-color: GRAY");
						break;
					}
				}
			}




		}
		if(openedCells==90){
			estimatedTime=System.nanoTime()-startTime;
			double seconds = (double) estimatedTime / 1000000000.0;
			seconds = Math.floor(seconds * 100) / 100;
			lbl.setText("You win! Time: "+seconds);
			Database.write(new Double(seconds).toString(), true, true);

		}


	}

	public void buttonRightClick(MouseEvent e){
		if (e.getButton() == MouseButton.SECONDARY) {
			if(((Button) e.getSource()).getText()=="")
				((Button) e.getSource()).setText("\u2691");
			else if(((Button) e.getSource()).getText()=="\u2691") 
				((Button) e.getSource()).setText("");
			

		}
	}
	public void Scores(ActionEvent e){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/ScoresWindow.fxml"));
			Scene scene = new Scene(root);
			scoresStage.setTitle("Best Scores");
			scoresStage.setScene(scene);
			scoresStage.show();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
}
	public void Exit(ActionEvent e){
		System.exit(0);
	}
}
