package com.frame;

import javax.swing.*;

public class WelcomeFrame {
    public WelcomeFrame(){
        int res= JOptionPane.showConfirmDialog(
                null,
                "你是wxl吗？",
                "确认身份",JOptionPane.YES_NO_CANCEL_OPTION);

    }

}
