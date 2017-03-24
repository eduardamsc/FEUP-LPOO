package dkeep.logic;

public abstract class Element {
	protected int x; // lines
	protected int y; // columns
	protected char symbol; // represents character on map
	
	/**
	 * @brief Constructor for class Character.
	 */
	public Element() {
		x = 0;
		y = 0;
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * @brief Get for attribute X, which represents the lines.
	 * @return x.
	 */
	public int getX() {
		return x;
	}
	/**
	 * @brief Get for attribute Y, which represents the columns.
	 * @return y.
	 */
	public int getY() {
		return y;
	}
	/**
	 * @brief Set for attribute X.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @brief Set for attribute Y.
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @brief Get for attribute symbol, which distinguishes each Character in the map.
	 * @return symbol.
	 */
	public char getSymbol() {
		return symbol;
	}
	/**
	 * @brief Set for attribute symbol.
	 */
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
