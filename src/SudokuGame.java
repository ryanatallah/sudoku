import java.util.*;
public class SudokuGame {

  int[][][] set = new int[9][9][9];
  int[][][] branches = new int[74][9][9];
  int[][] board = new int[9][9];
  int branchCounter = 0;
  int step = 0;
  boolean print = true;

  /**
   * Defines board and creates the initial possible values array with numbers 1 - 9.
   *
   * @param in the starting game board.
   */
  public SudokuGame(int[][] in, boolean printInstr) {
    board = in;
    print = printInstr;

    resetPossValues();
  }


  public void resetPossValues() {
    for (int a = 0; a < 9; a++)
      for (int b = 0; b < 9; b++)
        for (int c = 1; c < 10; c++)
          set[a][b][c-1] = c;

    makePossArr();
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
          for (int e = 0; e < 9; e++)
            set[i][j][e] = 0;

          // Inserts num as the only possible value for the filled space.
          set[i][j][num - 1] = num;
        }
        
        int possNum=checkIfOnly(i, j);
        if (possNum>0){
          for (int e=0; e<9; e++){
        	set[i][j][e]=0;
          }
          set[i][j][possNum-1]=possNum;
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
  public void solvePuzzle() {
    int changes = updateBoard();
    step++;

    if (checkFailure(board) > 0 && branchCounter > 0)
      revertBranch();

    if (changes > 0) {
      printBoard(board, "Step " + step, changes);

      if (isSolved())
        printBoard(board, "PUZZLE SOLVED", 0);
      else
        solvePuzzle();
    } 
    
    else {
      if (createBranch()) {
        printBoard(board, "No solutions; branch " + branchCounter + " created", 0);
        set = makePossArr();
        solvePuzzle();
      } 
      else {
        if (branchCounter > 0) {
          revertBranch();
          makePossArr();
          solvePuzzle();
        } 
        else {
          if (print)
            System.out.println("\nERROR: Puzzle could not be solved.\n");
        }
      }
    }
  }


  public boolean createBranch() {

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (numPossValues(i,j) == 2) {
          branchCounter++;

          for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
              branches[branchCounter - 1][x][y] = board[x][y];
            }
          }

          if (print)
            System.out.printf("\n\nBRANCH CREATED AT [%d][%d]\n", i, j);
          splitBranches(branchCounter, i, j);
          return true;
        }
      }
    }

    return false;
  }


  public void splitBranches(int branchCounter, int i, int j) {
    boolean numPicked = false;

    for (int g = 0; g < 9; g++) {
      int val = g + 1;
      if (set[i][j][g] != 0 && set[i][j][g] != board[i][j]) {
        if (numPicked != true) {
          if (print)
            System.out.println("Possible option 1: " + val);
          board[i][j] = val;
          numPicked = true;
        } 
        else {
          if (print)
            System.out.println("Possible option 2: " + val);
          branches[branchCounter - 1][i][j] = val;
        }
      }
    }
  }


  public void revertBranch() {
    board = branches[branchCounter - 1];
    resetPossValues();
    printBoard(board, "Board reverted to branch " + branchCounter, 0);
    branchCounter--;
  }


  public int updateBoard() {
    int counter = 0;
    int index = 0;
    int changes = 0;

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        for (int g = 0; g < 9; g++) {
          if (set[i][j][g] != 0 && set[i][j][g] != board[i][j]) {
            counter++;
            index = g;
          }
        }
        if (counter == 1 && board[i][j] == 0) {
          changes++;
          board[i][j] = set[i][j][index];
          makePossArr();
        }
        counter = 0;
      }
    }
    return changes;
  }


  public boolean isSolved() {
    boolean solved = true;

    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (board[i][j] == 0)
          solved = false;

    return solved;
  }


  public int numPossValues(int i, int j) {
    int counter = 0;

    for (int g = 0; g < 9; g++)
      if (set[i][j][g] != 0 && set[i][j][g] != board[i][j])
        counter++;

    return counter;
  }


  public int[][] getBoard() {
    return board;
  }


  public void printBoard(int[][] board, String title, int changes) {
    if (print) {
      if (changes == 1)
        System.out.printf("\n\n%s: (%d %s)\n", title, changes, "change");
      else if (changes > 1)
        System.out.printf("\n\n%s: (%d %s)\n", title, changes, "changes");
      else
        System.out.printf("\n\n%s:\n", title);

      int failures = checkFailure(board);
      if (failures > 0)
        System.out.println("FAILURES: " + failures);

      System.out.println("|-----------|-----------|-----------|");
      for (int j = 0; j < 9; j++) {
        for (int i = 0; i < 9; i++) {
          if (i == 0)
            System.out.print("|");
          if (board[i][j] == 0)
            System.out.printf("   ");
          else
            System.out.printf(" %d ", board[i][j]);
          System.out.print("|");
          if (i == 8)
            System.out.println("");
        }
        if (j % 3 == 2)
          System.out.println("|-----------|-----------|-----------|");
      }
    }
  }


  public int checkFailure(int[][] board) {
    int failures = 0;

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        boolean possValues = false;
        for (int g = 0; g < 9; g++){
          if (set[i][j][g] > 0)
            possValues = true;
        }
        if (possValues != true)
          failures++;

        int num = board[i][j];

        if (num > 0) {
          for (int x = 0; x < 9; x++){
            if (num == board[x][j] && x != i)
              failures++;
          }

          for (int y = 0; y < 9; y++){
            if (num == board[i][y] && y != j)
              failures++;
          }

          int[] squarePos = new int[2];

          squarePos[0] = (i / 3) * 3;
          squarePos[1] = (j / 3) * 3;

          for (int w = squarePos[0]; w < squarePos[0] + 3; w++) {
            for (int z = squarePos[1]; z < squarePos[1] + 3; z++) {
              if (num == board[w][z] && w != i && z != j)
                failures++;
            }
          }
        }
      }
    }
    return failures;
  }
  
  /**
   * Checks to see if the square at board[x][y] is the only square that can
   * be a particular value
   * @returns the value that is unique to that square
   */
  public int checkIfOnly(int x, int y){
	int possNum=0;
	ArrayList<Integer> index=new ArrayList<Integer>();
	boolean isOnly=true;
	for (int i=0; i<9; i++){
      if (set[x][y][i] != 0){
    	index.add(set[x][y][i]);
      }
	}
      
    for (int c=index.size()-1; c>=0; c--){
      int[] squarePos = new int[2];

      squarePos[0] = (x / 3) * 3;
      squarePos[1] = (y / 3) * 3;
        
      for (int w = squarePos[0]; w < squarePos[0] + 3; w++) {
        for (int z = squarePos[1]; z < squarePos[1] + 3; z++) {
          for (int k=0; k<9; k++){
            if (set[w][z][k] == index.get(c) && w != x && z != y)
              isOnly=false;
          }
        }
      }
        
      for (int a=0; a<9; a++) {
    	for (int b=0; b<9; b++) {
          if (set[x][a][b] == index.get(c) && a != y)
            isOnly=false;
          if (set[a][y][b] == index.get(c) && a != x)
        	isOnly=false;
    	}
      }
      if (isOnly)
    	possNum=index.get(c);
    }
    
	return possNum;
  }
  
  public void checkSets(){
	ArrayList<int[]> nums = new ArrayList<int[]>();
	int counter=0;
	ArrayList<Integer> index=new ArrayList<Integer>();
	int counter2=0;
	for (int n=2; n<9; n++){
		
	  for (int i=0; i<9; i++){
		for (int j=1; j<9; j++){
			
	      int[] values=new int[n];
	      
	      for (int g=0; g<9; g++){
			    for (int c=0; c<9; c++){
			      if (set[i][g][c] != 0){
			  	    counter++;
			  	    index.add(c+1);
			  	  }
			    }
			    if (counter==n){
			      for (int a=0; a<n; a++){
			    	values[a]=index.get(a);
			      }
			      nums.add(values);
			    }
			  }
		      for (int a=0; a<nums.size()-1; a++){
		    	for (int b=a+1; b<nums.size(); b++){
		    	  if (nums.get(a)==nums.get(b)){
		    		counter2++;
		    	  }
		    	}
		    	if (counter2==n){
		    	  int[] hope=nums.get(a);
		    	  for (int x=0; x<hope.length; x++){
		    	    for (int y=0; y<9; y++){
		    	      set[i][y][hope[x]]=0;
		    	    }
		    	  }
		    	}
		    	counter2=0;
		      }
	      
	      counter=0;
	      
	      for (int g=0; g<9; g++){
		    for (int c=0; c<9; c++){
		      if (set[g][j][c] != 0){
		  	    counter++;
		  	    index.add(c+1);
		  	  }
		    }
		    if (counter==n){
		      for (int a=0; a<n; a++){
		    	values[a]=index.get(a);
		      }
		      nums.add(values);
		    }
		  }
	      for (int a=0; a<nums.size()-1; a++){
	    	for (int b=a+1; b<nums.size(); b++){
	    	  if (nums.get(a)==nums.get(b)){
	    		counter2++;
	    	  }
	    	}
	    	if (counter2==n){
	    	  int[] hope=nums.get(a);
	    	  for (int x=0; x<hope.length; x++){
	    	    for (int y=0; y<9; y++){
	    	      set[y][j][hope[x]]=0;
	    	    }
	    	  }
	    	}
	    	counter2=0;
	      }
	      
	      counter=0;
	      
	      int[] squarePos=new int[2];
	      squarePos[0] = (i / 3) * 3;
          squarePos[1] = (j / 3) * 3;

          for (int w = squarePos[0]; w < squarePos[0] + 3; w++){
            for (int z = squarePos[1]; z < squarePos[1] + 3; z++){
            	for (int c=0; c<9; c++){
      		      if (set[w][z][c] != 0){
      		  	    counter++;
      		  	    index.add(c+1);
      		  	  }
      		    }
      		    if (counter==n){
      		      for (int a=0; a<n; a++){
      		    	values[a]=index.get(a);
      		      }
      		      nums.add(values);
      		    }
      		  }
      	      for (int a=0; a<nums.size()-1; a++){
      	    	for (int b=a+1; b<nums.size(); b++){
      	    	  if (nums.get(a)==nums.get(b)){
      	    		counter2++;
      	    	  }
      	    	}
      	    	if (counter2==n){
      	    	  int[] hope=nums.get(a);
      	    	  for (int x=0; x<hope.length; x++){
      	    	    for (int y=0; y<9; y++){
      	    	      for (int z=0; z<9; z++){
      	    	        set[y][z][hope[x]]=0;
      	    	      }
      	    	    }
      	    	  }
      	    	}
      	    	counter2=0;
      	      }
          
          counter=0;
          }
	    }
	  }
	}
  }
}
	
  

