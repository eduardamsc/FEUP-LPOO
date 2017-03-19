package dkeep.logic;
public class Hero extends Character {
	boolean armed =false;
	/**
	 * @brief Constructor for class Hero.
	 */	
	public Hero() {
		super();
		defineSymbol();
	}
	/**
	 * @brief Constructor for class Hero with coordinates.
	 */	
	public Hero(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
	}
	
	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * @brief Get for attribute armed, which represents if Hero has picked up a Club.
	 * @return armed.
	 */
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
	/**
	 * @brief Changes Hero's symbol to K, as he has caught the Key.
	 */
	public void hasKey() {
		this.symbol = 'K';
	}
	/**
	 * @brief Changes Hero's state to armed and symbol to A.
	 */
	public void armed()
	{
		this.armed=true;
		this.symbol='A';
	}
	/**
	 * @brief Checks if Hero can move in a certain direction.
	 * @param map Map in which Hero is moving.
	 * @param direction Direction in which Hero is trying to move.
	 * @return True for permission to move.
	 */	
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
