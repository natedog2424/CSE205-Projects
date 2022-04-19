// Assignment: Assignment 11
// Name: Nathan Anderson
// StudentID: 1221689573
// Lecture: Tu Th 9am
// Description: Assignment 11 class.

public class Assignment11 {

	public static void main(String[] args) {
		MazeSolver mazeSolver = new MazeSolver();
		mazeSolver.readMaze();

		System.out.println("Original maze: ");
		mazeSolver.printMaze();

		mazeSolver.depthFirstSearch();

		System.out.println("Maze after Easter Bunny traverse: ");
		mazeSolver.printMaze();

		if (mazeSolver.getEggFound() == 0)
			System.out.println("The Bunny was unable to recover the eggs, Easter is cancelled!");
		else
			System.out.println("The Easter Bunny found " + mazeSolver.getEggFound() + " egg(s)!");
	}
}