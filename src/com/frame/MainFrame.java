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
		this.setTitle("���Զ���");
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
			
			TrayIcon trayIcon = new TrayIcon(image,"zzw�Ļ����",popMenu);
			trayIcon.setImageAutoSize(true); // ����Ӧ�ߴ磬�������������Ҫ
			
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}


		}
	}

	class MouseEventListener implements MouseInputListener {
	     
	    Point origin;
	    //�����ק��Ҫ�ƶ���Ŀ�����
	    MainFrame frame;
	     
	    public MouseEventListener(MainFrame frame) {
	    	this.frame = frame;
	    	origin = new Point();
	    }
	     
	    @Override
	    public void mouseClicked(MouseEvent e) {}
	 
	    /**
	    * ��¼��갴��ʱ�ĵ�
	    */
	    @Override
	    public void mousePressed(MouseEvent e) {
	    	origin.x = e.getX(); 
	    	origin.y = e.getY();
	    }
	 
	    @Override
	    public void mouseReleased(MouseEvent e) {}
	 
	    /**
	    * ����ƽ�������ʱ���������ͼ��Ϊ�ƶ�ͼ��
	    */
	    @Override
	    public void mouseEntered(MouseEvent e) {

	    	this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    }
	     
	    /**
	    * ����Ƴ�������ʱ���������ͼ��ΪĬ��ָ��
	    */
	    @Override
	    public void mouseExited(MouseEvent e) {

	    	this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    }
	 
	    /**
	    * ����ڱ�������קʱ�����ô��ڵ�����λ��
	    * �����µ�����λ�� = �ƶ�ǰ����λ��+�����ָ�뵱ǰ����-��갴��ʱָ���λ�ã�
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
