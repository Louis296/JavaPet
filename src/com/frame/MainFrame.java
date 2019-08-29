package com.frame;

import java.awt.*;
import javax.swing.*;
import com.entity.*;

public class MainFrame extends JFrame {

	
	public void go() {
		this.setBounds(1000, 300, 500, 500);
		this.getContentPane().setLayout(null);
		this.setTitle("���Զ���");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(new Ready().go());
		
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.setBackground(new Color(0,0,0,0));
		this.setType(JFrame.Type.UTILITY);
		this.setVisible(true);
		this.setTray();
		
		
	}
	
	
	
	private void setTray() {
		if(SystemTray.isSupported()) {
			SystemTray tray=SystemTray.getSystemTray();
			
			PopupMenu popMenu=new PopupMenu();
			
			MenuItem itemOpen=new MenuItem("��");
			itemOpen.addActionListener(e->System.out.println("��"));
			
			MenuItem itemExit=new MenuItem("�˳�");
			itemExit.addActionListener(e->System.exit(0));
			
			popMenu.add(itemOpen);
			popMenu.add(itemExit);
			
			ImageIcon icon=new ImageIcon("trayIcon.png");
			Image image=icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT);
			
			TrayIcon trayIcon = new TrayIcon(image,"�������",popMenu);
			trayIcon.setImageAutoSize(true); // ����Ӧ�ߴ磬�������������Ҫ
			
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}


		}
	}
	


}
