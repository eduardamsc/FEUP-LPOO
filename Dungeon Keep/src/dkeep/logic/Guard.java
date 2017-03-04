package dkeep.logic;

public class Guard extends Character {
	boolean awake = true;

	public Guard() {
		super();
		defineSymbol();
	}

	public Guard(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	public boolean getAwake()
	{
		return this.awake;
	}

	/////////////////////////////////////////MOVEMENTS//////////////////////////////////////
	public char fixedTrajectory(int i) {
		char direction = ' ';

		// int[] xx = {0,-1,-1,-1,-1,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,1,1,1,1,1};
		// int[] yy = {-1,0,0,0,0,-1,-1,-1,-1,-1,-1,0,1,1,1,1,1,1,1,0,0,0,0,0};

		if (i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17 || i == 18)
			direction = 'd';
		if (i == 0 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10)
			direction = 'a';
		if (i == 1 || i == 2 || i == 3 || i == 4 || i == 11)
			direction = 's';
		if (i == 19 || i == 20 || i == 21 || i == 22 || i == 23)
			direction = 'w';

		return direction;
	}

	public char reverseTrajectory(int i) {
		char direction = ' ';

		// int[] xx = {0,-1,-1,-1,-1,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,1,1,1,1,1};
		// int[] yy = {-1,0,0,0,0,-1,-1,-1,-1,-1,-1,0,1,1,1,1,1,1,1,0,0,0,0,0};

		if (i == 0 || i == 23)
			return ' ';
		if (i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17)
			direction = 'a';
		if (i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9)
			direction = 'd';
		if (i == 0 || i == 1 || i == 2 || i == 3 || i == 10)
			direction = 'w';
		if (i == 18 || i == 19 || i == 20 || i == 21 || i == 22)
			direction = 's';

		return direction;
	}

	///////////////////////////////////////////////////////////////////////////////
	public void defineSymbol() {
		this.symbol = 'G';
	}

	public void asleep() {
		this.awake=false;
		this.symbol='g';
	}	
}
