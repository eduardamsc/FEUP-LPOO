package dkeep.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Key.
 */
public class Key extends Object {

	/** The picked up. */
	private boolean pickedUp;
	/**
	 * Constructor for class Key.
	 */	
	public Key() {
		super();
		pickedUp = false;
		this.symbol = 'k';
	}
	
	/**
	 * Constructor for class Hero with coordinates.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */	
	public Key(int x, int y) {
		this.x = x;
		this.y = y;
		pickedUp = false;
		this.symbol = 'k';
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * Get for attribute pickedUp, which represents if Key has been picked up.
	 * @return pickedUp.
	 */
	public boolean getPickedUp() {
		return pickedUp;
	}

	///////////////////////////////////////////////////////////////////////////////
	/**
	 * Changes Key to being pickedUp and symbol to empty, as it should no longer appear on the map.
	 */
	public void pickedUp() {
		pickedUp = true;
		this.symbol = ' ';
	}
}
