// Assignment #: 6
//         Name: Nathan Anderson
//    StudentID: 1221689573
//      Lecture: 9am
//  Description: This is the main pane for creating an army
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ArmyPane extends BorderPane {
	// COMPLETED: contains a list of heroes
	ArrayList<Hero> heroList;
	
	// COMPLETED: Variables containing army Damage, Strength, and Charisma
	int totalDamage;
	int totalStrength;
	int totalCharisma;

	
	// DONE 5. a) "Declare" (Do not "initialize" them yet!!!)
	// ONE Label to display Army information
	// ONE VBox to contain CheckBoxes
	// ONE "Load Heroes/Clear Selection" Button
	// vvvvvv 5. a) vvvvvv (about 3 lines)
	Label armyInfoLabel;
	VBox selectBox;
	Button submitButton;

	// ^^^^^^ 5. a) ^^^^^^

	public ArmyPane(ArrayList<Hero> heroList) {
		this.heroList = heroList;

		// DONE 5. a) Initialize the instance variables
		// This is where you use the "new" keyword
		// vvvvvv 5. a) vvvvvv (about 3 lines)
		armyInfoLabel = new Label("Build your Army");
		selectBox = new VBox();
		submitButton = new Button("Load Heroes/Clear Selection");

		// ^^^^^^ 5. a) ^^^^^^

		// DONE: 5. b) Bind "Load Heroes/Clear Selection" Button to its handler
		// vvvvvv 5. b) vvvvvv (1 line)
		submitButton.setOnAction(new LoadHeroesButtonHandler());
		// ^^^^^^ 5. b) ^^^^^^
		
		// DONE: 5. c) Organize components to their positions on BorderPane
		// Remeber that THIS class "is"/extends BorderPane, use BorderPane syntax to add components
		// vvvvvv 5. c) vvvvvv (1 line)
		this.setTop(armyInfoLabel);
		this.setBottom(submitButton);
		this.setCenter(selectBox);

		// ^^^^^^ 5. c) ^^^^^^

	}
	
	private class LoadHeroesButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			// DONE: 6. Clear the VBox (1 line)
			// vvvvvv 6. a) vvvvvv (1 line)
			selectBox.getChildren().clear();
			// ^^^^^^ 6. a) ^^^^^^

			
			
			// DONE: 6. b), c), d)  
			// vvvvvv 6. b), c), d) vvvvvv (about 5-8 lines)
			for (Hero hero : heroList) {
				CheckBox box = new CheckBox(hero.toString());
				box.setOnAction(new CheckBoxHandler(hero));
				selectBox.getChildren().add(box);
			}

			// ^^^^^^ 6. b), c), d) ^^^^^^

		}
	}

	private class CheckBoxHandler implements EventHandler<ActionEvent> {

		Hero hero;
		
		// When creating a new CheckBoxHandler, pass in a Hero object so it can be accessed later
		public CheckBoxHandler(Hero _hero) {
			this.hero = _hero;
		}

		@Override
		public void handle(ActionEvent event) {
			// DONE: 7. a) Use event.getSource() to get the CheckBox that triggered the event, cast it to CheckBox
			// vvvvvv 7. a) vvvvvv (1 line)
			CheckBox box = (CheckBox)event.getSource();
			// ^^^^^^ 7. a) ^^^^^^
			
			// DONE: 7. b) If the CheckBox was selected, add the current hero scores to totalStrength, 
			// 	totalCharisma, and totalDamge. Otherwise, subtract the current hero scores
			// vvvvvv 7. b) vvvvvv (about 8-12 lines)
			if(box.isSelected()){
				totalStrength += hero.getStrength();
				totalCharisma += hero.getCharisma();
				totalDamage += (int)hero.getDamage();
			} else {
				totalStrength -= hero.getStrength();
				totalCharisma -= hero.getCharisma();
				totalDamage -= (int)hero.getDamage();
			}

			// ^^^^^^ 7. b) ^^^^^^

			// DONE: 7. c) Set the Label to 
			// "Total Damage: " + totalDamage + "\t\tTotal Strength: " + totalStrength + "\tTotal Charisma: " + totalCharisma
			// vvvvvv 7. c) vvvvvv (1 line)
			armyInfoLabel.setText("Total Damage: " + totalDamage + "\t\tTotal Strength: " + totalStrength + "\tTotal Charisma: " + totalCharisma);

			// ^^^^^^ 7. c) ^^^^^^

		}
	}

}
