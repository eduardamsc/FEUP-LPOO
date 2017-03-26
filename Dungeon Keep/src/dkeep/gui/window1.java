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
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class window1 {

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
	private int level = 3;
	int[] j;
	private JLabel lblMazeHeight;
	private JLabel lblMazeWidth;
	private JTextField textFieldHeight;
	private JTextField textFieldWidth;
	private JButton btnDoor;
	private JButton btnWall;
	private JButton btnLever;
	private JButton btnKey;
	private JButton btnHero;
	private JButton btnGuard;
	private JButton btnOgre;
	private JButton btnClub;

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

		textField = new JTextField();
		textField.setBounds(150, 28, 43, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText("1");

		lblGameStatus = new JLabel("******* *DUNGEON'S KEEPER* *******");
		lblGameStatus.setBounds(20, 442, 252, 16);
		frame.getContentPane().add(lblGameStatus);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Rookie", "Drunken", "Suspicious" }));
		comboBox.setBounds(150, 55, 127, 27);
		frame.getContentPane().add(comboBox);

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(20, 31, 113, 16);
		frame.getContentPane().add(lblNumberOfOgres);

		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(20, 59, 141, 16);
		frame.getContentPane().add(lblGuardPersonality);

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
		img.setBounds(20, 113, 314, 317);
		frame.getContentPane().add(img);

		lblMazeHeight = new JLabel("Maze Height");
		lblMazeHeight.setBounds(426, 59, 99, 16);
		frame.getContentPane().add(lblMazeHeight);

		lblMazeWidth = new JLabel("Maze Width");
		lblMazeWidth.setBounds(426, 85, 99, 16);
		frame.getContentPane().add(lblMazeWidth);

		textFieldHeight = new JTextField();
		textFieldHeight.setText("10");
		textFieldHeight.setColumns(10);
		textFieldHeight.setBounds(524, 56, 43, 21);
		frame.getContentPane().add(textFieldHeight);

		textFieldWidth = new JTextField();
		textFieldWidth.setText("10");
		textFieldWidth.setColumns(10);
		textFieldWidth.setBounds(524, 82, 43, 21);
		frame.getContentPane().add(textFieldWidth);

		//////////////////////////////////////// MOVEMENT/////////////////////////////////////////////////
		callAllButtons();
	}

	///////////////////////////////////////// USEFUL//////////////////////////////////////
	public void level(char direction) {
		if (level == 1 && !g.getGameOver())
			level1(direction);
		else
			level2(direction);

		if (g.getGameOver() || g2.getGameOver()) {
			movementButtons(false);
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

	public void levelEnd(Game g) {
		if (g.getGameOver())
			lblGameStatus.setText("*********** *GAME OVER* ***********");
		else if (g.checkVictory()) {
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
				map[i][j] = ' ';
			}
		}
		return map;
	}

	///////////////////////////////////////// BUTTONS//////////////////////////////////////
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

	public void imageButtons(boolean a) {
		btnDoor.setEnabled(a);
		btnDoor.setVisible(a);
		btnWall.setEnabled(a);
		btnWall.setVisible(a);
		btnLever.setEnabled(a);
		btnLever.setVisible(a);
		btnKey.setEnabled(a);
		btnKey.setVisible(a);
		btnHero.setEnabled(a);
		btnHero.setVisible(a);
		btnGuard.setEnabled(a);
		btnGuard.setVisible(a);
		btnOgre.setEnabled(a);
		btnOgre.setVisible(a);
		btnClub.setEnabled(a);
		btnClub.setVisible(a);
	}

	public void actionImageButtonsMap() {
		btnDoor = new JButton("");
		try {
			Image a = ImageIO.read(new File("images/exitClosed.png"));
			btnDoor.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnDoor.setBounds(524, 113, 31, 31);
		frame.getContentPane().add(btnDoor);

		btnWall = new JButton("");
		try {
			Image a = ImageIO.read(new File("images/wall.png"));
			btnWall.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnWall.setBounds(524, 157, 31, 31);
		frame.getContentPane().add(btnWall);
	}
	
	public void actionImageButtonsCharacters()
	{
		btnHero = new JButton("");
		try {
			Image a = ImageIO.read(new File("images/heroFront.png"));
			btnHero.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnHero.setBounds(524, 286, 31, 31);
		frame.getContentPane().add(btnHero);

		btnGuard = new JButton("");
		try {
			Image a = ImageIO.read(new File("images/guardFront.png"));
			btnGuard.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnGuard.setBounds(524, 329, 31, 31);
		frame.getContentPane().add(btnGuard);

		btnOgre = new JButton("");
		try {
			Image a = ImageIO.read(new File("images/ogreFront.png"));
			btnOgre.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnOgre.setBounds(524, 372, 31, 31);
		frame.getContentPane().add(btnOgre);
	}
	
	public void actionImageButtonsObjects()
	{
		btnLever = new JButton("");
		try {
			Image a = ImageIO.read(new File("images/leverClosed.png"));
			btnLever.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnLever.setBounds(524, 200, 31, 31);
		frame.getContentPane().add(btnLever);

		btnKey = new JButton("");
		try {
			Image a = ImageIO.read(new File("images/key.png"));
			btnKey.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnKey.setBounds(524, 243, 31, 31);
		frame.getContentPane().add(btnKey);

		btnClub = new JButton("");
		try {
			Image a = ImageIO.read(new File("images/swordUp.png"));
			btnClub.setIcon(new ImageIcon(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnClub.setBounds(524, 415, 31, 31);
		frame.getContentPane().add(btnClub);
		imageButtons(false);
	}

	public void actionOtherButtons() {
		btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(366, 129, 117, 29);
		frame.getContentPane().add(btnNewGame);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(366, 387, 117, 29);
		frame.getContentPane().add(btnExit);
		
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movementButtons(true);

				if (Float.parseFloat(textField.getText()) > 5.0 || Float.parseFloat(textField.getText()) < 1.0
						|| (Float.parseFloat(textField.getText()) % 1 != 0)) {
					return;
				}

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
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void editableLevelButton()
	{
		btnEditLevel = new JButton("Edit Level");
		btnEditLevel.setBounds(450, 20, 117, 29);
		frame.getContentPane().add(btnEditLevel);
		
		btnEditLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movementButtons(false);
				imageButtons(true);

				if (Float.parseFloat(textFieldHeight.getText()) > 15 || Float.parseFloat(textFieldHeight.getText()) < 5
						|| (Float.parseFloat(textFieldHeight.getText()) % 1 != 0)) {
					return;
				}

				if (Float.parseFloat(textFieldWidth.getText()) > 15 || Float.parseFloat(textFieldWidth.getText()) < 5
						|| (Float.parseFloat(textFieldWidth.getText()) % 1 != 0)) {
					return;
				}

				Map a = new Map(1);
				a.setMap(drawEditableMap(Integer.parseInt(textFieldHeight.getText()),
						Integer.parseInt(textFieldWidth.getText())));

				g = new Game(a);
				img.updateMap(a.getMap(), g, level, new int[] { 0 });
				img.repaint();
			}
		});
	}
	
	public void actionEditableLevelAllButtons()
	{
		editableLevelButton();
		actionImageButtonsMap();
		actionImageButtonsCharacters();
		actionImageButtonsObjects();
	}

	public void callAllButtons()
	{
		actionOtherButtons();
		actionMovementButtons();
		actionEditableLevelAllButtons();
	}
}
