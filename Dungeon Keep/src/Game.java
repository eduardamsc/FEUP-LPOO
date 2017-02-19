import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Game {
	private Hero hero = new Hero();
	private Guard guard = new Guard();
	private Ogre ogre = new Ogre();
	private Map map;
	private Lever lever;
	private Vector<Exit> exits;
	private Key key;

	//Levels
	public boolean levelOne() {
		boolean nextLevel = true;
		
		map = new Map(1);
		lever = new Lever();
		lever.setX(8);
		lever.setY(7);
		map.insertObject(lever);
		
		exits = new Vector<Exit>();
		exits.add(new Exit(5,0));
		exits.add(new Exit(6,0));
		exits.add(new Exit(1,4));
		exits.add(new Exit(3,4));
		exits.add(new Exit(3,2));
		exits.add(new Exit(8,2));
		exits.add(new Exit(8,4));
		
		for (int i=0; i<exits.size();i++)
		{
			map.insertObject(exits.get(i));
		}
		
		guard.setX(1);
		guard.setY(8);
		map.insertCharacter(guard);
		hero.setX(1);
		hero.setY(1);
		map.insertCharacter(hero);
		
		System.out.println("-----LEVEL 1-----");
		
		map.printMap();
		
		Scanner s = new Scanner(System.in);
		boolean end = false;

		while (end != true) {
			System.out.println("Play with the keys a,w,s,d.");
			char direction = s.next().charAt(0);

			eraseTrail(hero);
			hero.movement(map, direction);
			updateCharacterPosition(hero);
			
			openLever();
			
			if (lever.getOpen())
			{
				exits.get(0).open();
				updateObjectPosition(exits.get(0));
				exits.get(1).open();
				updateObjectPosition(exits.get(1));
			}

			map.printMap();
			
			if (catchHero()) {
				end = true;
				nextLevel = false;
			}
			
			if ((exits.get(0).getX() == hero.getX() && exits.get(0).getY() == hero.getY()) ||
					(exits.get(1).getX() == hero.getX() && exits.get(1).getY() == hero.getY())) {
				System.out.println("YOU WIN");
				end = true;
			}
		}
		return nextLevel;
	}

	public void levelTwo() {
		
		map = new Map(2);
		key = new Key();
		key.setX(1);
		key.setY(7);
		map.insertObject(key);
		
		exits = new Vector<Exit>();
		exits.add(new Exit(1,0));
		
		for (int i=0; i<exits.size();i++)
		{
			map.insertObject(exits.get(i));
		}
		
		ogre.setX(1);
		ogre.setY(4);
		map.insertCharacter(ogre);
		hero.setX(7);
		hero.setY(1);
		map.insertCharacter(hero);
		
		System.out.println("-----LEVEL 2-----");
		map.printMap();
		
		boolean end = false;

		while (end != true) {
			System.out.println("Play with the keys a,w,s,d.");
			Scanner s = new Scanner(System.in);
			char direction = s.next().charAt(0);

			eraseTrail(hero);
			hero.movement(map, direction);
			updateCharacterPosition(hero);
			
			Random n = new Random();
			int value = n.nextInt(4);
			if (value==0) direction = 'a';
			if (value==1) direction = 'w';
			if (value==2) direction = 's';
			if (value==3) direction = 'd';
			eraseTrail(ogre);
			ogre.movement(map, direction);
			updateCharacterPosition(ogre);
			
			pickKey();
			nearKey();
			
			if (exits.get(0).getX() == hero.getX() && exits.get(0).getY()+1 == hero.getY() && key.getPickedUp()){
				exits.get(0).open();
				updateObjectPosition(exits.get(0));
			}
			
			map.printMap();
			end = catchHero();
			
			if (exits.get(0).getX() == hero.getX() && exits.get(0).getY() == hero.getY()){
				System.out.println("YOU WIN");
				end = true;
			}
		}
	}

	//Gets and sets
	public Hero getHero() {
		return hero;
	}
	
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	public Guard getGuard() {
		return guard;
	}
	
	public void setGuard(Guard guard) {
		this.guard = guard;
	}

	//extras
	
	public void openLever()
	{
		if ((lever.getX()-1 == hero.getX() && lever.getY() == hero.getY()) ||
				(lever.getX()+1 == hero.getX() && lever.getY() == hero.getY()) ||
				(lever.getX() == hero.getX() && lever.getY()-1 == hero.getY()) ||
				(lever.getX() == hero.getX() && lever.getY()+1 == hero.getY()))
		{
			lever.open();
		}
	}
	
	public void pickKey()
	{
		if ((key.getX() - 1 == hero.getX() && key.getY() == hero.getY())
				|| (key.getX() + 1 == hero.getX() && key.getY() == hero.getY())
				|| (key.getX() == hero.getX() && key.getY() - 1 == hero.getY())
				|| (key.getX() == hero.getX() && key.getY() + 1 == hero.getY())) {
			key.pickedUp();
			updateObjectPosition(key);
			hero.key();
			updateCharacterPosition(hero);
		}
	}

	public void nearKey()
	{
		if ((key.getX() - 1 == ogre.getX() && key.getY() == ogre.getY())
				|| (key.getX() + 1 == ogre.getX() && key.getY() == ogre.getY())
				|| (key.getX() == ogre.getX() && key.getY() - 1 == ogre.getY())
				|| (key.getX() == ogre.getX() && key.getY() + 1 == ogre.getY())) {
			ogre.setSymbol('$');
			updateCharacterPosition(ogre);
		} else {
			ogre.setSymbol('O');
			updateCharacterPosition(ogre);
		}
	}
	
	public boolean catchHero()
	{
		if ((guard.getX()-1 == hero.getX() && guard.getY() == hero.getY()) ||
				(guard.getX()+1 == hero.getX() && guard.getY() == hero.getY()) ||
				(guard.getX() == hero.getX() && guard.getY()-1 == hero.getY()) ||
				(guard.getX() == hero.getX() && guard.getY()+1 == hero.getY()))
		{
			System.out.println("***********");
			System.out.println("*GAME OVER*");
			System.out.println("***********");
			System.out.println("You just got caught!");
			return true;
		}
		
		if ((ogre.getX()-1 == hero.getX() && ogre.getY() == hero.getY()) ||
				(ogre.getX()+1 == hero.getX() && ogre.getY() == hero.getY()) ||
				(ogre.getX() == hero.getX() && ogre.getY()-1 == hero.getY()) ||
				(ogre.getX() == hero.getX() && ogre.getY()+1 == hero.getY()))
		{
			System.out.println("***********");
			System.out.println("*GAME OVER*");
			System.out.println("***********");
			System.out.println("You just got caught!");
			return true;
		}
		
		return false;
	}
	
	//update map
	public void eraseTrail(Character c)
	{
		map.getMap()[c.getX()][c.getY()]=' ';
	}
	
	public void updateCharacterPosition(Character c)
	{
		map.insertCharacter(c);
	}
	
	public void updateObjectPosition(Object o)
	{
		map.insertObject(o);
	}
	
}