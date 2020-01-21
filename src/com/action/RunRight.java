package com.action;


import javax.swing.*;
import com.frame.MainFrame;
import com.others.State;
import com.others.Tools;

public class RunRight {
	public JLabel go() {
		JLabel jLabel=new JLabel();
		Tools.cgJLabelImg(jLabel, "Image/RunRight/0.png");
		
        new Thread(() -> {
			int i = 1;
			try {
				while (true) {
					Thread.sleep(200);
					Tools.cgJLabelImg(jLabel, "Image/RunRight/" + i++ + ".png");
					if (i > 3) i = 0;

					if (MainFrame.actionState!= State.RUN_RIGHT) {
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
        return jLabel;
	}
	

}
