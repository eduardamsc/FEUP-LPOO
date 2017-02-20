package dkeep.logic;
import dkeep.cli.Map;

public abstract class Character {

	protected int x; // columns
	protected int y; // lines
	protected char symbol; // defines character on map

	public Character() {
		x = 0;
		y = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public abstract void defineSymbol();

	// Movements
	public void moveUp() {
		x--;
	}

	public void moveDown() {
		x++;
	}

	public void moveLeft() {
		y--;
	}

	public void moveRight() {
		y++;
	}

	// checks if there's a wall
	public boolean wall(Map map, char direction) {
		if (direction == 'w') {
			if (map.getMap()[x - 1][y] == 'X' || map.getMap()[x - 1][y] == 'k' || map.getMap()[x - 1][y] == 'I'
					|| map.getMap()[x - 1][y] == 'G' || map.getMap()[x - 1][y] == 'H' || map.getMap()[x - 1][y] == 'O'
					|| x - 1 < 0 || y < 0 || x - 1 > map.getMap().length || y > map.getMap()[x - 1].length)
				return false;
		}

		if (direction == 's') {
			if (map.getMap()[x + 1][y] == 'X' || map.getMap()[x + 1][y] == 'k' || map.getMap()[x + 1][y] == 'I'
					|| map.getMap()[x + 1][y] == 'G' || map.getMap()[x + 1][y] == 'H' || map.getMap()[x + 1][y] == 'O'
					|| x + 1 < 0 || y < 0 || x + 1 > map.getMap().length || y > map.getMap()[x - 1].length)
				return false;
		}

		if (direction == 'a') {
			if (map.getMap()[x][y - 1] == 'X' || map.getMap()[x][y - 1] == 'k' || map.getMap()[x][y - 1] == 'I'
					|| map.getMap()[x][y - 1] == 'G' || map.getMap()[x][y - 1] == 'H' || map.getMap()[x][y - 1] == 'O'
					|| x < 0 || y - 1 < 0 || x > map.getMap().length || y > map.getMap()[x].length)
				return false;
		}

		if (direction == 'd') {
			if (map.getMap()[x][y + 1] == 'X' || map.getMap()[x][y + 1] == 'k' || map.getMap()[x][y + 1] == 'I'
					|| map.getMap()[x][y + 1] == 'G' || map.getMap()[x][y + 1] == 'H' || map.getMap()[x][y + 1] == 'O'
					|| x < 0 || y - 1 < 0 || x > map.getMap().length || y > map.getMap()[x].length)
				return false;
		}

		return true;
	}

	// combines all movements and wall checking
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
