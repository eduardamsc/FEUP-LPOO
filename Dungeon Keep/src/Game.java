import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		
		System.out.println("*******");
		System.out.println("*START*");
		System.out.println("*******");
		
		//LEVEL 1
		System.out.println("-----LEVEL 1-----");
		char[][] map=Map.firstMap();
		
		boolean end = false;
		Scanner s = new Scanner(System.in);
		while (end != true)
		{
			System.out.println("Press 'u' for up, 'd' for down, 'l' for left and 'r' for right.");
			char direction = s.next().charAt(0);
			
			end = Hero.movement(map, direction);

			if (map[5][0] == 'H' || map[6][0] == 'H') {
				System.out.println("YOU WIN");
				end = true;
			}
			
		}
		s.close();
	}
}
