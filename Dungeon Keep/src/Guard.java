
public class Guard extends Character {
	public Guard() {
		super();
		defineSymbol();
	}
	
	public void defineSymbol()
	{
		this.symbol='G';
	}

	public static void fixedTrajectory()
	{
		int[] xx = {-1,0,0,0,0,-1,-1,-1,-1,-1,-1,0,1,1,1,1,1,1,1,0,0,0,0,0};
		int[] yy = {0,-1,-1,-1,-1,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,1,1,1,1,1};
		
	}

}
