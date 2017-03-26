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
import dkeep.logic.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class ImgPanel.
 */
public class ImgPanel extends JPanel{
	
	/** The start. */
	private BufferedImage start;
	
	/** The wall. */
	private BufferedImage wall;
	
	/** The nothing. */
	private BufferedImage nothing;
	
	/** The exit closed. */
	private BufferedImage exitClosed;
	
	/** The exit opened. */
	private BufferedImage exitOpened;
	
	/** The hero. */
	private BufferedImage hero;
	
	/** The hero armed. */
	private BufferedImage heroArmed;
	
	/** The hero key. */
	private BufferedImage heroKey;
	
	/** The hero dead. */
	private BufferedImage heroDead;
	
	/** The guard. */
	private BufferedImage guard;
	
	/** The guard sleeping. */
	private BufferedImage guardSleeping;
	
	/** The ogre. */
	private BufferedImage ogre;
	
	/** The ogre stunned. */
	private BufferedImage ogreStunned;
	
	/** The lever closed. */
	private BufferedImage leverClosed;
	
	/** The lever opened. */
	private BufferedImage leverOpened;
	
	/** The key. */
	private BufferedImage key;
	
	/** The club up. */
	private BufferedImage clubUp;
	
	/** The club down. */
	private BufferedImage clubDown;
	
	/** The club left. */
	private BufferedImage clubLeft;
	
	/** The club right. */
	private BufferedImage clubRight;
	
	/** The map. */
	private char[][] map = new char[][] { {'D'}};
	
	/** The game. */
	private Game game;
	
	/** The level. */
	private int level;
	
