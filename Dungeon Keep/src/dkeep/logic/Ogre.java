package dkeep.logic;

public class Ogre extends Character {

	public Ogre() {
		super();
		defineSymbol();
	}

	public void defineSymbol() {
		this.symbol = 'O';
	}

	public void closeKey() {
		this.symbol = '$';
	}
}
