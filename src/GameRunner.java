
public class GameRunner {
  public static void main(String[] args){

    int[][] test = {
      {0,0,7,0,0,9,8,2,0},
      {0,1,0,5,0,7,3,0,0},
      {0,9,4,0,1,3,0,0,5},
      {8,0,0,0,4,1,0,0,7},
      {4,0,1,0,0,0,2,0,6},
      {3,0,0,2,7,0,0,8,4},
      {0,0,0,7,8,0,4,1,0},
      {0,0,5,6,0,2,0,9,0},
      {0,8,2,1,0,0,5,0,0}
    };

    SudokuGame.printBoard(test, "INITIAL BOARD", 0);

    SudokuGame trial1 = new SudokuGame(test);
    trial1.makePossArr();
    trial1.solvePuzzle();
  }
}
