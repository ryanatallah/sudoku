
public class BoardMaker {
  public static void main(String[] args){
	CompleteBoard hope=new CompleteBoard();
	int[][] fire=hope.makeBoard();
	hope.printBoard(fire, "AWESOME", 0);
  }
}