package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.Game;

public class ImgPanel extends JPanel implements KeyListener{
	private BufferedImage start;
	private BufferedImage wall;
	private BufferedImage exitClosed;
	private BufferedImage exitOpened;
	private BufferedImage hero;
	private BufferedImage guard;
	private BufferedImage ogre;
	private BufferedImage leverClosed;
	private BufferedImage leverOpened;
	private BufferedImage key;
	private BufferedImage clubUp;
	private BufferedImage clubDown;
	private BufferedImage clubLeft;
	private BufferedImage clubRight;
	
	private char[][] map = new char[][] { {'D'}};
	private Game game;
	private Game game2;
	private int level;
	private int[] j;
	
	public ImgPanel(){

		try {
			this.start = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/cover.png" ) );
			this.wall = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/wall.png" ) );
			this.exitClosed = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/exitClosed.png" ) );
			this.exitOpened = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/exitOpened.png" ) );
			this.hero = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/ogreFront.png" ) );
			this.guard = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/19GuardTras.png" ) );
			this.ogre = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/OgreFront.png" ) );
			this.leverClosed = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/leverClosed.png" ) );
			this.leverOpened = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/leverOpened.png" ) );
			this.key = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/key.png" ) );
			this.clubUp = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/swordUp.png" ) );
			this.clubDown = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/swordDown.png" ) );
			this.clubLeft = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/swordLeft.png" ) );
			this.clubRight = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/swordRight.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}	
	}
	
	public void updateMap(char[][] map, Game g, Game g2, int level, int[] j)
	{
		this.map=map;
		this.game=g;
		this.game2=g;
		this.level = level;
		this.j=j;
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if(this.map[j][i]=='D')
				{
					g.drawImage(this.start, this.start.getWidth() * i, this.start.getWidth() * j, null);
				}
				paintMap(g, i, j);
				paintCharacters(g, i, j);
				paintObjects(g, i, j);
			}
		}
	}
	
	public void paintMap(Graphics g, int i, int j) {
		if(this.map[j][i]=='X')
		{
			g.drawImage(this.wall, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}
		if(this.map[j][i]=='I')
		{
			g.drawImage(this.exitClosed,this.wall.getWidth() * i, this.wall.getHeight() * j,null);
		}
		if(this.map[j][i]=='S')
		{
			g.drawImage(this.exitOpened, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}
	}

	public void paintCharacters(Graphics g, int i, int j) {
		if (this.map[j][i] == 'H') {
			g.drawImage(this.hero, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}

		if (this.map[j][i] == 'G') {
			g.drawImage(this.guard, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}

		if (this.map[j][i] == 'O') {
			g.drawImage(this.ogre, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}
	}
	
	public void paintObjects(Graphics g, int i, int j) {
		if (this.level == 1) {
			if (this.map[j][i] == 'k' && !this.game.getLever().getOpen()) {
				g.drawImage(this.leverClosed, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
			} else if (this.map[j][i] == 'k' && this.game.getLever().getOpen()) {
				g.drawImage(this.leverOpened, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
			}
		}
		if (this.level == 2) {
			if (this.map[j][i] == 'k') {
				g.drawImage(this.key, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
			}
			
			if (this.map[j][i] == '*') {
				for (int z=0;z<game2.getOgres().size();z++)
				{
					if (game2.getOgres().get(z).getX()-1==j && game2.getOgres().get(z).getY()==i)
					{
						g.drawImage(this.clubUp, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
					} else if (game.getOgres().get(z).getX()+1==j && game.getOgres().get(z).getY()==i)
					{
						g.drawImage(this.clubDown, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
					} else if (game.getOgres().get(z).getX()==j && game.getOgres().get(z).getY()-1==i)
					{
						g.drawImage(this.clubLeft, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
					} else if (game.getOgres().get(z).getX()==j && game.getOgres().get(z).getY()+1==i)
					{
						g.drawImage(this.clubRight, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
					}
				}
				if (game.getHero().getX()-1==j && game.getHero().getY()==i)
				{
					g.drawImage(this.clubUp, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				} else if (game.getHero().getX()+1==j && game.getHero().getY()==i)
				{
					g.drawImage(this.clubDown, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				} else if (game.getHero().getX()==j && game.getHero().getY()-1==i)
				{
					g.drawImage(this.clubLeft, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				} else if (game.getHero().getX()+1==j && game.getHero().getY()+1==i)
				{
					g.drawImage(this.clubRight, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key)
		{
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_LEFT:
			break;
		case KeyEvent.VK_RIGHT:
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
