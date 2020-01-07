package com.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.action.*;
import com.tool.Tools;

public class MainFrame extends JFrame {
	JLabel jLabel;
	int x = 1600,y = 25;
	int left,top;
	public static String actionflag ="Ready";
	public void go() {
		this.setBounds(1600, 25, 500,500);
		this.getContentPane().setLayout(null);
		this.setTitle("测试动画");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if ("Ready"== actionflag){
			jLabel=new Ready().go();
			setMouseMove(jLabel);

		}

		this.add(jLabel);
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.setBackground(new Color(0,0,0,0));
		this.setType(JFrame.Type.UTILITY);

		this.setTray();

		this.setVisible(true);
		while (true){

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

	private void setMouseMove(JLabel j){
		MouseEventListener mouseListener=new MouseEventListener(this);
		j.addMouseListener(mouseListener);
		j.addMouseMotionListener(mouseListener);
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
			danceAction.addActionListener(e->this.setDanceAction());

			MenuItem rabbitAction=new MenuItem("Rabbit");
			rabbitAction.addActionListener(e->this.setRabbitAction());

			Menu actionMenu=new Menu("Action");
			actionMenu.add(danceAction);
			actionMenu.add(rabbitAction);

			popMenu.add(itemOpen);
			popMenu.add(itemClose);
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
		this.remove(jLabel);
		jLabel=new Ready().go();
		setMouseMove(jLabel);
		this.add(jLabel);
		this.repaint();
	}

	private void setRunAction(String direct){
		this.remove(jLabel);
		if (direct.equals("R")){
			actionflag="RunRight";
			jLabel=new RunRight().go();
		}

		if (direct.equals("L")){
			actionflag="RunLeft";
			jLabel=new RunLeft().go();
		}

		setMouseMove(jLabel);
		this.add(jLabel);
		this.repaint();
		if (direct.equals("R")){
			new Thread(()->{
				try{

					while(true){
						Thread.sleep(20);
						this.setLocation(x++,y);

						left=this.getLocationOnScreen().x;

						if("RunRight"!= actionflag){
							break;
						}
						if(left>=1866){

							while(true){
								Thread.sleep(200);
								left=this.getLocationOnScreen().x;
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
						this.setLocation(x--,y);

						left=this.getLocationOnScreen().x;

						if("RunLeft"!= actionflag){
							break;
						}
						if(left<=0){

							while(true){
								Thread.sleep(200);
								left=this.getLocationOnScreen().x;
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
		this.remove(jLabel);
		jLabel=new Dance().go();
		setMouseMove(jLabel);
		this.add(jLabel);
		this.repaint();

	}

	private void setRabbitAction(){
		actionflag ="Rabbit";
		this.remove(jLabel);
		jLabel=new GangguanDance().go();
		setMouseMove(jLabel);
		this.add(jLabel);
		this.repaint();
	}

	private void setThankAction(){
		actionflag="Thanks";
		this.remove(jLabel);
		jLabel=new Thanks().go();
		setMouseMove(jLabel);
		this.add(jLabel);
		this.repaint();

	}

	class MouseEventListener implements MouseInputListener {
	     
	    Point origin=new Point();
	    //鼠标拖拽想要移动的目标组件
	    MainFrame frame;
	     
	    
		public MouseEventListener(MainFrame frame) {
	    	this.frame = frame;
	    	origin = new Point();
	    }

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
			Point p = Tools.getLocation(this.frame.jLabel);
			x=p.x + (e.getX() - origin.x);
			y=p.y + (e.getY() - origin.y);
			this.frame.setLocation(x,y);
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
