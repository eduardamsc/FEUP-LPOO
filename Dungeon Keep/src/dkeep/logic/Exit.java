package dkeep.logic;

public class Exit extends Object {

	private boolean open;

	/**
	 * @brief Constructor for class Exit.
	 */	
	public Exit() {
		super();
		open = false;
		defineSymbol();
	}
	/**
	 * @brief Constructor for class Exit with coordinates.
	 */	
	public Exit(int x, int y) {
		this.x = x;
		this.y = y;
		open = false;
		defineSymbol();
	}
	/**
	 * @brief Defines attribute symbol as I.
	 */	
	public void defineSymbol() {
		this.symbol = 'I';
	}
	/**
	 * @brief Get for attribute open, which represents the state of the Exit.
	 * @return open.
	 */
	public boolean getOpen() {
		return open;
	}
	/**
	 * @brief Changes Exit status to opened and changes symbol to S.
	 */
	public void open() {
		open = true;
		this.symbol = 'S';
	}
}
