/**
 * Random pointless interface
 */
public interface Sudoku {
  public void resetPossValues();
  public int[][][] makePossArr();
  public void solvePuzzle();
  public int updateBoard();
}
