package dkeep.logic;
// TODO: Auto-generated Javadoc

/**
 * The Class Hero.
 */
public class Hero extends Character {
	
	/** The armed. */
	boolean armed =false;
	/**
	 * Constructor for class Hero.
	 */	
	public Hero() {
		super();
		this.symbol = 'H';
	}
	
	/**
	 * Constructor for class Hero with coordinates.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */	
	public Hero(int x, int y) {
		this.x = x;
		this.y = y;
		this.symbol = 'H';
	}
	
	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * Get for attribute armed, which represents if Hero has picked up a Club.
	 * @return armed.
	 */
	public boolean getArmed()
	{
		return this.armed;
	}

	///////////////////////////////////////////////////////////////////////////////
	/**
	 * Changes Hero's symbol to K, as he has caught the Key.
	 */
	public void hasKey() {
		this.symbol = 'K';
	}
	/**
	 * Changes Hero's state to armed and symbol to A.
	 */
	public void armed()
	{
		this.armed=true;
		this.symbol='A';
	}
	/**
	 * Checks if Hero can move in a certain direction.
	 * @param map Map in which Hero is moving.
	 * @param direction Direction in which Hero is trying to move.
	 * @return True for permission to move.
	 */	
	public boolean wall(Map map, char direction) {
		switch (direction)
		{
			case 'w':
				return wallAux(map,-1,0);
			case 's':
				return wallAux(map,+1,0);
			case 'a':
				return wallAux(map,0,-1);
			case 'd':
				return wallAux(map,0,+1);
		}
		return true;
	}
	/**
	 * Checks if Character can move.
	 * @param map Map in which Character is moving.
	 * @param i parameter added to coordinate x.
	 * @param j parameter added to coordinate y.
	 * @return True for permission to move.
	 */
	public boolean wallAux(Map map, int i, int j)
	{
		int a=x+i, b=y+j;
		if (map.getMap()[a][b] == 'X' || map.getMap()[a][b] == 'k' || map.getMap()[a][b] == 'I'
				|| map.getMap()[a][b] == '*' || map.getMap()[a][b] == '8'|| map.getMap()[a][b] == '$'
				|| a < 0 || b < 0 || a > map.getMap().length
				|| b > map.getMap()[a].length)
			return false;
		else return true;
	}
}
