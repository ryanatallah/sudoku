
public class GameRunner {
  public static void main(String[] args){

    int[][] test = { // EVIL
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

    boolean print = true;
    SudokuGame trial1 = new SudokuGame(test, print);
    trial1.printBoard(test, "STARTING PUZZLE", 0);
    trial1.solvePuzzle();
  }
}
