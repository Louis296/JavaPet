package com.others;

import com.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tools {
    public static void cgJLabelImg(JLabel jLabel, String imgUrl){
        ImageIcon icon = new ImageIcon(imgUrl);
        int picWidth = icon.getIconWidth(),pinHeight = icon.getIconHeight();
        icon.setImage(icon.getImage().getScaledInstance(picWidth,pinHeight, Image.SCALE_DEFAULT));
        jLabel.setBounds(0,0,picWidth,pinHeight);
        jLabel.setIcon(icon);
    }

    public static void cgJLabelImg(JLabel jLabel,String imgUrl,int x,int y){
        ImageIcon icon = new ImageIcon(imgUrl);
        int picWidth = icon.getIconWidth(),pinHeight = icon.getIconHeight();
        icon.setImage(icon.getImage().getScaledInstance(picWidth,pinHeight, Image.SCALE_DEFAULT));
        jLabel.setBounds(x,y,picWidth,pinHeight);
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
            int i=0;
            while (true){
                i++;
                Thread.sleep(100);
                if (i>second*10)
                    break;
                if (MainFrame.haveOtherFrame)
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void closeFrame(JFrame frame) {
        frame.setVisible(false);
        MainFrame.haveOtherFrame = false;
        MainFrame.action = 0;
        MainFrame.mainFrame.setVisible(true);

    }

    public static String[] getListNameFrom(ArrayList<Food> foods){
        ArrayList<String> s=new ArrayList<>();
        for (Food food:foods){
            s.add(food.getName());
        }
        return s.toArray(new String[s.size()]);
    }
}
