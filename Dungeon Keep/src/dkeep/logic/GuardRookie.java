package dkeep.logic;

public class GuardRookie extends Guard{
	/**
	 * @brief Constructor for class GuardRookie.
	 */
	public GuardRookie()
	{
		super();
	}
	/**
	 * @brief Constructor for class GuardRookie with coordinates.
	 */	
	public GuardRookie(int x, int y) {
		identifier(x,y);
	}
	/**
	 * @brief Determines GuardRookie's behavior which is following his fixed trajectory religiously.
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
