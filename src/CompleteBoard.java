import java.util.Random;
public class CompleteBoard extends SudokuGame {
  static int[][] temp=new int[9][9];
  Random generator=new Random();
  static boolean print=true;
  
  public CompleteBoard(){
	  super(temp, print);
  }
  
  public int[][] makeBoard(){
    for (int i=0; i<9; i++){
      for (int j=0; j<9; j++){
    	  //int[][] tempBoard=board;
          //int[][][] tempSet=set;
        	insertNum(i, j);
        	makePossArr();
        	//if (!isSolvable()){
    	      //set=tempSet;
    	      //board=tempBoard;
    	      //set[i][j][insert]=0;
    	    //}
      }
    }
    return board;
  }
  
  public void insertNum(int i, int j){
	int num=generator.nextInt(9);
	int counter=0;
	if (set[i][j][num] != 0){
  	  board[i][j]=set[i][j][num];
  	}
	else if (counter<9){
	  counter++;
	  insertNum(i, j);
	}
	else
	  board[i][j]=0;
  }
  
  public boolean isSolvable(){
	int counter=0;
	for (int a=0; a<9; a++){
	  for (int b=0; b<9; b++){
		for (int c=0; c<9; c++){
	      if (set[a][b][c] != 0){
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
}
