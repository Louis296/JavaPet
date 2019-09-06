package com.action;

import javax.swing.*;
import com.tool.Tools;

public class Dance {
    public JLabel go(){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/Dance/0.png");

        new Thread(() -> {
            int i=1;
            try{
                while(true){
                    Thread.sleep(200);
                    Tools.cgJLabelImg(jLabel,"Image/Dance/"+i++ +".png");
                    if(i>3)i=0;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        return jLabel;
    }
}
