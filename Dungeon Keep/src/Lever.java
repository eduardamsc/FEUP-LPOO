
public class Lever extends Object{

	private boolean open;

	public Lever() {
		super();
		open=false;
		defineSymbol();
	}
	
	public void defineSymbol()
	{
		this.symbol='k';
	}

	public boolean getOpen() {
		return open;
	}

	public void open() {
		open = true;
	}
}
