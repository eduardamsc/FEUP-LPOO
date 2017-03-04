package dkeep.logic;

public class Key extends Object {

	private boolean pickedUp;

	public Key() {
		super();
		pickedUp = false;
		defineSymbol();
	}

	public Key(int x, int y) {
		this.x = x;
		this.y = y;
		pickedUp = false;
		defineSymbol();
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	public boolean getPickedUp() {
		return pickedUp;
	}

	///////////////////////////////////////////////////////////////////////////////
	public void defineSymbol() {
		this.symbol = 'k';
	}

	public void pickedUp() {
		pickedUp = true;
		this.symbol = ' ';
	}
}
