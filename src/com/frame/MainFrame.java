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
	public static int action=0;
	public static String actionflag ="Ready";
	public static JFrame mainFrame;
	public void go() {
		mainFrame=new JFrame();
		mainFrame.setBounds(1600, 25, 500,500);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		jLabel=new Ready().go();
		setMouseMove(jLabel);



		mainFrame.add(jLabel);
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setUndecorated(true);
		mainFrame.setBackground(new Color(0,0,0,0));
		mainFrame.setType(JFrame.Type.UTILITY);

		setTray();

		mainFrame.setVisible(true);
		while (true){

			if (action>5)
				action=0;
			if (!InformationFrame.infoflag){

                switch (action){
                    case 0:
                        setReadyAction();
                        Tools.pauseProgram(3);
                        break;
                    case 1:
                        setRunAction("L");
                        Tools.pauseProgram(5);
                        break;
                    case 2:
                        setDanceAction();
                        Tools.pauseProgram(7);
                        break;
                    case 3:
                        setThankAction();
                        Tools.pauseProgram(6);
                        break;
                    case 4:
                        setRunAction("R");
                        Tools.pauseProgram(5);
                        break;
                    case 5:
                        setDanceAction();
                        Tools.pauseProgram(7);
                        break;
                }
            }

            action++;
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

			MenuItem danceAction=new MenuItem("dance");
			danceAction.addActionListener(e->setDanceAction());

			MenuItem rabbitAction=new MenuItem("rabbit");
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

			TrayIcon trayIcon = new TrayIcon(image,"Wxl的火柴人",popMenu);
			trayIcon.setImageAutoSize(true); // 自适应尺寸，这个属性至关重要

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

                        if("RunLeft"!= actionflag){
                            break;
                        }

						left=mainFrame.getLocationOnScreen().x;


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
		//鼠标拖拽想要移动的目标组件


		@Override
		public void mousePressed(MouseEvent e) {
			origin.x = e.getX();
			origin.y = e.getY();
		}

		/**
		 * 鼠标在标题栏拖拽时，设置窗口的坐标位置
		 * 窗口新的坐标位置 = 移动前坐标位置+（鼠标指针当前坐标-鼠标按下时指针的位置）
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
