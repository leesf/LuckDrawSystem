package com.leesf.handler;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.leesf.interfaces.MainInterface;
import com.leesf.ui.MainPanel;

public class MouseHandler extends MouseAdapter{
	private MainPanel mainPanel = MainPanel.getInstance();

	private JPopupMenu jpopupMenu;
	private JMenuItem[] jmenuItems;
	
	public MouseHandler() {
		init();
	}
	
	private void init() {
		jpopupMenu = new JPopupMenu();
		//jpopupMenu.setFont(new Font(MainInterface.FONT, Font.BOLD, 20));
		jmenuItems = new JMenuItem[7];
		jmenuItems[0] = new JMenuItem("一等奖");
		jmenuItems[1] = new JMenuItem("二等奖");
		jmenuItems[2] = new JMenuItem("三等奖");
		jmenuItems[3] = new JMenuItem("单人模式");
		jmenuItems[4] = new JMenuItem("双人模式");
		jmenuItems[5] = new JMenuItem("三人模式");
		jmenuItems[6] = new JMenuItem("重设");
		
		jpopupMenu.setBounds(new Rectangle(0, 0, 500, 100));
		
		jmenuItems[0].setFont(new Font(MainInterface.FONT, Font.BOLD, 15));
		jmenuItems[1].setFont(new Font(MainInterface.FONT, Font.BOLD, 15));
		jmenuItems[2].setFont(new Font(MainInterface.FONT, Font.BOLD, 15));
		jmenuItems[3].setFont(new Font(MainInterface.FONT, Font.BOLD, 15));
		jmenuItems[4].setFont(new Font(MainInterface.FONT, Font.BOLD, 15));
		jmenuItems[5].setFont(new Font(MainInterface.FONT, Font.BOLD, 15));
		jmenuItems[6].setFont(new Font(MainInterface.FONT, Font.BOLD, 15));
		
		jpopupMenu.add(jmenuItems[0]);
		jpopupMenu.add(jmenuItems[1]);
		jpopupMenu.add(jmenuItems[2]);
		jpopupMenu.add(jmenuItems[3]);
		jpopupMenu.add(jmenuItems[4]);
		jpopupMenu.add(jmenuItems[5]);
		jpopupMenu.add(jmenuItems[6]);
		
		
		jmenuItems[0].addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				MainPanel.start = true;
				if(MainPanel.firstPrize - MainPanel.drawMode >= 0){
					MainPanel.remain = true;
					MainPanel.firstPrize = MainPanel.firstPrize - MainPanel.drawMode;
				}
				else
					MainPanel.remain = false;
		}});
		
		jmenuItems[1].addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				MainPanel.start = true;
				if(MainPanel.secondPrize - MainPanel.drawMode >= 0){
					MainPanel.remain = true;
					MainPanel.secondPrize = MainPanel.secondPrize - MainPanel.drawMode;	
				}
				else
					MainPanel.remain = false;
		}});

		jmenuItems[2].addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				MainPanel.start = true;
				if(MainPanel.thirdPrize - MainPanel.drawMode >= 0){
					MainPanel.remain = true;
					MainPanel.thirdPrize = MainPanel.thirdPrize - MainPanel.drawMode;
				}
				else
					MainPanel.remain = false;
		}});
		
		jmenuItems[3].addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {//单人模式
				MainPanel.drawMode = 1;

		}});
		
		jmenuItems[4].addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {//双人模式
				MainPanel.drawMode = 2;
		}});
		
		jmenuItems[5].addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {//三人模式
				MainPanel.drawMode = 3;
		}});
		
		
		
		jmenuItems[6].addMouseListener(new MouseAdapter(){//恢复缺省设置
			public void mouseReleased(MouseEvent e) {
				MainPanel.firstPrize = 1;
				MainPanel.secondPrize = 2;
				MainPanel.thirdPrize = 5;
				MainPanel.start = false;
				MainPanel.remain = true;
		}});
		
		
		
	}
	
	// 按下
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(e.getButton() == MouseEvent.BUTTON1){//鼠标左键点击
			MainPanel.start = false;//表示停止
			
		}
		
		
		if(e.getButton() == MouseEvent.BUTTON3){//鼠标右键点击
			jpopupMenu.show(mainPanel, x, y - 20);
		}

	}
	// 释放
	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
