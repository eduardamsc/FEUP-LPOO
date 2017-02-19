public class Hero extends Character {

	public Hero() {
		super();
		defineSymbol();
	}
	
	public void defineSymbol()
	{
		this.symbol='H';
	}
	
	public void key()
	{
		this.symbol='K';
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
