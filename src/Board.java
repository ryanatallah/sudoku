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
	
  public Board(){
    setBackground(Color.WHITE);
    for (int a=0; a<9; a++)
  	  for (int b=0; b<9; b++)
  	    unsolved[a][b] = board[a][b];
  }

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
  }
  
  
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
		if (board[i][j] != 0)
		  g.drawString("" + board[i][j], xPos, yPos);
	  }
	}

  }
  
  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    int i = (x - padx)/boxSize;
    int j = (y-pady)/boxSize;
    String str = text.getText();
    if (str != "0" && unsolved[i][j] == 0){
      board[i][j] = Integer.parseInt(str);
      repaint();
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

  public void actionPerformed(ActionEvent e){
	String str = e.getActionCommand();
	text.setText(str);
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


