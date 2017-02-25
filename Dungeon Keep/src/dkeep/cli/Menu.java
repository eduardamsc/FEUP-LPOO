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

		level1(g);
		//boolean nextLevel;

		/*nextLevel = g.levelOne();

		if (nextLevel)
			g.levelTwo();*/
	}
	
	public static boolean level1(Game g)
	{
		boolean nextLevel = true; // permission to play level 2
		
		g.loadElementsLevel1();
		legendaLevel1();
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
			if (end==true) return false;
			
			if (g.checkVictory()) System.out.println("YOU WIN");
		}
		return nextLevel;
	}
	
	public static void legendaLevel1()
	{
		System.out.println("-----LEVEL 1-----");
		System.out
		.println("*************\n* X - wall  *\n* I - door  *\n* H - guard *\n* k - lever *\n*************\n");
		System.out.println("Play with the keys a,w,s,d.");
	}
	
	public static void legendaLevel2()
	{
		System.out.println("-----LEVEL 2-----");
		System.out.println("******************\n" + "* X - wall       *\n" + "* I - exit door  *\n"
				+ "* O - crazy ogre *\n" + "* k - key        *\n" + "******************\n");
		System.out.println("Play with the keys a,w,s,d.");
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
