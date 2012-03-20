import java.util.Random;
public class TenBoard extends CompleteBoard{
  public TenBoard(){
	super();
  }
  
  /**
   * Makes a random board that isn't Sudoku
   * @param n number
   * @return not-Sudoku board
   */
  public int[][] makePuzzle(){
	Random generator = new Random();
	int n = generator.nextInt(5);
	int[][] board = super.makeBoard();
	for (int i=0; i<9; i++){
	  for (int j=0; j<9; j++){
		board[i][j] = board[i][j] + function(n);
	  }
	}
	return board;
  }
  
  /**
   * Random math functions
   * @param x variable of function
   * @return output of function
   */
  public int function(int x){
	int[] arr = new int[5];
	arr[0] = x;
	arr[1] = 1/2 * x * x;
	arr[2] = 1/3 * x * x * x;
	arr[3] = (int) Math.sqrt((double)x);
	arr[4] = (int) (Math.sqrt((double)x) * Math.sqrt((double)x) * Math.sqrt((double)x));
	return arr[x];
  }
}
