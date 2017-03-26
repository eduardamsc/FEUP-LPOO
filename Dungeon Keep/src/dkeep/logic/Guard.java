package dkeep.logic;

public abstract class Guard extends Character {
	boolean awake = true;
	protected int i;
	protected int[] xFixed;
	protected int[] yFixed;
	boolean rightWay;
	/**
	 * Constructor for class Guard.
	 */	
	public Guard() {
		super();
		defineSymbol();
	}
	/**
	 * Constructor for class Guard with coordinates.
	 */	
	public Guard(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * Get for attribute awake, which represents if Guard is awake.
	 * @return awake.
	 */
	public boolean getAwake()
	{
		return this.awake;
	}

	///////////////////////////////////////////////////////////////////////////////
	/**
	 * Defines attribute symbol as G.
	 */	
	public void defineSymbol() {
		this.symbol = 'G';
	}
	/**
	 * Changes Guard from awake to asleep and changes symbol to g.
	 */
	public void asleep() {
		this.awake=false;
		this.symbol='g';
	}	
	/**
	 * Changes Guard from asleep to awake and changes symbol to G.
	 */
	public void awake() {
		this.awake=true;
		this.symbol='G';
	}
	/**
	 * Determines Guard's behavior according to his personality.
	 */
	public abstract void behavior();
	
	/**
	 * Auxiliar to behaviour.
	 */
	public void identifier(int x, int y)
	{
		this.x=x;
		this.y=y;
		defineSymbol();
		xFixed = new int[]{1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2,1};
		yFixed = new int[]{7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8,8};
		this.i=-1;
		this.rightWay=true;
	}
}
