package com.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.action.*;

public class MainFrame extends JFrame {
	JLabel jLabel;
	public static String flag="Run";
	public void go() {
		this.setBounds(1000, 300, 500,500);
		this.getContentPane().setLayout(null);
		this.setTitle("测试动画");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if ("Run"==flag){
			jLabel=new Run().go();


		}
		this.add(jLabel);
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.setBackground(new Color(0,0,0,0));
		this.setType(JFrame.Type.UTILITY);
		

		this.setTray();
				

		this.setVisible(true);
		
		MouseEventListener mouseListener = new MouseEventListener(this);
	    jLabel.addMouseListener(mouseListener);
	    jLabel.addMouseMotionListener(mouseListener);
	    
		
		
		
	}

	private void setTray() {
		if(SystemTray.isSupported()) {
			SystemTray tray=SystemTray.getSystemTray();
			
			PopupMenu popMenu=new PopupMenu();
			
			MenuItem itemOpen=new MenuItem("Open");
			itemOpen.addActionListener(e->this.setVisible(true));
			
			MenuItem itemExit=new MenuItem("Exit");
			itemExit.addActionListener(e->System.exit(0));

			MenuItem itemClose=new MenuItem("Hide");
			itemClose.addActionListener(e->this.setVisible(false));

			MenuItem danceAction=new MenuItem("Dance");
			danceAction.addActionListener(e->{
				flag="Dance";
				this.remove(jLabel);
				jLabel=new Dance().go();

				this.go();

			});
			Menu actionMenu=new Menu("Action");
			actionMenu.add(danceAction);

			popMenu.add(itemOpen);
			popMenu.add(itemClose);
			popMenu.add(actionMenu);
			popMenu.add(itemExit);

			ImageIcon icon=new ImageIcon("trayIcon.png");
			Image image=icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT);
			
			TrayIcon trayIcon = new TrayIcon(image,"zzw的火柴人",popMenu);
			trayIcon.setImageAutoSize(true); // 自适应尺寸，这个属性至关重要
			
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}


		}
	}

	class MouseEventListener implements MouseInputListener {
	     
	    Point origin;
	    //鼠标拖拽想要移动的目标组件
	    MainFrame frame;
	     
	    public MouseEventListener(MainFrame frame) {
	    	this.frame = frame;
	    	origin = new Point();
	    }
	     
	    @Override
	    public void mouseClicked(MouseEvent e) {}
	 
	    /**
	    * 记录鼠标按下时的点
	    */
	    @Override
	    public void mousePressed(MouseEvent e) {
	    	origin.x = e.getX(); 
	    	origin.y = e.getY();
	    }
	 
	    @Override
	    public void mouseReleased(MouseEvent e) {}
	 
	    /**
	    * 鼠标移进标题栏时，设置鼠标图标为移动图标
	    */
	    @Override
	    public void mouseEntered(MouseEvent e) {

	    	this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    }
	     
	    /**
	    * 鼠标移出标题栏时，设置鼠标图标为默认指针
	    */
	    @Override
	    public void mouseExited(MouseEvent e) {

	    	this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    }
	 
	    /**
	    * 鼠标在标题栏拖拽时，设置窗口的坐标位置
	    * 窗口新的坐标位置 = 移动前坐标位置+（鼠标指针当前坐标-鼠标按下时指针的位置）
	    */
	    @Override
	    public void mouseDragged(MouseEvent e) {
	    	Point p = this.frame.getLocation();
	    	this.frame.setLocation(
	    			p.x + (e.getX() - origin.x), 
	    			p.y + (e.getY() - origin.y)); 
	    }
	 
	    @Override
	    public void mouseMoved(MouseEvent e) {}
	     
	}


}
