package dkeep.logic;
public class Lever extends Object {

	private boolean open;
	/**
	 * @brief Constructor for class Lever.
	 */	
	public Lever() {
		super();
		open = false;
		defineSymbol();
	}
	/**
	 * @brief Constructor for class Lever with coordinates.
	 */	
	public Lever(int x, int y) {
		this.x = x;
		this.y = y;
		open = false;
		defineSymbol();
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * @brief Get for attribute open, which represents if Lever has been raised.
	 * @return open.
	 */
	public boolean getOpen() {
		return open;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	/**
	 * @brief Defines attribute symbol as k.
	 */	
	public void defineSymbol() {
		this.symbol = 'k';
	}
	/**
	 * Changes Lever's status to raised.
	 */
	public void open() {
		open = true;
	}
}
