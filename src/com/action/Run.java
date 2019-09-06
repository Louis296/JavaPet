package com.action;


import javax.swing.*;
import com.tool.Tools;

public class Run {
	public JLabel go() {
		JLabel jLabel=new JLabel();
		Tools.cgJLabelImg(jLabel, "Image/Ready/0.png");
		
        new Thread(new Runnable() {
			
			public void run() {
				int i=1;
				try{
					while (true){
						Thread.sleep(200);
						Tools.cgJLabelImg(jLabel,"Image/Ready/"+ i++ +".png");
						if(i>3)i=0;
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
		
        return jLabel;
	}
	

}
