package dkeep.logic;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class GuardSuspicious.
 */
public class GuardSuspicious extends Guard{
	/**
	 * Constructor for class GuardSuspicious.
	 */
	public GuardSuspicious()
	{
		super();
	}
	
	/**
	 * Constructor for class GuardSuspicious with coordinates.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public GuardSuspicious(int x, int y) {
		identifier(x, y);
	}

	/**
	 * Determines GuardSuspicious's behavior which is following his fixed trajectory and reversing his trajectory.       
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
