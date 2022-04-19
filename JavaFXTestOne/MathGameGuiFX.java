//MathGameGuiFX.java
//see also EventHandleMathGameFX.java
//This program allow user to enter two numbers into textfields, then by picking
//one of the four buttons, computes their sum, difference, multiplication, division.

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;	//**Need to import
import javafx.event.EventHandler;	//**Need to import

import java.text.DecimalFormat;

public class MathGameGuiFX extends GridPane
{
	//declare all necessary GUI components
	private Label instruction, num1Label, num2Label, resultLabel, result;
	private TextField num1Text, num2Text;
	private Button addi, sub, multi, divi;

	//within constructor - initialize all instance variables
	//and set up the layout
	public MathGameGuiFX ()
	{
		//Step #1: instantiate all instance varibles
		instruction = new Label("Enter two numbers:");
		num1Label = new Label("Number 1:");
		num2Label = new Label("Number 2:");
		resultLabel = new Label("Result is: ");
		result = new Label ("---");

		num1Text = new TextField();
		num2Text = new TextField();

		addi = new Button("+");
		sub = new Button("-");
		multi = new Button("*");
		divi = new Button("/");

		//Step #1:Set up the layout
		//Create a GridPane and set its properties
		GridPane subPane1 = new GridPane();
		subPane1.setAlignment(Pos.CENTER);
		subPane1.setPadding(new Insets(11, 12, 13, 14));
		subPane1.setHgap(5);
		subPane1.setVgap(5);

		//add labels and text fields
		subPane1.add(num1Label, 0, 0);
		subPane1.add(num1Text, 1, 0);
		subPane1.add(num2Label, 0, 1);
		subPane1.add(num2Text, 1, 1);
		subPane1.add(resultLabel, 0, 2);
		subPane1.add(result,1, 2);

		//Create a HBox - subPane2 and set its properties
		//subPane2 contains the four buttons
		HBox subPane2 = new HBox(15);	//horizontal gap is 15
		subPane2.setPadding(new Insets(11, 12, 13, 14));
		subPane2.setAlignment(Pos.CENTER);
		subPane2.getChildren().addAll(addi, sub, multi, divi);

		//the MathGameGuiFX is a GridPane
		this.add(instruction, 0, 0);
		this.add(subPane1, 0, 1);
		this.add(subPane2, 0, 2);

		//step #3: register the source object with a new handler object
		addi.setOnAction(new ButtonHandler());
		sub.setOnAction(new ButtonHandler());
		multi.setOnAction(new ButtonHandler());
		divi.setOnAction(new ButtonHandler());

	} //end of the constructor

	//step #2: define a handler class that implements the corresponding handler interface
	//         you will need to override the abstract method inside that handler interface
	private class ButtonHandler implements EventHandler<ActionEvent>
	{
		//Within the method, you will need to define what happened when you click on the button
		public void handle(ActionEvent e)
		{
			//get the two numbers from the two text fields
			double num1 = Double.parseDouble(num1Text.getText());
			double num2 = Double.parseDouble(num2Text.getText());
			double numResult;

			//Check which button is picked
			Object source = e.getSource();
			if(source == addi)
				numResult = num1+num2;
			else if(source == sub)
				numResult = num1 - num2;
				else if(source == multi)
					numResult = num1*num2;
					else
						numResult = (num1*1.0)/num2;
			//result contain exactly two decimal digits
			DecimalFormat fmt = new DecimalFormat("0.00");
			result.setText(fmt.format(numResult));
		}
	}//end of ButtonHandler class
}