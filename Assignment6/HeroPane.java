// Assignment #: 6
//         Name: Nathan Anderson
//    StudentID: 1221689573
//      Lecture: 9am
//  Description: This is the main pane for creating heroes


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

// There are 4 (FOUR) tasks to be completed in this file - Look for TODO: keywords
// DONE: 1. Build the GUI
// DONE: 2. Write "Random" Button Handler
// DONE: 3. "Add New Hero" button handler - Check for valid input before adding the new Hero to the list:
// DONE: 4. Update rightTextArea with updated information from heroList
public class HeroPane extends HBox {
	// COMPLETED: contains a list of heroes
	ArrayList<Hero> heroList;

	// COMPLETED: Save current Hero Type
	String selectedHeroType;

	// COMPLETED: Main layout components
	TextArea rightTextArea;
	VBox leftVBox;
	ComboBox<String> heroTypeComboBox;
	ImageView imageView;
	// DONE 1. a) "Declare" (Do not "initialize" them yet!!!)
	// vvvvvv 1. a) vvvvvv (about 8-12 lines)
	// ONE GridPane to hold
	GridPane statPane;
	// FOUR Labels (Name, Strength, Charisma, Damage),
	Label nameLabel;
	Label strengthLabel;
	Label charismaLabel;
	Label damageLabel;
	// FOUR corresponding TextFields
	TextField nameField;
	TextField strengthField;
	TextField charismaField;
	TextField damageField;
	// ONE "Random" Button
	Button randomButton;
	GridPane inputPane;

	// ^^^^^^ 1. a) ^^^^^^

	// DONE 1. b) "Declare" (Do not "initialize" them yet!!!)
	// ONE "Add New Hero!!!" Button
	// ONE red Label to display add hero status.
	// vvvvvv 1. b) vvvvvv (about 2 lines)

	Button newHeroButton;
	Label statusLabel;
	// ^^^^^^ 1. b) ^^^^^^

	// COMPLETED: Define window size
	public static final int WINSIZE_X = 950, WINSIZE_Y = 600;

	// Constructor - what to do when HeroPane is first created
	public HeroPane(ArrayList<Hero> heroList) {

		// COMPLETED: Assign the heroList passed into this constructor
		this.heroList = heroList;

		// COMPLETED: Initialize main layout components
		this.leftVBox = new VBox();
		this.rightTextArea = new TextArea();

		// COMPLETED: Setting up ComboBox
		String[] heroType = { "Mage", "Fighter", "Unicorn", "Zombie" };
		heroTypeComboBox = new ComboBox<String>();
		heroTypeComboBox.setValue("Hero Type");
		heroTypeComboBox.getItems().addAll(heroType);
		heroTypeComboBox.setOnAction(new HeroTypeComboBoxHandler());
		leftVBox.getChildren().add(heroTypeComboBox);

		// DONE 1. a) Initialize the instance variables
		// This is where you use the "new" keyword
		// vvvvvv 1. a) vvvvvv (about 8-12 lines)
		statPane = new GridPane();
		nameLabel = new Label("Name");
		strengthLabel = new Label("Strength");
		charismaLabel = new Label("Charisma");
		damageLabel = new Label("Damage");

		nameField = new TextField();
		strengthField = new TextField();
		charismaField = new TextField();
		damageField = new TextField();

		randomButton = new Button("Random");
		newHeroButton = new Button("Add new Hero!");
		statusLabel = new Label();

		
		// ^^^^^^ 1. a) ^^^^^^

		// DONE 1. b) Initialize the instance variables and set Label color to RED
		// vvvvvv 1. b) vvvvvv (about 3 lines)
		statusLabel.setTextFill(Color.RED);
		// ^^^^^^ 1. b) ^^^^^^

		// DONE 1. c) Organize Labels, TextFields, and Button onto the GridPane
		// vvvvvv 1. c) vvvvvv (about 8-12 lines)
		inputPane = new GridPane();
		inputPane.add(nameLabel, 0, 0);
		inputPane.add(nameField, 1, 0);
		inputPane.add(strengthLabel, 0, 1);
		inputPane.add(strengthField, 1, 1);
		inputPane.add(charismaLabel, 0, 2);
		inputPane.add(charismaField, 1, 2);
		inputPane.add(damageLabel, 0, 3);
		inputPane.add(damageField, 1, 3);
		inputPane.add(randomButton, 2, 3);
		// ^^^^^^ 1. c) ^^^^^^

		// DONE: 1. d) Bind buttons to their handlers (RandomButtonHandler and
		// AddNewHeroButtonHandler)
		// vvvvvv 1. d) vvvvvv (about 2 lines)
		randomButton.setOnAction(new RandomButtonHandler());
		newHeroButton.setOnAction(new AddNewHeroButtonHandler());
		// ^^^^^^ 1. d) ^^^^^^

		// DONE: 1. e) Add GridPane, “Add Hero” Button, and red Label to leftVBox
		// vvvvvv 1. e) vvvvvv (about 1-3 lines)
		leftVBox.getChildren().addAll(inputPane, newHeroButton, statusLabel);
		// ^^^^^^ 1. e) ^^^^^^

		// COMPLETED: VBox layout alignment
		inputPane.setHgap(20);
		leftVBox.setPadding(new Insets(40, 50, 0, 50));
		leftVBox.setSpacing(40);
		leftVBox.setAlignment(Pos.TOP_CENTER);
		leftVBox.setPrefWidth(WINSIZE_X / 2);

		// COMPLETED: Setting up ImageView
		imageView = new ImageView();
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(100);
		leftVBox.getChildren().add(imageView);
		FileInputStream input;
		try {
			input = new FileInputStream("unicorn.png");
			Image image = new Image(input);
			imageView.setImage(image);
		} catch (FileNotFoundException e) {
			imageView.setImage(null);
		}

		// COMPLETED: Add main components to "Add Hero" tab
		this.getChildren().addAll(leftVBox, rightTextArea);
	}

