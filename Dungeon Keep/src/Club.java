import java.util.Random;

public class Club extends Ogre {

	public Club() {
		super();
		defineSymbol();
	}
	
	public void defineSymbol()
	{
		this.symbol='*';
	}
	
	public char randomTrajectory()
	{
		char direction=' ';
		Random n = new Random();
		int value = n.nextInt(4);
		if (value==0) direction = 'a';
		if (value==1) direction = 'w';
		if (value==2) direction = 's';
		if (value==3) direction = 'd';
		
		return direction;
	}
}
