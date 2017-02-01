package back_end;
import java.util.ArrayList;
public abstract class Simulation{
	
	private Cell[][] myGrid;
	private SimulationInfo myInfo;
	
	/**
	 * update the grid based on the cells' current state
	 * @return the updated myGrid
	 */
	//changed to void
	public abstract Cell[][] updateGrid();
	
	
	/**
	 * 
	 * @param row the row of the current cell
	 * @param col the col of the current cell
	 * @return the Cell neighbors of the current cell
	 */
	//changed from Cell[] to AL<Cell>
	protected abstract ArrayList<Cell> getNeighbors(int row, int col);
	
	/**
	 * generates a [row, column] pair such that newGrid[row][column] is empty for putting a new cell
	 * @return int[]. 0 position is row,1 position is column
	 */
	protected abstract int[] move(Cell[][] newGrid);
	
	/**
	 * getter method
	 * @return myGrid
	 */
	public Cell[][] getGrid(){
		return myGrid;
	}
	
	/**
	 * setter method for myGrid
	 */
	public void setGrid(Cell[][] newGrid){
		myGrid=newGrid;
	}
	
	/**
	 * getter method
	 * @return myInfo
	 */
	public SimulationInfo getSimInfo(){
		return myInfo;
	}
	
	/**
	 * setter method. Sets sim's myInfo to newInfo
	 * @param newInfo
	 */
	public abstract void setSimInfo(SimulationInfo newInfo);
	/**
	 * checks whether the position specified by (row, col) is valid i.e. won't cause OutOfBoundsException.
	 * Assumes each row of myGrid has the same number of columns
	 * @param row
	 * @param col
	 * @return true if the position is valid
	 */
	
	//added this util method. helpful for all subclass simulations as they need to check for boundaries
	protected boolean isValidPosition(int row, int col){
		return row < myGrid.length && row>=0 
				&& col<myGrid[0].length &&     col>=0;
	}
}
