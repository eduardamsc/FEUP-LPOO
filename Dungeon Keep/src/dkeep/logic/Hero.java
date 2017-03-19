package dkeep.logic;
public class Hero extends Character {
	boolean armed =false;
	
	public Hero() {
		super();
		defineSymbol();
	}

	public Hero(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
	}
	
	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	public boolean getArmed()
	{
		return this.armed;
	}

	///////////////////////////////////////////////////////////////////////////////
	/**
	 * @brief Defines attribute symbol as H.
	 */	
	public void defineSymbol() {
		this.symbol = 'H';
	}

	public void hasKey() {
		this.symbol = 'K';
	}
	
	public void armed()
	{
		this.armed=true;
		this.symbol='A';
	}
	
	public boolean wall(Map map, char direction) {
		if (direction == 'w') {
			if (map.getMap()[x - 1][y] == 'X' || map.getMap()[x - 1][y] == 'k' || map.getMap()[x - 1][y] == 'I'
					|| map.getMap()[x - 1][y] == '*' || map.getMap()[x - 1][y] == '8'|| map.getMap()[x - 1][y] == '$'
					|| x - 1 < 0 || y < 0 || x - 1 > map.getMap().length
					|| y > map.getMap()[x - 1].length)
				return false;
		}

		if (direction == 's') {
			if (map.getMap()[x + 1][y] == 'X' || map.getMap()[x + 1][y] == 'k' || map.getMap()[x + 1][y] == 'I'
					|| map.getMap()[x + 1][y] == '*' || map.getMap()[x + 1][y] == '8'|| map.getMap()[x + 1][y] == '$'
					|| x + 1 < 0 || y < 0 || x + 1 > map.getMap().length
					|| y > map.getMap()[x - 1].length)
				return false;
		}

		if (direction == 'a') {
			if (map.getMap()[x][y - 1] == 'X' || map.getMap()[x][y - 1] == 'k' || map.getMap()[x][y - 1] == 'I'
					|| map.getMap()[x][y-1] == '*' || map.getMap()[x][y-1] == '8'|| map.getMap()[x][y-1] == '$'
					|| x < 0 || y - 1 < 0 || x > map.getMap().length
					|| y > map.getMap()[x].length)
				return false;
		}

		if (direction == 'd') {
			if (map.getMap()[x][y + 1] == 'X' || map.getMap()[x][y + 1] == 'k' || map.getMap()[x][y + 1] == 'I'
					|| map.getMap()[x][y+1] == '*' || map.getMap()[x][y+1] == '8'|| map.getMap()[x][y+1] == '$'
					|| x < 0 || y - 1 < 0 || x > map.getMap().length
					|| y > map.getMap()[x].length)
				return false;
		}

		return true;
	}
}
