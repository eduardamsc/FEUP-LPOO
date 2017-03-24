package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImgPanel extends JPanel {
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
	
	private char[][] map = new char[][] { {'D'}};
	
	public ImgPanel(){

		try {
			this.start = ImageIO.read( new File( "/Users/eduardacunha/Desktop/Picture1.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}	
////////////////////////////////////////MAP/////////////////////////////////////////////////
		try {
			this.wall = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/wall.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}
		
		try {
			this.exitClosed = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/exitClosed.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}
		try {
			this.exitOpened = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/exitOpened.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}
////////////////////////////////////////CHARACTERS/////////////////////////////////////////////////
		try {
			this.hero = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/ogreFront.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}
		try {
			this.guard = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/19GuardTras.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}
		try {
			this.ogre = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/OgreFront.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}
////////////////////////////////////////OBJECTS/////////////////////////////////////////////////
		try {
			this.leverClosed = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/leverClosed.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}
		/*try {
			this.leverOpened = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/leverOpened.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}*/
		try {
			this.key = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/key.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}
	}
	
	public void updateMap(char[][] map)
	{
		this.map=map;
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
////////////////////////////////////////MAP/////////////////////////////////////////////////
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
////////////////////////////////////////CHARACTERS/////////////////////////////////////////////////
				if(this.map[j][i]=='H')
				{
					g.drawImage(this.hero, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				}

				if(this.map[j][i]=='G')
				{
					g.drawImage(this.guard, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				}
				
				if(this.map[j][i]=='O')
				{
					g.drawImage(this.ogre, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				}
////////////////////////////////////////OBJECTS/////////////////////////////////////////////////
				/*if(this.map[j][i]=='k')
				{
					g.drawImage(this.leverClosed, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				}*/
				if(this.map[j][i]=='k')
				{
					g.drawImage(this.key, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				}
			}
		}
		
	}
}
