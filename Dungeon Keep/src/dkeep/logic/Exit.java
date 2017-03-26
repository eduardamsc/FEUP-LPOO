package dkeep.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Exit.
 */
public class Exit extends Object {

	/** The open. */
	private boolean open;

	/**
	 * Constructor for class Exit.
	 */	
	public Exit() {
		super();
		open = false;
		this.symbol = 'I';
	}
	
	/**
	 * Constructor for class Exit with coordinates.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */	
	public Exit(int x, int y) {
		this.x = x;
		this.y = y;
		open = false;
		this.symbol = 'I';
	}
	/**
	 * Get for attribute open, which represents the state of the Exit.
	 * @return open.
	 */
	public boolean getOpen() {
		return open;
	}
	/**
	 * Changes Exit status to opened and changes symbol to S.
	 */
	public void open() {
		open = true;
		this.symbol = 'S';
	}
}
