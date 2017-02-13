
public class Map {

	public static void main(String[] args) {
		char[][] map = new char[10][10];

		//inicializar toda a matriz com espaços
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = ' ';
			}
		}

		// 1ºlinha com x
		for (int i = 0; i < map[0].length; i++) {
			map[0][i] = 'X';
		}

		// ultima linha com x
		for (int i = 0; i < map[9].length; i++) {
			map[9][i] = 'X';
		}

		// ultima lateral com x
		for (int i = 0; i < map.length; i++) {
			map[i][9] = 'X';
		}

		for (int i = 0; i < map.length; i++) {
			if (i == 5 || i == 6) {
				map[i][0] = 'I';
			} else {
				map[i][0] = 'X';
			}
		}

		map[1][6] = 'X';
		map[2][1] = 'X';
		map[2][2] = 'X';
		map[2][4] = 'X';
		map[2][5] = 'X';
		map[2][6] = 'X';
		map[3][6] = 'X';
		map[4][1] = 'X';
		map[4][2] = 'X';
		map[4][4] = 'X';
		map[4][5] = 'X';
		map[4][6] = 'X';
		map[7][1] = 'X';
		map[7][2] = 'X';
		map[7][4] = 'X';
		map[7][5] = 'X';
		map[7][6] = 'X';
		map[7][7] = 'X';
		map[8][6] = 'X';
		
		
		map[1][4] = 'I';
		map[3][4] = 'I';
		map[3][2] = 'I';
		map[8][2] = 'I';
		map[8][4] = 'I';

		map[1][1] = 'H';
		map[1][8] = 'G';
		map[8][7] = 'K';

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
