package com.action;

import com.frame.MainFrame;
import com.others.Tools;

import javax.swing.*;

public class GangguanDance {
    public JLabel go(){
        JLabel jLabel=new JLabel();
        Tools.cgJLabelImg(jLabel,"Image/GangGuanDance/gangguantu (0) 0.png");

        new Thread(()->{
          int i=1;
          try{
              while(true){
                  Thread.sleep(100);
                  Tools.cgJLabelImg(jLabel,"Image/GangGuanDance/gangguantu (0) "+ i++ +".png");
                  if (i>196){
                      i=0;
                  }
                  if (!MainFrame.actionflag.equals("Rabbit"))
                      break;
              }
          }catch (Exception e){
              e.printStackTrace();
          }
        }).start();

        return jLabel;
    }

}