	/** The j. */
	private int[] j;
	
	
	/**
	 * Instantiates a new img panel.
	 */
	public ImgPanel(){
		try {
			this.start = ImageIO.read( new File( "images/cover.png" ) );
			this.wall = ImageIO.read( new File( "images/wall.png" ) );
			this.nothing = ImageIO.read( new File( "images/11floor.png" ) );
			this.exitClosed = ImageIO.read( new File( "images/exitClosed.png" ) );
			this.exitOpened = ImageIO.read( new File( "images/exitOpened.png" ) );
			this.hero = ImageIO.read( new File( "images/heroFront.png" ) );
			this.heroArmed = ImageIO.read( new File( "images/heroArmedFront.png" ) );
			this.heroKey = ImageIO.read( new File( "images/heroKeyFront.png" ) );
			this.heroDead = ImageIO.read( new File( "images/heroDeadFront.png" ) );
			this.guard = ImageIO.read( new File( "images/guardFront.png" ) );
			this.guardSleeping = ImageIO.read( new File( "images/guardSleepingFront.png" ) );
			this.ogre = ImageIO.read( new File( "images/OgreFront.png" ) );
			this.ogreStunned = ImageIO.read( new File( "images/ogreStunnedFront.png" ) );
			this.leverClosed = ImageIO.read( new File( "images/leverClosed.png" ) );
			this.leverOpened = ImageIO.read( new File( "images/leverOpened.png" ) );
			this.key = ImageIO.read( new File( "images/key.png" ) );
			this.clubUp = ImageIO.read( new File( "images/swordUp.png" ) );
			this.clubDown = ImageIO.read( new File( "images/swordDown.png" ) );
			this.clubLeft = ImageIO.read( new File( "images/swordLeft.png" ) );
			this.clubRight = ImageIO.read( new File( "images/swordRight.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}	
	}
	
	/**
	 * Update map.
	 *
	 * @param map
	 *            the map
	 * @param g
	 *            the g
	 * @param level
	 *            the level
	 * @param j
	 *            the j
	 */
	public void updateMap(char[][] map, Game g, int level, int[] j)
	{
		this.map=map;
		this.game=g;
		this.level = level;
		this.j=j;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if(this.map[j][i]=='D')
				{
					//g.drawImage(this.start, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				}
				paintMap(g, i, j);
				paintCharacters(g, i, j);
				paintObjects(g, i, j);
			}
		}
	}
	
	/**
	 * Paint map.
	 *
	 * @param g
	 *            the g
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
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

	/**
	 * Paint characters.
	 *
	 * @param g
	 *            the g
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
	public void paintCharacters(Graphics g, int i, int j) {
		paintHeros(g, i, j);
		paintGuards(g, i, j);
		paintOgres(g, i, j);
	}
	
	/**
	 * Paint heros.
	 *
	 * @param g
	 *            the g
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
	public void paintHeros(Graphics g, int i, int j) {
		if (this.map[j][i] == 'H') {
			g.drawImage(this.hero, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		} else if (this.map[j][i] == 'A') {
			g.drawImage(this.heroArmed, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		} else if (this.map[j][i] == 'K') {
			g.drawImage(this.heroKey, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}
		if (level == 1) {
			if ((this.map[j][i] == 'H') && game.GuardCatchHero())
				g.drawImage(this.heroDead, this.wall.getWidth() * i, this.wall.getHeight() * j, null);

		} else if (level == 2) {
			if ((this.map[j][i] == 'H'||this.map[j][i] == 'A'||this.map[j][i] == 'K') && game.OgreCatchHero())
				g.drawImage(this.heroDead, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}
	}

	/**
	 * Paint ogres.
	 *
	 * @param g
	 *            the g
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
	public void paintOgres(Graphics g, int i, int j)
	{
		if (this.map[j][i] == 'O') {
			g.drawImage(this.ogre, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		} else if (this.map[j][i] == '8') {
			g.drawImage(this.ogreStunned, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		} else if (this.map[j][i] == '$') {
			g.drawImage(this.ogre, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}
	}
	
	/**
	 * Paint guards.
	 *
	 * @param g
	 *            the g
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
	public void paintGuards(Graphics g, int i, int j)
	{
		if (this.map[j][i] == 'G') {
			g.drawImage(this.guard, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		} else if (this.map[j][i] == 'g') {
			g.drawImage(this.guardSleeping, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}
	}
	
	/**
	 * Paint objects.
	 *
	 * @param g
	 *            the g
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
	public void paintObjects(Graphics g, int i, int j) {
		switch(level)
		{
		case 1:
			paintLevers(g,i,j);
			break;
		case 2:
			paintKey(g,i,j);
			paintClubs(g,i,j);
			break;
		case 5:
			paintKey(g,i,j);
			paintClubs(g,i,j);
			break;
		}
	}
	
	/**
	 * Paint levers.
	 *
	 * @param g
	 *            the g
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
	public void paintLevers(Graphics g, int i, int j) {
		if (this.map[j][i] == 'k' && !this.game.getLever().getOpen()) {
			g.drawImage(this.leverClosed, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		} else if (this.map[j][i] == 'k' && this.game.getLever().getOpen()) {
			g.drawImage(this.leverOpened, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}
	}
	
	/**
	 * Paint key.
	 *
	 * @param g
	 *            the g
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
	public void paintKey(Graphics g, int i, int j) {
		if ((this.map[j][i] == 'k' ||this.map[j][i] == 'p')) {
			g.drawImage(this.key, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}
	}
	
	/**
	 * Paint clubs.
	 *
	 * @param g
	 *            the g
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 */
	public void paintClubs(Graphics g, int i, int j) {
		if (this.map[j][i] == '*') {
			if (aux(j, i, 0, 1)) g.drawImage(this.clubUp, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
			else if (aux(j, i, 0, -1)) g.drawImage(this.clubDown, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
			else if (aux(j, i, 1, 0)) g.drawImage(this.clubLeft, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
			else if (aux(j, i, -1, 0)) g.drawImage(this.clubRight, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
			else g.drawImage(this.clubUp, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
		}
	}
	
	/**
	 * Aux.
	 *
	 * @param j
	 *            the j
	 * @param i
	 *            the i
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return true, if successful
	 */
	public boolean aux(int j, int i,int x, int y)
	{
		int a=x+i, b=y+j;
		if (this.map[b][a] == 'O' || this.map[b][a] == '8' || this.map[b][a] == '$')
			return true;
		else return false;
	}
	
	
}
