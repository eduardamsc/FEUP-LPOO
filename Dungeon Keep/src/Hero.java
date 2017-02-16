public class Hero extends Characters {

	public Hero() {
		// TODO Auto-generated constructor stub
	}

	public static boolean openLever(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'k') {
					if (map[i - 1][j] == 'H' || map[i + 1][j] == 'H' || map[i][j - 1] == 'H' || map[i][j + 1] == 'H') {
						for (int k = 0; k < map.length; k++) {
							for (int z = 0; z < map[k].length; z++) {
								if (map[k][z] == 'I') {
									map[k][z] = 'S';
								}
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void movement(char[][] map, char direction) {
		switch (direction) {
		case 'u':
			Hero.moveUp(map);
			break;
		case 'd':
			Hero.moveDown(map);
			break;
		case 'l':
			Hero.moveLeft(map);
			break;
		case 'r':
			Hero.moveRight(map);
			break;
		}

		if (Hero.openLever(map)) {
			System.out.println("The gates are now open!");
		}

		Map.printMap(map);
	}
}
