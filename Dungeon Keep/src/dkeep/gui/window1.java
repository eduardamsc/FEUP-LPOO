package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;

import dkeep.cli.Menu;
import dkeep.logic.Club;
import dkeep.logic.Exit;
import dkeep.logic.Game;
import dkeep.logic.Guard;
import dkeep.logic.GuardDrunken;
import dkeep.logic.GuardRookie;
import dkeep.logic.GuardSuspicious;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;

public class window1{

	private static ImgPanel img;
	private JFrame frame;
	private JTextField textField;
	private JLabel lblGameStatus;
	private JButton btnNewGame;
	private JButton btnExit;
	private JButton btnEditLevel;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;
	private JComboBox comboBox;
	private Game g;
	private Game g2;
	private int level;
	int[] j;
	private JLabel lblMazeSize;
	private JTextField textFieldSize;
	private JButton btnDoor;
	private JButton btnWall;
	private JButton btnKey;
	private JButton btnHero;
	private JButton btnOgre;
	private JButton btnDelete;
	private JButton btnValidateMaze;
	private int size;
	private char element;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window1 window = new window1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 592, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lblGameStatus = new JLabel("******* *DUNGEON'S KEEPER* *******");
		lblGameStatus.setBounds(20, 442, 252, 16);
		frame.getContentPane().add(lblGameStatus);
		inputFixedLevel();
		outputPanel();
		inputEditableLevel();
		callAllButtons();
	}

	///////////////////////////////////////// USEFUL//////////////////////////////////////
	public void level(char direction) {
		if (level == 1 && !g.getGameOver())
			level1(direction);
		else if (level == 2 && !g2.getGameOver()  && !g2.checkVictory())
			level2(direction);
		else if (level == 5 && !g.getGameOver() && !g.checkVictory()) {
			levelEditable(direction);
		}
	}

	public void level1(char direction) {
		if (g.getHero().wall(g.getMap(), direction)) {
			lblGameStatus.setText("-----LEVEL 1-----");
			g.logicLevel1(direction);
			img.updateMap(g.getMap().getMap(), g, level, j);
			img.repaint();
			levelEnd(g);
		}
	}

	public void level2(char direction) {
		if (g2.getHero().wall(g2.getMap(), direction)) {
			lblGameStatus.setText("-----LEVEL 2-----");
			g2.logicLevel2(direction, j);
			img.updateMap(g2.getMap().getMap(), g, level, j);
			img.repaint();
			for (int w = 0; w < g2.getOgres().size(); w++) {
				if (!g2.getOgres().get(w).getStunned())
					j[w] = 0;
				else
					j[w]++;
			}
			levelEnd(g2);
		}
	}

	public void levelEditable(char direction)
	{
		if (g.getHero().wall(g.getMap(), direction)) {
		
			g.moveHero(direction);
			g.pickKey();
			for (int i = 0; i < g.getOgres().size(); i++) {
				direction = g.getOgres().get(i).randomTrajectory();
				while (!g.getOgres().get(i).wall(g.getMap(), direction)) {
					direction = g.getOgres().get(i).randomTrajectory();
				}
				g.eraseTrailC(g.getOgres().get(i)); // erases ogre's trail as it changes position
				g.getOgres().get(i).movement(g.getMap(), direction);
				g.updateCharacterPosition(g.getOgres().get(i)); // updates ogre's position on map

				if (g.getOgres().get(i).getArmed()) {
					g.eraseTrailC(g.getClubs().get(i));
					g.getClubs().get(i).movement(g.getMap(), g.getOgres().get(i).getX(), g.getOgres().get(i).getY());
					g.updateCharacterPosition(g.getClubs().get(i));
				}
			}
			for (int j = 0; j < g.getOgres().size(); j++) {
				if (!g.getOgres().get(j).getStunned()) {
					if (g.OgreCatchHero() || auxClubCatch()) { // checks if ogre has caught hero
						g.setGameOver(true);
					} 
				}
			}
			g.nearKey(); // checks if ogre is near key and updates
			g.checkExitsOpen();
			img.updateMap(g.getMap().getMap(), g, level, j);
			img.repaint();
			levelEnd(g);
		}
	}
	
	public boolean auxClubCatch()
	{
		for (int i = 0; i < g.getOgres().size(); i++) {
			if ((g.getClubs().get(i).getX() - 1 == g.getHero().getX() && g.getClubs().get(i).getY() == g.getHero().getY()) 
				|| (g.getClubs().get(i).getX() + 1 == g.getHero().getX() && g.getClubs().get(i).getY() == g.getHero().getY())
					|| (g.getClubs().get(i).getX() == g.getHero().getX() && g.getClubs().get(i).getY() - 1 == g.getHero().getY())
					|| (g.getClubs().get(i).getX() == g.getHero().getX() && g.getClubs().get(i).getY() + 1 == g.getHero().getY())) {
				return true;
			}
		}
		return false;
	}
	
	public void levelEnd(Game g) {
		if (g.getGameOver()) {
			lblGameStatus.setText("*********** *GAME OVER* ***********");
			movementButtons(false);
		} else if (g.checkVictory()) {
			lblGameStatus.setText("YOU WIN");
			level++;
		}
	}

	public static String printMap(Map map) {
		String p = "";
		for (int i = 0; i < map.getMap().length; i++) {
			for (int j = 0; j < map.getMap()[i].length; j++) {
				p += map.getMap()[i][j] + " ";
			}
			p += "\n";
		}
		return p;
	}

	public char[][] drawEditableMap(int x, int y) {
		char[][] map = new char[x][y];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = 'X';
			}
		}
		return map;
	}

