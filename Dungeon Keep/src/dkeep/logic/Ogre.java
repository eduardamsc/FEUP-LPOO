package dkeep.logic;

public class Ogre extends Character {

	boolean stunned=false;
	
	public Ogre() {
		super();
	}
	
	public Ogre(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
	}

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
