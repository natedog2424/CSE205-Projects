// Assignment: Assignment 11
// Name: Nathan Anderson
// StudentID: 1221689573
// Lecture: Tu Th 9am
// Description: Maze Solver class.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class MazeSolver {
	
	private char[][] maze;
	private Stack<Node> stack = new Stack<Node>();
	private int eggFound;
	private int mHeight;
	private int mWidth;

	// ************************************************************************************
	// ************** Utility method to read maze from user input *************************
	// ************************************************************************************
	public void readMaze() {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Height of the maze: ");
			String line = reader.readLine();
			mHeight = Integer.parseInt(line);

			System.out.println("Width of the maze: ");
			line = reader.readLine();
			mWidth = Integer.parseInt(line);
			maze = new char[mHeight][mWidth];

			for (int i = 0; i < mHeight; i++) {
				line = reader.readLine();
				for (int j = 0; j < mWidth; j++) {
					maze[i][j] = line.charAt(j);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void depthFirstSearch(){
		stack.push(new Node(0, 0));

		while(stack.size() != 0){
			// main loop of searching algorithm
			//Pop a node from the stack
			Node current = stack.pop();
            //Check if the node has any egg (collect egg and increase egg count if yes)
			if(maze[current.getX()][current.getY()] == 'E'){
				eggFound++;
			}
            //Mark the current node as visited 
			maze[current.getX()][current.getY()] = 'x';
			//Calculate the coordinate of the adjacent node to the south
			Node south = new Node(current.getX(), current.getY() + 1);
            //Check if the node coordinates are valid and if so push it into the stack
			if(isValid(south)){
				stack.push(south);
			}

			//repeat for east, north, and west
			Node east = new Node(current.getX() + 1, current.getY());
			if(isValid(east)){
				stack.push(east);
			}

			Node north = new Node(current.getX(), current.getY() - 1);
			if(isValid(north)){
				stack.push(north);
			}

			Node west = new Node(current.getX() - 1, current.getY());
			if(isValid(west)){
				stack.push(west);
			}
		}
	}

	//utility method for checking if a node position is valid (within bounds, not a wall, and not visited)
	private boolean isValid(Node node){
		if(node.getX() < 0 || node.getX() >= mHeight || node.getY() < 0 || node.getY() >= mWidth){
			return false;
		}
		if(maze[node.getX()][node.getY()] == '#'){
			return false;
		}
		if(maze[node.getX()][node.getY()] == 'x'){
			return false;
		}
		return true;
	}

	public int getEggFound(){
		return eggFound;
	}

	//print the 2d matrix maze to the console
	public void printMaze(){
		for (int i = 0; i < mHeight; i++) {
			for (int j = 0; j < mWidth; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
