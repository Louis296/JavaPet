package com.tool;

import javax.swing.*;
import java.awt.*;

public class Tools {
    public static void cgJLabelImg(JLabel jLabel, String imgUrl){

        ImageIcon icon = new ImageIcon(imgUrl);
        int picWidth = icon.getIconWidth(),pinHeight = icon.getIconHeight();
        icon.setImage(icon.getImage().getScaledInstance(picWidth,pinHeight, Image.SCALE_DEFAULT));
        jLabel.setBounds(0,0,picWidth,pinHeight);
        jLabel.setIcon(icon);

    }

    public static Point getLocation(JLabel jLabel){
        Point p=new Point();
        p.x=jLabel.getX();
        p.y=jLabel.getY();
        SwingUtilities.convertPointToScreen(p,jLabel);
        return p;
    }

    public static void pauseProgram(int second){
        try {
            Thread.sleep(second*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
