package com.frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.event.*;

import com.action.*;
import com.others.DataSaver;
import com.others.Setting;
import com.others.State;
import com.others.Tools;

public class MainFrame {
	private JLabel jLabel;
	private int x = 1600,y = 25;
	private int left;
	private PopupMenu popMenu;
	private MenuItem incubatorItem;
	private MenuItem babyFoodItem;
	private MenuItem restaurantItem;
	private CheckboxMenuItem checkFollowItem;
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
		mainFrame.setAlwaysOnTop(Setting.onTop);
		mainFrame.setUndecorated(true);
		mainFrame.setBackground(new Color(0,0,0,0));
		mainFrame.setType(JFrame.Type.UTILITY);

		setTray();

		mainFrame.setVisible(true);

		while(true){

			if (Setting.getAge()==-1) {
				Setting.programLoading=false;
				incubatorItem=new MenuItem("孵化温室");
				incubatorItem.addActionListener(e->new IncubatorFrame().go());
				popMenu.insert(incubatorItem,2);
				doEggAction();
			}
			if (Setting.getAge()==-2){
				Setting.programLoading=false;
				popMenu.remove(incubatorItem);
				popMenu.remove(2);
				setRabbitAction();
				break;
			}
			if (Setting.getAge()>=0&&Setting.getAge()<5) {
				Setting.programLoading=false;
				popMenu.remove(incubatorItem);
				setBabyFoodItem();
				doBabyAction();
			}
			if (Setting.getAge()>=5&&Setting.getAge()<10) {
				if (!Setting.programLoading) {
					Setting.hobby="华农食堂";
					JOptionPane.showMessageDialog(mainFrame,Setting.name+"长大了！\n解锁了新的用餐地点：华农食堂！\n解锁了新的技能：追随模式！\n（可以挂在麻麻鼠标上啦！）");
				}
				Setting.programLoading=false;

				popMenu.remove(babyFoodItem);
				setRestaurantMenuItem();

				checkFollowItem=new CheckboxMenuItem("追随模式");
				checkFollowItem.addItemListener(new FollowCheckListener());
				popMenu.insert(checkFollowItem,3);

				doTeenagerAction();
			}
			if (Setting.getAge()>=10) {
				if (!Setting.programLoading)
			    	JOptionPane.showMessageDialog(mainFrame,
                        Setting.name+"长大了！\n解锁了新技能：火柴人助理！\n（可以帮麻麻记东西啦！）");
				Setting.programLoading=false;

				popMenu.remove(restaurantItem);
				setRestaurantMenuItem();

				popMenu.remove(checkFollowItem);
				checkFollowItem=new CheckboxMenuItem("追随模式");
				checkFollowItem.addItemListener(new FollowCheckListener());
				popMenu.insert(checkFollowItem,3);

                MenuItem managerItem=new MenuItem("火柴人助理");
                managerItem.addActionListener(e->new ManagerFrame().go());
                popMenu.insert(managerItem,4);

                if (Setting.getAge()>=99){
                    MenuItem mailItem=new MenuItem("zzw的信");
                    mailItem.addActionListener(e->new MailFrame().go());
                    popMenu.insert(mailItem,5);
                }

				doAdultAction();
			}
		}

	}

	private void setMouseMove(JLabel j){
		MouseEventListener mouseListener=new MouseEventListener();
		j.addMouseListener(mouseListener);
		j.addMouseMotionListener(mouseListener);
	}

	private void setRestaurantMenuItem(){
		restaurantItem =new MenuItem("华农食堂");
		restaurantItem.addActionListener(e->new RestaurantFrame().go());
		popMenu.insert(restaurantItem,2);
	}

	private void setBabyFoodItem(){
		babyFoodItem=new MenuItem("婴儿餐厅");
		babyFoodItem.addActionListener(e->new BabyFoodFrame().go());
		popMenu.insert(babyFoodItem,2);
	}

	private void setTray() {
		if(SystemTray.isSupported()) {
			SystemTray tray=SystemTray.getSystemTray();

			popMenu=new PopupMenu();

			MenuItem itemOpen=new MenuItem("显示");
			itemOpen.addActionListener(e->{
				MainFrame.haveOtherFrame=false;
				action=0;
				mainFrame.setVisible(true);
			});

			MenuItem itemExit=new MenuItem("退出");
			itemExit.addActionListener(e->{
				//可在此处暂时关闭存档的自动保存系统以便于程序调试
				try {
					FileOutputStream fs=new FileOutputStream(new File("Data.class"));
					ObjectOutputStream objectOutputStream=new ObjectOutputStream(fs);
					objectOutputStream.writeObject(new DataSaver());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			});

			MenuItem itemClose=new MenuItem("隐藏");
			itemClose.addActionListener(e->{
				MainFrame.haveOtherFrame=true;
				mainFrame.setVisible(false);
			});

			MenuItem infoItem=new MenuItem("信息面板");
			infoItem.addActionListener(e->new InformationFrame().go());

			popMenu.add(itemOpen);
			popMenu.add(itemClose);
			popMenu.add(infoItem);
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
				Tools.pauseProgram(2);

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

				if (isHungry()){
					actionState=State.HUNGRY;
					mainFrame.remove(jLabel);
					jLabel=new Hungry().go();
					setMouseMove(jLabel);
					mainFrame.add(jLabel);
					mainFrame.repaint();
					try {
						while (isHungry())
							Thread.sleep(100);
					}catch (Exception e){
						e.printStackTrace();
					}
				}
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

				if (isHungry()){
					actionState=State.HUNGRY;
					mainFrame.remove(jLabel);
					jLabel=new Hungry().go();
					setMouseMove(jLabel);
					mainFrame.add(jLabel);
					mainFrame.repaint();
					try {
						while (isHungry())
							Thread.sleep(100);
					}catch (Exception e){
						e.printStackTrace();
					}
				}
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

	private boolean isHungry(){
		Calendar current=Calendar.getInstance();
		current.add(Calendar.DAY_OF_YEAR,-1);
		if (current.after(Setting.nextEatCalendar))
			return true;
		return false;
	}

	class FollowCheckListener implements ItemListener{
        Point currentPoint =new Point();
        @Override
        public void itemStateChanged(ItemEvent e) {
        	if (checkFollowItem.getState()){

				haveOtherFrame=true;
				actionState=State.FOLLLOW;
				mainFrame.remove(jLabel);
				jLabel=new Follow().go();
				mainFrame.add(jLabel);
				mainFrame.repaint();

				new Thread(()->{
					try {
						while (checkFollowItem.getState()){
							PointerInfo pointerInfo=MouseInfo.getPointerInfo();
							currentPoint=pointerInfo.getLocation();
							Thread.sleep(20);
						}
					}catch (Exception ex){
						ex.printStackTrace();
					}
				}).start();

				new Thread(()->{
					while (checkFollowItem.getState()){

						try {
							Thread.sleep(20);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
						mainFrame.setLocation(currentPoint.x+5,currentPoint.y+15);
					}
				}).start();
			}

            if (!checkFollowItem.getState()){
				haveOtherFrame=false;
				try {
					Thread.sleep(20);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				mainFrame.setLocation(1600,25);
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
