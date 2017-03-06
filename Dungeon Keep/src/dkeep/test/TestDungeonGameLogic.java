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
		game.moveHero('a');
		assertEquals(true, game.checkVictoryLevel1());
	}
}
