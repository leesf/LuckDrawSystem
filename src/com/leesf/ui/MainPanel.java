package com.leesf.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.leesf.interfaces.MainInterface;



public class MainPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private BufferedImage buff;
	private Timer timer;
	
	//存放背景图片	
	private BufferedImage image;
	
	//
	private List<BufferedImage> imageList = new ArrayList<BufferedImage>();
	private List<String> imageNameList = new ArrayList<String>();
	
	
	//使用单例模式
	private static MainPanel mainPanel = new MainPanel();
	
	//生成随机数
	private Random random = new Random();
	
	private static int index1 = 0;
	private static int index2 = 1;
	private static int index3 = 2;
	
	public static boolean start = false;
	public static boolean remain = true;
	
	public static int firstPrize = 1;
	public static int secondPrize = 2;
	public static int thirdPrize = 5;
	
	public static int drawMode = 1;//默认为单人模式
	
	public static Graphics2D g2d;

	private MainPanel() {
		//完成panel的初始化(添加计时器)
		init();
		
		//添加组件,如pen(Graphics),双缓冲BufferedImage
		initComs();
	}
	
	public static MainPanel getInstance() {
		return mainPanel;
	}
	
	private void init() {
		String name = null;
		try {	
			image = ImageIO.read(new File("image/background.jpg"));// 读取关于游戏背景图片
			File file = new File("candidate");
			if(file.isDirectory()){
				String[] fileList = file.list();
				for(int i = 0; i < fileList.length; i++){
					imageList.add(ImageIO.read(new File("candidate/" + fileList[i])));
					int indexOf = fileList[i].toString().indexOf('.');
					name = fileList[i].substring(0, indexOf);
					imageNameList.add(name);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//panel中添加计时器,并启动计时器进行触发控制
		timer = new Timer(MainInterface.TIME, this);
		timer.setActionCommand("begin");
		timer.start();
	}
	
	private void initComs() {
		// 使用双缓冲技术
		buff = new BufferedImage(MainInterface.WIDTH,
				MainInterface.HEIGHT, BufferedImage.TYPE_INT_ARGB);
	}
	
	
	
	@Override
	public void paint(Graphics g) {//此方法会自动被调用进行绘制
		
		g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, null);
		
		if(remain){
			if(start){
				switch(MainPanel.drawMode){
				case 1://
					index1 = random.nextInt(imageList.size());
					break;
				case 2:
					index1 = random.nextInt(imageList.size());
					index2 = random.nextInt(imageList.size());
					while(index2 == index1){
						index2 = random.nextInt(imageList.size());
					}
					break;
				case 3://
					index1 = random.nextInt(imageList.size());
					index2 = random.nextInt(imageList.size());
					while(index2 == index1){
						index2 = random.nextInt(imageList.size());	
					}
					index3 = random.nextInt(imageList.size());
					while(index3 == index2 || index3 == index1){
						index3 = random.nextInt(imageList.size());
					}
					break;
				default:
					break;
				}
				
				g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index1), 120, 270);
		
				g2d.drawImage(imageList.get(index2), 340, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index2), 390, 270);
					
				g2d.drawImage(imageList.get(index3), 240, 340, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index3), 340, 570);
				
			}else{//单击鼠标左键,停止
				g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index1), 120, 270);
		
				g2d.drawImage(imageList.get(index2), 340, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index2), 390, 270);
					
				g2d.drawImage(imageList.get(index3), 240, 340, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index3), 340, 570);
			}
		}else{
			g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
			g2d.setColor(Color.RED);
			g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
			g2d.drawString(imageNameList.get(index1), 120, 270);
	
			g2d.drawImage(imageList.get(index2), 340, 40, 200, 200, null);
			g2d.setColor(Color.RED);
			g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
			g2d.drawString(imageNameList.get(index2), 390, 270);
				
			g2d.drawImage(imageList.get(index3), 240, 340, 200, 200, null);
			g2d.setColor(Color.RED);
			g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
			g2d.drawString(imageNameList.get(index3), 340, 570);
		}
		
		/*
		if(remain){
			if(start){
				switch(MainPanel.drawMode){
				case 1://
					index1 = random.nextInt(imageList.size());
					break;
				case 2:
					index1 = random.nextInt(imageList.size());
					index2 = random.nextInt(imageList.size());
					while(index2 == index1){
						index2 = random.nextInt(imageList.size());
					}
					break;
				case 3://
					index1 = random.nextInt(imageList.size());
					index2 = random.nextInt(imageList.size());
					while(index2 == index1){
						index2 = random.nextInt(imageList.size());	
					}
					index3 = random.nextInt(imageList.size());
					while(index3 == index2 || index3 == index1){
						index3 = random.nextInt(imageList.size());
					}
					break;
				default:
					break;
				}
				
				switch(MainPanel.drawMode){
				case 1://
					g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index1), 120, 270);
					break;
				case 2://
					g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index1), 120, 270);

					g2d.drawImage(imageList.get(index2), 340, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index2), 270, 270);
					break;
			
				case 3://
					g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index1), 120, 270);
			
					g2d.drawImage(imageList.get(index2), 340, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index2), 270, 270);
						
					g2d.drawImage(imageList.get(index3), 240, 340, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index3), 270, 270);
					break;	
				}
			}else{//点击了鼠标左键停止滚动
				switch(MainPanel.drawMode){
				case 1://
					g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index1), 120, 270);
					break;
				case 2://
					g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index1), 120, 270);
	
					g2d.drawImage(imageList.get(index2), 340, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index2), 270, 270);
					break;
					
				case 3://
					g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index1), 120, 270);
	
					g2d.drawImage(imageList.get(index2), 340, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index2), 270, 270);
					
					g2d.drawImage(imageList.get(index3), 240, 340, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index3), 270, 270);
					break;			
				}
			}
		}else{//没有剩余抽奖机会了
			switch(MainPanel.drawMode){
			case 1://
				g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index1), 120, 270);
				break;
			case 2://
				g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index1), 120, 270);

				g2d.drawImage(imageList.get(index2), 340, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index2), 270, 270);
				break;
				
			case 3://
				g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index1), 120, 270);

				g2d.drawImage(imageList.get(index2), 340, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index2), 270, 270);
				
				g2d.drawImage(imageList.get(index3), 240, 340, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index3), 270, 270);
				break;		
			}
		}
		
		*/		
		//随机选择图片,单人模式
		/*
		if(start){
			if(remain){
				index1 = random.nextInt(imageList.size());
				g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index1), 120, 270);
			}else{
				g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index1), 120, 270);
			}
		}else{
			
			g2d.drawImage(imageList.get(index1), 40, 40, 200, 200, null);
			g2d.setColor(Color.RED);
			g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
			g2d.drawString(imageNameList.get(index1), 120, 270);
		}
		*/
		//按照图片的顺序进行循环
		/*
		if(start){
			if(remain){
				if(index < imageList.size()){
					g2d.drawImage(imageList.get(index), 40, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index), 120, 270);
					index++;
				}
				else
					index = 0;
			}else{
				if(index == imageList.size()){
					g2d.drawImage(imageList.get(index - 1), 40, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index - 1), 120, 270);
				}
				else{
					g2d.drawImage(imageList.get(index), 40, 40, 200, 200, null);
					g2d.setColor(Color.RED);
					g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
					g2d.drawString(imageNameList.get(index), 120, 270);
				}
					
			}
		}
		else{//点击了鼠标左键,停止滚动,如果恰好index == imageList.size(),则重设index;
			if(index == imageList.size()){
				g2d.drawImage(imageList.get(index - 1), 40, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index - 1), 120, 270);
			}
			else{
				g2d.drawImage(imageList.get(index), 40, 40, 200, 200, null);
				g2d.setColor(Color.RED);
				g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
				g2d.drawString(imageNameList.get(index), 120, 270);
			}
		}
		*/
		
		//g.drawImage(imageList.get(index), 300, 40, 200, 200, null);
		//index++;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(MainInterface.BORDER_COLOR_HYALINE);// 颜色带透明
		
		//绘制圆角矩形
		g2d.fillRoundRect(550, 350, 250, 200, 30, 40);
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 20));
		
		g2d.drawString("一等奖剩余: ", MainInterface.PRIZE_X, MainInterface.PRIZE_Y);
		g2d.drawString("二等奖剩余: ", MainInterface.PRIZE_X, MainInterface.PRIZE_Y + 50);
		g2d.drawString("三等奖剩余: ", MainInterface.PRIZE_X, MainInterface.PRIZE_Y + 100);
		
		g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 40));
		g2d.setColor(Color.RED);
		g2d.drawString(String.valueOf(firstPrize), MainInterface.PRIZE_X + 140, MainInterface.PRIZE_Y + 10);
		g2d.drawString(String.valueOf(secondPrize), MainInterface.PRIZE_X + 140, MainInterface.PRIZE_Y + 60);
		g2d.drawString(String.valueOf(thirdPrize), MainInterface.PRIZE_X + 140, MainInterface.PRIZE_Y + 110);
		
	}

	// 监听
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("begin".equals(cmd)) {
			//重新绘制MainPanel
			repaint();
		}
	}
}