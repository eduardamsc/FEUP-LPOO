import java.util.Random;

public class Ogre extends Character {

	public Ogre() {
		super();
		defineSymbol();
	}
	
	public void defineSymbol()
	{
		this.symbol='O';
	}

	public void movement(Map map, char direction) {
		
		if (wall(map, direction)) {
			switch (direction) {
			case 'w':
				moveUp();
				break;
			case 's':
				moveDown();
				break;
			case 'a':
				moveLeft();
				break;
			case 'd':
				moveRight();
				break;
			}
		}
	}
}
