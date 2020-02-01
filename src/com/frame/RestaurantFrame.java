package com.frame;

import com.others.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class RestaurantFrame {
    public static ArrayList<FoodList<Food>> restaurants;
    public static boolean atHUST;
    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel HUSTPanel;
    private JMenuBar menuBar;
    public void go(){
        if (MainFrame.haveOtherFrame){
            JOptionPane.showMessageDialog(null,Setting.name+"：麻麻我还挂在鼠标上呢！");
        }
        else{
            atHUST=false;

            MainFrame.haveOtherFrame=true;
            MainFrame.actionState= State.STOP;
            frame=new JFrame("去哪里吃呢？");
            frame.setBounds(300,400,300,250);
            frame.setResizable(false);

            setRestaurants();

            menuBar=new JMenuBar();
            JMenu mainMenu=new JMenu("信息/求助");
            JMenuItem infoItem=new JMenuItem("食堂营业时间");
            infoItem.addActionListener(e->JOptionPane.showMessageDialog(frame,"早上 7:00-9:00\n中午 11:00-13:00\n晚上 18:00-20:00"));
            JMenu helpMenu=new JMenu("华农太难吃了我不想吃华农了.....");
            JMenuItem helpItem=new JMenuItem("去找zzw吃点好的！");
            helpItem.addActionListener(e1 -> {
                if (couldGoHUST()){
                    atHUST=true;
                    HUSTPanel=new JPanel();
                    HUSTPanel.setBorder(new EmptyBorder(5,5,5,5));
                    HUSTPanel.setLayout(new GridLayout(2,3,5,5));
                    JButton xiYi=new JButton(restaurants.get(6).getName());
                    xiYi.addActionListener(e->new FoodFrame().go(frame,restaurants.get(6)));
                    JButton baiJingYuan=new JButton(restaurants.get(7).getName());
                    baiJingYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(7)));
                    JButton xiaoChiCheng=new JButton(restaurants.get(8).getName());
                    xiaoChiCheng.addActionListener(e->new FoodFrame().go(frame,restaurants.get(8)));
                    JButton jiJinYuan=new JButton(restaurants.get(9).getName());
                    jiJinYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(9)));
                    JButton dongYi=new JButton(restaurants.get(10).getName());
                    dongYi.addActionListener(e->new FoodFrame().go(frame,restaurants.get(10)));
                    JButton yunJiu=new JButton(restaurants.get(11).getName());
                    yunJiu.addActionListener(e->{
                        int res=JOptionPane.showConfirmDialog(frame,"麻麻都到华科了，你忍心让我吃韵酒吗","可怜巴巴的"+Setting.name,JOptionPane.YES_NO_OPTION);
                        if (res==0){
                            JOptionPane.showMessageDialog(frame,"好叭。（难过难过难过，超委屈）");
                            new FoodFrame().go(frame,restaurants.get(11));
                        }
                        else{
                            JOptionPane.showMessageDialog(frame,"麻麻最好啦！mua~");
                        }
                    });

                    HUSTPanel.add(xiYi);
                    HUSTPanel.add(baiJingYuan);
                    HUSTPanel.add(xiaoChiCheng);
                    HUSTPanel.add(jiJinYuan);
                    HUSTPanel.add(dongYi);
                    HUSTPanel.add(yunJiu);

                    menuBar.removeAll();
                    menuBar.add(new JMenu("随便吃哦，zzw请客！"));
                    frame.setTitle("到华科咯！");
                    frame.remove(buttonPanel);
                    frame.getContentPane().add(HUSTPanel,BorderLayout.CENTER);
                    frame.validate();
                }else
                    JOptionPane.showMessageDialog(frame, Setting.name+"：md好远哦不想动，过两三天再说吧");
            });
            helpMenu.add(helpItem);
            mainMenu.add(infoItem);
            mainMenu.add(helpMenu);
            menuBar.add(mainMenu);
            frame.setJMenuBar(menuBar);

            buttonPanel=new JPanel();
            buttonPanel.setBorder(new EmptyBorder(5,5,5,5));
            buttonPanel.setLayout(new GridLayout(2,3,5,5));
            JButton taoYuan=new JButton(restaurants.get(0).getName());
            taoYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(0)));
            JButton huiYuan=new JButton(restaurants.get(1).getName());
            huiYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(1)));
            JButton mengZeYuan=new JButton(restaurants.get(2).getName());
            mengZeYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(2)));
            JButton boYuan=new JButton(restaurants.get(3).getName());
            boYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(3)));
            JButton buXingJie=new JButton(restaurants.get(4).getName());
            buXingJie.addActionListener(e->new FoodFrame().go(frame,restaurants.get(4)));
            JButton zhuYuan=new JButton(restaurants.get(5).getName());
            zhuYuan.addActionListener(e->new FoodFrame().go(frame,restaurants.get(5)));
            buttonPanel.add(taoYuan);
            buttonPanel.add(huiYuan);
            buttonPanel.add(mengZeYuan);
            buttonPanel.add(boYuan);
            buttonPanel.add(buXingJie);
            buttonPanel.add(zhuYuan);

            frame.getContentPane().add(buttonPanel,BorderLayout.CENTER);

            MainFrame.mainFrame.setVisible(false);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    Tools.closeFrame(frame);
                }
            });
            frame.setVisible(true);
        }

    }

    private void setRestaurants(){
        restaurants=new ArrayList<>();

        FoodList<Food> taoYuan=new FoodList<>("桃园");
        taoYuan.add(new Food("精品套餐","用散粒的大米辅以精心调制的大量食用油制作而成的佳肴，火柴人吃完就会吐（不是）",0.3));
        taoYuan.add(new Food("蛋包饭","传说是wxl的最爱，但因为wxl吃的时间太长感到厌倦而被抛弃，至今wxl仍会尝试",0.7));
        taoYuan.add(new Food("卤肉饭","听起来很正常的卤肉饭，但其实里面都是玉米，玉米的甜味与卤肉的香味巧妙融合，是不可多得的佳肴（reui）",0.4));
        FoodList<Food> huiYuan=new FoodList<>("荟园");
        huiYuan.add(new Food("水饺","虽然是只有一种馅的普通水饺，但一直是华农的招牌菜，维持着众多华农学子生存的最低保障。这种普通的饺子能引来长队的奇观只会在华农看到",0.7));
        huiYuan.add(new Food("贵贵套餐","这一套餐曾在华农红极一时，但出现了不久后味道就逐渐下降。在历次更迭中更是遵遁了华农食堂一贯“去其精华，取其糟粕”的传统，而且它真的名副其实的贵",0.5));
        huiYuan.add(new Food("麻辣烫","不怎么麻也不怎么烫，但是是一碗味道正常的麻辣烫（你还想期盼什么呢）",0.4));
        FoodList<Food> mengZeYuan=new FoodList<>("梦泽园");
        mengZeYuan.add(new Food("猪脚饭","华农食堂的绝对招牌，美味的猪脚肉配上可口的咸菜，虽然有时米饭会很黏，但瑕不掩瑜，作为wxl钦点的美味佳肴，绝对不可错过！",0.8));
        mengZeYuan.add(new Food("椒盐鸡柳","华农食堂最奇葩的食物！没有之一！又干又硬还没熟的鸡柳配上半生不熟的米饭，只要吃一口就会神魂颠倒。zzw：不到万不得已我不会再吃它了，不，万不得已的时候我也不想吃",0.1));
        mengZeYuan.add(new Food("猪手饭","其实就是多了几块骨头的猪脚饭，但价格却是它的1.5倍",0.7));
        mengZeYuan.add(new Food("铁板滑蛋小鲜肉","小鲜肉不鲜，蛋也不滑，铁板的份量倒是很足。zzw：这不就是一大块鸡蛋上面撒了点像剩菜一样的肉吗！！",0.2));
        FoodList<Food> boYuan=new FoodList<>("博园");
        boYuan.add(new Food("金牌烧鹅饭","zzw：这就是打着十佳窗口名号的大屁眼子饭，难吃的要死",0.3));
        boYuan.add(new Food("番茄龙利鱼","暗红色的汤汁中浸泡着炸过的龙利鱼肉，看似味道很棒，但实际上整套饭里面只有鱼肉是可以吃的，不论汤汁还是配菜，味道都一言难尽",0.2));
        boYuan.add(new Food("经济套餐","在华农生活的小白脸的专属食物，wxl曾经用这道菜来养zzw，又便宜又经济，唯一的缺点就是容易吃死人",0.1));
        FoodList<Food> buXingJie=new FoodList<>("步行街");
        buXingJie.add(new Food("啵啵鱼","华农唯一吃起来还不错的东西，zzw经常会乞求wxl带他去吃这个，但是wxl一般都会残忍地拒绝",0.8));
        buXingJie.add(new Food("黄焖鸡","味道很一般的黄焖鸡，味道和外卖差别不大，但肉量也和外卖一样少",0.7));
        buXingJie.add(new Food("烤肉饭","看起来很好吃的烤肉饭，烤肉的味道不错，但饭却被番茄酱浸泡过，呈现出奇特的酸味,令人难以下咽",0.5));
        FoodList<Food> xiaoMaiBu=new FoodList<>("小卖部");
        xiaoMaiBu.add(new Food("肉松","礼拜四：麻麻麻麻我要吃肉松嘛！！！\n  zzw:醒醒礼拜四这个程序里面没有你！这是火柴人的特供肉松！！",0.1));
        xiaoMaiBu.add(new Food("黄油","一种热量非常高的食物，它的特点是喜欢穿粉色的衣服（不是）",0.2));
        xiaoMaiBu.add(new Food("牛奶","一瓶平凡的牛奶，wxl跑步后经常会买一杯",0.1));

        FoodList<Food> xiYi=new FoodList<>("西一食堂");
        xiYi.add(new Food("葱花炒蛋","wxl最喜欢的菜！没有之一！这道菜具有非常大的现实扭曲力，能让食用过的人内心极度欢乐，甚至感觉到此生无憾。据说wxl因为这道菜深爱上了zzw，这道菜的秘方除了制作人之外至今无人知晓",2.5));
        xiYi.add(new Food("脆皮鸡腿","“做我女朋友好吗wxl”\n    “好”",3));
        xiYi.add(new Food("牛排套餐","wxl每次都会问这个牛排单不单卖，阿姨永远坚定的说“这不是丹麦的！”",1));
        FoodList<Food> baiJingYuan=new FoodList<>("百景园");
        baiJingYuan.add(new Food("灌汤包","wxl&zzw:原来华科也会有这么噩梦的东西，始料未及",0.1));
        baiJingYuan.add(new Food("自助餐","wxl:“这么多好吃的东西啊！有一种皇上选妃的感觉”，“这个好好吃，这个也好好吃”",2));
        FoodList<Food> xiaoChiCheng=new FoodList<>("小吃城");
        xiaoChiCheng.add(new Food("烤肉饭","wxl在小吃城的最爱，每次吃到这个饭就会不理zzw（哼！吃醋！）",2));
        xiaoChiCheng.add(new Food("肠粉","在吃这个东西之前，wxl一直觉得肠粉不好吃。但吃过之后，wxl天天问zzw什么时候带她去吃肠粉。",2));
        xiaoChiCheng.add(new Food("铁板烤肉饭","在又软又嫰的肉上刷上一层酱汁，再配上wxl最喜欢的配菜和米饭，绝对是难得的极品美味",2));
        FoodList<Food> jiJinYuan=new FoodList<>("集锦园");
        jiJinYuan.add(new Food("小炒任选二","集锦园的小炒品味繁多，总是能够受到wxl的青睐。不过这里的小炒比不上西一",2));
        jiJinYuan.add(new Food("蜜汁叉烧饭","zzw觉得很好吃的东西，并向wxl反复推荐，但可惜因为太甜了而没有受到wxl的青睐",1));
        FoodList<Food> dongYi=new FoodList<>("东一");
        dongYi.add(new Food("麻辣香锅","很辣很辣，和华农的麻辣香锅没有什么区别，甚至还更辣一点，还好wxl从来没吃过（小声）",0.2));
        dongYi.add(new Food("烧鹅饭","很油也很腻，偶尔吃一口会觉得很好吃，但吃多了就会觉得有些恶心",1));
        dongYi.add(new Food("鸭腿","又硬又干的鸭腿，仿佛在制作之前被抽干了所有水分，一口咬下去都是干涩的肉质纤维，非常恶心",0.1));
        FoodList<Food> yunJiu=new FoodList<>("韵酒");
        yunJiu.add(new Food("蛋饼","加了非常多的油煎出来的蛋饼，每次吃这个蛋饼的时候都觉得油好像不要钱一样，而且它身为一个蛋饼，好像并没有多少蛋",0.5));
        yunJiu.add(new Food("干干小炒","具有奇怪魔力的小炒，第一口吃下去不会觉得很难吃，但多吃几口就会觉得嘴巴越来越干，有一种水分被抽干的奇妙感觉",0.1));

        restaurants.add(taoYuan);
        restaurants.add(huiYuan);
        restaurants.add(mengZeYuan);
        restaurants.add(boYuan);
        restaurants.add(buXingJie);
        restaurants.add(xiaoMaiBu);

        restaurants.add(xiYi);
        restaurants.add(baiJingYuan);
        restaurants.add(xiaoChiCheng);
        restaurants.add(jiJinYuan);
        restaurants.add(dongYi);
        restaurants.add(yunJiu);
    }

    private boolean couldGoHUST(){
        boolean couldGo;
        Calendar current=Calendar.getInstance();
        couldGo= current.after(Setting.nextGoHUSTCalendar);
        if (couldGo){
            current.add(Calendar.DATE,3);
            Setting.nextGoHUSTCalendar =current;
        }
        return couldGo;
    }
}
