package com.lyh.app;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private static final String[] NUM_IMAGE={"./src/images/0.png","./src/images/2.png",
			"./src/images/4.png","./src/images/8.png","./src/images/16.png",
			"./src/images/32.png","./src/images/64.png","./src/images/128.png",
			"./src/images/256.png","./src/images/512.png","./src/images/1024.png",};
	private Array_2048 game;
	private Icon[] icon;
	private JPanel mypanel;
	private JLabel[] label;
	
	public MainFrame() {
		setTitle("辣鸡2048小游戏");
		setBounds(300,300,600,600);
		setVisible(true);
		setDefaultCloseOperation(3);
		init();
		setBounds(300,300,601,600);
	}

	private void init() {
		game = new Array_2048();
		icon = new Icon[16];
		mypanel = new JPanel();
		label = new JLabel[16];
		mypanel.setLayout(new GridLayout(4, 4, 5, 5));
		mypanel.setBackground(Color.GRAY);
		game.init();
		for(int i = 0; i < 11;i++) {
			icon[i] = new ImageIcon(NUM_IMAGE[i]);	
		}
		initFrame(game.getArr());		
		for(int i = 0; i < 16;i++) {
			mypanel.add(label[i]);
		}
		addKeyListener(new MyListener());
		add(mypanel,"Center");
	}
	
	private void initFrame(int[][] arr) {
		int index = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4;j++) {
				if(arr[i][j]==0) {
					label[index++] = new JLabel(icon[0]);
				}
				else 
					label[index++] = new JLabel(icon[1]);
			}
		}
	}
	
	private void showFrame(int[][] arr) {
		int index=0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4;j++) {
				if(arr[i][j]==0)
					label[index++].setIcon(icon[0]);
				else if(arr[i][j]==2) 
					label[index++].setIcon(icon[1]);
				else if(arr[i][j]==4) 
					label[index++].setIcon(icon[2]);
				else if(arr[i][j]==8) 
					label[index++].setIcon(icon[3]);
				else if(arr[i][j]==16) 
					label[index++].setIcon(icon[4]);
				else if(arr[i][j]==32) 
					label[index++].setIcon(icon[5]);
				else if(arr[i][j]==64) 
					label[index++].setIcon(icon[6]);
				else if(arr[i][j]==128) 
					label[index++].setIcon(icon[7]);
				else if(arr[i][j]==256) 
					label[index++].setIcon(icon[8]);
				else if(arr[i][j]==512) 
					label[index++].setIcon(icon[9]);
				else if(arr[i][j]==1024) 
					label[index++].setIcon(icon[10]);
			}
		}
	}
	
	private class MyListener extends KeyAdapter{

		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				game.checkUp();
				showFrame(game.getArr());
				game.checkOver();
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				game.checkDown();
				showFrame(game.getArr());
				game.checkOver();
			}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				game.checkLeft();
				showFrame(game.getArr());
				game.checkOver();
			}else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				game.checkRight();
				showFrame(game.getArr());
				game.checkOver();
			}
		}
		
	}
	
}