	// We have finished setting up the GUI for Add Hero tab, let's move on to the
	// logic/back-end side!
	// Writing handlers

	// Generate random damage value (50 <= damage <= 100)
	private class RandomButtonHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			// DONE: 2. Write "Random" Button Handler
			// vvvvvv 2. vvvvvv (about 8-12 lines)
			Random rand = new Random();
			int randVal = rand.nextInt(50) + 50;
			damageField.setText(String.valueOf(randVal));
			// ^^^^^^ 2. ^^^^^^

		}
	}

	// DONE 3. "Add New Hero" button handler - Check for valid input before adding
	// the new Hero to the list
	private class AddNewHeroButtonHandler implements EventHandler<ActionEvent> {

		// This method will be called once we click the button
		public void handle(ActionEvent event) {

			// DONE 3. a) Create 4 String variables and assign them to the values retrieved
			// from TextFields using .getText()
			// vvvvvv 3. a) vvvvvv (about 4 lines)
			String name = nameField.getText();
			String strengthData = strengthField.getText();
			String charismaData = charismaField.getText();
			String damageData = damageField.getText();
			// ^^^^^^ 3. a) ^^^^^^

			// "Always sanitize user input"
			// You need to make sure the input is valid, for example:
			// - Not empty
			// - In the correct format (String, Integer, Double, etc)
			// - Satisfied all input requirement (non-negative, between a range, etc)
			// Use this try/catch block AND "throw new Exception()" to handle invalid input
			try {

				// EXAMPLE: When the hero type is not selected
				if (selectedHeroType == null) {
					// When you throw a new Exception, the program STOPS immediately
					// and SKIPS to the catch{} block, so the lines below will NOT execute.
					// Check out the catch{} block below
					throw new Exception("Hero type is not yet selected");
				}

				// DONE: 3. b) If one of the TextFields is empty, throw exception with
				// error message: "At least one of the text fields is empty"
				// vvvvvv 3. b) vvvvvv (about 4 lines)
				if (name.compareTo("") == 0 || strengthData.compareTo("") == 0 || charismaData.compareTo("") == 0  || damageData .compareTo("") == 0 ){
					throw new Exception("At least one of the text fields is empty");
				}
				// ^^^^^^ 3. b) ^^^^^^

				// DONE: 3. c) Loop through heroList to check for hero that has the same name;
				// throw exception with
				// error message: "Hero existed!"
				// vvvvvv 3. c) vvvvvv (about 5 lines)
				for (Hero hero : heroList) {
					if (hero.getName().compareTo(name) == 0){
						throw new Exception("Hero existed!");
					}
				}
				// ^^^^^^ 3. c) ^^^^^^

				// DONE: 3. d) Now try to parse Strength, Charisma, and Damage to integers
				// The parseInt method will automatically throw error for you if the input is
				// not in the integer format
				// Create 3 integers and convert the Strings from 3. a)
				// vvvvvv 3. d) vvvvvv (about 3 lines)
				int strength = Integer.parseInt(strengthData);
				int charisma = Integer.parseInt(charismaData);
				int damage = Integer.parseInt(damageData);

				// ^^^^^^ 3. d) ^^^^^^

				// DONE: 3. e) Check if Strength or Charisma is a negative number
				// if so, throw exception with error message "Both Strength and Charisma must be
				// positive numbers"
				// vvvvvv 3. e) vvvvvv (about 3 lines)
				if (strength < 0 || charisma < 0){
					throw new Exception("Both strength and Charisma must be positive numbers");
				}
				// ^^^^^^ 3. e) ^^^^^^

				// DONE: 3. f) Check if the sum of Strength and Charisma exceeds 100.
				// If so, throw exception with error message "The sum of strength and charisma
				// must be less or equal to 100"
				// vvvvvv 3. f) vvvvvv (about 3 lines)
				if(strength + charisma > 100){
					throw new Exception("The sum of strength and charisma must be less or equal to 100");
				}
				// ^^^^^^ 3. f) ^^^^^^

				// DONE: 3. g) Input is valid, now create new Hero object (remember to check out
				// Hero.java file)
				// with data from user input. Don't forget "selectedHeroType"
				// Finally, add the newly created hero to heroList
				// vvvvvv 3. g) vvvvvv (about 2-8 lines, depends on your implementation)
				heroList.add(new Hero(name, selectedHeroType, strength, charisma, damage));
				// ^^^^^^ 3. g) ^^^^^^

				// DONE 3: Set the Red Label to "Hero added successfully" and empty all
				// TextFields
				// vvvvvv 3. h) vvvvvv (about 5 lines)
				statusLabel.setText("Hero added successfully");
				nameField.setText("");
				charismaField.setText("");
				strengthField.setText("");
				damageField.setText("");
				// ^^^^^^ 3. h) ^^^^^^

				// DONE 4. b) Call updateTextArea() to update heroes list
				// vvvvvv 4. b) vvvvvv (1 line)
				updateTextArea();
				// ^^^^^^ 4. b) ^^^^^^

			} catch (NumberFormatException exception) {
				// set RED LABEL to "At least one of the text fields is in the incorrect format"
				// vvvvvv 3. d) vvvvvv (1 line)
				statusLabel.setText("At least one of the text fields is in the incorrect format");
				// ^^^^^^ 3. d) ^^^^^^

			} catch (Exception exception) {
				// DONE: 3. b) The message that we passed in "throw new Exception(MESSAGE);"
				// above
				// can be retrieved using exception.getMessage()
				// Set the value of the RED LABEL to exception.getMessage() to display error
				// message
				// vvvvvv 3. b) vvvvvv (1 line)
				System.out.print(exception.getClass().toString());

				statusLabel.setText(exception.getMessage());
				// ^^^^^^ 3. b) ^^^^^^

			}

		}
	}

	// DONE 4. a) Create a String containing all hero information
	// and loop through heroList to add all heroes' data together
	private void updateTextArea() {
		// vvvvvv 4. a) vvvvvv (about 5-10 lines)
		String summary = "";
		for (Hero hero : heroList) {
			summary += hero.toString() + "\n";
		}
		rightTextArea.setText(summary);
		// ^^^^^^ 4. a) ^^^^^^
	}

	// Completed: HeroTypeComboBoxHandler - You don't need to modify this handler
	private class HeroTypeComboBoxHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			selectedHeroType = heroTypeComboBox.getSelectionModel().getSelectedItem();
			FileInputStream input;
			try {
				input = new FileInputStream(selectedHeroType.toLowerCase() + ".png");
				Image image = new Image(input);
				imageView.setImage(image);
			} catch (FileNotFoundException e) {
				imageView.setImage(null);
			}

		}
	}

}
