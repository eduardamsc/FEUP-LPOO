package dkeep.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Element.
 */
public abstract class Element {
	
	/** The x. */
	protected int x; // lines
	
	/** The y. */
	protected int y; // columns
	
	/** The symbol. */
	protected char symbol; // represents character on map
	
	/**
	 * Constructor for class Character.
	 */
	public Element() {
		x = 0;
		y = 0;
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * Get for attribute X, which represents the lines.
	 * @return x.
	 */
	public int getX() {
		return x;
	}
	/**
	 * Get for attribute Y, which represents the columns.
	 * @return y.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set for attribute X.
	 *
	 * @param x
	 *            the new x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Set for attribute Y.
	 *
	 * @param y
	 *            the new y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Get for attribute symbol, which distinguishes each Character in the map.
	 * @return symbol.
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * Set for attribute symbol.
	 *
	 * @param symbol
	 *            the new symbol
	 */
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
