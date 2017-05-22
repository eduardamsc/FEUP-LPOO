package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.*;
import jdk.internal.dynalink.support.Guards;

public class TestDungeonGameLogic {

	char[][] map =  {{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};
	
	char[][] map2 =  {{'X','X','X','X','X'},
			{'X','H',' ','O','X'},
			{'I',' ',' ',' ','X'},
			{'I','K',' ','*','X'},
			{'X','X','X','X','X'}};
	
	@Test
	public void testMoveHeroToFreeCell()
	{
		Map map = new Map(this.map);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('s');
		assertEquals(2,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
	}
	
	@Test
	public void testMoveHeroToWall()
	{
		Map map = new Map(this.map);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('a');
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
	}
	
	@Test
	public void testMoveHeroToGuard()
	{
		Map map = new Map(this.map);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('d');
		assertEquals(1,game.getHero().getX());
		assertEquals(2,game.getHero().getY());
		assertEquals(true, game.GuardCatchHero());
		assertEquals(true, game.getGameOver());
	}
	
	@Test
	public void testMoveHeroToClosedExit()
	{
		Map map = new Map(this.map);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('s');
		game.moveHero('a');
		assertEquals(2,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
	}

	@Test
	public void testMoveHeroToLever()
	{
		Map map = new Map(this.map);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('s');
		assertEquals(true, game.openLever());
		for (int i=0;i<game.getExits().size();i++)
		{
			assertEquals(true, game.getExits().get(i).getOpen());
		}
	}

	@Test
	public void testMoveHeroVictory()
	{
		Map map = new Map(this.map);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('s');
		assertEquals(true, game.openLever());
		game.moveHero('a');
		assertEquals(true, game.checkVictory());
	}

	@Test
	public void testMoveHeroToOgre()
	{
		Map map = new Map(this.map2);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('d');
		assertEquals(1,game.getHero().getX());
		assertEquals(2,game.getHero().getY());
		assertEquals(true, game.OgreCatchHero());
		assertEquals(true, game.getGameOver());
	}

	@Test
	public void testMoveHeroToKey()
	{
		Map map = new Map(this.map2);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('s');
		assertEquals(true, game.pickKey());
		assertEquals('K',game.getHero().getSymbol());
	}

	@Test
	public void testMoveHeroToClosedExit2()
	{
		Map map = new Map(this.map2);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('s');
		game.moveHero('a');
		assertEquals(2,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
	}

	@Test
	public void testMoveHeroToDoors2()
	{
		Map map = new Map(this.map2);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('s');
		assertEquals(true, game.pickKey());
		game.moveHero('a');
		assertEquals(true,game.checkExitsOpen());
	}

	@Test
	public void testMoveHeroVictory2()
	{
		Map map = new Map(this.map2);
		Game game = new Game(map);
		assertEquals(1,game.getHero().getX());
		assertEquals(1,game.getHero().getY());
		game.moveHero('s');
		assertEquals(true, game.pickKey());
		assertEquals(true, game.checkExitsOpen());
		game.moveHero('a');
		assertEquals(true, game.checkVictory());
	}
	
	@Test(timeout=1000)
	public void testSomeRandomBehaviour()
	{
		Map map = new Map(this.map2);
		Game game = new Game(map);
		game.getOgres().get(0).setArmed(false);
		boolean moveUp=false, moveDown=false, moveLeft=false, moveRight=false;
		int x,y;
		while(!moveUp||!moveDown||!moveLeft||!moveRight)
		{
			x=game.getOgres().get(0).getX();
			y=game.getOgres().get(0).getY();
			game.moveOgre();
			if (game.getOgres().get(0).getX()==x-1 && game.getOgres().get(0).getY()==y)
			{
				moveUp=true;
			} else if (game.getOgres().get(0).getX()==x+1 && game.getOgres().get(0).getY()==y)
			{
				moveDown=true;
			} else if (game.getOgres().get(0).getX()==x && game.getOgres().get(0).getY()==y-1)
			{
				moveLeft=true;
			} else if (game.getOgres().get(0).getX()==x && game.getOgres().get(0).getY()==y+1)
			{
				moveRight=true;
			} else fail("Error!");
		}
	}

	//extra
	//LEVEL1
	@Test
	public void map()
	{
		Map map = new Map(1);
		assertEquals(map.firstMap(),map.getMap());
		
	}
	
	@Test
	public void mapSet()
	{
		Map map = new Map(1);
		map.setMap(new char[][] {{'x','H'},{'x','x'}});
		Game g = new Game(map);
		assertEquals(g.getMap(), map);
		assertEquals(0, g.getHero().getX());
		assertEquals(1, g.getHero().getY());
	}
	
	@Test
	public void load()
	{
		Game g = new Game();
		g.loadElementsLevel1();
		assertEquals(8,g.getLever().getX());
		assertEquals(7,g.getLever().getY());
	}
	
	@Test
	public void logic1()
	{
		Game g = new Game();
		g.loadElementsLevel1();
		assertEquals(false,g.logicLevel1('d'));
	}
	
	@Test
	public void testMoveHeroTog()
	{
		Map map = new Map(this.map);
		Game game = new Game(map);
		game.getGuards().get(0).asleep();
		game.updateCharacterPosition(game.getGuards().get(0));
		assertEquals(false,game.getGuards().get(0).getAwake());
		assertEquals('g',game.getGuards().get(0).getSymbol());
		game.moveHero('d');
		assertEquals(false, game.GuardCatchHero());
	}
	
	@Test
	public void testMoveHeroToDrunken()
	{
		Map map = new Map(this.map);
		Game game = new Game(map);
		game.eraseTrailC(game.getGuards().get(0));
		game.getGuards().clear();
		Guard s = new GuardDrunken(1,3);
		map.insertCharacter(s);
		game.getGuards().add(s);
		game.updateCharacterPosition(game.getGuards().get(0));
		assertEquals(1,game.getGuards().get(0).getX());
		assertEquals(3,game.getGuards().get(0).getY());
		assertEquals('G',game.getGuards().get(0).getSymbol());
	}
	
	//LEVEL2
	@Test
	public void map2()
	{
		Map map = new Map(2);
		assertEquals(map.secondMap(),map.getMap());
		
	}
	
	@Test
	public void logic2()
	{
		Game g = new Game();
		g.loadElementsLevel2();
		int[] i={0};
		assertEquals(false,g.logicLevel2('d',i));
	}
	
	@Test
	public void test8()
	{
		Map map = new Map(this.map2);
		Game game = new Game(map);
		game.getOgres().get(0).stun();
		assertEquals('8', game.getOgres().get(0).getSymbol());
	}
	
	@Test
	public void testMoveHeroTo$()
	{
		Map map = new Map(this.map2);
		Game game = new Game(map);
		game.getOgres().get(0).closeToKey();
		game.moveHero('d');
		assertEquals(true, game.OgreCatchHero());
	}
	
	@Test
	public void testMoveHeroToClub()
	{
		Map map = new Map(this.map2);
		Game game = new Game(map);
		game.moveHero('s');
		game.moveHero('d');
		game.moveHero('s');
		assertEquals(true,game.pickClub());
		assertEquals(true,game.getHero().getArmed());
	}
	
	@Test
	public void testOgreNearKey()
	{
		Game game = new Game();
		game.loadElementsLevel2();
		for(int i=0; i<game.getOgres().size();i++)
		{
			game.eraseTrailC(game.getOgres().get(i));
			game.updateCharacterPosition(game.getOgres().get(i));
		}
		game.getOgres().clear();
		game.getOgres().add(new Ogre(1,6));
		game.updateCharacterPosition(game.getOgres().get(0));
		
		game.nearKey();
		assertEquals('$',game.getOgres().get(0).getSymbol());
	}
	
	@Test(timeout=1000)
	public void testClubRandomBehaviour()
	{
		Game game = new Game();
		game.loadElementsLevel2();
		boolean moveUp=false, moveDown=false, moveLeft=false, moveRight=false;
		int x,y;
		while(!moveUp||!moveDown||!moveLeft||!moveRight)
		{
			game.getOgres().get(0).setX(2);
			game.getOgres().get(0).setY(4);
			x=game.getOgres().get(0).getX();
			y=game.getOgres().get(0).getY();
			game.eraseTrailC(game.getClubs().get(1));
			game.getClubs().get(1).movement(game.getMap(), x, y);
			game.updateCharacterPosition(game.getClubs().get(1));
			if (game.getClubs().get(1).getX()==x-1 && game.getClubs().get(1).getY()==y)
			{
				moveUp=true;
			} else if (game.getClubs().get(1).getX()==x+1 && game.getClubs().get(1).getY()==y)
			{
				moveDown=true;
			} else if (game.getClubs().get(1).getX()==x && game.getClubs().get(1).getY()==y-1)
			{
				moveLeft=true;
			} else if (game.getClubs().get(1).getX()==x && game.getClubs().get(1).getY()==y+1)
			{
				moveRight=true;
			} else fail("Error!");
		}
	}
}
