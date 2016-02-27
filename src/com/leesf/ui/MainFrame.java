package com.leesf.ui;

import javax.swing.JFrame;

import com.leesf.handler.MouseHandler;
import com.leesf.interfaces.MainInterface;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	// 得到唯一的MainPanel实例
	private MainPanel panel = MainPanel.getInstance();
	private MouseHandler mouse = new MouseHandler();

	public MainFrame() {
		// 初始化开始界面的一些信息
		init();
		// 初始化Frame上的组件
		initComs();
		// 添加监听器
		initListener();
	}

	private void initComs() {
		// 添加panel
		add(panel);
	}

	private void initListener() {
		// 鼠标监听
		addMouseListener(mouse);
	}

	private void init() {
		setTitle("抽奖系统");
		setResizable(false);
		setSize(MainInterface.WIDTH, MainInterface.HEIGHT);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
