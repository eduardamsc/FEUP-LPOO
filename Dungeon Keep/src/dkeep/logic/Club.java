package dkeep.logic;

public class Club extends Character{
	/**
	 * @brief Constructor for class Club.
	 */	
	public Club() {
		super();
		defineSymbol();
	}
	/**
	 * @brief Constructor for class Club with coordinates.
	 */	
	public Club(int x, int y) {
		this.x = x;
		this.y = y;
		defineSymbol();
	}
	/**
	 * @brief Defines attribute symbol as *.
	 */	
	public void defineSymbol() {
		this.symbol = '*';
	}

	/////////////////////////////////////////MOVEMENTS//////////////////////////////////////
	/**
	 * @brief Moves Club around Ogre.
	 * @param map Map in which Club is moving.
	 * @param x Line of Ogre's current position.
	 * @param y Column of Ogre's current position.
	 */	
	public void movement(Map map, int x, int y) {

		char direction = randomTrajectory();

		while (!wall(map, direction, x, y)) {
			direction = randomTrajectory();
		}

		if (direction == 'w') {
			this.x = x - 1;
			this.y = y;
		} else if (direction == 's') {
			this.x = x + 1;
			this.y = y;
		} else if (direction == 'a') {
			this.x = x;
			this.y = y - 1;
		} else if (direction == 'd') {
			this.x = x;
			this.y = y + 1;
		}
	}
	/**
	 * @brief Checks if Club can move to a certain position around the Ogre.
	 * @param map Map in which Club is moving.
	 * @param direction Direction in which Club is trying to move.
	 * @return True for permission to move.
	 */	
	public boolean wall(Map map, char direction, int x, int y) {
		if (direction == 'w') {
			if (map.getMap()[x - 1][y] == 'X' || map.getMap()[x - 1][y] == 'k' || map.getMap()[x - 1][y] == 'I'
					|| map.getMap()[x - 1][y] == 'G' || map.getMap()[x - 1][y] == 'H' || map.getMap()[x - 1][y] == 'A'|| map.getMap()[x - 1][y] == 'K'
					|| x - 1 < 0 || y < 0
					|| x - 1 > map.getMap().length || y > map.getMap()[x - 1].length)
				return false;
		}

		if (direction == 's') {
			if (map.getMap()[x + 1][y] == 'X' || map.getMap()[x + 1][y] == 'k' || map.getMap()[x + 1][y] == 'I'
					|| map.getMap()[x + 1][y] == 'G' || map.getMap()[x + 1][y] == 'H' || map.getMap()[x + 1][y] == 'A' || map.getMap()[x + 1][y] == 'K'
					|| x + 1 < 0 || y < 0
					|| x + 1 > map.getMap().length || y > map.getMap()[x - 1].length)
				return false;
		}

		if (direction == 'a') {
			if (map.getMap()[x][y - 1] == 'X' || map.getMap()[x][y - 1] == 'k' || map.getMap()[x][y - 1] == 'I'
					|| map.getMap()[x][y - 1] == 'G' || map.getMap()[x][y - 1] == 'H' || map.getMap()[x][y - 1] == 'A' || map.getMap()[x][y - 1] == 'K'
					|| x < 0 || y - 1 < 0
					|| x > map.getMap().length || y > map.getMap()[x].length)
				return false;
		}

		if (direction == 'd') {
			if (map.getMap()[x][y + 1] == 'X' || map.getMap()[x][y + 1] == 'k' || map.getMap()[x][y + 1] == 'I'
					|| map.getMap()[x][y + 1] == 'G' || map.getMap()[x][y + 1] == 'H' || map.getMap()[x][y + 1] == 'A' || map.getMap()[x][y + 1] == 'K'
					|| x < 0 || y - 1 < 0
					|| x > map.getMap().length || y > map.getMap()[x].length)
				return false;
		}

		return true;
	}
}
