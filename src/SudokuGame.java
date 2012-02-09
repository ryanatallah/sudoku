import java.util.*;

public class SudokuGame {

  int[][][] set = new int[9][9][9];
  int[][] board = new int[9][9];

  public SudokuGame(int[][] in){
    board = in;

    for (int a = 0; a < 9; a++)
      for (int b = 0; b < 9; b++)
        for (int i = 1; i < 10; i++)
          set[a][b][i-1] = i;
  }

  // takes an board that's already made and generates a list of possible numbers for each space
  // does not update the board
  public int[][][] makePossArr(){

    for (int i = 0; i < 9; i++){
      for (int j = 0; j < 9; j++){
        int num = board[i][j];

        if (num > 0){

          for (int x = 0; x < 9; x++){
            for (int c = 0; c < 9; c++){
              if (set[x][j][c] == num){
                set[x][j][c] = 0;
              }
            }
          }

          for (int y = 0; y < 9; y++){
            for (int d = 0; d < 9; d++){
              if (set[i][y][d] == num)
                set[i][y][d] = 0;
            }
          }

          int[] squarePos = new int[2];

          if (i < 3)
              squarePos[0] = 0;
          else if (i < 6)
              squarePos[0] = 3;
          else
              squarePos[0] = 6;
          if (j < 3)
              squarePos[1] = 0;
          else if (j < 6)
              squarePos[1] = 3;
          else
              squarePos[1] = 6;

          for (int w = squarePos[0]; w < squarePos[0] + 3; w++){
              for (int z = squarePos[1]; z < squarePos[1] + 3; z++){
                for (int l = 0; l < 9; l++){
                  if (set[w][z][l] == num)
                    set[w][z][l] = 0;
                }
              }
          }

          for (int e = 1; e < 9; e++){
            set[i][j][e] = 0;
          }

          set[i][j][0] = num;
        }
      }
    }
    return set;
  }

  //updates the array of possible values
  //if there's only one possible value for a space, inserts value into space in board
  //then makes a new array of possible values given a more complete board
  public int[][][] solveArr(){
    int counter = 0;
    int index = 0;
    boolean isSolved = false;

    for (int i = 0; i < 9; i++){
        for (int j = 0; j < 9; j++){
          for (int g = 0; g < 9; g++){
            if (set[i][j][g] != 0){
              counter++;
              index = g;
            }
          }
          if (counter == 1){
            board[i][j] = set[i][j][index];
            set = makePossArr();
          }
          counter = 0;
        }
    }

    while (isSolved == false){
      isSolved = true;
      for (int a = 0; a < 9; a++){
        for (int b = 0; b < 9; b++){
          if (board[a][b] == 0){
            isSolved = false;
            set = solveArr();
          }
        }
      }
    }
    return set;
  }

  public int[][] getBoard(){
    return board;
  }

  public static void printBoard(int[][] test)
  {
    System.out.println("----------|-----------|----------");
    for (int j = 0; j < 9; j++){
      for (int i = 0; i < 8; i++){
        System.out.print(test[i][j] + " | ");
      }
      System.out.println(test[8][j]);
      if (j % 3 == 2)
      {
        System.out.println("----------|-----------|----------");
      }
    }
  }
}
