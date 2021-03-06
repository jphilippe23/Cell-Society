package back_end;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.Consumer;
import Grids.Grid;
import Grids.HexagonalGrids.*;
import Grids.RectangleGrids.*;
import Grids.TriangularGrid.*;
import utilities.GridLocation;

public abstract class Simulation
{
	private Grid myCellGrid;
	private Grid myGroundGrid;
	
	/**
	 * update the grid based on the cells' current state
	 * @return the updated myGrid
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public abstract Grid updateGrid();

	public Grid createGrid(Cell cellType)
	{
		Constructor<? extends Grid> constructor = null;
		try {
			constructor = myCellGrid.getClass().getConstructor(int.class, int.class ,Cell.class);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new Error("No such constructor");
		}
		try {
			return constructor.newInstance(getNumRows(), getNumCols(), cellType);
		} catch (Exception e) {
			throw new Error("No such constructor");
		}
	}

	public Grid createCellGrid(Cell[][] cellArray)
	{
		Constructor<? extends Grid> constructor = null;
		try {
			constructor = myCellGrid.getClass().getConstructor(Cell[][].class);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new Error("No such constructor");
		}
		try {
			return constructor.newInstance(cellArray);
		} catch (Exception e) {
			throw new Error("No such constructor");
		}
	}
	
	@Deprecated
	public Grid createGroundGrid(Cell[][] cellArray, Cell cellType)
	{
		Constructor<? extends Grid> constructor = null;
		try {
			constructor = myGroundGrid.getClass().getConstructor(Cell[][].class ,Cell.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			return constructor.newInstance(cellArray, cellType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param oldArray
	 * @return copiedArray a copy of old array, with each cell in copiedArray a new copy of the corresponding cell
	 * @throws Exception 
	 */
	protected Cell[][] deepCopyCellArray(Cell[][] oldArray){
		int numRows = oldArray.length;
		int numCols = oldArray[0].length;
		Cell[][] copiedArray = new Cell[numRows][numCols];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				Constructor<? extends Cell> constructor = null;
				try {
					constructor = oldArray[row][col].getClass().getConstructor(oldArray[row][col].getClass());
				} catch (NoSuchMethodException | SecurityException e) {
					throw new Error("bad constructor");
				}
				try {
					Cell oldLocation = oldArray[row][col];
					copiedArray[row][col] = constructor.newInstance(oldLocation);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					throw new Error("bad constructor");
				}
			}
		}
		return copiedArray;
	}
	
	/**
	 * generates a GridLocation such that newGrid[GridLocation] is empty for putting a new cell
	 * AND (row, column) is not its current position
	 * simulates a cell moving to somewhere else
	 * @return GridLocation
	 */
	protected abstract GridLocation findEmptySpots(Grid grid, int currentRow, int currentCol);
	
	/**
	 * getter method
	 * @return Grid containing cell info
	 */
	public Grid getCellGrid(){
		return myCellGrid;
	}
	public Grid getGroundGrid(){
		return myGroundGrid;
	}
	
	
	/**
	 * setter method for myGrid and myGroundGrid
	 */
	protected void setGrid(Grid grid){
		myCellGrid=grid;
	}

	protected void setGroundGrid(Grid groundGrid){
		myGroundGrid = groundGrid;
	}
	

    /**
	 * creates a new grid with the defined configuration (whose origin lies in simulation builder) in terms of bounds, shapes  and celltype
	 * @param gridBoundsType
	 * @param shapeType
	 * @param cellArray
	 */
	protected void makeGrid(String gridBoundsType, String shapeType, Cell[][] cellArray)
	{
		if (gridBoundsType.equals("Toroidal") && shapeType.equals("Rectangular"))
			setGrid(new RectangleToroidalGrid(cellArray));
		else if (gridBoundsType.equals("Finite") && shapeType.equals("Rectangular"))
			setGrid(new RectangleFiniteGrid(cellArray));
		else if (gridBoundsType.equals("Infinite") && shapeType.equals("Rectangular"))
			setGrid(new RectangleInfiniteGrid(cellArray));
		
		else if (gridBoundsType.equals("Toroidal") && shapeType.equals("Triangular"))
			setGrid(new TriangularToroidalGrid(cellArray));
		else if (gridBoundsType.equals("Finite") && shapeType.equals("Triangular"))
			setGrid(new TriangularFiniteGrid(cellArray));
		else if (gridBoundsType.equals("Toroidal") && shapeType.equals("Hexagonal"))
			setGrid(new HexToroidalGrid(cellArray));
		else if (gridBoundsType.equals("Finite") && shapeType.equals("Hexagonal"))
			setGrid(new HexFiniteGrid(cellArray));
		else
			throw new Error("Incorrect Grid Type");
	}
	

	/**
	 * setter method. Sets sim's myInfo to newInfo
	 * @param newInfo
	 */
	public abstract void setSimInfo(SimulationInfo newInfo);
	
	/**
	 * setter method. Sets sim's myInfo to newInfo
	 * @param newInfo
	 */
	public abstract SimulationInfo getSimInfo();

	
	/**
	 * 
	 * @return number of rows in myGrid
	 * used for ground and regular grid
	 */
	public int getNumRows(){
		return myCellGrid.getNumRows();
	}
	
	public int getNumRowsGround(){
		return myGroundGrid.getNumRows();
	}
	
	/**
	 * @return number of columns in myGrid
	 */
	public int getNumCols(){
		return myCellGrid.getNumCols();
	}
	public int getNumColsGround(){
		return myGroundGrid.getNumCols();
	}
	public void setLines(boolean linesOn)
	{
		myCellGrid.setLines(linesOn);
	}

	public abstract ArrayList<String> getParameterList();
	public abstract Consumer<Number> getChangeMethod(String x);
	public abstract double getSliderLowerBound(String x);
	public abstract double getSliderUpperBound(String x);
	public abstract double getCurrentValue(String x);
}