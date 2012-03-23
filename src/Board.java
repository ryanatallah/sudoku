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
  boolean welcomeScreen = true;
  static JFrame frame = new JFrame("Sudoku");
  JPanel p;
	
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
	c.addMouseListener(this);
	
	if (!welcomeScreen){
	nums = new JButton[9];
	for (int a = 1; a<10; a++){
	  nums[a-1] = new JButton("" + a);
	  nums[a-1].setBounds((a)*54+26, 500, 54, 60);
	  nums[a-1].setActionCommand("" + a);
	  nums[a-1].addActionListener(this);
	  c.add(nums[a-1]);
	}
	JButton delete = new JButton("X");
	delete.setBounds(26, 500, 54, 60);
	delete.setActionCommand("X");
	delete.addActionListener(this);
	c.add(delete);
	
	JCheckBox help = new JCheckBox();
	help.addActionListener(this);
	help.setActionCommand("help");
	help.setBounds(10, 10, 90, 30);
	help.setOpaque(false);
	help.setText("Help");
	help.setForeground(Color.WHITE);
	add(help);
	}
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
	
	if (welcomeScreen){
	  g.setColor(Color.WHITE);
	  g.setFont(new Font("Welcome", Font.BOLD + Font.ITALIC, 40));
	  Image dragon = (new ImageIcon("fire-dragon.jpg")).getImage();
	  g.drawImage(dragon, 0, 0, null);
	  g.drawString("WELCOME", 200, 40);
	  g.drawString("TO THE JUNGLE", 140, 90);
	  g.fillRoundRect(240, 500, 120, 50, 10, 10);
	  g.setColor(Color.BLACK);
	  g.setFont(new Font("Start", Font.BOLD, 20));
	  g.drawString("Start Game", 247, 530);
	}

	
	else{
	super.paintComponent(g);
	/**
	p = new JPanel();
	p.setVisible(true);
	frame.add(p);
	frame.setVisible(true);
	g = p.getGraphics();
	*/
	
	Image flowers = (new ImageIcon("fire-flowers.gif")).getImage();
	g.drawImage(flowers, 0, 0, null);
	g.setColor(Color.WHITE);
	g.setFont(new Font("Title", Font.BOLD, 30));
	g.drawString("Sudoku Board", 200, 30);
	
	for (int i=0; i<=9; i++){
	  g.drawLine(padx, i*boxSize + pady, padx + boxSize*9, i*boxSize + pady);
	  if (i%3 == 0){  
		g.drawLine(padx, i*boxSize + pady + 1, padx + boxSize*9, i*boxSize + pady + 1);
		g.drawLine(padx, i*boxSize + pady - 1, padx + boxSize*9, i*boxSize + pady - 1);
	  }
	}

	for (int j=0; j<=9; j++){
	  g.drawLine(j*boxSize + padx, pady, j*boxSize + padx, pady + boxSize*9);
	  if (j%3 == 0){
		g.drawLine(j*boxSize + padx + 1, pady, j*boxSize + padx + 1, pady + boxSize*9);
		g.drawLine(j*boxSize + padx - 1, pady, j*boxSize + padx - 1, pady + boxSize*9);
	  }
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
		    g.setColor(new Color(130, 215, 251));
		    g.drawString("" + board[i][j], xPos, yPos);
		  }
		  else{
		    g.setColor(Color.WHITE);
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
			  g.setColor(Color.WHITE);
			  g.drawString("" + board[i][j], xPos, yPos);
			}
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
    if (str != "" && i>=0 && i<9 && j>=0 && j<9 && unsolved[i][j] == 0){
      board[i][j] = Integer.parseInt(str);
      repaint(0, 75, 40, 450, 450);
    }
    if (welcomeScreen && x>240 && x<360 && y>500 && y<550){
      welcomeScreen=false;
      repaint();
      Container c = frame.getLayeredPane();
      addComponentsToPane(c);
    }
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
	if (e.getActionCommand() == "help"){
	  if (! mark)
		mark = true;
	  else
		mark = false;
	  repaint(0, 25, 40, 450, 450);
	}
	else if (e.getActionCommand() == "X"){
	  text.setText("0");
	  //repaint(0, 25, 40, 450, 450);
	}
	else{
	  String str = e.getActionCommand();
	  text.setText(str);
	  //repaint(0, 25, 40, 450, 450);
	}
  }
  
  public static void main(String[] args){
	//Create and set up the window.
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(600, 600);
      frame.setResizable(false);
      frame.setBackground(Color.WHITE);
      //frame.setVisible(true);
      
      Container c = frame.getLayeredPane();
      
      Board board = new Board();

      frame.add(board);
      board.addComponentsToPane(c);
      
      frame.setVisible(true);
  }
}


