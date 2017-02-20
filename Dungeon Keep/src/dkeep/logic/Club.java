package dkeep.logic;
import dkeep.cli.Map;

public class Club extends Ogre {

	public Club() {
		super();
		defineSymbol();
	}

	public void defineSymbol() {
		this.symbol = '*';
	}

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

	public boolean wall(Map map, char direction, int x, int y) {
		if (direction == 'w') {
			if (map.getMap()[x - 1][y] == 'X' || map.getMap()[x - 1][y] == 'k' || map.getMap()[x - 1][y] == 'I'
					|| map.getMap()[x - 1][y] == 'G' || map.getMap()[x - 1][y] == 'H' || x - 1 < 0 || y < 0
					|| x - 1 > map.getMap().length || y > map.getMap()[x - 1].length)
				return false;
		}

		if (direction == 's') {
			if (map.getMap()[x + 1][y] == 'X' || map.getMap()[x + 1][y] == 'k' || map.getMap()[x + 1][y] == 'I'
					|| map.getMap()[x + 1][y] == 'G' || map.getMap()[x + 1][y] == 'H' || x + 1 < 0 || y < 0
					|| x + 1 > map.getMap().length || y > map.getMap()[x - 1].length)
				return false;
		}

		if (direction == 'a') {
			if (map.getMap()[x][y - 1] == 'X' || map.getMap()[x][y - 1] == 'k' || map.getMap()[x][y - 1] == 'I'
					|| map.getMap()[x][y - 1] == 'G' || map.getMap()[x][y - 1] == 'H' || x < 0 || y - 1 < 0
					|| x > map.getMap().length || y > map.getMap()[x].length)
				return false;
		}

		if (direction == 'd') {
			if (map.getMap()[x][y + 1] == 'X' || map.getMap()[x][y + 1] == 'k' || map.getMap()[x][y + 1] == 'I'
					|| map.getMap()[x][y + 1] == 'G' || map.getMap()[x][y + 1] == 'H' || x < 0 || y - 1 < 0
					|| x > map.getMap().length || y > map.getMap()[x].length)
				return false;
		}

		return true;
	}
}
