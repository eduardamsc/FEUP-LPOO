package dkeep.logic;

public class Map {

	private char[][] map;
	/**
	 * @brief Get for attribute map, which is a matrix of chars.
	 * @return map.
	 */
	public char[][] getMap() {
		return map;
	}
	/**
	 * @brief Set for attribute map.
	 */
	public void setMap(char[][] map) {
		this.map = map;
	}
	/**
	 * @brief Constructor for class Map.
	 */
	public Map(char[][] map)
	{
		this.map=map;
	}
	/**
	 * @brief Constructor for class Map according to level.
	 */
	public Map(int level) {
		if (level == 1)
			firstMap();
		if (level == 2)
			secondMap();
	}

	/**
	 * @brief Generates map for level 1.
	 */
	public char[][] firstMap() {

		this.map = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

		return map;
	}

	/**
	 * @brief Generates map for level 2.
	 */
	public char[][] secondMap() {

		this.map = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

		return map;
	}

	/**
	 * @brief Inserts Character on map.
	 * @param c Character.
	 */
	public void insertCharacter(Character c) {
		map[c.getX()][c.getY()] = c.getSymbol();
	}

	/**
	 * @brief Inserts Object on map.
	 * @param o Object.
	 */
	public void insertObject(Object o) {
		map[o.getX()][o.getY()] = o.getSymbol();
	}
}
