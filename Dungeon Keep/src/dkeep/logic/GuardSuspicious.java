package dkeep.logic;

import java.util.Random;

public class GuardSuspicious extends Guard{
	/**
	 * @brief Constructor for class GuardSuspicious.
	 */
	public GuardSuspicious()
	{
		super();
	}
	/**
	 * @brief Constructor for class GuardSuspicious with coordinates,.
	 */	
	public GuardSuspicious(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
		xFixed = new int[]{1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2,1};
		yFixed = new int[]{7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8,8};
		this.i=-1;
		this.rightWay=true;
	}
	/**
	 * @brief Determines GuardSuspicious's behavior which is following his fixed trajectory and reversing his trajectory.
	 */
	public void behavior()
	{
		if (this.rightWay)
		{
			if (this.i==23)
			{
				this.i=0;
			} else this.i++;
		} else
		{
			if (this.i==0)
			{
				this.i=23;
			} else this.i--;
		}
		
		Random n=new Random();
		int value = n.nextInt(4);
		if (value==0) this.rightWay=false;
		else this.rightWay=true;
		
		this.x=xFixed[i];
		this.y=yFixed[i];
	}
}
