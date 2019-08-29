package com.entity;

import java.awt.*;
import javax.swing.*;

public class Ready {
	public JLabel go() {
		JLabel jLabel=new JLabel();
		this.cgJLabelImg(jLabel, "Image/Ready/0.png");
		
        new Thread(new Runnable() {
			
			public void run() {
				int i=1;
				try{
					while (true){
						Thread.sleep(200);
						cgJLabelImg(jLabel,"Image/Ready/"+ i++ +".png");
						if(i>3)i=0;
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
		
        return jLabel;
	}
	
	private void cgJLabelImg(JLabel jLabel,String imgUrl){
		ImageIcon icon = new ImageIcon(imgUrl);
		int picWidth = icon.getIconWidth(),pinHeight = icon.getIconHeight();
		icon.setImage(icon.getImage().getScaledInstance(picWidth,pinHeight, Image.SCALE_DEFAULT));
		jLabel.setBounds(0,0,picWidth,pinHeight);
		jLabel.setIcon(icon);	
		
	}
}
