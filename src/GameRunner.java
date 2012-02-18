
public class GameRunner {
  public static void main(String[] args){

    int[][] test = {
      {0,0,0,2,0,0,0,6,3},
      {3,0,0,0,0,5,4,0,1},
      {0,0,1,0,0,3,9,8,0},
      {0,0,0,0,0,0,0,9,0},
      {0,0,0,5,3,8,0,0,0},
      {0,3,0,0,0,0,0,0,0},
      {0,2,6,3,0,0,5,0,0},
      {5,0,3,7,0,0,0,0,8},
      {4,7,0,0,0,1,0,0,0}
    };

    boolean print = true;
    SudokuGame trial1 = new SudokuGame(test, print);
    trial1.printBoard(test, "STARTING PUZZLE", 0);
    trial1.solvePuzzle();
  }
}
