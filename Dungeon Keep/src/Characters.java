
public class Characters {

	public Characters() {
		// TODO Auto-generated constructor stub
	}

	public static boolean moveUp(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'H') {
					if (map[i - 1][j] != 'X' && map[i - 1][j] != 'I' && map[i - 1][j] != 'k') {
						map[i - 1][j] = 'H';
						map[i][j] = ' ';
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean moveDown(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'H') {
					if (map[i + 1][j] != 'X' && map[i + 1][j] != 'I' && map[i + 1][j] != 'k') {
						map[i + 1][j] = 'H';
						map[i][j] = ' ';
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean moveLeft(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'H') {
					if (map[i][j - 1] != 'X' && map[i][j - 1] != 'I' && map[i][j - 1] != 'k') {
						map[i][j - 1] = 'H';
						map[i][j] = ' ';
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean moveRight(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'H') {
					if (map[i][j + 1] != 'X' && map[i][j + 1] != 'I' && map[i][j + 1] != 'k') {
						map[i][j + 1] = 'H';
						map[i][j] = ' ';
						return true;
					}
				}
			}
		}
		return false;
	}
}
