package dkeep.logic;

public abstract class Guard extends Character {
	boolean awake = true;
	protected int i;
	protected int[] xFixed;
	protected int[] yFixed;
	boolean rightWay;

	public Guard() {
		super();
		defineSymbol();
	}

	public Guard(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	public boolean getAwake()
	{
		return this.awake;
	}

	///////////////////////////////////////////////////////////////////////////////
	public void defineSymbol() {
		this.symbol = 'G';
	}

	public void asleep() {
		this.awake=false;
		this.symbol='g';
	}	
	
	public void notAsleep() {
		this.awake=true;
		this.symbol='G';
	}
	
	public abstract void behaviour();
}
