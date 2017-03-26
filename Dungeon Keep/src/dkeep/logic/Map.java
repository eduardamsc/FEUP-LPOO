package dkeep.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Map.
 */
public class Map {

	/** The map. */
	private char[][] map;
	/**
	 * Get for attribute map, which is a matrix of chars.
	 * @return map.
	 */
	public char[][] getMap() {
		return map;
	}
	
	/**
	 * Set for attribute map.
	 *
	 * @param map
	 *            the new map
	 */
	public void setMap(char[][] map) {
		this.map = map;
	}
	
	/**
	 * Constructor for class Map.
	 *
	 * @param map
	 *            the map
	 */
	public Map(char[][] map)
	{
		this.map=map;
	}
	
	/**
	 * Constructor for class Map according to level.
	 *
	 * @param level
	 *            the level
	 */
	public Map(int level) {
		if (level == 1)
			firstMap();
		if (level == 2)
			secondMap();
	}

	/**
	 * Generates map for level 1.
	 *
	 * @return the char[][]
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
	 * Generates map for level 2.
	 *
	 * @return the char[][]
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
	 * Inserts Character on map.
	 * @param c Character.
	 */
	public void insertCharacter(Character c) {
		map[c.getX()][c.getY()] = c.getSymbol();
	}

	/**
	 * Inserts Object on map.
	 * @param o Object.
	 */
	public void insertObject(Object o) {
		map[o.getX()][o.getY()] = o.getSymbol();
	}
}
