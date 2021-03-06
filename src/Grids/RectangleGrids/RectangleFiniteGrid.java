package Grids.RectangleGrids;

import java.util.ArrayList;
import java.util.Collection;
import back_end.Cell;
import utilities.GridLocation;

public class RectangleFiniteGrid extends RectangularGrid {
		
	public RectangleFiniteGrid(Cell[][] cellGrid) {
		super(cellGrid);
	}
	
	/**
	 * 
	 * @param numRows
	 * @param numCols
	 * @param instanceCell
	 */
	public RectangleFiniteGrid(int numRows, int numCols) {
		super(numRows, numCols);
	}

	/**
	 * 
	 */
	public Collection<Cell> getNeighbors(GridLocation location, int flag) {
		int[] rowOffset=super.getRowOffsetArray(flag), colOffset=super.getColOffsetArray(flag);
		return getNeighbors(location, rowOffset, colOffset);
	}
	
	@Override
	public Collection<Cell> getNeighbors(GridLocation location, int[] rowOffset, int[] colOffset) {
		Collection<Cell> output = new ArrayList<Cell>();
		int row = location.getRow(), col = location.getCol();
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.isValidAbstractedPosition(resultant_row, resultant_col)) {
				output.add(super.getCellAt(new GridLocation(resultant_row, resultant_col)));
			}
		}
		return output;
	}

	
	/**
	 * get the neighbors of A CERTAIN TYPE from the original grid. top, down,
	 * left, right
	 */
	public Collection<GridLocation> getNeighborLocationByType(GridLocation location, int neighborType, int flag) {
		int row = location.getRow(), col = location.getCol();
		ArrayList<GridLocation> output = new ArrayList<GridLocation>();
		int[] rowOffset=super.getRowOffsetArray(flag), colOffset=super.getColOffsetArray(flag);
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.isValidAbstractedPosition(resultant_row, resultant_col)
					&& super.getCellAt(new GridLocation(resultant_row, resultant_col)).getMyType() == neighborType){
				output.add(new GridLocation(resultant_row, resultant_col));
			}
		}
		return output;
	}

	
}
