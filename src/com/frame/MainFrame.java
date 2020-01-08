package com.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.action.*;
import com.tool.Tools;

public class MainFrame {
	JLabel jLabel;
	int x = 1600,y = 25;
	int left,top;
	public static String actionflag ="Ready";
	public static JFrame mainFrame;
	public void go() {
		mainFrame=new JFrame();
		mainFrame.setBounds(1600, 25, 500,500);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setTitle("���Զ���");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if ("Ready"== actionflag){
			jLabel=new Ready().go();
			setMouseMove(jLabel);

		}

		mainFrame.add(jLabel);
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setUndecorated(true);
		mainFrame.setBackground(new Color(0,0,0,0));
		mainFrame.setType(JFrame.Type.UTILITY);

		setTray();

		mainFrame.setVisible(true);
		while (true){
			if (!actionflag.equals("Info")){
				Tools.pauseProgram(30);
				setRunAction("L");
				Tools.pauseProgram(5);
				setDanceAction();
				Tools.pauseProgram(7);
				setThankAction();
				Tools.pauseProgram(6.5f);
				setRunAction("R");
				Tools.pauseProgram(5);
				setDanceAction();
				Tools.pauseProgram(7);
				setReadyAction();
			}

		}

	}

	private void setMouseMove(JLabel j){
		MouseEventListener mouseListener=new MouseEventListener();
		j.addMouseListener(mouseListener);
		j.addMouseMotionListener(mouseListener);
	}

	private void setTray() {
		if(SystemTray.isSupported()) {
			SystemTray tray=SystemTray.getSystemTray();
			
			PopupMenu popMenu=new PopupMenu();
			
			MenuItem itemOpen=new MenuItem("Open");
			itemOpen.addActionListener(e->mainFrame.setVisible(true));
			
			MenuItem itemExit=new MenuItem("Exit");
			itemExit.addActionListener(e->System.exit(0));

			MenuItem itemClose=new MenuItem("Hide");
			itemClose.addActionListener(e->mainFrame.setVisible(false));

			MenuItem danceAction=new MenuItem("Dance");
			danceAction.addActionListener(e->setDanceAction());

			MenuItem rabbitAction=new MenuItem("Rabbit");
			rabbitAction.addActionListener(e->setRabbitAction());

			Menu actionMenu=new Menu("Action");
			actionMenu.add(danceAction);
			actionMenu.add(rabbitAction);

			MenuItem infoItem=new MenuItem("Info");
			infoItem.addActionListener(e->new InformationFrame().go());

			popMenu.add(itemOpen);
			popMenu.add(itemClose);
			popMenu.add(infoItem);
			popMenu.add(actionMenu);
			popMenu.add(itemExit);

			ImageIcon icon=new ImageIcon("Image/MainIcon.png");
			Image image=icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT);
			
			TrayIcon trayIcon = new TrayIcon(image,"Wxl�Ļ����",popMenu);
			trayIcon.setImageAutoSize(true); // ����Ӧ�ߴ磬�������������Ҫ
			
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}


		}
	}

	private void setReadyAction(){
		actionflag="Ready";
		mainFrame.remove(jLabel);
		jLabel=new Ready().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();
	}

	private void setRunAction(String direct){
		mainFrame.remove(jLabel);
		if (direct.equals("R")){
			actionflag="RunRight";
			jLabel=new RunRight().go();
		}

		if (direct.equals("L")){
			actionflag="RunLeft";
			jLabel=new RunLeft().go();
		}

		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();
		if (direct.equals("R")){
			new Thread(()->{
				try{

					while(true){
						Thread.sleep(20);
						mainFrame.setLocation(x++,y);

						left=mainFrame.getLocationOnScreen().x;

						if("RunRight"!= actionflag){
							break;
						}
						if(left>=1866){

							while(true){
								Thread.sleep(200);
								left=mainFrame.getLocationOnScreen().x;
								if(left<1866){
									break;
								}
							}
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
		if (direct.equals("L")){
			new Thread(()->{
				try{

					while(true){
						Thread.sleep(20);
						mainFrame.setLocation(x--,y);

						left=mainFrame.getLocationOnScreen().x;

						if("RunLeft"!= actionflag){
							break;
						}
						if(left<=0){

							while(true){
								Thread.sleep(200);
								left=mainFrame.getLocationOnScreen().x;
								if(left>0){
									break;
								}
							}
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}


	}

	private void setDanceAction(){
		actionflag ="Dance";
		mainFrame.remove(jLabel);
		jLabel=new Dance().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();

	}

	private void setRabbitAction(){
		actionflag ="Rabbit";
		mainFrame.remove(jLabel);
		jLabel=new GangguanDance().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();
	}

	private void setThankAction(){
		actionflag="Thanks";
		mainFrame.remove(jLabel);
		jLabel=new Thanks().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();

	}

	class MouseEventListener implements MouseInputListener {
	     
	    Point origin=new Point();
	    //�����ק��Ҫ�ƶ���Ŀ�����
	    MainFrame frame;


		@Override
	    public void mousePressed(MouseEvent e) {
	    	origin.x = e.getX(); 
	    	origin.y = e.getY();
	    }

		/**
		 * ����ڱ�������קʱ�����ô��ڵ�����λ��
		 * �����µ�����λ�� = �ƶ�ǰ����λ��+�����ָ�뵱ǰ����-��갴��ʱָ���λ�ã�
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			Point p = Tools.getLocation(jLabel);
			x=p.x + (e.getX() - origin.x);
			y=p.y + (e.getY() - origin.y);
			mainFrame.setLocation(x,y);
		}

		@Override
		public void mouseClicked(MouseEvent mouseEvent) {}

		@Override
		public void mouseReleased(MouseEvent mouseEvent) {}

		@Override
		public void mouseEntered(MouseEvent mouseEvent) {}

		@Override
		public void mouseExited(MouseEvent mouseEvent) {}

	    @Override
	    public void mouseMoved(MouseEvent e) {}
	     
	}



}
