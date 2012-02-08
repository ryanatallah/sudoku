
public class GameRunner {
    public static void main(String[] args){
        int[][] test={
            {0,0,8,0,0,9,0,0,3},
            {0,6,0,3,2,8,0,5,0},
            {9,3,0,0,0,4,0,6,1},
            {0,0,1,0,0,5,0,0,8},
            {5,2,0,0,0,0,0,7,4},
            {3,0,0,9,0,0,2,0,0},
            {7,4,0,1,0,0,0,8,6},
            {0,9,0,5,7,3,0,4,0},
            {2,0,0,4,0,0,5,0,0}
        };

        System.out.println("INITIAL BOARD");
        SudokuGame2.printBoard(test);
        System.out.println("\n\n");

        SudokuGame2 trial1=new SudokuGame2(test);
        trial1.makePossArr();
        trial1.solveArr();
        int[][] solvedPuzzle=trial1.getBoard();
        System.out.println("SOLVED BOARD");
        SudokuGame2.printBoard(solvedPuzzle);
    }
}
