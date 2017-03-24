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
		identifier(x,y);
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
