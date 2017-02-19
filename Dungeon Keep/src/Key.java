
public class Key extends Object {

	private boolean pickedUp;

	public Key() {
		super();
		pickedUp=false;
		defineSymbol();
	}
	
	public Key(int x, int y)
	{
		this.x=x;
		this.y=y;
		pickedUp = false;
		defineSymbol();
	}
	
	public void defineSymbol()
	{
		this.symbol='k';
	}

	public boolean getPickedUp() {
		return pickedUp;
	}

	public void pickedUp() {
		this.symbol=' ';
		pickedUp = true;
	}
}
