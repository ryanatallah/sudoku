import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements ActionListener, MouseListener {
  JButton[] nums;
  TextField text = new TextField(20);
  int padx, pady, boxSize;
  IncompleteBoard flame = new IncompleteBoard(4);
  int[][] board = flame.makePuzzle();
  int[][] unsolved = new int[9][9];
  int[][] solved = flame.getSolved();
  boolean mark = false;
	
  public Board(){
    setBackground(Color.WHITE);
    for (int a=0; a<9; a++)
  	  for (int b=0; b<9; b++)
  	    unsolved[a][b] = board[a][b];
  }

  /**
   * Adds the row of buttons at the bottom with numbers 1 through 9
   * Adds the mouse listener to the container
   * Adds the help check box
   * @param c the container to which the buttons are added
   */
  public void addComponentsToPane(Container c){
	c.setLayout(null);
	c.setBackground(Color.WHITE);

	nums = new JButton[9];
	for (int a = 1; a<10; a++){
	  nums[a-1] = new JButton("" + a);
	  nums[a-1].setBounds((a-1)*60+25, 500, 60, 60);
	  nums[a-1].setActionCommand("" + a);
	  nums[a-1].addActionListener(this);
	  c.add(nums[a-1]);
	}

	c.addMouseListener(this);
	
	JCheckBox help = new JCheckBox();
	help.addActionListener(this);
	help.setActionCommand("help");
	help.setBounds(10, 10, 50, 30);
	help.setText("Help");
	c.add(help);
  }
  
  /**
   * Paints the grid for the sudoku
   * Adds the numbers into the grid
   * If the numbers are not part of the original grid, makes them blue
   * If the help check box is selected, makes correct numbers green and incorrect ones red
   */
  public void paintComponent(Graphics g){
	int width = getWidth();
	boxSize = 50;
	padx = (width - boxSize*9)/2;
	pady = 40;
	
	super.paintComponent(g);
	g.setColor(Color.BLACK);
	g.setFont(new Font("Title", Font.BOLD, 30));
	g.drawString("Sudoku Board", 200, 30);
	
	for (int i=0; i<=9; i++){
	  g.drawLine(padx, i*boxSize + pady, padx + boxSize*9, i*boxSize + pady);
	  if (i%3 == 0)  
		g.drawLine(padx, i*boxSize + pady + 1, padx + boxSize*9, i*boxSize + pady + 1);
	}

	for (int j=0; j<=9; j++){
	  g.drawLine(j*boxSize + padx, pady, j*boxSize + padx, pady + boxSize*9);
	  if (j%3 == 0)
		g.drawLine(j*boxSize + padx + 1, pady, j*boxSize + padx + 1, pady + boxSize*9);
	}
	
	g.setFont(new Font("Numbers", Font.PLAIN, 30));
	int xPos = 0;
	int yPos = 0;
	for (int i = 0; i < 9; i++){
	  for (int j = 0; j < 9; j++){
		xPos = i*boxSize + padx + boxSize/2 - 8;
		yPos = j*boxSize + pady + boxSize/2 + 10;
		if (board[i][j] != 0){
		  if (board[i][j] != unsolved[i][j]){
		    g.setColor(Color.BLUE);
		    g.drawString("" + board[i][j], xPos, yPos);
		  }
		  else{
		    g.setColor(Color.BLACK);
		    g.drawString("" + board[i][j], xPos, yPos);
		  }
		  if (mark){
			if (board[i][j] != solved[i][j]){
			  g.setColor(Color.RED);
			  g.drawString("" + board[i][j], xPos, yPos);
			}
			else if (board[i][j] != unsolved[i][j]){
			  g.setColor(Color.GREEN);
			  g.drawString("" + board[i][j], xPos, yPos);
			}
			else{
			  g.setColor(Color.BLACK);
			  g.drawString("" + board[i][j], xPos, yPos);
			}
		  }
		}
	  }
	}

  }
  
  /**
   * Defines what to do with mouse clicks
   * If the mouse click is within a space and a number is selected,
   * inserts the number into the board
   * If there is a preset number within the space, will not change anything
   */
  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    int i = (x - padx)/boxSize;
    int j = (y-pady)/boxSize;
    String str = text.getText();
    if (str != "" && i>=0 && j>=0 && unsolved[i][j] == 0){
      board[i][j] = Integer.parseInt(str);
      repaint(0, 75, 40, 450, 450);
    }
    System.out.println("X = " + i +", Y = " + j + ", text = " + str);
  }

  public void mouseEntered(MouseEvent e) {	
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  /**
   * Sets what to do with the buttons
   * If the button is a number, sets the number as selected
   * If the check box is checked or unchecked, sets boolean mark and repaints
   */
  public void actionPerformed(ActionEvent e){
	if (e.getActionCommand() != "help"){
	  String str = e.getActionCommand();
	  text.setText(str);
	}
	else{
	  if (! mark)
	    mark = true;
	  else
		mark = false;
    repaint(0, 75, 40, 450, 450);
	}
  }
  
  public static void main(String[] args){
	//Create and set up the window.
      JFrame frame = new JFrame("Sudoku");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(600, 600);
      frame.setResizable(false);
      frame.setBackground(Color.WHITE);
      
      Container c = frame.getLayeredPane();
      
      Board board = new Board();

      frame.add(board);
      board.addComponentsToPane(c);

      //Display the window.
      frame.setVisible(true);
  }
}


