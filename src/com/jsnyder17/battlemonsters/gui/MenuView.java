package com.jsnyder17.battlemonsters.gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuView implements ActionListener {
	private JFrame frame;
	private JPanel cards;
	private EditPanel editPanel;
	private BattlePanel battlePanel;
	private JButton battleButton;
	private JButton editButton;
	private JButton resetButton;
	private JButton updateButton;
	private JButton quitButton;
	
	public MenuView() {
		initializeComponents();
	}
	
	private void initializeComponents() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 650);
		
		cards = new JPanel(new CardLayout());
		
		cards.add(battlePanel);
		cards.add(battlePanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == battleButton) {
			
		}
		else if (e.getSource() == editButton) {
			
		}
		else if (e.getSource() == resetButton) {
			
		}
		else if (e.getSource() == updateButton) {
			
		}
		else if (e.getSource() == quitButton) {
			
		}
	}
}
