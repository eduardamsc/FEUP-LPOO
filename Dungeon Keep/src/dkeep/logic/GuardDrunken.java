package dkeep.logic;

import java.util.Random;

public class GuardDrunken extends Guard{
	/**
	 * @brief Constructor for class GuardDrunken.
	 */	
	public GuardDrunken()
	{
		super();
	}
	/**
	 * @brief Constructor for class GuardDrunken with coordinates.
	 */	
	public GuardDrunken(int x, int y) {
		identifier(x,y);
	}
	/**
	 * @brief Determines GuardDrunken's behavior which is following his fixed trajectory, reversing his trajectory and falling asleep.
	 */
	public void behavior() {
		
		if (getAwake()) {

			if (this.rightWay) {
				if (this.i == 23) {
					this.i = 0;
				} else
					this.i++;
			} else {
				if (this.i == 0) {
					this.i = 23;
				} else
					this.i--;
			}

			this.x = xFixed[i];
			this.y = yFixed[i];
		}
		
		Random n = new Random();
		int value = n.nextInt(6);
		if (value == 0) {
			this.rightWay = false;
			awake();
		} else if (value >0 && value <4) {
			asleep();
		} else {
			this.rightWay = true;
			awake();
		}
	}
}
