package dkeep.logic;

public class Key extends Object {

	private boolean pickedUp;
	/**
	 * @brief Constructor for class Key.
	 */	
	public Key() {
		super();
		pickedUp = false;
		this.symbol = 'k';
	}
	/**
	 * @brief Constructor for class Hero with coordinates.
	 */	
	public Key(int x, int y) {
		this.x = x;
		this.y = y;
		pickedUp = false;
		this.symbol = 'k';
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * @brief Get for attribute pickedUp, which represents if Key has been picked up.
	 * @return pickedUp.
	 */
	public boolean getPickedUp() {
		return pickedUp;
	}

	///////////////////////////////////////////////////////////////////////////////
	/**
	 * @brief Changes Key to being pickedUp and symbol to empty, as it should no longer appear on the map.
	 */
	public void pickedUp() {
		pickedUp = true;
		this.symbol = ' ';
	}
}
