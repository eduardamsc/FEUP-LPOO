package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;

public class TestDungeonGameLogic {

	char[][] map =  {{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};
	
	char[][] map2 =  {{'X','X','X','X','X'},
			{'X','H',' ','O','X'},
			{'I',' ',' ',' ','X'},
			{'I','K',' ',' ','X'},
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
}
