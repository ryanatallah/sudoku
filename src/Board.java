import java.awt.*;
import javax.swing.*;

public class Board extends JPanel{
  int width = getWidth();
  int height = getHeight();
  //int[][] input = new int[9][9];
  int xPos, yPos;
  int length = width;
  int boxSize = width/10;
  static JButton[] buttons = new JButton[9];
  
  public Board(){
	setBackground(Color.WHITE);
	for (Integer a=1; a<=9; a++){
	  String temp = "" + a;
	  buttons[a-1] = new JButton(temp);
	}
  }
  public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.setColor(Color.BLACK);
	for (int i=0; i<=9; i++){
	  g.drawLine(10, i*boxSize+30, 280, i*boxSize+30);
	}
	for (int j=0; j<=9; j++){
	  g.drawLine(j*30+10, 30, j*30+10, 300);
	}
  }
  public static void main(String[] args){
	JFrame w = new JFrame("Sudoku");
	w.setBounds(300, 300, 300, 380);
	w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Container c = w.getContentPane();
	c.add(new Board());
	JPanel j = new JPanel();
	for (int b=0; b<9; b++){
	  j.add(buttons[b]);
	}
	w.setResizable(false);
	w.setVisible(true);
  }
}
