package back_end;


import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * Abstract super class that describes the properties shared by cells of all simulations
 * @author Yuxiang He
 *
 */
public abstract class Cell {
	/**
	 * represents the type of the cell in the simulation, starts from 0
	 */
	private int myType;

	
	public Cell(int type){
		myType=type;
	}
	
	/**
	 * checks the information about neighbors change the cell's attributes.
	 * NOTE this method MODIFIES THE CELL.
	 * DO NOT call this method on the cells in myGrid
	 * @see updateGrid() in Simulation
	 * @return true if the cell wants to move
	 */
	public abstract boolean checkAndTakeAction(ArrayList<Cell> neighbors, SimulationInfo simInfo);
	
	/**
	 * getter
	 * @return
	 */
	public int getMyType() {
		return myType;
	}

	/**
	 * setter
	 * @param type
	 */
	public void setMyType(int newType) {
		myType=newType;
	}
	
	
	public abstract Color getColor();
	
	
}
