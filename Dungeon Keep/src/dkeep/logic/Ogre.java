package dkeep.logic;

public class Ogre extends Character {

	boolean stunned=false;
	boolean armed=true;
	
	public boolean isArmed() {
		return armed;
	}

	public void setArmed(boolean armed) {
		this.armed = armed;
	}

	public Ogre() {
		super();
	}
	
	public Ogre(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
	}
	/**
	 * @brief Defines attribute symbol as O.
	 */	
	public void defineSymbol() {
		this.symbol = 'O';
	}

	public void closeToKey() {
		this.symbol = '$';
	}
	
	public boolean getStunned()
	{
		return this.stunned;
	}
	
	public void stun()
	{
		this.stunned=true;
		this.symbol = '8';
	}
	
	public void notStunned()
	{
		this.stunned=false;
		this.symbol = 'O';
	}
}
