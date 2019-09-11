package com.action;


import javax.swing.*;
import com.frame.MainFrame;
import com.tool.Tools;

public class Run {
	public JLabel go() {
		JLabel jLabel=new JLabel();
		Tools.cgJLabelImg(jLabel, "Image/Ready/0.png");
		
        Thread runThread=new Thread(() -> {
			int i=1;
			try{
				while (true){
					Thread.sleep(200);
					Tools.cgJLabelImg(jLabel,"Image/Ready/"+ i++ +".png");
					if(i>3)i=0;
					System.out.println(i);
					if("Run"!=MainFrame.flag){
						break;
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		});
        runThread.start();
		
        return jLabel;
	}
	

}
