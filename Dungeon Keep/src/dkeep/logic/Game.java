package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Game {
	private Hero hero = new Hero();
	private Vector<Guard> guards;
	private ArrayList<Ogre> ogres=new ArrayList<Ogre>();
	private Vector<Club> clubs;
	private Map map;
	private Lever lever;
	private Vector<Exit> exits;
	private Key key;

	private boolean gameOver=false;

	/**
	 * Default constructor for class Game.
	 */
	public Game()
	{
		
	}

	/**
	 * Constructor for class Game with parameter.
	 * @param map Array of chars which defines a map.
	 */
	public Game(Map map)
	{
		this.setMap(map);
		guards = new Vector<Guard>();
		ogres = new ArrayList<Ogre>();
		clubs = new Vector<Club>();
		exits = new Vector<Exit>();
		
		
		for (int i = 0; i < map.getMap().length; i++) {
			for (int j = 0; j < map.getMap()[i].length; j++) {
				if (map.getMap()[i][j]=='H')
				{
					hero = new Hero(i,j);
				} else if (map.getMap()[i][j]=='G')
				{
					guards.add(new GuardRookie(i,j));
				} else if (map.getMap()[i][j]=='O')
				{
					ogres.add(new Ogre(i,j));
				} else if (map.getMap()[i][j]=='I')
				{
					exits.add(new Exit(i,j));
				} else if (map.getMap()[i][j]=='k')
				{
					lever = new Lever(i,j);
				} else if (map.getMap()[i][j]=='K')
				{
					key = new Key(i,j);
				} else if (map.getMap()[i][j]=='*')
				{
					clubs.add(new Club(i,j));
				}
					
			}
		}
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	///////////////////Characters & Objects//////////////////////
	/**
	 * Get for object of type Hero.
	 */
	public Hero getHero() {
		return hero;
	}
	/**
	 * Get for object of type Vector of Guards.
	 * @return object vector of Guards.
	 */
	public Vector<Guard> getGuards() {
		return guards;
	}
	/**
	 * Get for object of type ArrayList of Ogres.
	 * @return object arraylist of Ogres.
	 */
	public ArrayList<Ogre> getOgres() {
		return ogres;
	}
	/**
	 * Get for object of type Vector of Clubs.
	 * @return object vector of Clubs. 
	 */
	public Vector<Club> getClubs() {
		return clubs;
	}
	/**
	 * Get for object of type Vector of Exits.
	 * @return object vector of Exits. 
	 */
	public Vector<Exit> getExits() {
		return exits;
	}
	/**
	 * Get for object of type Lever.
	 * @return object lever.
	 */
	public Lever getLever() {
		return lever;
	}
	/**
	 * Get for object of type Key.
	 * @return object key.
	 */
	public Key getKey() {
		return key;
	}
	/**
	 * Get for object of type Map.
	 */	
	public Map getMap() {
		return map;
	}
	/**
	 * Set for object of type Map.
	 * @return object map.
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	/**
	 * Get for object of type Game Over.
	 * @return True for game over.
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	/**
	 * Set for object of type gameOver.
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	/////////////////////////////////////////LEVELS//////////////////////////////////////
	/**
	 * Deals with all the logic related to level 1.
	 * @param direction Direction received as input from the user to move the Hero.
	 * @return End of game and permission to play level 2.
	 */	
	public boolean logicLevel1(char direction) {
		moveHero(direction);
		moveGuard();
		
		openLever(); // checks to see if lever can be opened and updates

		if (GuardCatchHero()) {
			return true;
		}

		return false;
	}
	/**
	 * Deals with all the logic related to level 2.
	 * @param direction Direction received as input from the user to move the Hero.
	 * @param i Array of ints with amount of consecutive turns that certain Ogres have been stunned.
	 * @return End of game.
	 */	
	public boolean logicLevel2(char direction, int[] i) {
		setGameOver(false);
		heroLevel2(direction);
		ogreLevel2(i);
		checkExitsOpen();
		for (int j = 0; j < ogres.size(); j++) {
			if (!ogres.get(j).getStunned()) {
				if (OgreCatchHero()) { // checks if ogre has caught hero
					setGameOver(true);
					return true;
				}
			}
		}
		return false;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	/**
	 * Checks if Hero is past the exits, winning.
	 * @return True for victory.
	 */	
	public boolean checkVictory() {
		
		for (int i=0; i< exits.size();i++)
		{
			if (exits.get(i).getX() == hero.getX() && exits.get(i).getY() == hero.getY())
			// if hero is on the exit, you win
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * Checks if Exits are opened.
	 * @return True for open Exits.
	 */	
	public boolean checkExitsOpen()
	{
		for (int i = 0; i < exits.size(); i++) {
			if (auxExit(i,-1,0)||auxExit(i,1,0)||auxExit(i,0,-1)||auxExit(i,0,1)) {
				exits.get(i).open();
				updateObjectPosition(exits.get(i));
				return true;
			}
		}
		return false;
	}
	/**
	 * Auxiliary method to see surrounding sides of Exit.
	 */
	public boolean auxExit(int z, int x, int y)
	{
		int a=x+exits.get(z).getX(), b=y+exits.get(z).getY();
		if (a == hero.getX() && b == hero.getY())
			return true;
		else return false;
	}
	
	/////////////////////////////////////////HERO//////////////////////////////////////
	/**
	 * Moves Hero.
	 * @param direction Direction received as input from the user to move the Hero.
	 */	
	public void moveHero(char direction) {
		if(direction=='a' || direction == 'w' || direction == 's' || direction == 'd')
		{
		eraseTrailC(hero); // deletes trail when hero moves
		hero.movement(map, direction);
		updateCharacterPosition(hero); // updates hero's position on the map
		}
	}
	/**
	 * Checks if Hero has caught Key.
	 * @return True for caught Key.
	 */	
	public boolean pickKey() {
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
			return true;
		}
		
		return false;
	}
	/**
	 * Checks if Hero has raised Lever.
	 * @return True for raised Lever.
	 */	
	public boolean openLever() {
		if ((lever.getX() - 1 == hero.getX() && lever.getY() == hero.getY())
				|| (lever.getX() + 1 == hero.getX() && lever.getY() == hero.getY())
				|| (lever.getX() == hero.getX() && lever.getY() - 1 == hero.getY())
				|| (lever.getX() == hero.getX() && lever.getY() + 1 == hero.getY()))
		// if hero is near lever (up,down,right,left)
		{
			// lever opens, exits open and both are updated
			lever.open();
			for (int i=0; i<exits.size();i++)
			{
				exits.get(i).open();
				updateObjectPosition(exits.get(i));
			}
			return true;
		}else return false;
	}
	/**
	 * Checks if Hero has picked up Club.
	 * @return True for Club picked up.
	 */	
	public boolean pickClub() {
		if ((clubs.get(0).getX() - 1 == hero.getX() && clubs.get(0).getY() == hero.getY())
				|| (clubs.get(0).getX() + 1 == hero.getX() && clubs.get(0).getY() == hero.getY())
				|| (clubs.get(0).getX() == hero.getX() && clubs.get(0).getY() - 1 == hero.getY())
				|| (clubs.get(0).getX() == hero.getX() && clubs.get(0).getY() + 1 == hero.getY()))
		// if hero is near clubs.get(0) (up,down,right,left)
		{
			// clubs.get(0) gets picked up, disappears, hero turns to A and all is updated
			hero.armed();
			updateCharacterPosition(hero);
			eraseTrailC(clubs.get(0));
			return true;
		}
		return false;
	}
	/**
	 * Changes Ogres' symbols according to being stunned or not.
	 */	
	public void stunOgre(int[] i)
	{
		for (int j=0; j<ogres.size();j++)
		{
			if ((auxStun(j,-1,0)||auxStun(j,1,0)||auxStun(j,0,-1)||auxStun(j,0,1)) && hero.getArmed())
			{
				ogres.get(j).stun();
				updateCharacterPosition(ogres.get(j));
			} else if (ogres.get(j).getStunned() && i[j] == 2) {
				ogres.get(j).notStunned();
				updateCharacterPosition(ogres.get(j));
			}
		}
	}
	/**
	 * Auxiliary method to see surrounding sides of Hero to stun Ogre.
	 */
	public boolean auxStun(int z, int x, int y)
	{
		int a=x+ogres.get(z).getX(), b=y+ogres.get(z).getY();
		if (a == hero.getX() && b == hero.getY())
			return true;
		else return false;
	}
	/**
	 * Deals with all logic of Hero movement and picking up things in Level 2.
	 */
	public void heroLevel2(char direction)
	{
		moveHero(direction);
		pickKey();
		pickClub();
	}
	/////////////////////////////////////////GUARD//////////////////////////////////////
	/**
	 * Moves Guards.
	 */	
	public void moveGuard()
	{
		for (int j=0; j<guards.size(); j++)
		{
			eraseTrailC(guards.get(j)); // erases guard's trail as it changes position
			guards.get(j).behavior();
			updateCharacterPosition(guards.get(j)); // updates guard's position on map
		}
	}
	/**
	 * Checks if any Guard has caught Hero.
	 * @return True for Hero caught.
	 */	
	public boolean GuardCatchHero() {

		for (int i = 0; i < guards.size(); i++) {
			if ((auxGuard(i,-1,0)||auxGuard(i,1,0)||auxGuard(i,0,1)||auxGuard(i,0,-1))
					&& guards.get(i).getAwake()) {
				this.gameOver = true;
				return true;
			}
		}
		this.gameOver = false;
		return false;
	}
	
	/**
	 * Auxiliary method to see surrounding sides of Guard to catch Hero.
	 */
	public boolean auxGuard(int z, int x, int y)
	{
		int a=x+guards.get(z).getX(), b=y+guards.get(z).getY();
		if (a == hero.getX() && b == hero.getY())
			return true;
		else return false;
	}
	
	/////////////////////////////////////////OGRE//////////////////////////////////////
	/**
	 * Moves Ogres and respective Clubs.
	 */	
	public void moveOgre() {
		
		for (int i = 0; i < ogres.size(); i++) {
			char direction = ogres.get(i).randomTrajectory();
			while (!ogres.get(i).wall(map, direction)) {
				direction = ogres.get(i).randomTrajectory();
			}
			eraseTrailC(ogres.get(i)); // erases ogre's trail as it changes position
			ogres.get(i).movement(map, direction);
			updateCharacterPosition(ogres.get(i)); // updates ogre's position on map

			if (ogres.get(i).getArmed()) {
				eraseTrailC(clubs.get(i + 1));
				clubs.get(i + 1).movement(map, ogres.get(i).getX(), ogres.get(i).getY());
				updateCharacterPosition(clubs.get(i + 1));
			}
		}
	}
	/**
	 * Checks if any Ogre has caught Hero.
	 * @return True for Hero caught.
	 */	
	public boolean OgreAndClubCatchHero() {

		if(!OgreCatchHero()) this.gameOver = false;
		if(!ClubCatchHero()) this.gameOver = false;
		if (OgreCatchHero()||ClubCatchHero())
		{
			return true;
		}else return false;
	}
	
	/**
	 * Checks if any Ogre has caught Hero.
	 * @return True for Hero caught.
	 */	
	public boolean OgreCatchHero() {

		for (int i = 0; i < ogres.size(); i++) {
			if ((ogres.get(i).getX() - 1 == hero.getX() && ogres.get(i).getY() == hero.getY())
					|| (ogres.get(i).getX() + 1 == hero.getX() && ogres.get(i).getY() == hero.getY())
					|| (ogres.get(i).getX() == hero.getX() && ogres.get(i).getY() - 1 == hero.getY())
					|| (ogres.get(i).getX() == hero.getX() && ogres.get(i).getY() + 1 == hero.getY())) {
				this.gameOver = true;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if any Club has hit Hero.
	 * @return True for Hero caught.
	 */	
	public boolean ClubCatchHero() {

		for (int i = 0; i < ogres.size(); i++) {
			if ((clubs.get(i + 1).getX() - 1 == hero.getX() && clubs.get(i + 1).getY() == hero.getY()) 
				|| (clubs.get(i + 1).getX() + 1 == hero.getX() && clubs.get(i + 1).getY() == hero.getY())
					|| (clubs.get(i + 1).getX() == hero.getX() && clubs.get(i + 1).getY() - 1 == hero.getY())
					|| (clubs.get(i + 1).getX() == hero.getX() && clubs.get(i + 1).getY() + 1 == hero.getY())) {
				this.gameOver = true;
				return true;
			}
		}
		this.gameOver = false;
		return false;
	}
	/**
	 * Changes Ogres' symbols according to Key proximity.
	 */	
	public void nearKey() {
		for (int i = 0; i < ogres.size(); i++) {
			if (auxKey(i,-1, 0)||auxKey(i,1, 0)||auxKey(i,0,-1)||auxKey(i,0,1)
					&& !key.getPickedUp()) {
				ogres.get(i).setSymbol('$');
				updateCharacterPosition(ogres.get(i));
			} else if (ogres.get(i).getStunned() == true) {
				ogres.get(i).setSymbol('8');
				updateCharacterPosition(ogres.get(i));
			} else {
				ogres.get(i).setSymbol('O');
				updateCharacterPosition(ogres.get(i));
			}
		}
	}
	/**
	 * Deals with all logic of Ogre movement and being near things in Level 2.
	 */
	public void ogreLevel2(int[] i)
	{
		moveOgre();
		nearKey(); // checks if ogre is near key and updates
		stunOgre(i); // checks if hero stuns ogre
	}
	/**
	 * Auxiliary method to see surrounding sides of Ogre to look for Key.
	 */
	public boolean auxKey(int z, int x, int y)
	{
		int a=x+key.getX(), b=y+key.getY();
		if (a == ogres.get(z).getX() && b == ogres.get(z).getY())
			return true;
		else return false;
	}
	/////////////////////////////////////////UPDATE MAP//////////////////////////////////////
	/**
	 * initializes all objects and inserts them on level 1 map.
	 */		
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
		guards.add(new GuardRookie(1, 8)); //Rookie
		guards.add(new GuardDrunken(1, 8)); //Drunken
		guards.add(new GuardSuspicious(1, 8)); //Suspicious
		
		for (int i = 0; i < guards.size(); i++) {
			map.insertCharacter(guards.get(i));
		}

		// hero
		hero.setX(1);
		hero.setY(1);
		map.insertCharacter(hero);
	}
	/**
	 * initializes all objects and inserts them on level 2 map.
	 */	
	public void loadElementsLevel2() {
		map = new Map(2);
		key = new Key(1, 7);
		map.insertObject(key);
		exits = new Vector<Exit>();
		exits.add(new Exit(1, 0));
		for (int i = 0; i < exits.size(); i++) {
			map.insertObject(exits.get(i));
		}
		loadOgres();
		loadClubs();
		hero.setX(7);
		hero.setY(1);
		map.insertCharacter(hero);
	}
	/**
	 * initializes all ogres and clubs and inserts them on level 2 map.
	 */	
	public void loadOgres() {
		Random n = new Random();
		int value = n.nextInt(5);
		while(value==0)
		{
			value = n.nextInt(5);
		}
		for (int i=0; i<value;i++)
		{
			ogres.add(new Ogre(1,4));
			map.insertCharacter(ogres.get(i));
		}
	}
	/**
	 * initializes all clubs and inserts them on level 2 map.
	 */	
	public void loadClubs() {
		clubs = new Vector<Club>();
		clubs.add(new Club(7,5)); //hero's club
		for (int i = 1; i < ogres.size() + 1; i++) {
				clubs.add(new Club(7, 3)); // ogre's club
				clubs.get(i).movement(map, ogres.get(i - 1).getX(), ogres.get(i - 1).getY());
		}
		for (int i = 0; i < clubs.size(); i++) {
			map.insertCharacter(clubs.get(i));
		}
	}

	/**
	 * Deletes Characters trail.
	 */	
	public void eraseTrailC(Character c) {
		map.getMap()[c.getX()][c.getY()] = ' ';
	}
	/**
	 * Updates Characters' position and appearance on map.
	 */	
	public void updateCharacterPosition(Character c) {
		map.insertCharacter(c);
	}
	/**
	 * Updates Objects' position and appearance on map.
	 */	
	public void updateObjectPosition(Object o) {
		map.insertObject(o);
	}

	/**
	 * initializes all objects and inserts them on editable map.
	 */	
	public void loadElementsEditableLevel() {
		map = new Map(2);
		key = new Key();
		exits = new Vector<Exit>();
		hero = new Hero();
	}
}