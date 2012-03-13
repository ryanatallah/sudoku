import java.util.*;
public class IncompleteBoard extends CompleteBoard{
  int[][] puzzle;
  int[][] solution;
  int[][] waves;
  Random generator = new Random();
  
  public IncompleteBoard(){
	super();
  }
  
  /**
   * Randomly removes approximately 50 numbers from individual spaces
   * Mirrors the spaces removed so that the grid is symmetrical
   * over the line from the top left corner to the bottom right corner
   * Checks to see if the board is solvable
   * @return puzzle, a solvable puzzle
   */
  public int[][] makePuzzle(){
	puzzle = super.makeBoard();
	solution = puzzle;
	for (int n=0; n<25; n++){
	  int i = generator.nextInt(9);
	  int j = generator.nextInt(9);
	  puzzle[i][j]=0;
	  puzzle[8-i][8-j]=0;
	}
	waves = puzzle;
	SudokuGame test = new SudokuGame(puzzle, true);
	printBoard(waves, "Temporary", 0);
	test.solvePuzzle();
	printBoard(waves, "Temporary", 0);
	if (test.isSolved() == true){
	  puzzle = waves;
	  System.out.println("Solvable");
	}
	else{
	  System.out.println("Not solvable");
	  puzzle = makePuzzle();
	}
	return puzzle;
  }
}
