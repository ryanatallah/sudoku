
public class SudokuGame {

    PossibleValues[][] set=new PossibleValues[9][9];
    int[][] board=new int[9][9];
    public SudokuGame(int[][] in){
        board=in;
        PossibleValues insert=new PossibleValues();
        for (int q=0; q<9; q++){
            for (int r=0; r<9; r++){
            	   set[q][r]=insert;
            }
        }
    }
    //takes an board that's already made and generates a list of possible numbers for each space
    //does not update the board
    public PossibleValues[][] makePossArr(){
        // doesn't work without it.
        // It makes it so that there's actually a possible value object in every space of set.
        // No null pointer exceptions.
    	
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                int num=board[i][j];
                if (num>0){
                    for (int x=0; x<9; x++){
                        set[x][j].removeNum(num);
                    }
                    for (int y=0; y<9; y++){
                        set[i][y].removeNum(num);
                    }
                    int[] squarePos=new int[2];
                    if (i<3)
                        squarePos[0]=0;
                    else if (i<6)
                        squarePos[0]=3;
                    else
                        squarePos[0]=6;
                    if (j<3)
                        squarePos[1]=0;
                    else if (j<6)
                        squarePos[1]=3;          
                    else
                        squarePos[1]=6;
                    for (int w=squarePos[0]; w<squarePos[0]+3; w++){
                        for (int z=squarePos[1]; z<squarePos[1]+3; z++)
                            {set[w][z].removeNum(num);}
                    }

                    set[i][j].setNum(num);
                    num=0;
                }
            }
        }
        
        return set;
    }
    
    //updates the array of possible values
    //if there's only one possible value for a space, inserts value into space in board
    //then makes a new array of possible values given a more complete board
    public PossibleValues[][] updateArr(){
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (set[i][j].getLength()==1){
                    int newNum=set[i][j].getNum(0);
                    //set[i][j].printSet();
                    board[i][j]=newNum;
                }
                //if (set[i][j].getLength() == 2)
                //{
                	//int newNum=set[i][j].getNum(0) + set[i][j].getNum(1);
                    //board[i][j]=newNum;
                //}
            }
        }
        printBoard(board);
        System.out.println("Array updated.");
        set=makePossArr();
        return set;
    }
    //run updateArr() before this
    //if there are 2 possible values for a space, inserts the smaller one into the board
    //then updates the array, so that if any of the spaces have 1 possible value it gets updated
    //keeps updating the arrays until there aren't any spaces left with only one possible value
    //hopefully by the time it runs this far any problems will have appeared
    public PossibleValues[][] solveArr(){
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                PossibleValues possNums=set[i][j];
                if (possNums.getLength()==2){
                    int[][] tempBoard=board;
                    PossibleValues[][] tempSet=set;
                    board[i][j]=possNums.getNum(0);
                    boolean twoPoss=false;
                    while (twoPoss==false){
                        twoPoss=true;
                        for (int c=0; c<9; c++){
                            for (int d=0; d<9; d++){
                                if (set[c][d].getLength()==1){
                                    twoPoss=false;
                                }
                            }
                        }
                        set=updateArr();
                    }
                    boolean isEmpty=false;
                    while (isEmpty==false){
                        for (int x=0; x<9; x++){
                            for (int y=0; y<9; y++){
                                if (set[x][y].getLength()==0){
                                    board=tempBoard;
                                    set=tempSet;
                                    board[i][j]=possNums.getNum(0);
                                    set=updateArr();
                                    isEmpty=true;
                                }
                            }
                        }
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
        for (int j=0; j<9; j++){
            for (int i=0; i<8; i++){
                System.out.print(test[i][j]+" | ");
            }
            System.out.println(test[8][j]);
            if (j % 3 == 2)
            {
                System.out.println("----------|-----------|----------");
            }
        }
    }
}
