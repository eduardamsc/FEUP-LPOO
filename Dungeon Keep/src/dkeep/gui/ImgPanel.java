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
	private BufferedImage hero;
	private BufferedImage guard;
	private char[][] map = new char[][] { {'D'}};
	
	public ImgPanel(){
		try {
			this.start = ImageIO.read( new File( "/Users/eduardacunha/Desktop/Picture1.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}	
		try {
			this.wall = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/wall.png" ) );
			
		} catch (IOException e1) {
			System.out.println(e1.getCause());
			e1.printStackTrace();
		}
		try {
			this.hero = ImageIO.read( new File( "/Users/eduardacunha/Documents/Uni/2º/LPOO/images/12OgreTras.png" ) );
			
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
				if(this.map[j][i]=='X')
				{
					g.drawImage(this.wall, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				}
				if(this.map[j][i]=='H')
				{
					g.drawImage(this.hero, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				}

				if(this.map[j][i]=='G')
				{
					g.drawImage(this.guard, this.wall.getWidth() * i, this.wall.getHeight() * j, null);
				}
			}
		}
		
	}
}
