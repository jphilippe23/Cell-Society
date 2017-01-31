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
<<<<<<< HEAD
	private double size;
	public static final double CELL_SIZE = 20;
	
=======

>>>>>>> 596136aa1718e149db67eb9dc6bfa1ccffbf1696
	
	/**
	 * checks the information about 
	 * @param true if the cell wants to move
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
