import java.util.Scanner;

public class Hero extends Characters{

	public static boolean openLever(char[][] map)
	{
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'k')
				{
					if (map[i-1][j]=='H'|| map[i+1][j]=='H'|| map[i][j-1]=='H' || map[i][j+1]=='H')
					{
						map[5][0]='S';
						map[6][0]='S';
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean movement(char[][] map, char direction)
	{
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
		
		if (Hero.openLever(map))
		{
			System.out.println("The gates are now open!");
		}
		
		Map.printMap(map);
		
		return Guard.catchHero(map);
	}
}
