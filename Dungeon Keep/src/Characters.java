
public class Characters {

	public static boolean moveUp(char[][] map)
	{
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'H')
				{
					if (map[i-1][j] == ' ')
					{
						map[i - 1][j] = 'H';
						map[i][j] = ' ';
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean moveDown(char[][] map)
	{
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'H') {
					if (map[i + 1][j] == ' ') {
						map[i + 1][j] = 'H';
						map[i][j] = ' ';
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean moveLeft(char[][] map)
	{
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'H')
				{
					if (map[i][j-1] == ' ')
					{
						map[i][j-1] = 'H';
						map[i][j] = ' ';
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean moveRight(char[][] map)
	{
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'H')
				{
					if (map[i][j+1] == ' ')
					{
						map[i][j+1] = 'H';
						map[i][j] = ' ';
						return true;
					}
				}
			}
		}
		return false;
	}
}
