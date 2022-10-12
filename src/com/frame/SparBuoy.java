package com.frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;

import java.awt.Toolkit;

import com.allpanel.*;

public class SparBuoy extends JDialog
{
    JTabbedPane tp = new JTabbedPane(); //
    public SparBuoy()                   //
    {
        setTitle("号码走势");            //
        setResizable(false);            //
        //
        setIconImage(
                Toolkit.getDefaultToolkit().getImage(SparBuoy.class.getResource("/imgs/log.png"))
        );
        tp.add("第一位",new Apanel()); //把显示第一位~第七位开奖号码的走势面板添加到选项卡面板中
        tp.add("第二位", new Bpanel());
        tp.add("第三位",new Cpanel());
        tp.add("第四位",new Dpanel());
        tp.add("第五位",new Epanel());
        tp.add("第六位",new Fpanel());
        tp.add("第七位",new Gpanel());
        this.getContentPane().add(tp);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //这是号码走势对话框的关闭方式
        this.setBounds(450,100,563,593);    //
    }
    public static void main(String[] args)
    {
        try
        {
            //
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        new SparBuoy().setVisible(true);   //
    }
}
