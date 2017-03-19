package dkeep.logic;

public abstract class Guard extends Character {
	boolean awake = true;
	protected int i;
	protected int[] xFixed;
	protected int[] yFixed;
	boolean rightWay;
	/**
	 * @brief Constructor for class Guard.
	 */	
	public Guard() {
		super();
		defineSymbol();
	}
	/**
	 * @brief Constructor for class Guard with coordinates.
	 */	
	public Guard(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * @brief Get for attribute awake, which represents if Guard is awake.
	 * @return awake.
	 */
	public boolean getAwake()
	{
		return this.awake;
	}

	///////////////////////////////////////////////////////////////////////////////
	/**
	 * @brief Defines attribute symbol as G.
	 */	
	public void defineSymbol() {
		this.symbol = 'G';
	}
	/**
	 * @brief Changes Guard from awake to asleep and changes symbol to g.
	 */
	public void asleep() {
		this.awake=false;
		this.symbol='g';
	}	
	/**
	 * @brief Changes Guard from asleep to awake and changes symbol to G.
	 */
	public void awake() {
		this.awake=true;
		this.symbol='G';
	}
	/**
	 * @brief Determines Guard's behavior according to his personality.
	 */
	public abstract void behavior();
}
