import java.awt.*;
import javax.swing.*;


class TestBoard extends JPanel {
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.drawLine(9, 29, 280, 29);
    for (int i=0; i<=9; i++){
      g.drawLine(10, i*30+30, 280, i*30+30);
    }
    g.drawLine(9, 30, 9, 300);
    for (int j=0; j<=9; j++){
      g.drawLine(j*30+10, 30, j*30+10, 300);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Sudoku");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(300, 300, 300, 380);
    frame.getContentPane();
    frame.pack();
    frame.setVisible(true);
  }
}