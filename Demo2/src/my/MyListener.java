package my;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
/**
 * 鼠标监听器的类
 * 
 * @author Administrator
 * 
 */
public class MyListener implements ActionListener {
	Graphics g;
	private int x = 100, y = 100;
	private int width = 10, height = 10;
 

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("简单")) {
			MyPanel.setting=1;
			System.out.println(MyPanel.setting);
		}
		if(e.getActionCommand().equals("中等")) {
			MyPanel.setting=2;
			System.out.println(MyPanel.setting);
		}
		if(e.getActionCommand().equals("困难")) {
			MyPanel.setting=3;
			System.out.println(MyPanel.setting);
		}
//		if(e.getActionCommand().equals("开始游戏")) {
//			MyPanel.My_start();
//		}
		if(e.getActionCommand().equals("设置小球数量")) {
			String inputValue = JOptionPane.showInputDialog("请输入您想要的小球数量");
			try {
				MyPanel.num=Integer.parseInt(inputValue);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
//			System.out.println(MyPanel.num);
		}
	}
}
 