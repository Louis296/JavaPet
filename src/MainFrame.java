
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
	
	public void go() {
		this.setSize(200,200);
		this.getContentPane().setLayout(null);
		this.setTitle("测试动画");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel jLabel=new JLabel();
		this.cgJLabelImg(jLabel, "a1.png");
		this.add(jLabel);
		
		this.setUndecorated(true);
		this.setBackground(new Color(0,0,0,0));
		this.setType(JFrame.Type.UTILITY);
		this.setVisible(true);
		this.setTray();
		new Thread(new Runnable() {
			
			public void run() {
				int i=1;
				try{
					while (true){
						Thread.sleep(200);
						cgJLabelImg(jLabel,"Image/Dance/"+"Dance0"+ i++ +".png");
						if(i>72)i=1;
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
		
		
	}
	
	private void cgJLabelImg(JLabel jLabel,String imgUrl){
		ImageIcon icon = new ImageIcon(imgUrl);
		int picWidth = icon.getIconWidth(),pinHeight = icon.getIconHeight();
		icon.setImage(icon.getImage().getScaledInstance(picWidth,pinHeight, Image.SCALE_DEFAULT));
		jLabel.setBounds(0,0,picWidth,pinHeight);
		jLabel.setIcon(icon);	
		
	}
	
	private void setTray() {
		if(SystemTray.isSupported()) {
			SystemTray tray=SystemTray.getSystemTray();
			
			PopupMenu popMenu=new PopupMenu();
			
			MenuItem itemOpen=new MenuItem("打开");
			itemOpen.addActionListener(e->System.out.println("打开"));
			
			MenuItem itemExit=new MenuItem("退出");
			itemExit.addActionListener(e->System.exit(0));
			
			popMenu.add(itemOpen);
			popMenu.add(itemExit);
			
			ImageIcon icon=new ImageIcon("trayIcon.png");
			Image image=icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT);
			
			TrayIcon trayIcon = new TrayIcon(image,"桌面宠物",popMenu);
			trayIcon.setImageAutoSize(true); // 自适应尺寸，这个属性至关重要
			
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}


		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new MainFrame().go();
	}

}
