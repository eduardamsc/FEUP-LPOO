import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		
		System.out.println("*******");
		System.out.println("*START*");
		System.out.println("*******");
		System.out.println("\n\n");
		
		Game.levelOne();
		
		System.out.println("\n\n");
		Game.levelTwo();
	}
	
	public static void levelOne()
	{
		System.out.println("-----LEVEL 1-----");
		char[][] map = Map.firstMap();

		boolean end = false;
		Scanner s = new Scanner(System.in);
		while (end != true) {
			System.out.println("Press 'u' for up, 'd' for down, 'l' for left and 'r' for right.");
			char direction = s.next().charAt(0);

			Hero.movement(map, direction);
			end = Guard.catchHero(map);

			if (map[5][0] == 'H' || map[6][0] == 'H') {
				System.out.println("YOU WIN");
				end = true;
			}
		}
		s.close();
	}
	
	public static void levelTwo()
	{
		System.out.println("-----LEVEL 2-----");
		char[][] map = Map.secondMap();

		boolean end = false;
		Scanner s = new Scanner(System.in);
		while (end != true) {
			System.out.println("Press 'u' for up, 'd' for down, 'l' for left and 'r' for right.");
			//linha seguinte da erro. nao percebo porque
			char direction = s.next().charAt(0);

			Hero.movement(map, direction);
			end = Ogre.catchHero(map);

			if (map[1][0] == 'H') {
				System.out.println("YOU WIN");
				end = true;
			}
		}
		s.close();
	}
}
