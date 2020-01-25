package com.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.action.*;
import com.others.Setting;
import com.others.State;
import com.others.Tools;

public class MainFrame {
	private JLabel jLabel;
	private int x = 1600,y = 25;
	int left,top;
	public static Boolean haveOtherFrame=false;
	public static int action=0;
	public static State actionState=State.READY;
	public static JFrame mainFrame;
	public void go() {
		mainFrame=new JFrame();
		mainFrame.setBounds(1600, 25, 500,500);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jLabel=new JLabel();

		mainFrame.add(jLabel);
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setUndecorated(true);
		mainFrame.setBackground(new Color(0,0,0,0));
		mainFrame.setType(JFrame.Type.UTILITY);

		setTray();

		mainFrame.setVisible(true);

		while(true){
			if (Setting.getAge()==-1)
				doEggAction();
			if (Setting.getAge()>=1&&Setting.getAge()<5)
				doBabyAction();
			if (Setting.getAge()>=5&&Setting.getAge()<10)
				doTeenagerAction();
			if (Setting.getAge()>=10)
				doAdultAction();
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

			MenuItem itemOpen=new MenuItem("显示");
			itemOpen.addActionListener(e->{
				MainFrame.haveOtherFrame=false;
				action=0;
				mainFrame.setVisible(true);
			});

			MenuItem itemExit=new MenuItem("退出");
			itemExit.addActionListener(e->System.exit(0));

			MenuItem itemClose=new MenuItem("隐藏");
			itemClose.addActionListener(e->{
				MainFrame.haveOtherFrame=true;
				mainFrame.setVisible(false);
			});

//			MenuItem danceAction=new MenuItem("dance");
//			danceAction.addActionListener(e->setDanceAction());
//
//			MenuItem rabbitAction=new MenuItem("rabbit");
//			rabbitAction.addActionListener(e->setRabbitAction());

//			Menu actionMenu=new Menu("Action");
//			actionMenu.add(danceAction);
//			actionMenu.add(rabbitAction);

			MenuItem infoItem=new MenuItem("信息面板");
			infoItem.addActionListener(e->new InformationFrame().go());

			MenuItem foodItem=new MenuItem("火柴人的餐厅");
			foodItem.addActionListener(e->new RestaurantFrame().go());

			MenuItem incubatorItem=new MenuItem("孵化器");
			incubatorItem.addActionListener(e->new IncubatorFrame().go());

			popMenu.add(itemOpen);
			popMenu.add(itemClose);
			popMenu.add(foodItem);
			popMenu.add(infoItem);
			popMenu.add(incubatorItem);
//			popMenu.add(actionMenu);
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
		actionState=State.READY;
		mainFrame.remove(jLabel);
		jLabel=new Ready().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();
	}

	private void setRunAction(String direct){
		mainFrame.remove(jLabel);
		if (direct.equals("R")){
			actionState=State.RUN_RIGHT;
			jLabel=new RunRight().go();
		}

		if (direct.equals("L")){
			actionState=State.RUN_LEFT;
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

						if(actionState!=State.RUN_RIGHT){
							break;
						}

						left=mainFrame.getLocationOnScreen().x;

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

                        if(actionState!=State.RUN_LEFT){
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
		actionState=State.DANCE;
		mainFrame.remove(jLabel);
		jLabel=new Dance().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();

	}

	private void setRabbitAction(){
		actionState=State.RABBIT;
		mainFrame.remove(jLabel);
		jLabel=new GangguanDance().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();

	}

	private void setThanksAction(){
		actionState=State.THANKS;
		mainFrame.remove(jLabel);
		jLabel=new Thanks().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();

	}

	private void setEggAction(){
		actionState=State.EGG;
		mainFrame.remove(jLabel);
		jLabel=new Egg().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();

	}

	private void setPlayBallAction(){
		actionState=State.PLAYBALL;
		mainFrame.remove(jLabel);
		jLabel=new PlayBall().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();

	}

	private void setBabyAction(){
		actionState=State.BABY;
		mainFrame.remove(jLabel);
		jLabel=new Baby().go();
		setMouseMove(jLabel);
		mainFrame.add(jLabel);
		mainFrame.repaint();
	}

	private void doEggAction(){
		while(Setting.getAge()==-1){
			if(!MainFrame.haveOtherFrame) {
				setEggAction();
				Tools.pauseProgram(5);
			}
			else{
				try {
					while(MainFrame.haveOtherFrame){
						Thread.sleep(100);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	private void doBabyAction(){
		while(Setting.getAge()>=0&&Setting.getAge()<5){
			if(!MainFrame.haveOtherFrame) {
				setBabyAction();
				Tools.pauseProgram(5);
			}
			else{
				try {
					while(MainFrame.haveOtherFrame){
						Thread.sleep(100);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	private void doTeenagerAction(){
		while(Setting.getAge()>=5&&Setting.getAge()<10){
			if(!MainFrame.haveOtherFrame) {
				setPlayBallAction();
				Tools.pauseProgram(8);
			}
			else{
				try {
					while(MainFrame.haveOtherFrame){
						Thread.sleep(100);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	private void doAdultAction(){
		while (Setting.getAge()>=10){
			if (!MainFrame.haveOtherFrame){
				if (action>5)
					action=0;
				switch (action){
					case 0:
						setReadyAction();
						Tools.pauseProgram(5);
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
						setThanksAction();
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
				action++;
			}
			else{
				try {
					while(MainFrame.haveOtherFrame){
						Thread.sleep(100);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
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
