import java.util.*;

public class SudokuGame {

  int[][][] set = new int[9][9][9];
  int[][] board = new int[9][9];

  /**
   * Defines board and creates the initial possible values array with numbers 1 - 9.
   *
   * @param in the starting game board.
   */
  public SudokuGame(int[][] in) {
    board = in;

    for (int a = 0; a < 9; a++)
      for (int b = 0; b < 9; b++)
        for (int c = 1; c < 10; c++)
          set[a][b][c-1] = c;
  }


  /**
   * Updates the array of possible values for each space on the board by taking each
   * filled space and removing that space's value from the possible values of each
   * space in the same row, column, and 3x3 square. The first two dimensions of set
   * represent the coordinates of the space on the board, and the third dimension
   * represents the remaining possible values for that space. Values that are no
   * longer possible for a space are replaced by 0s.
   *
   * Does not update board.
   *
   * @return the set of possible values for each space on board.
   */
  public int[][][] makePossArr() {

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        int num = board[i][j];

        // Blank spaces on the board are represented by 0.
        if (num > 0) {

          // Removes num from possible values of the spaces in column j.
          for (int x = 0; x < 9; x++)
            set[x][j][num - 1] = 0;

          // Removes num from possible values of the spaces in row i.
          for (int y = 0; y < 9; y++)
            set[i][y][num - 1] = 0;

          // 2-value array that holds the square position of a space.
          int[] squarePos = new int[2];

          // Calculates the square position based on row i and column j.
          squarePos[0] = (i / 3) * 3;
          squarePos[1] = (j / 3) * 3;

          // Removes num from possible values of other spaces of the squarePos[0][1].
          for (int w = squarePos[0]; w < squarePos[0] + 3; w++)
            for (int z = squarePos[1]; z < squarePos[1] + 3; z++)
              set[w][z][num - 1] = 0;

          // Removes all possible values for the filled space.
          for (int e = 1; e < 9; e++)
            set[i][j][e] = 0;

          // Inserts num as the only possible value for the filled space.
          set[i][j][num - 1] = num;
        }
      }
    }
    return set;
  }


  /**
   * Updates the array of possible values. If there's only one possible value for a
   * space, inserts value into space in board. Then makes a new array of possible
   * values given the updated board.
   *
   * @return the set of possible values for each space on board.
   */
  public int[][][] solveArr() {
    boolean isSolved = false;

    updateBoard();

    while (isSolved == false) {
      isSolved = true;
      for (int a = 0; a < 9; a++) {
        for (int b = 0; b < 9; b++) {
          if (board[a][b] == 0) {
            isSolved = false;
            set = solveArr();
          }
        }
      }
    }
    return set;
  }

  public void updateBoard() {
    int counter = 0;
    int index = 0;

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        for (int g = 0; g < 9; g++) {
          if (set[i][j][g] != 0) {
            counter++;
            index = g;
          }
        }
        if (counter == 1) {
          board[i][j] = set[i][j][index];
          set = makePossArr();
        }
        counter = 0;
      }
    }
  }
  


  public int[][] getBoard() {
    return board;
  }

  public static void printBoard(int[][] board)
  {
    System.out.println("----------|-----------|----------");
    for (int j = 0; j < 9; j++) {
      for (int i = 0; i < 8; i++) {
        System.out.print(board[i][j] + " | ");
      }
      System.out.println(board[8][j]);
      if (j % 3 == 2)
      {
        System.out.println("----------|-----------|----------");
      }
    }
  }
}
