// Assignment #7, CSE205, Arizona State University 
// Name: Nathan Anderson
// StudentID: 1221689573
// Lecture time: Tu Th 9am
// Description: SketchPane provides a canvas for users to draw on

import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class SketchPane extends BorderPane {
	
	//Task 1: Declare all instance variables listed in UML diagram
private ArrayList<Shape> shapeList;
private ArrayList<Shape> tempList;
private Button undoButton;
private Button eraseButton;
private Label fillColorLabel;
private Label strokeColorLabel;
private Label strokeWidthLabel;
private ComboBox<String> fillColorCombo;
private ComboBox<String> strokeWidthCombo;
private ComboBox<String> strokeColorCombo;
private RadioButton radioButtonRectangle;
private RadioButton radioButtonCircle;
private RadioButton radioButtonLine;
private Pane sketchCanvas;
private Color[] colors;
private String[] strokeWidth;
private String[] colorLabels;
private Color  currentStrokeColor;
private Color  currentFillColor;
private int currentStrokeWidth;
private Line line;
private Circle circle;
private Rectangle rectangle;
private double x1;
private double y1;
	//Task 2: Implement the constructor
	public SketchPane() {
		// Colors, labels, and stroke widths that are available to the user
		colors = new Color[] {Color.BLACK, Color.GREY, Color.YELLOW, Color.GOLD, Color.ORANGE, Color.DARKRED, Color.PURPLE, Color.HOTPINK, Color.TEAL, Color.DEEPSKYBLUE, Color.LIME} ;
        colorLabels = new String[] {"black", "grey", "yellow", "gold", "orange", "dark red", "purple", "hot pink", "teal", "deep sky blue", "lime"};
        fillColorLabel = new Label("Fill Color:");
        strokeColorLabel = new Label("Stroke Color:");
        strokeWidthLabel = new Label("Stroke Width:");
        strokeWidth = new String[] {"1", "3", "5", "7", "9", "11", "13"};

		shapeList = new ArrayList<Shape>();
		tempList = new ArrayList<Shape>();
		
		fillColorCombo = new ComboBox<String>();
		strokeColorCombo = new ComboBox<String>();
		strokeWidthCombo = new ComboBox<String>();

		fillColorCombo.getItems().addAll(colorLabels);
		strokeColorCombo.getItems().addAll(colorLabels);
		strokeWidthCombo.getItems().addAll(strokeWidth);

		fillColorCombo.setValue("black");
		strokeColorCombo.setValue("black");
		strokeWidthCombo.setValue("1");

		fillColorLabel = new Label("Fill Color:");
		strokeColorLabel = new Label("Stroke Color:");
		strokeWidthLabel = new Label("Stroke Width:");

		ToggleGroup shapeSelector = new ToggleGroup();
		radioButtonRectangle = new RadioButton("Rectangle");
		radioButtonCircle = new RadioButton("Circle");
		radioButtonLine = new RadioButton("Line");

		radioButtonRectangle.setToggleGroup(shapeSelector);
		radioButtonCircle.setToggleGroup(shapeSelector);
		radioButtonLine.setToggleGroup(shapeSelector);

		radioButtonLine.setSelected(true);

		undoButton = new Button("Undo");
		eraseButton = new Button("Erase");

		undoButton.setOnAction(new ButtonHandler());
		eraseButton.setOnAction(new ButtonHandler());

		sketchCanvas = new Pane();
		sketchCanvas.setStyle("-fx-background-color: WHITE;");

		HBox topBar = new HBox();
		topBar.setSpacing(20);
		topBar.setMinSize(20, 40);
		topBar.setAlignment(Pos.CENTER);
		topBar.setStyle("-fx-background-color: LIGHTGREY;");
		topBar.getChildren().addAll(fillColorLabel, fillColorCombo, strokeWidthLabel, strokeWidthCombo, strokeColorLabel, strokeColorCombo);

		HBox bottomBar = new HBox();
		bottomBar.setSpacing(20);
		bottomBar.setMinSize(20, 40);
		bottomBar.setAlignment(Pos.CENTER);
		bottomBar.setStyle("-fx-background-color: LIGHTGREY;");
		bottomBar.getChildren().addAll(radioButtonLine, radioButtonRectangle, radioButtonCircle, undoButton, eraseButton);

		this.setCenter(sketchCanvas);
		this.setTop(topBar);
		this.setBottom(bottomBar);
		

		x1 = 0;
		y1 = 0;

		currentFillColor = Color.BLACK;
		currentStrokeColor = Color.BLACK;
		currentStrokeWidth = 1;

		MouseHandler handler = new MouseHandler();
		sketchCanvas.setOnMousePressed(handler);
		sketchCanvas.setOnMouseDragged(handler);
		sketchCanvas.setOnMouseReleased(handler);

		fillColorCombo.setOnAction(new ColorHandler());
		strokeColorCombo.setOnAction(new ColorHandler());
		strokeWidthCombo.setOnAction(new WidthHandler());

    }

	private class MouseHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// TASK 3: Implement the mouse handler for Circle and Line
			// Rectange Example given!
			if (radioButtonRectangle.isSelected()) {
				//Mouse is pressed
				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					x1 = event.getX();
					y1 = event.getY();
					rectangle = new Rectangle();
					rectangle.setX(x1);
					rectangle.setY(y1);
					shapeList.add(rectangle);
					rectangle.setFill(Color.WHITE);
					rectangle.setStroke(Color.BLACK);	
					sketchCanvas.getChildren().add(rectangle);
				}
				//Mouse is dragged
				else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
					rectangle.setWidth(Math.abs(event.getX() - x1));
					rectangle.setHeight(Math.abs(event.getY() - y1));

				}
				//Mouse is released
				else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
					rectangle.setFill(currentFillColor);
					rectangle.setStroke(currentStrokeColor);
					rectangle.setStrokeWidth(currentStrokeWidth);
				}
			}
			else if (radioButtonLine.isSelected()) {
				//Mouse is pressed
				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					x1 = event.getX();
					y1 = event.getY();
					line = new Line();
					line.setStartX(x1);
					line.setStartY(y1);
					line.setEndX(x1);
					line.setEndY(y1);
					shapeList.add(line);
					line.setFill(Color.WHITE);
					line.setStroke(Color.BLACK);	
					sketchCanvas.getChildren().add(line);
				}
				//Mouse is dragged
				else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
					line.setEndX(event.getX());
					line.setEndY(event.getY());

				}
				//Mouse is released
				else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
					line.setStroke(currentStrokeColor);
					line.setStrokeWidth(currentStrokeWidth);
				}
			}
			else if (radioButtonCircle.isSelected()) {
				//Mouse is pressed
				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					x1 = event.getX();
					y1 = event.getY();
					circle = new Circle();
					circle.setCenterX(x1);
					circle.setCenterY(y1);
					circle.setRadius(0);
					shapeList.add(circle);
					circle.setFill(Color.WHITE);
					circle.setStroke(Color.BLACK);	
					sketchCanvas.getChildren().add(circle);
				}
				//Mouse is dragged
				else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
					circle.setRadius(getDistance(x1, y1, event.getX(), event.getY()));
				}
				//Mouse is released
				else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
					circle.setFill(currentFillColor);
					circle.setStroke(currentStrokeColor);
					circle.setStrokeWidth(currentStrokeWidth);
				}
			}
		}
	}
		
	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if(event.getSource() == undoButton){ //if undo is pressed
				if(shapeList.size() != 0){ //if canvas not empty
					sketchCanvas.getChildren().clear();
					shapeList.remove(shapeList.size() - 1);
					sketchCanvas.getChildren().addAll(shapeList);
				} else if (tempList.size() != 0){ //if canvas is empty and temo list has shapes
					shapeList = new ArrayList<>(tempList); //reinitialize shapelist with elements from templist
					sketchCanvas.getChildren().addAll(shapeList);
					tempList.clear();
				}
			} else if (event.getSource() == eraseButton && shapeList.size() != 0){ //if erase button pressed and there are shapes on screen
				tempList = new ArrayList<>(shapeList); //reinitialize templist with elements of shapelist
				shapeList.clear();
				sketchCanvas.getChildren().clear();
			}
		}
	}

	private class ColorHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			// TASK 5: Implement the color handler
			ComboBox<String> selector = (ComboBox<String>)event.getSource(); //get the action event anccast it to a combobox
			// this line gives a warning because there is no gaurantee that the event will be a combobox however this should be okay for this application
			Color selectedColor = colors[selector.getSelectionModel().getSelectedIndex()];
			if (selector == fillColorCombo){
				currentFillColor = selectedColor;
			} else if (selector == strokeColorCombo){
				currentStrokeColor = selectedColor;
			}
		}
	}
	
	private class WidthHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event){
			// TASK 6: Implement the stroke width handler
			ComboBox<String> source = (ComboBox<String>) event.getSource();
			System.out.print("wow");
			currentStrokeWidth = Integer.parseInt(source.getValue());
		}
	}
	
		
	// Get the Euclidean distance between (x1,y1) and (x2,y2)
    private double getDistance(double x1, double y1, double x2, double y2)  {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}