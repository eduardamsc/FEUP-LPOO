package dkeep.logic;

public class Ogre extends Character {

	boolean stunned=false;
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
