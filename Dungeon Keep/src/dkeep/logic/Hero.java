package dkeep.logic;
public class Hero extends Character {
	boolean armed =false;
	
	public Hero() {
		super();
		defineSymbol();
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	public boolean getArmed()
	{
		return this.armed;
	}

	///////////////////////////////////////////////////////////////////////////////
	public void defineSymbol() {
		this.symbol = 'H';
	}

	public void hasKey() {
		this.symbol = 'K';
	}
	
	public void armed()
	{
		this.armed=true;
		this.symbol='A';
	}
}
