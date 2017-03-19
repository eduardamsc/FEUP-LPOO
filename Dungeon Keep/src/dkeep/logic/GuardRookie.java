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
		this.x = x;
		this.y = y;
		defineSymbol();
		xFixed = new int[]{1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2,1};
		yFixed = new int[]{7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8,8};
		this.i=-1;
		this.rightWay=true;
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
