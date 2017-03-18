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

	public Game()
	{
		
	}
	
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
					clubs.add(new Club()); //hero's club
				} else if (map.getMap()[i][j]=='G')
				{
					guards.add(new GuardRookie(i,j));
				} else if (map.getMap()[i][j]=='O')
				{
					ogres.add(new Ogre(i,j));
					clubs.add(new Club()); //ogre's club
					clubs.get(i).movement(map, ogres.get(i-1).getX(), ogres.get(i-1).getY());
				} else if (map.getMap()[i][j]=='I')
				{
					exits.add(new Exit(i,j));
				} else if (map.getMap()[i][j]=='k')
				{
					lever = new Lever(i,j);
				} else if (map.getMap()[i][j]=='K')
				{
					key = new Key(i,j);
				}
					
			}
		}
		
		for (int i = 0; i < clubs.size(); i++) {
			map.insertCharacter(clubs.get(i));
		}
	}

	/////////////////////////////////////////GETS AND SETS//////////////////////////////////////
	///////////////////Characters & Objects//////////////////////
	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	public Vector<Guard> getGuards() {
		return guards;
	}

	public void setGuards(Vector<Guard> guards) {
		this.guards = guards;
	}
	
	public ArrayList<Ogre> getOgres() {
		return ogres;
	}

	public void setOgres(ArrayList<Ogre> ogres) {
		this.ogres = ogres;
	}
	
	public Vector<Exit> getExits() {
		return exits;
	}

	public void setExits(Vector<Exit> exits) {
		this.exits = exits;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	/////////////////////////////////////////
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
		
	public boolean getGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	/////////////////////////////////////////LEVELS//////////////////////////////////////
	/**
	 * @brief Trata de toda a lógica inerente ao nível 1.
	 * @param direction Direção recebida como input do utilizador para mover o Heroi.
	 * @return Fim do jogo com permissão para jogar o nível 2.
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
	 * @brief Trata de toda a lógica inerente ao nível 2.
	 * @param direction Direção recebida como input do utilizador para mover o Heroi.
	 * @param i Array de inteiros com quantidades de jogadas seguidas que cada ogre esteve stunned.
	 * @return Fim do jogo.
	 */	
	public boolean logicLevel2(char direction, int[] i) {
		setGameOver(false);
		moveHero(direction);
		pickClub(); // checks if hero has picked up club and updates
		moveOgre();

		pickKey(); // checks if hero has picked up key and updates
		nearKey(); // checks if ogre is near key and updates
		
		stunOgre(i); //checks if hero stuns ogre

		checkExitsOpen();
		
		for (int j=0; j<ogres.size();j++)
		{
			if (!ogres.get(j).getStunned())
			{
				if (OgreCatchHero()) { // checks if guard has caught hero
					setGameOver(true);
					return true;
				}
			}
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////
	/**
	 * @brief Verifica se utilizador passou ou não as portas, ganhando.
	 * @return True p/ vitória.
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
	 * @brief Verifica se portas estão ou não abertas.
	 * @return True p/ portas abertas.
	 */	
	public boolean checkExitsOpen()
	{
		for (int i = 0; i < exits.size(); i++) {
			if (exits.get(i).getX() == hero.getX() && exits.get(i).getY() + 1 == hero.getY() && key.getPickedUp()) {
				// if hero is close to exit and has the key
				// the exit turns to stairs
				exits.get(i).open();
				updateObjectPosition(exits.get(i));
				return true;
			}
		}
		return false;
	}

	/////////////////////////////////////////HERO//////////////////////////////////////
	/**
	 * @brief Move o Heroi, apagando o seu rasto e atualizando a sua posição no mapa.
	 * @param direction Direção recebida como input do utilizador para mover o Heroi.
	 */	
	public void moveHero(char direction) {
		eraseTrailC(hero); // deletes trail when hero moves
		hero.movement(map, direction);
		updateCharacterPosition(hero); // updates hero's position on the map
	}
	/**
	 * @brief Verifica se Heroi apanhou a chave.
	 * @return True p/ chave apanhada.
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
	 * @brief Verifica se Heroi levantou a alavanca.
	 * @return True p/ alavanca levantada.
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
	 * @brief Verifica se Heroi pegou num bastão.
	 * @return True p/ Heroi armado.
	 */	
	public void pickClub() {
		if ((clubs.get(0).getX() - 1 == hero.getX() && clubs.get(0).getY() == hero.getY())
				|| (clubs.get(0).getX() + 1 == hero.getX() && clubs.get(0).getY() == hero.getY())
				|| (clubs.get(0).getX() == hero.getX() && clubs.get(0).getY() - 1 == hero.getY())
				|| (clubs.get(0).getX() == hero.getX() && clubs.get(0).getY() + 1 == hero.getY()))
		// if hero is near clubs.get(1) (up,down,right,left)
		{
			// clubs.get(0) gets picked up, disappears, hero turns to A and all is updated
			hero.armed();
			updateCharacterPosition(hero);
			eraseTrailC(clubs.get(0));
		}
	}
	/**
	 * @brief Altera símbolo dos Ogres conforme são atordoadas pelo Heroi.
	 */	
	public void stunOgre(int[] i)
	{
		for (int j=0; j<ogres.size();j++)
		{

			if (((ogres.get(j).getX() - 1 == hero.getX() && ogres.get(j).getY() == hero.getY())
					|| (ogres.get(j).getX() + 1 == hero.getX() && ogres.get(j).getY() == hero.getY())
					|| (ogres.get(j).getX() == hero.getX() && ogres.get(j).getY() - 1 == hero.getY())
					|| (ogres.get(j).getX() == hero.getX() && ogres.get(j).getY() + 1 == hero.getY())) && hero.getArmed())
			// if ogre is near hero's club (up,down,left,right) and hero is
			// armed with it
			{
				// ogre turns to 8 and updates
				ogres.get(j).stun();
				updateCharacterPosition(ogres.get(j));
			} else if (ogres.get(j).getStunned() && i[j] == 2) {
				// otherwise, ogre goes back to O and updates
				ogres.get(j).notStunned();
				updateCharacterPosition(ogres.get(j));
			}
		}
	}
	
	/////////////////////////////////////////GUARD//////////////////////////////////////
	/**
	 * @brief Move os Guardas, apagando o seu rasto e atualizando a sua posição no mapa.
	 */	
	public void moveGuard()
	{
		for (int j=0; j<guards.size(); j++)
		{
			eraseTrailC(guards.get(j)); // erases guard's trail as it changes position
			guards.get(j).behaviour();
			updateCharacterPosition(guards.get(j)); // updates guard's position on map
		}
	}
	/**
	 * @brief Verifica se algum Guarda apanhou o Heroi.
	 * @return True p/ Heroi apanhado.
	 */	
	public boolean GuardCatchHero() {

		for (int i = 0; i < guards.size(); i++) {
			if ((guards.get(i).getX() - 1 == hero.getX() && guards.get(i).getY() == hero.getY())
					|| (guards.get(i).getX() + 1 == hero.getX() && guards.get(i).getY() == hero.getY())
					|| (guards.get(i).getX() == hero.getX() && guards.get(i).getY() - 1 == hero.getY())
					|| (guards.get(i).getX() == hero.getX() && guards.get(i).getY() + 1 == hero.getY())
					&& guards.get(i).getAwake()) {
				this.gameOver = true;
				return true;
			}
		}
		this.gameOver = false;
		return false;
	}
	
	/////////////////////////////////////////OGRE//////////////////////////////////////
	/**
	 * @brief Move os Ogres e respetivos Bastões, apagando o seu rasto e atualizando a sua posição no mapa.
	 * @param direction Direção recebida como input do utilizador para mover o Heroi.
	 */	
	public void moveOgre() {
		
		for (int i = 0; i < ogres.size(); i++) {
			// generates ogre's trajectory randomly
			char direction = ogres.get(i).randomTrajectory();

			while (!ogres.get(i).wall(map, direction)) {
				direction = ogres.get(i).randomTrajectory();
			}
			
			eraseTrailC(ogres.get(i)); // erases ogre's trail as it changes position
			ogres.get(i).movement(map, direction);
			updateCharacterPosition(ogres.get(i)); // updates ogre's position on map

			// generates club's position randomly
			eraseTrailC(clubs.get(i+1));
			clubs.get(i+1).movement(map, ogres.get(i).getX(), ogres.get(i).getY());
			updateCharacterPosition(clubs.get(i+1));
		}
	}
	/**
	 * @brief Verifica se algum Ogre apanhou o Heroi.
	 * @return True p/ Heroi apanhado.
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
	 * @brief Altera símbolo dos Ogres e seus bastões conforme se aproximam da chave.
	 */	
	public void nearKey() {
		
		for (int i = 0; i < ogres.size(); i++) {
			if (((key.getX() - 1 == ogres.get(i).getX() && key.getY() == ogres.get(i).getY())
					|| (key.getX() + 1 == ogres.get(i).getX() && key.getY() == ogres.get(i).getY())
					|| (key.getX() == ogres.get(i).getX() && key.getY() - 1 == ogres.get(i).getY())
					|| (key.getX() == ogres.get(i).getX() && key.getY() + 1 == ogres.get(i).getY())) && !key.getPickedUp())
			// if ogre is near key (up,down,left,right)
			{
				// ogre turns to $ and updates
				ogres.get(i).setSymbol('$');
				updateCharacterPosition(ogres.get(i));
			} else {
				if (ogres.get(i).getStunned() == true) {
					ogres.get(i).setSymbol('8');
					updateCharacterPosition(ogres.get(i));
				} else {
					ogres.get(i).setSymbol('O');
					updateCharacterPosition(ogres.get(i));
				}
			}

			for (int j = 1; j < clubs.size(); j++) {
				if (((key.getX() - 1 == clubs.get(j).getX() && key.getY() == clubs.get(j).getY())
						|| (key.getX() + 1 == clubs.get(j).getX() && key.getY() == clubs.get(j).getY())
						|| (key.getX() == clubs.get(j).getX() && key.getY() - 1 == clubs.get(j).getY())
						|| (key.getX() == clubs.get(j).getX() && key.getY() + 1 == clubs.get(j).getY()))
						&& !key.getPickedUp())
				// if club is near key (up,down,left,right)
				{
					// club turns to $ and updates
					clubs.get(j).setSymbol('$');
					updateCharacterPosition(clubs.get(j));
				} else {
					clubs.get(j).setSymbol('*');
					updateCharacterPosition(clubs.get(j));
				}
			}
		}
	}

	/////////////////////////////////////////UPDATE MAP//////////////////////////////////////
	/**
	 * @brief Inicializa todos os objetos e insere-os no mapa para o nível 1.
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
	 * @brief Inicializa todos os objetos e insere-os no mapa para o nível 2.
	 */	
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
		Random n = new Random();
		int value = n.nextInt(5);
		for (int i=0; i<value;i++)
		{
			ogres.add(new Ogre(1,4));
			map.insertCharacter(ogres.get(i));
		}
		
		clubs = new Vector<Club>();
		clubs.add(new Club(7,5)); //hero's club
		
		for (int i=1; i<ogres.size()+1;i++)
		{
			clubs.add(new Club(7,3)); //ogre's club
			clubs.get(i).movement(map, ogres.get(i-1).getX(), ogres.get(i-1).getY());
		}
		
		
		for (int i = 0; i < clubs.size(); i++) {
			map.insertCharacter(clubs.get(i));
		}

		// hero
		hero.setX(7);
		hero.setY(1);
		map.insertCharacter(hero);
	}
	/**
	 * @brief Apaga rasto das personagens.
	 */	
	public void eraseTrailC(Character c) {
		map.getMap()[c.getX()][c.getY()] = ' ';
	}
	/**
	 * @brief Atualiza posição/aparência das personagens no mapa.
	 */	
	public void updateCharacterPosition(Character c) {
		map.insertCharacter(c);
	}
	/**
	 * @brief Atualiza posição/aparência dos objetos no mapa.
	 */	
	public void updateObjectPosition(Object o) {
		map.insertObject(o);
	}
}