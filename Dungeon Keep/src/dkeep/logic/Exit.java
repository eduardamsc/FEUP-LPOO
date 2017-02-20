package dkeep.logic;

public class Exit extends Object {

	private boolean open;

	public Exit() {
		super();
		open = false;
		defineSymbol();
	}

	public Exit(int x, int y) {
		this.x = x;
		this.y = y;
		open = false;
		defineSymbol();
	}

	public void defineSymbol() {
		this.symbol = 'I';
	}

	public boolean getOpen() {
		return open;
	}

	public void open() {
		open = true;
		this.symbol = 'S';
	}
}
