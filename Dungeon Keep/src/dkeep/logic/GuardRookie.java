package dkeep.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class GuardRookie.
 */
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
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
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
