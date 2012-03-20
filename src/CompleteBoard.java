import java.util.*;
public abstract class CompleteBoard extends SudokuGame {
  static int[][] temp=new int[9][9];
  Random generator=new Random();
  static boolean print=true;
  boolean solvable;
  
  public CompleteBoard(){
	  super(temp, print);
  }
  
  /**
   * Inserts random numbers into the board at every space
   * @return board, the completed board
   */
  public int[][] makeBoard(){
    for (int i=0; i<9; i++){
      for (int j=0; j<9; j++){
    	insertNum(i,j);
      }
      //printBoard(board, "TestBoard "+ i, 0);
    }
    return board;
  }
  
  /**
   * Given a blank space, inserts a random number that exists in the set
   * of possible values for that space
   * Checks to see if the board is still solvable
   * If not, resets the board to its prior state and inserts a different number
   * @param i the x-value of the space
   * @param j the y-value of the space
   */
  public void insertNum(int i, int j){
	int[][] tempBoard=board;
	int[][][] tempSet=set;
	int num=generator.nextInt(9);
	if (set[i][j][num] != 0){
  	  board[i][j]=set[i][j][num];
	}
  	else{
  	  for (int c=0; c<9; c++){
  		if (set[i][j][c] != 0){
  		  board[i][j]=set[i][j][c];
  		  num=c;
  		}
  	  }
  	}
  	makePossArr();
  	
  	if (isSolvable(set)==false){
  	  //printBoard(board, "Not fixed", 0);
  	  set=tempSet;
  	  board=tempBoard;
  	  for (int a=0; a<9; a++){
  		board[i][a]=0;
  	  }
  	  resetPossValues();
  	  for (int b=0; b<=j; b++){
  		insertNum(i, b);
  	  }
  	  //printBoard(board, "Fixed", 0);
  	}
  }
  
  /**
   * Checks to see if the board is solvable
   * If there are spaces with no possible values, the board
   * cannot be solved
   * @param test the board to be checked
   * @return if the board is solvable
   */
  public boolean isSolvable(int[][][] test){
	int counter=0;
	for (int a=0; a<9; a++){
	  for (int b=0; b<9; b++){
		for (int c=0; c<9; c++){
	      if (test[a][b][c] != 0){
		    counter++;
	      }
		}
	    if (counter==0){
	  	  return false;
	    }
	    counter=0;
	  }
	}
	return true;
  }
  
  public abstract int[][] makePuzzle();
}