/////////////////////////////////////////BUTTONS//////////////////////////////////////
	public void movementButtons(boolean a) {
		btnUp.setEnabled(a);
		btnDown.setEnabled(a);
		btnRight.setEnabled(a);
		btnLeft.setEnabled(a);
	}

	public void actionMovementButtons() {
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setBounds(387, 208, 78, 29);
		frame.getContentPane().add(btnUp);
		
		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.setBounds(346, 235, 79, 29);
		frame.getContentPane().add(btnLeft);

		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setBounds(426, 235, 79, 29);
		frame.getContentPane().add(btnRight);

		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setBounds(386, 265, 79, 29);
		frame.getContentPane().add(btnDown);
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				level('w');
				img.requestFocus();
			}
		});

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				level('a');
				img.requestFocus();
			}
		});

		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				level('d');
				img.requestFocus();
			}
		});

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				level('s');
				img.requestFocus();
			}
		});
	}

	/////////////////////////////////////////IMAGE BUTTONS//////////////////////////////////////
	public void imageButtons(boolean a) {
		btnDoor.setEnabled(a);
		btnDoor.setVisible(a);
		btnWall.setEnabled(a);
		btnWall.setVisible(a);
		btnKey.setEnabled(a);
		btnKey.setVisible(a);
		btnHero.setEnabled(a);
		btnHero.setVisible(a);
		btnOgre.setEnabled(a);
		btnOgre.setVisible(a);
		btnValidateMaze.setEnabled(a);
		btnValidateMaze.setVisible(a);
		btnDelete.setEnabled(a);
		btnDelete.setVisible(a);
	}

	public void actionImageButtonsMap() {
		btnDoor = new JButton("");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element='I';
			}
		});
		try {
			Image a = ImageIO.read(new File("images/exitClosed.png"));
			btnDoor.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnDoor.setBounds(524, 138, 31, 31);
		frame.getContentPane().add(btnDoor);

		btnWall = new JButton("");
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element='X';
			}
		});
		try {
			Image a = ImageIO.read(new File("images/wall.png"));
			btnWall.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnWall.setBounds(524, 181, 31, 31);
		frame.getContentPane().add(btnWall);
		
		btnDelete = new JButton("Delete Element");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element=' ';
			}
		});
		btnDelete.setBounds(438, 436, 127, 31);
		frame.getContentPane().add(btnDelete);
	}
	
	public void actionImageButtonsCharacters()
	{
		btnHero = new JButton("");
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element='H';
			}
		});
		try {
			Image a = ImageIO.read(new File("images/heroFront.png"));
			btnHero.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnHero.setBounds(524, 270, 31, 31);
		frame.getContentPane().add(btnHero);

		btnOgre = new JButton("");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element='O';
			}
		});
		try {
			Image a = ImageIO.read(new File("images/ogreFront.png"));
			btnOgre.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnOgre.setBounds(524, 313, 31, 31);
		frame.getContentPane().add(btnOgre);
	}
	
	public void actionImageButtonsObjects()
	{
		btnKey = new JButton("");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				element='p';
			}
		});
		try {
			Image a = ImageIO.read(new File("images/key.png"));
			btnKey.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnKey.setBounds(524, 224, 31, 31);
		frame.getContentPane().add(btnKey);
		try {
			Image a = ImageIO.read(new File("images/swordUp.png"));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

/////////////////////////////////////////OTHER BUTTONS//////////////////////////////////////
	public void actionExitButton() {
		btnExit = new JButton("Exit");
		btnExit.setBounds(366, 387, 117, 29);
		frame.getContentPane().add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void actionNewGameButton() {
		btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(366, 129, 117, 29);
		frame.getContentPane().add(btnNewGame);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageButtons(false);
				movementButtons(true);
				newGameLevel1();
				newGameLevel2();
			}
		});
	}
	
	public void newGameLevel1() {
		if (Float.parseFloat(textField.getText()) > 5.0 || Float.parseFloat(textField.getText()) < 1.0
				|| (Float.parseFloat(textField.getText()) % 1 != 0))
			return;
		g = new Game();
		level = 1;

		lblGameStatus.setText("-----LEVEL 1-----");
		g.loadElementsLevel1();
		for (int i = 0; i < g.getGuards().size(); i++) {
			g.eraseTrailC(g.getGuards().get(i));
		}
		g.getGuards().clear();

		if (comboBox.getSelectedItem() == "Rookie") {
			g.getGuards().add(new GuardRookie(1, 8));
		} else if (comboBox.getSelectedItem() == "Drunken") {
			g.getGuards().add(new GuardDrunken(1, 8));
		}
		if (comboBox.getSelectedItem() == "Suspicious") {
			g.getGuards().add(new GuardSuspicious(1, 8));
		}
		g.getMap().insertCharacter(g.getGuards().get(0));
		img.updateMap(g.getMap().getMap(), g, level, j);
		img.repaint();
	}
	
	public void newGameLevel2()
	{
		g2 = new Game();
		g2.loadElementsLevel2();
		g2.eraseTrailC(g2.getClubs().get(0));
		for (int i = 0; i < g2.getOgres().size(); i++) {
			g2.eraseTrailC(g2.getOgres().get(i));
			g2.eraseTrailC(g2.getClubs().get(i + 1));
		}
		g2.getOgres().clear();
		g2.getClubs().clear();
		g2.getClubs().add(new Club(7, 5));
		g2.getMap().insertCharacter(g2.getClubs().get(0));
		for (int i = 0; i < Integer.parseInt(textField.getText()); i++) {
			g2.getOgres().add(new Ogre(1, 4));
			g2.getMap().insertCharacter(g2.getOgres().get(i));
		}
		for (int z = 0; z < g2.getOgres().size(); z++) {
			g2.getClubs().add(new Club(7, 3));
			g2.getClubs().get(z + 1).movement(g2.getMap(), g2.getOgres().get(z).getX(),
					g2.getOgres().get(z).getY());
			g2.getMap().insertCharacter(g2.getClubs().get(z + 1));
		}
		j = new int[g2.getOgres().size()];
		Arrays.fill(j, 0);
		img.requestFocus();
	}

	public void callAllButtons()
	{
		actionExitButton();
		actionNewGameButton();
		actionMovementButtons();
		actionEditableLevelAllButtons();
		imageButtons(false);
	}

