import java.util.*;
public class CompleteBoard extends SudokuGame {
  static int[][] temp=new int[9][9];
  Random generator=new Random();
  static boolean print=true;
  boolean solvable;
  
  public CompleteBoard(){
	  super(temp, print);
  }
  
  public int[][] makeBoard(){
    for (int i=0; i<9; i++){
      for (int j=0; j<9; j++){
    	insertNum(i,j);
      }
      printBoard(board, "TestBoard "+ i, 0);
    }
    return board;
  }
  
  public void insertNum(int i, int j){
	int[][] tempBoard=board;
	int[][][] tempSet=set;
	int num=generator.nextInt(9);
	if (set[i][j][num] != 0){
  	  board[i][j]=set[i][j][num];
	}
  	else{
  	  for (int c=0; c<9; c++){
  		if (set[i][j][c] != 0){
  		  board[i][j]=set[i][j][c];
  		}
  	  }
  	}
  	makePossArr();
  	
  	if (isSolvable(set)==false){
  	  printBoard(board, "Not fixed", 0);
  	  set=tempSet;
  	  board=tempBoard;
  	  printBoard(board, "Fixed", 0);
  	  set[i][j][num]=0;
  	}
  }
  
  public boolean isSolvable(int[][][] test){
	int counter=0;
	int index1=0;
	int index2=0;
	ArrayList<Integer> list1=new ArrayList<Integer>();
	int[][] list2=new int[9][2];
	for (int a=0; a<9; a++){
	  for (int b=0; b<9; b++){
		for (int c=0; c<9; c++){
	      if (test[a][b][c] != 0){
		    counter++;
		    if (counter==1)
		      index1=c;
		    else if (counter==2)
		      index2=c;
		  }
		}
	    if (counter==0){
	  	  return false;
	    }
	    else if (counter==1){
	      list1.add(test[a][b][index1]);
	    }
	    else if (counter==2){
	      list2[b][0]=index1;
	      list2[b][1]=index2;
	    }
	    counter=0;
	  }
	  for (int d=0; d<list1.size(); d++){
		for (int e=0; e<list1.size(); e++){
		  if (list1.get(e)==list1.get(d) && e != d){
			return false;
	      }
		}
	  }
	  for (int g=0; g<9; g++){
		for (int h=0; h<9; h++){
		  for (int l=0; l<9; l++){
			if (list2[g]==list2[h] && list2[g]==list2[l] && g != h && g != l)
			  return false;
		  }
		}
	  }
	  list1.clear();
	}
	return true;
  }
}
