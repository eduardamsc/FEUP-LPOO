import java.util.Scanner;

public class Map {

	public static void main(String[] args) {
		System.out.println(
				"*************\n* X - wall  *\n* I - door  *\n* H - guard *\n* K - lever *\n*************\n");

		char[][] map = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
				{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

		
		Map.printMap(map);
		
		System.out.println("START");
		Scanner s = new Scanner(System.in);
		boolean end = false;
		while(end!=true)
		{
			System.out.println("Press 'u' for up, 'd' for down, 'l' for left and 'r' for right.");
			
			char direction = s.next().charAt(0);
			
			
			switch(direction)
			{
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
			
			Map.openLever(map);
			
			Map.printMap(map);
			if (map[5][0]=='S' && map[6][0]=='S')
			{
				end = true;
			}
		}
		s.close();

	}
	
	public static void printMap(char[][] map)
	{
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void openLever(char[][] map)
	{
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'K')
				{
					if (map[i-1][j]=='H'|| map[i+1][j]=='H'|| map[i][j-1]=='H' || map[i][j+1]=='H')
					{
						map[5][0]='S';
						map[6][0]='S';
					}
				}
			}
		}
	}
}
