import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener{
  int[][] input;
  JButton[][] buttons = new JButton[9][9];
  
  public Board(int[][] in){
	input = in;
	setBackground(Color.WHITE);
  }

  public void paintComponent(Graphics g){
	int width = getWidth();
	int boxSize = width/10 + 1; 
	
	  
	super.paintComponent(g);
	g.setColor(Color.BLACK);
	g.drawString("Sudoku Board", 10, 10);
	
	for (int i=0; i<=9; i++){
	  g.drawLine(10, i*boxSize+30, width-30, i*boxSize+30);
	}
	g.drawLine(9, 30, 9, 300);
	for (int j=0; j<=9; j++){
	  g.drawLine(j*boxSize+10, 30, j*boxSize+10, width-10);
	}
  }


  public void actionPerformed(ActionEvent e){
	System.out.println("Button clicked");
  }
  
  public void makeButtons(){
	  Container c = new Container();
	  c.setLayout(new GridLayout(9, 9));
	  c.setVisible(true);
	  for (int a=0; a<9; a++){
		  for (int b=0; b<9; b++){
			String str = "" + input[a][b];
			buttons[a][b] = new JButton(str);
			buttons[a][b].setActionCommand("disable");
			buttons[a][b].setToolTipText("1 2 3 4 5 6 7 8 9");
			buttons[a][b].addActionListener(this);
	        c.add(buttons[a][b]);
		  }
		}
  }
  
  public static void main(String[] args){
	JFrame w = new JFrame("Sudoku");
	w.setBounds(300, 300, 600, 600);
	w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	int[][] test = new CompleteBoard().makeBoard();
	Board hope = new Board(test);
	hope.makeButtons();
	w.add(hope);
	w.setResizable(false);
	w.setVisible(true);
  }
}
