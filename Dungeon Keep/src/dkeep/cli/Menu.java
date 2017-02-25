package dkeep.cli;

import java.util.Scanner;

import dkeep.logic.Game;
import dkeep.logic.Map;

public class Menu {
	public static void main(String[] args) {

		System.out.println("*******");
		System.out.println("*START*");
		System.out.println("*******");

		Game g = new Game();

		boolean nextLevel=true;
		nextLevel = level1(g);
		if (nextLevel) 
			level2(g);
	}
	
	public static boolean level1(Game g)
	{
		boolean nextLevel = true; // permission to play level 2
		
		System.out.println("-----LEVEL 1-----");
		System.out
		.println("*************\n* X - wall  *\n* I - door  *\n* H - guard *\n* k - lever *\n*************\n");
		System.out.println("Play with the keys a,w,s,d.");
		
		g.loadElementsLevel1();
		printMap(g.getMap());
		
		Scanner s = new Scanner(System.in);
		boolean end = false;
		
		int i=-1;
		while (end != true) {
			char direction = s.next().charAt(0);
			i++;
			if (i > 23) {
				i = 0;
			}
			end = g.logicLevel1(direction, i);
			printMap(g.getMap());
			if (end) nextLevel=false;
			
			if (g.checkVictoryLevel1()) {
				nextLevel = true;
				System.out.println("YOU WIN");
				break;
			}
		}
		return nextLevel;
	}
	
	public static void level2(Game g)
	{	
		System.out.println("-----LEVEL 2-----");
		System.out.println("******************\n" + "* X - wall       *\n" + "* I - exit door  *\n"
				+ "* O - crazy ogre *\n" + "* k - key        *\n" + "******************\n");
		System.out.println("Play with the keys a,w,s,d.");
		
		g.loadElementsLevel2();
		printMap(g.getMap());
		
		Scanner s = new Scanner(System.in);
		boolean end = false;
		
		while (end != true) {
			char direction = s.next().charAt(0);
			
			end = g.logicLevel2(direction);
			printMap(g.getMap());
			
			if (g.checkVictoryLevel2()) {
				System.out.println("YOU WIN");
			}
		}
	}
	
	public static void printMap(Map map)
	{
		for (int i = 0; i < map.getMap().length; i++) {
			for (int j = 0; j < map.getMap()[i].length; j++) {
				System.out.print(map.getMap()[i][j] + " ");
			}
			System.out.println();
		}
	}
}
