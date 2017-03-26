package dkeep.logic;

public class GuardRookie extends Guard{
	/**
	 * Constructor for class GuardRookie.
	 */
	public GuardRookie()
	{
		super();
	}
	
	/**
	 * Constructor for class GuardRookie with coordinates.
	 */
	public GuardRookie(int x, int y) {
		identifier(x, y);
	}

	/**
	 * Determines GuardRookie's behavior which is following his fixed trajectory religiously.
	 */
	public void behavior()
	{
		if (this.i>=23)
		{
			this.i=0;
		} else this.i++;
		this.x=xFixed[i];
		this.y=yFixed[i];
	}
}
