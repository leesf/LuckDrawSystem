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

public class MainPanel extends JPanel implements ActionListener {
	// 版本序列号
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private BufferedImage buff;
	// 定时器
	private Timer timer;
	// 存放背景图片
	private BufferedImage image;
	// 候选者图片列表
	private List<BufferedImage> imageList = new ArrayList<BufferedImage>();
	// 候选者名字列表
	private List<String> imageNameList = new ArrayList<String>();
	// 使用单例模式
	private static MainPanel mainPanel = new MainPanel();
	// 生成随机数
	private Random random = new Random();

	private static int index1 = 0;
	private static int index2 = 1;
	private static int index3 = 2;
	// 开始标志
	public static boolean start = false;
	// 奖项是否还有剩余
	public static boolean remain = true;
	// 一、二、三等奖数量
	public static int firstPrize = 1;
	public static int secondPrize = 2;
	public static int thirdPrize = 5;
	// 默认为单人模式
	public static int drawMode = 1;
	// 画板
	public static Graphics2D g2d;
	
	private MainPanel() {
		// 完成panel的初始化(添加计时器)
		init();
		// 添加组件,如pen(Graphics),双缓冲BufferedImage
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
			if (file.isDirectory()) {
				String[] fileList = file.list();
				for (int i = 0; i < fileList.length; i++) {
					imageList.add(ImageIO.read(new File("candidate/"
							+ fileList[i])));
					// 分离出候选者姓名
					int indexOf = fileList[i].toString().indexOf('.');
					name = fileList[i].substring(0, indexOf);
					imageNameList.add(name);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// panel中添加计时器,并启动计时器进行触发控制
		timer = new Timer(MainInterface.TIME, this);
		timer.setActionCommand("begin");
		timer.start();
	}

	private void initComs() {
		// 使用双缓冲技术
		buff = new BufferedImage(MainInterface.WIDTH, MainInterface.HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
	}
	
	/**
	 * 此方法会自动被调用进行绘制
	 */
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, null);
		
		if (remain) { // 奖项有剩余
			if (start) { // 开始状态
				switch (MainPanel.drawMode) {
				case 1: // 单人模式
					index1 = random.nextInt(imageList.size());
					break;
				case 2:
					index1 = random.nextInt(imageList.size());
					index2 = random.nextInt(imageList.size());
					// 保证index1与index2不相等
					while (index2 == index1) {
						index2 = random.nextInt(imageList.size());
					}
					break;
				case 3://
					index1 = random.nextInt(imageList.size());
					index2 = random.nextInt(imageList.size());
					// 保证index1与index2不相等
					while (index2 == index1) {
						index2 = random.nextInt(imageList.size());
					}
					// 保证index3、index2、index1均不相等
					index3 = random.nextInt(imageList.size());
					while (index3 == index2 || index3 == index1) {
						index3 = random.nextInt(imageList.size());
					}
					break;
				default:
					break;
				}
				// 在指定位置绘制中奖者图片和姓名
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
			} else { // 单击鼠标左键,停止
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
		} else { // 奖项没有剩余
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

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);		
		String hint = "";
		if (drawMode == 1) {
			hint = "单人模式";
		} else if (drawMode == 2) {
			hint = "双人模式";
		} else {
			hint = "三人模式";
		}
		g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 20));
		g2d.setColor(Color.RED);
		g2d.drawString("当前抽奖模式为: ", MainInterface.PRIZE_X - 60, 
				MainInterface.PRIZE_Y - 70);
		g2d.drawString(hint, MainInterface.PRIZE_X + 115,
				MainInterface.PRIZE_Y - 70);	
		
		

		g2d.setColor(MainInterface.BORDER_COLOR_HYALINE); // 颜色带透明
		// 绘制圆角矩形
		g2d.fillRoundRect(550, 350, 250, 200, 30, 40);

		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 20));

		g2d.drawString("一等奖剩余: ", MainInterface.PRIZE_X, MainInterface.PRIZE_Y);
		g2d.drawString("二等奖剩余: ", MainInterface.PRIZE_X,
				MainInterface.PRIZE_Y + 50);
		g2d.drawString("三等奖剩余: ", MainInterface.PRIZE_X,
				MainInterface.PRIZE_Y + 100);
		
		g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 40));
		g2d.setColor(Color.RED);
		g2d.drawString(String.valueOf(firstPrize), MainInterface.PRIZE_X + 140,
				MainInterface.PRIZE_Y + 10);
		g2d.drawString(String.valueOf(secondPrize),
				MainInterface.PRIZE_X + 140, MainInterface.PRIZE_Y + 60);
		g2d.drawString(String.valueOf(thirdPrize), MainInterface.PRIZE_X + 140,
				MainInterface.PRIZE_Y + 110);
		
	}

	// 监听
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("begin".equals(cmd)) {
			// 重新绘制MainPanel
			repaint();
		}
	}
}