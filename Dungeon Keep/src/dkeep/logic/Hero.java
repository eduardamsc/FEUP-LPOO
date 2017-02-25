package dkeep.logic;
public class Hero extends Character {
	boolean armed =false;
	
	public Hero() {
		super();
		defineSymbol();
	}

	public void defineSymbol() {
		this.symbol = 'H';
	}

	public void hasKey() {
		this.symbol = 'K';
	}
	
	public void isArmed()
	{
		this.armed=true;
		this.symbol='A';
	}
	
	public boolean getArmed()
	{
		return this.armed;
	}
}
