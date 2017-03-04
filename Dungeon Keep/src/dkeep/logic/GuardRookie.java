package dkeep.logic;

public class GuardRookie extends Guard{
	
	public GuardRookie()
	{
		super();
	}
	
	public GuardRookie(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
		xFixed = new int[]{1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2,1};
		yFixed = new int[]{7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8,8};
		this.i=-1;
		this.rightWay=true;
	}
	
	public void behaviour()
	{
		if (this.i>=23)
		{
			this.i=0;
		} else this.i++;
		this.x=xFixed[i];
		this.y=yFixed[i];
	}
}
