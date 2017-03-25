package dkeep.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKey implements KeyListener{

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
			System.out.println("1");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("1");
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("1");
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("1");
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
