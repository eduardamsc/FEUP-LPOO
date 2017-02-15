import java.util.Scanner;

public class Game {
	
	//private static Scanner s;
	public static void main(String[] args) {
		
		System.out.println("*******");
		System.out.println("*START*");
		System.out.println("*******");
		System.out.println("\n\n");
		//s = new Scanner(System.in);
		Game.levelOne();
		
		//System.out.println("\n\n");
		Game.levelTwo();
		//s.close();
	}
	
	public static void levelOne()
	{
		System.out.println("-----LEVEL 1-----");
		char[][] map = Map.firstMap();
		Scanner s = new Scanner(System.in);
		boolean end = false;
	
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
	}
	
	public static void levelTwo()
	{
		System.out.println("-----LEVEL 2-----");
		char[][] map = Map.secondMap();

		boolean end = false;
		
		while (end != true) {
			System.out.println("Press 'u' for up, 'd' for down, 'l' for left and 'r' for right.");
			//linha seguinte da erro. nao percebo porque
			Scanner s = new Scanner(System.in);
			char direction = s.next().charAt(0);

			Hero.movement(map, direction);
			end = Ogre.catchHero(map);

			if (map[1][0] == 'H') {
				System.out.println("YOU WIN");
				end = true;
			}
		}
		
	}
}
