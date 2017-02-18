
public class Menu {

	public static void main(String[] args) {
		
		System.out.println("*******");
		System.out.println("*START*");
		System.out.println("*******");
		
		Game g = new Game();
		
		boolean nextLevel;
		
		nextLevel = g.levelOne();
		
		if (nextLevel) g.levelTwo();
	}

}
