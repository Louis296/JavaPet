package com.frame;


import com.others.State;
import com.others.Tools;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MailFrame {
    public void go(){
        MainFrame.haveOtherFrame=true;
        JFrame frame=new JFrame("给wxl的信");

        JPanel textPanel=new JPanel();
        textPanel.setBorder(new EmptyBorder(5,5,5,5));
        textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.Y_AXIS));
        JTextArea mailText=new JTextArea();
        mailText.setLineWrap(true);
        mailText.setEditable(false);
        mailText.setText("你好呀亲爱的：");
        mailText.append("\n    呼~~一下子还有点紧张，毕竟这是写给未来的wxl的，不知道未来的wxl看到这封信的时候在干什么呢？但现在的我正在一边和你打电话一边码代码哦！");
        mailText.append("\n    我觉得你应该是看不到这封信的。把火柴人养到一百岁，我算了一下，按照你的性格，最快也要过个一年左右吧，这个程序能在你的电脑里撑住一年我可能就要谢天谢地了，毕竟没准他里面还藏了什么我没找出来的bug，然后程序崩溃掉了也说不定的，况且我还把这封信藏的这么深。但如果你能看到这里，我还挺开心的，因为你有认真的在对待我送给你的东西，就像我每次都把你给我的东西小心放好一样。");
        mailText.append("\n    我真的好喜欢你啊，每次写东西的时候我都觉得自已好俗套，只会翻来覆去地说这一句，也难怪会次次都被你骂文笔烂，但我真的好喜欢你。可能也是出于这一点我才想写点东西藏在程序里，因为这样我就可以跨越时间地对你说出这句话啦！我最爱你了。");
        mailText.append("\n    现在的我们在干什么呢？时间过的好快，也许看这封信的时候，我们已经在一起好久好久了。我们会不会在吵架呀，如果是的话你现在就跟我说，“以前的你发消息来骂你了！他骂你是一个大傻蛋白痴，居然又惹你的宝宝生气！”hhhhhh。那要是我们没在吵架的话，你就把我拉过来，抱紧我，再亲我一口好不好。每次你把我抱住的时候，我都觉得好幸福，好有归属感。其实我并不是一个对待生活特别乐观的人，是不是挺惊讶的，我也是一个内心挺丧的人，但你却给我带来的好多好多光明，让我觉得生活也有了奔头，有了为之奋斗和努力的理由。");
        mailText.append("\n    如果。。。我是说如果，如果发生了最糟糕的事情，比如我们现在因为什么事情分开了，当然我觉得这是不可能的哦！但如果发生了，我一定要代未来的我和你道歉，未来的我一定又是因为在和你赌气，才不小心把你弄丢的。我最了解我自已了，我超过一个多小时没有收到你的消息就会心神不安的，更别说和你分开了，我可能心都要碎掉了吧。但我一定不会和你讲的，所以就由这个2020年初的我来告诉你吧，如果发生了这样的事，未来的我现在一定是一副硬撑着再也不理你，表面装着什么都没发生的死样子。其实对付我可简单了呢，只要你找我说想见我，我就会屁颠屁颠地跑过去哦！所以真的不要离开我，不然我真的指不定会干出什么傻事来，因为你对我真的好重要好重要。");
        mailText.append("\n    好啦好啦，我真是个啰嗦老太婆，一不小心说了这么多话，总之总结一下就是，收下这份来自2020年初的爱吧！我的爱人。");
        mailText.append("\n    好了我要去陪2020年初的wxl聊天了，她正一边看爱情公寓一边和我吐嘈呢。记得看完也去找找zzw哦，没准他现在正无聊地一边对着电脑发呆，一边想wxl什么时候给他发消息呢。");
        mailText.append("\n    那就这样，爱你哦！");
        mailText.append("\n                                      2020.1.31\n                                        爱你的zzw");
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setViewportView(mailText);
        mailText.setCaretPosition(0);
        JScrollBar scrollBar=scrollPane.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMinimum());
        textPanel.add(scrollPane,BorderLayout.CENTER);

        JButton confirmButton=new JButton("读完了");
        confirmButton.addActionListener(e->Tools.closeFrame(frame));

        MainFrame.actionState= State.STOP;
        MainFrame.mainFrame.setVisible(false);
        frame.setBounds(300,400,400,500);
        frame.getContentPane().add(textPanel, BorderLayout.CENTER);
        frame.getContentPane().add(confirmButton,BorderLayout.SOUTH);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Tools.closeFrame(frame);
            }
        });
        frame.setVisible(true);
    }
}
