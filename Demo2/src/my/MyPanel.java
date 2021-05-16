package my;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * MyPanel.java.
 * 
 * @author Jeremy Zhang
 */
public class MyPanel extends JPanel implements MouseMotionListener {
	int x = 400, y = 100;
	int n = 1, m = 1;
	static int num = 2; // 小球数量
	int x1 = 1, y1 = 1; // 鼠标坐标
	Ball b;
	ArrayList<Ball> blist;
	JLabel time = null;
	static long start, end; // 计时
	static long sum_time;
	static int setting = 1; // 标记难度 1-3 简单中等困难
//	MyListener ml=null;

	public MyPanel() {
//	    b = new Ball();

//		b.setPanel(this);
//		Graphics g;
//		g = this.getGraphics();
//		MyListener ml = new MyListener(g);
//		this.addMouseListener(ml);
//		this.addMouseMotionListener(new);
		this.addMouseMotionListener(this);
		time = new JLabel();
		this.add(time);
	}

	public void Setnum(int n) {
		this.n = n;
	}

	// 设置难度
	public void Settings() {

	}

	public void My_start() {
		x1 = y1 = 100;
		blist = null;
		blist = new ArrayList<Ball>();
		for (int i = 0; i < num; i++) {
			blist.add(new Ball());
		}
		for (int i = 0; i < blist.size(); i++) {
			blist.get(i).setPanel(this);
		}
		// 难度分级
		Random ran = new Random();

		if (setting == 1) {
			for (int i = 0; i < blist.size(); i++) {
				blist.get(i).x = ran.nextInt(500);
				blist.get(i).y = ran.nextInt(500);
				blist.get(i).m = ran.nextInt(10) + 1;
				blist.get(i).n = ran.nextInt(10) + 1;
				blist.get(i).start();
			}
		}
		if (setting == 2) {
			for (int i = 0; i < blist.size(); i++) {
				blist.get(i).x = ran.nextInt(500);
				blist.get(i).y = ran.nextInt(500);
				blist.get(i).m = ran.nextInt(10) + 10;
				blist.get(i).n = ran.nextInt(10) + 10;
				blist.get(i).start();
			}
		}
		if (setting == 3) {
			for (int i = 0; i < blist.size(); i++) {
				blist.get(i).x = ran.nextInt(500);
				blist.get(i).y = ran.nextInt(500);
				blist.get(i).m = ran.nextInt(10) + 20;
				blist.get(i).n = ran.nextInt(10) + 20;
				blist.get(i).start();
			}
		}
		start = System.currentTimeMillis();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		for (int i = 0; i < blist.size(); i++) {
			g.fillOval(blist.get(i).x, blist.get(i).y, 20, 20);
		}
		g.setColor(Color.BLUE);
		g.fillRect(x1, y1, 20, 20);
		if (setting == 1)
			time.setText("当前为简单模式 小球数量:" + num + "   你已经坚持了" + sum_time + "秒");
		if (setting == 2)
			time.setText("当前为一般模式 小球数量:" + num + "   你已经坚持了" + sum_time + "秒");
		if (setting == 3)
			time.setText("当前为困难模式 小球数量:" + num + "   你已经坚持了" + sum_time + "秒");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		x1 = e.getX();
		y1 = e.getY();
	}
}

class Ball extends Thread {
	int x = 400;
	int y = 100;
	int n = 10, m = 5;
	MyPanel p;
	int index = 0;
	private volatile Thread blinker;
	static int nnn = 0;

	public void setPanel(MyPanel p) {
		this.p = p;
	}

//线程停止	
	public void stop1() {
		blinker = null;
	}
//判断游戏失败条件

	public int judge() {
		int k = 0;
		if (index == 1)
			return 1;
		MyPanel.end = System.currentTimeMillis();
		MyPanel.sum_time = (MyPanel.end - MyPanel.start) / 1000;
//		p.jl.setText(MyPanel.sum_time+"秒");
		// case 1
		int a1 = Math.min(Math.abs(p.x1 - (x + 10)), Math.abs(p.x1 + 20 - (x + 10)));
		int b1 = Math.min(Math.abs(p.y1 - (y + 10)), Math.abs(p.y1 + 20 - (y + 10)));
		if (a1 * a1 + b1 * b1 < 100) {
			k = 1;
			System.out.println("case1");
		}
		// case 2
		else if (Math.abs(x - p.x1) < 20 && Math.abs(y - p.y1) < 20) {
			k = 1;
			System.out.println("case2");
		}
		// case 3
		else if (p.x1 >= p.getWidth() - 20 || p.x1 <= 0 || p.y1 <= 0 || p.y1 >= p.getHeight() - 20) {
			k = 1;
			System.out.println("case3 x1 y1" + p.x1 + " " + p.y1);
		}
//		if (Math.pow((x - p.x1), 2) + Math.pow((y - p.y1), 2) <= Math.pow(10+Math.pow(200, 0.5), 2)) {
//			k = 1;	
//		}
//		if(x>p.x1&&Math.pow((x - p.x1), 2) + Math.pow((y - p.y1), 2) <=Math.pow(800, 0.5)) {
//			k=1;
//		}
		if (k == 1 && index == 0) {
//			p.blist
			for (int i = 0; i < p.blist.size(); i++) {
				p.blist.get(i).stop1();
				p.blist.get(i).index = 1;
//				System.out.println("球"+(i+1)+"销毁");
			}
			int value = JOptionPane.showConfirmDialog(null, "您坚持了" + MyPanel.sum_time + "s" + "，是否继续游戏?");
			if (value == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			if (value == JOptionPane.YES_OPTION) {
				p.My_start();
			}
//			stop1();
		}
		return 0;
	}

	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		blinker = thisThread;
		while (blinker == thisThread) {
			y += m;
			x += n;
			if (y > p.getHeight() - 20 || y < 0) {
				m = -m;
			} else {
			}
			if (x < 0 || x > p.getWidth() - 20) {
				n = -n;
			}
			p.repaint();
			judge();
			try {
				sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("线程结束" + (++nnn) + " 共" + p.blist.size() + "球");
	}
}
