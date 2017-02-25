package dkeep.logic;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Game {
	private Hero hero = new Hero();
	private Vector<Guard> guards;
	private Ogre ogre = new Ogre();
	private Club club = new Club();
	private Map map;
	private Lever lever;
	private Vector<Exit> exits;
	private Key key;

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public boolean logicLevel1(char direction, int i) {
		moveHero(direction);
		moveGuard(i);

		openLever(); // checks to see if lever can be opened and updates

		if (catchHero(guards.get(0))) { // checks if guard Rookie has caught hero
			return true;
		}
		if (catchHero(guards.get(1))) { // checks if guard Drunken has caught hero
			return true;
		}
		if (catchHero(guards.get(2))) { // checks if guard Suspicious has caught hero
			return true;
		}

		return false;
	}

	public boolean logicLevel2(char direction) {
		moveHero(direction);
		moveOgre();

		pickKey(); // checks if hero has picked up key and updates
		nearKey(); // checks if ogre is near key and updates

		if (exits.get(0).getX() == hero.getX() && exits.get(0).getY() + 1 == hero.getY() && key.getPickedUp()) {
			// if hero is close to exit and has the key
			// the exit turns to stairs
			exits.get(0).open();
			updateObjectPosition(exits.get(0));
		}

		if (catchHero(ogre)) { // checks if guard has caught hero
			return true;
		}
		return false;
	}

	//check victory
	public boolean checkVictoryLevel1() {
		if ((exits.get(0).getX() == hero.getX() && exits.get(0).getY() == hero.getY())
				|| (exits.get(1).getX() == hero.getX() && exits.get(1).getY() == hero.getY()))
		// if hero is on the exit, you win
		{
			return true;
		}
		return false;
	}

	public boolean checkVictoryLevel2() {
		if (exits.get(0).getX() == hero.getX() && exits.get(0).getY() == hero.getY())
		// if hero is on top of stairs, you win
		{
			return true;
		}
		return false;
	}

	//moves
	public void moveHero(char direction) {
		eraseTrailC(hero); // deletes trail when hero moves
		hero.movement(map, direction);
		updateCharacterPosition(hero); // updates hero's position on the map
	}

	public void moveGuard(int i) {
		moveGuardRookie(i);
		moveGuardDrunken(i);
		moveGuardSuspicious(i);
	}
	
	public void moveGuardRookie(int i)
	{
		char direction = guards.get(0).fixedTrajectory(i);
		eraseTrailC(guards.get(0)); // erases guard's trail as it changes position
		guards.get(0).movement(map, direction);
		updateCharacterPosition(guards.get(0)); // updates guard's position on map
	}

	public void moveGuardDrunken(int i)
	{
		String move = " ";
		Random n = new Random();
		int value = n.nextInt(3);
		if (value==0) move = "asleep";
		if (value==1) move = "reverse";
		if (value==2) move = "continue";
		
		if (i==0 && move!="continue") move="continue"; 
		
		if (move == "asleep") {
			guards.get(1).isAsleep();
			updateCharacterPosition(guards.get(1));
		} else {
			guards.get(1).setSymbol('G');
			updateCharacterPosition(guards.get(1));
		}
		
		char direction = guards.get(1).fixedTrajectory(i);
		
		if ( move=="continue")
		{
			eraseTrailC(guards.get(1)); // erases guard's trail as it changes position
			guards.get(1).movement(map, direction);
			updateCharacterPosition(guards.get(1)); // updates guard's position on map
		} else if (move == "reverse")
		{
			eraseTrailC(guards.get(1)); // erases guard's trail as it changes position
			guards.get(1).movement(map, direction);
			updateCharacterPosition(guards.get(1)); // updates guard's position on map
		}	
	}
	
	public void moveGuardSuspicious(int i)
	{
		String move = " ";
		Random n = new Random();
		int value = n.nextInt(2);
		if (value==0) move = "reverse";
		if (value==1) move = "continue";
		
		if (i==0 && move!="continue") move="continue";
		
		char direction;
		
		if (move == "reverse") {
			direction = guards.get(2).reverseTrajectory(i);
			eraseTrailC(guards.get(2)); // erases guard's trail as it changes position
			guards.get(2).movement(map, direction);
			updateCharacterPosition(guards.get(2)); // updates guard's position on map
		} else if (move == "continue")
		{
			direction = guards.get(2).fixedTrajectory(i);
			eraseTrailC(guards.get(2)); // erases guard's trail as it changes position
			guards.get(2).movement(map, direction);
			updateCharacterPosition(guards.get(2)); // updates guard's position on map
		}
	}
	
	public void moveOgre() {
		// generates ogre's trajectory randomly
		char direction = ogre.randomTrajectory();

		eraseTrailC(ogre); // erases ogre's trail as it changes position
		ogre.movement(map, direction);
		updateCharacterPosition(ogre); // updates ogre's position on map

		// generates club's position randomly
		eraseTrailC(club);
		club.movement(map, ogre.getX(), ogre.getY());
		updateCharacterPosition(club);
	}
	
	// useful functions
	public void openLever() {
		if ((lever.getX() - 1 == hero.getX() && lever.getY() == hero.getY())
				|| (lever.getX() + 1 == hero.getX() && lever.getY() == hero.getY())
				|| (lever.getX() == hero.getX() && lever.getY() - 1 == hero.getY())
				|| (lever.getX() == hero.getX() && lever.getY() + 1 == hero.getY()))
		// if hero is near lever (up,down,right,left)
		{
			// lever opens, exits open and both are updated
			lever.open();
			exits.get(0).open();
			updateObjectPosition(exits.get(0));
			exits.get(1).open();
			updateObjectPosition(exits.get(1));
		}
	}

	public void pickKey() {
		if ((key.getX() - 1 == hero.getX() && key.getY() == hero.getY())
				|| (key.getX() + 1 == hero.getX() && key.getY() == hero.getY())
				|| (key.getX() == hero.getX() && key.getY() - 1 == hero.getY())
				|| (key.getX() == hero.getX() && key.getY() + 1 == hero.getY()))
		// if hero is near key (up,down,right,left)
		{
			// key gets picked up, disappears, hero turns to K and all is
			// updated
			key.pickedUp();
			updateObjectPosition(key);
			hero.hasKey();
			updateCharacterPosition(hero);
		}
	}

	public void nearKey() {
		if (((key.getX() - 1 == ogre.getX() && key.getY() == ogre.getY())
				|| (key.getX() + 1 == ogre.getX() && key.getY() == ogre.getY())
				|| (key.getX() == ogre.getX() && key.getY() - 1 == ogre.getY())
				|| (key.getX() == ogre.getX() && key.getY() + 1 == ogre.getY())) && !key.getPickedUp())
		// if ogre is near key (up,down,left,right)
		{
			// ogre turns to $ and updates
			ogre.setSymbol('$');
			updateCharacterPosition(ogre);
		} else {
			// otherwise, ogre goes back to O and updates
			ogre.setSymbol('O');
			updateCharacterPosition(ogre);
		}

		if (((key.getX() - 1 == club.getX() && key.getY() == club.getY())
				|| (key.getX() + 1 == club.getX() && key.getY() == club.getY())
				|| (key.getX() == club.getX() && key.getY() - 1 == club.getY())
				|| (key.getX() == club.getX() && key.getY() + 1 == club.getY())) && !key.getPickedUp())
		// if club is near key (up,down,left,right)
		{
			// club turns to $ and updates
			club.setSymbol('$');
			updateCharacterPosition(club);
		} else {
			// otherwise, club goes back to * and updates
			club.setSymbol('*');
			updateCharacterPosition(club);
		}
	}

	public boolean catchHero(Character c) {
		
		if ((c.getX() - 1 == hero.getX() && c.getY() == hero.getY())
				|| (c.getX() + 1 == hero.getX() && c.getY() == hero.getY())
				|| (c.getX() == hero.getX() && c.getY() - 1 == hero.getY())
				|| (c.getX() == hero.getX() && c.getY() + 1 == hero.getY())) {
			System.out.println("***********");
			System.out.println("*GAME OVER*");
			System.out.println("***********");
			System.out.println("You just got caught!");
			return true;
		}
		
		if (c== guards.get(1))
		{
			if (guards.get(1).isAsleep())
			{
				return false;
			}
		}

		if (c == ogre) {
			if ((club.getX() - 1 == hero.getX() && club.getY() == hero.getY())
					|| (club.getX() + 1 == hero.getX() && club.getY() == hero.getY())
					|| (club.getX() == hero.getX() && club.getY() - 1 == hero.getY())
					|| (club.getX() == hero.getX() && club.getY() + 1 == hero.getY())) {
				System.out.println("***********");
				System.out.println("*GAME OVER*");
				System.out.println("***********");
				System.out.println("You just got caught!");
				return true;
			}

		}
		return false;
	}

	// update map
	public void eraseTrailC(Character c) {
		map.getMap()[c.getX()][c.getY()] = ' ';
	}

	public void eraseTrailO(Object o) {
		map.getMap()[o.getX()][o.getY()] = ' ';
	}

	public void updateCharacterPosition(Character c) {
		map.insertCharacter(c);
	}

	public void updateObjectPosition(Object o) {
		map.insertObject(o);
	}

	// load elements
	public void loadElementsLevel1() {
		// map
		map = new Map(1);

		// lever
		lever = new Lever(8, 7);
		map.insertObject(lever);

		// exits
		exits = new Vector<Exit>();
		exits.add(new Exit(5, 0));
		exits.add(new Exit(6, 0));
		exits.add(new Exit(1, 4));
		exits.add(new Exit(3, 4));
		exits.add(new Exit(3, 2));
		exits.add(new Exit(8, 2));
		exits.add(new Exit(8, 4));

		for (int i = 0; i < exits.size(); i++) {
			map.insertObject(exits.get(i));
		}

		// guard
		guards = new Vector<Guard>();
		guards.add(new Guard(1, 8)); //Rookie
		guards.add(new Guard(1, 8)); //Drunken
		guards.add(new Guard(1, 8)); //Suspicious
		
		for (int i = 0; i < guards.size(); i++) {
			map.insertCharacter(guards.get(i));
		}

		// hero
		hero.setX(1);
		hero.setY(1);
		map.insertCharacter(hero);
	}

	public void loadElementsLevel2() {
		// map
		map = new Map(2);

		// key
		key = new Key(1, 7);
		map.insertObject(key);

		// exits
		exits = new Vector<Exit>();
		exits.add(new Exit(1, 0));

		for (int i = 0; i < exits.size(); i++) {
			map.insertObject(exits.get(i));
		}

		// ogre
		ogre.setX(1);
		ogre.setY(4);
		map.insertCharacter(ogre);

		// ogre's club
		club.setX(0);
		club.setY(0);
		club.movement(map, ogre.getX(), ogre.getY());
		map.insertCharacter(club);

		// hero
		hero.setX(7);
		hero.setY(1);
		map.insertCharacter(hero);
	}
}