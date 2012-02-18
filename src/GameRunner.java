
public class GameRunner {
  public static void main(String[] args){

    int[][] test = {
      {0,5,0,0,0,0,0,6,0},
      {3,0,0,2,0,8,0,0,7},
      {0,0,1,0,6,0,8,0,0},
      {0,2,0,0,0,0,0,8,0},
      {0,0,4,0,1,0,6,0,0},
      {0,7,0,0,0,0,0,3,0},
      {0,0,2,0,7,0,9,0,0},
      {4,0,0,5,0,9,0,0,2},
      {0,3,0,0,0,0,0,4,0}
    };

    SudokuGame.printBoard(test, "INITIAL BOARD", 0);

    SudokuGame trial1 = new SudokuGame(test);
    trial1.makePossArr();
    trial1.solvePuzzle();
  }
}
