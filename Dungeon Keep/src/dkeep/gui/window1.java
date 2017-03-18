package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dkeep.logic.Game;
import dkeep.logic.Map;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class window1 {

	private JFrame frame;
	private JTextField textField;

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
		frame.setBounds(100, 100, 536, 388);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(150, 28, 43, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(150, 55, 127, 27);
		frame.getContentPane().add(comboBox);
		
		JButton btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setBounds(406, 153, 78, 29);
		frame.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.setBounds(360, 182, 79, 29);
		frame.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setBounds(451, 182, 79, 29);
		frame.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setBounds(405, 212, 79, 29);
		frame.getContentPane().add(btnDown);
		
		JLabel lblGameStatus = new JLabel("Game status");
		lblGameStatus.setBounds(20, 327, 252, 16);
		frame.getContentPane().add(lblGameStatus);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(393, 301, 117, 29);
		frame.getContentPane().add(btnExit);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBounds(20, 98, 329, 221);
		frame.getContentPane().add(textArea);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(20, 31, 113, 16);
		frame.getContentPane().add(lblNumberOfOgres);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(20, 59, 141, 16);
		frame.getContentPane().add(lblGuardPersonality);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				
				lblGameStatus.setText("*******\n*START*\n*******");
				Game g = new Game();
				
				boolean nextLevel = true; // permission to play level 2
				
				lblGameStatus.setText("-----LEVEL 1-----");
				
				g.loadElementsLevel1();
				textArea.setText(printMap(g.getMap()));
				
				Scanner s = new Scanner(System.in); //alterar para receber input dos botoes
				boolean end = false;
				
				while (end != true) {
					char direction = s.next().charAt(0);//alterar para receber input dos botoes

					end = g.logicLevel1(direction);
					textArea.setText(printMap(g.getMap()));
					if (end) nextLevel=false;
					
					if (g.getGameOver())
					{
						lblGameStatus.setText("***********\n*GAME OVER*\n***********\nYou just got caught!");
					}
					
					if (g.checkVictory()) {
						nextLevel = true;
						lblGameStatus.setText("YOU WIN");
						break;
					}
				}
				
				lblGameStatus.setText("-----LEVEL 2-----");
				
				g.loadElementsLevel2();
				textArea.setText(printMap(g.getMap()));
				
				Scanner s = new Scanner(System.in);
				end = false;
				
				int[] i = new int[g.getOgres().size()];
				Arrays.fill(i, 0);
				while (end != true) {
					char direction = s.next().charAt(0);
					
					end = g.logicLevel2(direction, i);
					textArea.setText(printMap(g.getMap()));
					
					for (int w = 0; w < g.getOgres().size(); w++) {
						if (!g.getOgres().get(w).getStunned()) {
							i[w] = 0;
						} else {
							i[w]++;
						}
					}

					if (g.getGameOver())
					{
						lblGameStatus.setText("***********\n*GAME OVER*\n***********\nYou just got caught!");
					}
					
					if (g.checkVictory()) {
						lblGameStatus.setText("YOU WIN");
					}
				}
			}
		});
		btnNewGame.setBounds(393, 91, 117, 29);
		frame.getContentPane().add(btnNewGame);
		
	}

	/////////////////////////////////////////USEFUL//////////////////////////////////////
	public static String printMap(Map map)
	{
		String p="";
		for (int i = 0; i < map.getMap().length; i++) {
			for (int j = 0; j < map.getMap()[i].length; j++) {
				p+=map.getMap()[i][j] + " ";
			}
			p+="\n";
		}
		return p;
	}
}
