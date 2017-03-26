package dkeep.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Ogre.
 */
public class Ogre extends Character {

	/** The stunned. */
	boolean stunned=false;
	
	/** The armed. */
	boolean armed=true;
	/**
	 * Get for attribute armed.
	 * @return armed.
	 */
	public boolean getArmed() {
		return armed;
	}
	
	/**
	 * Set for attribute armed.
	 *
	 * @param armed
	 *            the new armed
	 */
	public void setArmed(boolean armed) {
		this.armed = armed;
	}
	/**
	 * Constructor for class Ogre.
	 */
	public Ogre() {
		super();
	}
	
	/**
	 * Constructor for class Ogre, with coordinates.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public Ogre(int x, int y) {
		this.x = x;
		this.y = y;
		this.symbol = 'O';
	}
	/**
	 * Changes Ogre's symbol to $ as he is near the Key.
	 */
	public void closeToKey() {
		this.symbol = '$';
	}
	/**
	 * Get for attribute stunned.
	 * @return stunned.
	 */
	public boolean getStunned()
	{
		return this.stunned;
	}
	/**
	 * Changes Ogre's status to being stunned and symbol to 8.
	 */
	public void stun()
	{
		this.stunned=true;
		this.symbol = '8';
	}
	/**
	 * Changes Ogre's status to not being stunned and symbol to O.s
	 */
	public void notStunned()
	{
		this.stunned=false;
		this.symbol = 'O';
	}
}
