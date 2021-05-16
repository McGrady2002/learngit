package my;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GUI {
	static JMenuBar menubar = new JMenuBar();

	public static void main(String[] args) {

		JFrame frame = new JFrame("弹珠小游戏");
//	    Graphics g;
//	    g=p.getGraphics();
//	    MyListener ml=new MyListener(g);
//	    p.addMouseListener(ml);
//	    p.addMouseMotionListener(ml);
		frame.setJMenuBar(menubar);
		JMenu menu = new JMenu("开始菜单");
		menubar.add(menu);
		JMenu menu2 = new JMenu("难度设置");
		menubar.add(menu2);
		JMenu menu3 = new JMenu("帮助");
		menubar.add(menu3);
		JMenuItem begin=new JMenuItem("开始游戏");
		menu.add(begin);
		JMenuItem set=new JMenuItem("设置小球数量");
		menu2.add(set);
		JMenuItem easy = new JMenuItem("简单");
		menu2.add(easy);
		JMenuItem medium = new JMenuItem("中等");
		menu2.add(medium);
		JMenuItem difficult = new JMenuItem("困难");
		menu2.add(difficult);
		MyListener ml=new MyListener();
		easy.addActionListener(ml);
		medium.addActionListener(ml);
		difficult.addActionListener(ml);
		set.addActionListener(ml);
//		ArrayList<Ball> b = new ArrayList<>();
//		for (int i = 0; i < 3; i++) {
//			b.add(new Ball());
//		}
		MyPanel p = new MyPanel();
	
//	    System.out.println(menubar.);
		/* panel thread, paint the monkey */
		frame.setSize(700, 700);
		JLabel jl = new JLabel("aaa");
//		frame.setLayout(new FlowLayout());
//		frame.add(jl);
	
		frame.add(p);
	    System.out.println(MyPanel.setting);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		p.My_start(); // 框架调用pack()之前 无法获得Jpanel的正确尺寸
	begin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				p.My_start();
			}
		});
	}
}