/////////////////////////////////////////INPUT OUTPUT//////////////////////////////////////
	public void outputPanel()
	{
		img = new ImgPanel();
		img.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch (key) {
				case KeyEvent.VK_UP:
					level('w');
					break;
				case KeyEvent.VK_DOWN:
					level('s');
					break;
				case KeyEvent.VK_LEFT:
					level('a');
					break;
				case KeyEvent.VK_RIGHT:
					level('d');
					break;
				}
			}
		});
		img.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				float x = e.getX();
				float y = e.getY();
				double coorX = ((float) (x / img.getWidth())*size);
				double coorY = ((float) (y / img.getHeight())*size);
				g.getMap().getMap()[(int)coorY][(int)coorX] = element;
				img.updateMap(g.getMap().getMap(), g, level, new int[] { 0 });
				img.repaint();
				
			}
		});
		img.setBounds(20, 113, 314, 317);
		frame.getContentPane().add(img);
	}

	public void inputFixedLevel()
	{
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Rookie", "Drunken", "Suspicious" }));
		comboBox.setBounds(150, 55, 127, 27);
		frame.getContentPane().add(comboBox);

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(20, 31, 113, 16);
		frame.getContentPane().add(lblNumberOfOgres);
		textField = new JTextField();
		textField.setBounds(150, 28, 43, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText("1");

		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(20, 59, 141, 16);
		frame.getContentPane().add(lblGuardPersonality);
	}
	
	public void inputEditableLevel() {
		lblMazeSize = new JLabel("Maze Size");
		lblMazeSize.setBounds(426, 59, 99, 16);
		frame.getContentPane().add(lblMazeSize);
		textFieldSize = new JTextField();
		textFieldSize.setText("10");
		textFieldSize.setColumns(10);
		textFieldSize.setBounds(524, 56, 43, 21);
		frame.getContentPane().add(textFieldSize);
	}
	
/////////////////////////////////////////EDITABLE//////////////////////////////////////
	public void actionEditableLevelAllButtons()
	{
		editableLevelButton();
		validateMazeButton();
		actionImageButtonsMap();
		actionImageButtonsCharacters();
		actionImageButtonsObjects();
	}

	public void editableLevelButton()
	{
		btnEditLevel = new JButton("Edit Level");
		btnEditLevel.setBounds(450, 20, 117, 29);
		frame.getContentPane().add(btnEditLevel);
		
		btnEditLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				level=5;
				if (Float.parseFloat(textFieldSize.getText()) > 10 || Float.parseFloat(textFieldSize.getText()) < 5
						|| (Float.parseFloat(textFieldSize.getText()) % 1 != 0)) {
					return;
				}
				movementButtons(false);
				imageButtons(true);

				Map a = new Map(1);
				size=Integer.parseInt(textFieldSize.getText());
				a.setMap(drawEditableMap(size,size));

				g = new Game(a);
				img.updateMap(a.getMap(), g, level, new int[] { 0 });
				img.repaint();
			}
		});
	}
	
	public void validateMazeButton()
	{
		btnValidateMaze = new JButton("Validate Maze");
		btnValidateMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkMazeValide())
				{
					lblGameStatus.setText("Your maze is valid! Go ahead and play!");
					level=5;
					playEditableMaze();
				} else {
					for (int i = 0; i < g.getMap().getMap().length; i++) {
						for (int j = 0; j < g.getMap().getMap()[i].length; j++) {
							g.getMap().getMap()[i][j]='X';
						}
					}
					lblGameStatus.setText("Start Over. Your maze isn't valid.");
				}
				img.repaint();
				img.requestFocus();
			}
		});
		btnValidateMaze.setBounds(450, 87, 117, 29);
		frame.getContentPane().add(btnValidateMaze);
	}
	
	public boolean checkMazeValide()
	{
		int hero=0, ogre=0, key=0, door=0, club=0;
		for (int i = 0; i < g.getMap().getMap().length; i++) {
			for (int j = 0; j < g.getMap().getMap()[i].length; j++) {
				if (g.getMap().getMap()[i][j] == 'H') hero++;
				else if (g.getMap().getMap()[i][j] == 'O') ogre++;
				else if (g.getMap().getMap()[i][j] == 'p') key++;
				else if (g.getMap().getMap()[i][j] == 'I') door++;
			}
		}
		
		if (hero!=1 || ogre<1 || door<1 || key!=1)
		{
			return false;
		} else return true;
	}

	public void loadCharactersEditableMaze() {
		Map edit = g.getMap();
		g.loadElementsEditableLevel();
		g.getMap().setMap(edit.getMap());
		
		for (int i = 0; i < g.getMap().getMap().length; i++) {
			for (int j = 0; j < g.getMap().getMap()[i].length; j++) {
				loadCharacters(i,j);
				loadObjects(i,j);
			}
		}
	}
	
	public void loadCharacters(int i, int j)
	{
		if (g.getMap().getMap()[i][j] == 'H') {
			g.getHero().setX(i);
			g.getHero().setY(j);
			g.updateCharacterPosition(g.getHero());
		} else if (g.getMap().getMap()[i][j] == 'O')
		{
			g.getOgres().add(new Ogre(i, j));
		}
		
		for (int z = 0; z < g.getOgres().size(); z++) {
			Club c=new Club();
			c.movement(g.getMap(), g.getOgres().get(z).getX(), g.getOgres().get(z).getY());
			g.updateCharacterPosition(g.getOgres().get(z));
			g.getClubs().add(c);
			g.updateCharacterPosition(g.getClubs().get(z));
		}
	}
	
	public void loadObjects(int i, int j)
	{
		if (g.getMap().getMap()[i][j] == 'I')
		{
			g.getExits().add(new Exit(i, j));
		}
		else if (g.getMap().getMap()[i][j] == 'p') {
			g.getKey().setX(i);
			g.getKey().setY(j);
			g.updateObjectPosition(g.getKey());
		} else if (g.getMap().getMap()[i][j] == '*') {
			g.getClubs().add(new Club(i,j));
			g.updateObjectPosition(g.getKey());
		}
		for(int z=0; z<g.getExits().size();z++)
		{
			g.updateObjectPosition(g.getExits().get(z));
		}
	}

	public void playEditableMaze() {
		lblGameStatus.setText("-----EDITABLE LEVEL-----");
		loadCharactersEditableMaze();
		movementButtons(true);
		j = new int[g.getOgres().size()];
		Arrays.fill(j, 0);
		img.updateMap(g.getMap().getMap(), g, level, j);
		img.repaint();
	}

}
