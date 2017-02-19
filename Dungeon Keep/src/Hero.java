public class Hero extends Character {

	public Hero() {
		super();
		defineSymbol();
	}
	
	public void defineSymbol()
	{
		this.symbol='H';
	}
	
	public void hasKey()
	{
		this.symbol='K';
	}
}
