import java.util.*;
public class IncompleteBoard extends CompleteBoard{
  int[][] puzzle = new int[9][9];
  int[][] solution = new int[9][9];
  int[][] waves = new int[9][9];
  Random generator = new Random();
  int num;
  
  public IncompleteBoard(int diff){
	super();
	if (diff == 1)
	  num = 29;
	else if (diff == 2)
	  num = 32;
	else if (diff == 3)
	  num = 35;
	else if (diff == 4)
	  num = 38;
	else
	  num = 41;
  }
  
  public IncompleteBoard(){
	super();
	num = 35;
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
	for (int a=0; a<9; a++){
	  for (int b=0; b<9; b++){
		solution[a][b] = puzzle[a][b];
	  }
	}
	for (int n=0; n<num; n++){
	  int i = generator.nextInt(9);
	  int j = generator.nextInt(9);
	  puzzle[i][j]=0;
	  puzzle[8-i][8-j]=0;
	}
	
	for (int i=0; i<9; i++){
	  for (int j=0; j<9; j++){
		waves[i][j] = puzzle[i][j];
	  }
	}
	
	SudokuGame test = new SudokuGame(puzzle, true);
	printBoard(waves, "Temporary", 0);

	test.solvePuzzle();
	
	
	if (test.isSolved() == true){
	  for (int c=0; c<9; c++){
		for (int d=0; d<9; d++){
	      puzzle[c][d]=waves[c][d];
		}
	  }
	  System.out.println("Solvable");
	}
	else{
	  System.out.println("Not solvable");
	  puzzle = makePuzzle();
	}
	return puzzle;
  }
  
  /**
   * Gets the solved board
   * @return solution, the completed board
   */
  public int[][] getSolved(){
	return solution;
  }
}
