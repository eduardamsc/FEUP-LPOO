package dkeep.logic;
// TODO: Auto-generated Javadoc

/**
 * The Class Lever.
 */
public class Lever extends Object {

	/** The open. */
	private boolean open;
	
	/**
	 * Constructor for class Lever with coordinates.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */	
	public Lever(int x, int y) {
		this.x = x;
		this.y = y;
		open = false;
		this.symbol = 'k';
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * Get for attribute open, which represents if Lever has been raised.
	 * @return open.
	 */
	public boolean getOpen() {
		return open;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	/**
	 * Changes Lever's status to raised.
	 */
	public void open() {
		open = true;
	}
}
