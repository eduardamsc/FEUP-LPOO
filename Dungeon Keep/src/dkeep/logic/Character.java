package dkeep.logic;

import java.util.Random;

public abstract class Character {

	protected int x; // lines
	protected int y; // columns
	protected char symbol; // represents character on map
	
	/**
	 * @brief Constructor for class Character.
	 */
	public Character() {
		x = 0;
		y = 0;
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	/**
	 * @brief Get for attribute X, which represents the lines.
	 * @return x.
	 */
	public int getX() {
		return x;
	}
	/**
	 * @brief Get for attribute Y, which represents the columns.
	 * @return y.
	 */
	public int getY() {
		return y;
	}
	/**
	 * @brief Set for attribute X.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @brief Set for attribute Y.
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @brief Get for attribute symbol, which distinguishes each Character in the map.
	 * @return symbol.
	 */
	public char getSymbol() {
		return symbol;
	}
	/**
	 * @brief Set for attribute symbol.
	 */
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	/////////////////////////////////////////MOVEMENTS//////////////////////////////////////
	/**
	 * @brief Moves Character up by reducing the attribute x.
	 */	
	public void moveUp() {
		x--;
	}
	/**
	 * @brief Moves Character down by adding to the attribute x.
	 */	
	public void moveDown() {
		x++;
	}
	/**
	 * @brief Moves Character left by reducing the attribute y.
	 */	
	public void moveLeft() {
		y--;
	}
	/**
	 * @brief Moves Character up by adding to the attribute y.
	 */	
	public void moveRight() {
		y++;
	}
	/**
	 * @brief Checks if Character can move in a certain direction.
	 * @param map Map in which Character is moving.
	 * @param direction Direction in which Character is trying to move.
	 * @return True for permission to move.
	 */	
	public boolean wall(Map map, char direction) {
		switch (direction)
		{
			case 'w':
				return wallAux(map,-1,0);
			case 's':
				return wallAux(map,1,0);
			case 'a':
				return wallAux(map,0,-1);
			case 'd':
				return wallAux(map,0,1);
		}
		return true;
			
		/*if (direction == 'w') {
			if (map.getMap()[x - 1][y] == 'X' || map.getMap()[x - 1][y] == 'k' || map.getMap()[x - 1][y] == 'I'
					|| map.getMap()[x - 1][y] == 'H' || map.getMap()[x - 1][y] == 'A' || map.getMap()[x - 1][y] == 'K' 
					|| x - 1 < 0 || y < 0 || x - 1 > map.getMap().length
					|| y > map.getMap()[x - 1].length)
				return false;
		}

		if (direction == 's') {
			if (map.getMap()[x + 1][y] == 'X' || map.getMap()[x + 1][y] == 'k' || map.getMap()[x + 1][y] == 'I'
					|| map.getMap()[x + 1][y] == 'H' || map.getMap()[x + 1][y] == 'A' || map.getMap()[x + 1][y] == 'K'
					|| x + 1 < 0 || y < 0 || x + 1 > map.getMap().length
					|| y > map.getMap()[x - 1].length)
				return false;
		}

		if (direction == 'a') {
			if (map.getMap()[x][y - 1] == 'X' || map.getMap()[x][y - 1] == 'k' || map.getMap()[x][y - 1] == 'I'
					|| map.getMap()[x][y - 1] == 'H' || map.getMap()[x][y - 1] == 'A' || map.getMap()[x][y - 1] == 'K'
					|| x < 0 || y - 1 < 0 || x > map.getMap().length
					|| y > map.getMap()[x].length)
				return false;
		}

		if (direction == 'd') {
			if (map.getMap()[x][y + 1] == 'X' || map.getMap()[x][y + 1] == 'k' || map.getMap()[x][y + 1] == 'I'
					|| map.getMap()[x][y + 1] == 'H'|| map.getMap()[x][y + 1] == 'A'|| map.getMap()[x][y + 1] == 'K'
					|| x < 0 || y - 1 < 0 || x > map.getMap().length
					|| y > map.getMap()[x].length)
				return false;
		}*/
	}	
	/**
	 * @brief Checks if Character can move.
	 * @param map Map in which Character is moving.
	 * @param i parameter added to coordinate x.
	 * @param j parameter added to coordinate y.
	 * @return True for permission to move.
	 */	
	public boolean wallAux(Map map, int i, int j)
	{
		int a=x+i, b=y+j;
		if (map.getMap()[a][b] == 'X' || map.getMap()[a][b] == 'k' || map.getMap()[a][b] == 'I'
				|| map.getMap()[a][b] == 'H' || map.getMap()[a][b] == 'A' || map.getMap()[a][b] == 'K' 
				|| a < 0 || (b) < 0 || a > map.getMap().length
				|| b > map.getMap()[a].length)
			return false;
		else return true;
	}
	/**
	 * @brief Moves Character in all directions as long as it's possible.
	 * @param map Map in which Character is moving.
	 * @param direction Direction in which Character is trying to move.
	 */	
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
			case ' ':
				break;
			}
		}
	}
	/**
	 * @brief Generates random direction.
	 * @return char referring to a direction (a for left, w for up, s for down and d for right).
	 */	
	public char randomTrajectory() {
		char direction = ' ';
		Random n = new Random();
		int value = n.nextInt(4);
		if (value == 0)
			direction = 'a';
		if (value == 1)
			direction = 'w';
		if (value == 2)
			direction = 's';
		if (value == 3)
			direction = 'd';

		return direction;
	}
}
