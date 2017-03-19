package dkeep.logic;
public class Lever extends Object {

	private boolean open;

	public Lever() {
		super();
		open = false;
		defineSymbol();
	}

	public Lever(int x, int y) {
		this.x = x;
		this.y = y;
		open = false;
		defineSymbol();
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
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
	
	public void open() {
		open = true;
	}
}
