package com.frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;
import com.db.ConnMySQL;
import com.model.UpDateNumber;

public class MainFrame extends JFrame
{
    private JPanel jcontentPane;                    // 内容面板
    private JButton firstPageButton;                //
    private JButton latePageButton;                 //
    private JButton nextPageButton;                 //
    private JButton lastPageButton;                 //
    private JTable table;                           //表格模型
    private int maxPageNumber;                      //
    private int maxrows = 0;                        //
    private int currentPageNumber = 1;              //
    private double pageSize = 20;                   //
    private DefaultTableModel defaultModel;         //

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
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    //
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    public MainFrame()
    {
        setForeground(Color.BLACK);
        setTitle("明日彩票预测系统");
        setResizable(false);
        //
        setIconImage(
                Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/imgs/log.png"))
        );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//
        setBounds(200,100,1100,600);//
        jcontentPane = new JPanel();//
        jcontentPane.setLayout(new BorderLayout(0,0));//
        setContentPane(jcontentPane);//
        BackgroundPanel contentPane = new BackgroundPanel();//
        //
        contentPane.setImage(getToolkit().getImage(getClass().getResource("/imgs/main.png")));
        jcontentPane.add(contentPane, BorderLayout.CENTER);
        JButton btnNewButton = new JButton(""); //
        //
        btnNewButton.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/10.png")));
        btnNewButton.setBounds(6,114,184,40);//
        contentPane.add(btnNewButton);//
        JButton button = new JButton("");//
        //
        button.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/09.png")));
        button.setBounds(6,74,184,40);//
        contentPane.add(button);//
        JButton button_1 = new JButton("");
        //
        button_1.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/11.png")));
        button_1.setBounds(6,154,184,40);//
        contentPane.add(button_1);//
        JButton updatebutton = new JButton("");//
        //
        updatebutton.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/12.png")));
        updatebutton.setBounds(6,194,184,40);
        contentPane.add(updatebutton);
        JButton button_3 = new JButton("");//
        //
        button_3.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/14.png")));
        button_3.setBounds(6,234,184,40);//
        contentPane.add(button_3);//
        JButton button_4 = new JButton("");//
        //
        button_4.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/15.png")));
        button_4.setBounds(6,274,184,40);//
        contentPane.add(button_4);//
        JButton button_5 = new JButton("");
        button_5.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/17.png")));
        button_5.setBounds(6,314,184,40);//
        contentPane.add(button_5);//
        JButton button_6 = new JButton("");//
        //
        button_6.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/18.png")));
        button_6.setBounds(6,354,184,40);//
        contentPane.add(button_6);//
        JButton button_2 = new JButton("");
        //
        button_2.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/08.png")));
        button_2.setBounds(6,394,184,40);//
        contentPane.add(button_2);//
        JScrollPane scrollPane = new JScrollPane();//
        scrollPane.setBackground(new Color(0,51,204));//
        scrollPane.setBounds(217,74,848,351);//
        contentPane.add(scrollPane);//
        table = new JTable();//
        scrollPane.setViewportView(table);//
        firstPageButton = new JButton("首页");//
        //
        firstPageButton.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/7_08.png")));
        firstPageButton.setBounds(416,439,84,27);//
        contentPane.add(firstPageButton);//
        latePageButton = new JButton("上一页");//
        //
        latePageButton.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/7_10.png")));
        latePageButton.setBounds(550,439,84,27);//
        contentPane.add(latePageButton);//
        nextPageButton = new JButton("下一页");//
        //
        nextPageButton.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/7_09.png")));
        nextPageButton.setBounds(686,439,84,27);//
        contentPane.add(nextPageButton);//
        lastPageButton = new JButton("尾页");//
        //
        lastPageButton.setIcon(new ImageIcon(MainFrame.class.getResource("/img_btn/7_11.png")));
        lastPageButton.setBounds(819,439,84,27);//
        contentPane.add(lastPageButton);
        firstPageButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                do_firstPageButton_actionPerformed(e);
            }
        });
        lastPageButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                do_lastPageButton_actionPerformed(e);
            }
        });
        latePageButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                do_latePageButton_actionPerformed(e);
            }
        });

        nextPageButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                do_nextPageButton_actionPerformed(e);
            }
        });
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);// 为“批量添加号码”按钮添加动作事件的监听
            }
        });
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_2_actionPerformed(e);// 为“退出系统”按钮添加动作事件的监听
            }
        });
        button_3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                do_button_3_actionPerformed(e);     //
            }
        });
        selecttable();//
        button_4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                do_button_4_actionPerformed(e);     //
            }
        });
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_5_actionPerformed(e);// 为“中奖查询”按钮添加动作事件的监听
            }
        });
        button_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_6_actionPerformed(e);// 为“历史战绩”按钮添加动作事件的监听
            }
        });
        updatebutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                do_updatebutton_actionPerformed(e);// 为“修改开奖号码”按钮添加动作事件的监听
            }
        });
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_btnNewButton_actionPerformed(e);// 为“添加开奖号码”按钮添加动作事件的监听
            }
        });
    }

    protected void do_button_6_actionPerformed(ActionEvent e) {// “历史战绩”按钮添加动作事件的监听
        SelectForecast selectForecast = new SelectForecast();// 查询历史对话框
        selectForecast.setVisible(true);// 使查询历史对话框可见
        selecttable();// 重新加载表格中的数据
    }
    protected void do_button_2_actionPerformed(ActionEvent e) {// “退出系统”按钮添加动作事件的监听
        System.exit(0);// 退出当前应用程序
    }

    protected void do_button_actionPerformed(ActionEvent e)
    {// “查看历届开奖”按钮添加动作事件的监听
        currentPageNumber = 1;// 将当前页码设置成1
        Vector dataVector = defaultModel.getDataVector();// 获得原表格模型中的数据
        DefaultTableModel newModel = new DefaultTableModel();// 创建新的表格模型
        newModel.setColumnIdentifiers(new Object[] { "期数", "第1位", "第2位", "第3位", "第4位", "第5位", "第6位", "第7位", "开奖时间" });// 定义表头
        for (int i = 0; i < pageSize; i++) {
            newModel.addRow((Vector) dataVector.elementAt(i));// 根据页面大小来获得数据
        }
        table.setModel(newModel);// 设置表格模型
        firstPageButton.setEnabled(false);// 禁用“首页”按钮
        latePageButton.setEnabled(false);// 禁用“上一页”按钮
        nextPageButton.setEnabled(true);// 启用“下一页”按钮
        lastPageButton.setEnabled(true);// 启用“尾页”按钮

    }

    protected void do_btnNewButton_actionPerformed(ActionEvent e)
    {// “添加开奖号码”按钮添加动作事件的监听
        HistoryAddframe historyAddframe = new HistoryAddframe();// 添加开奖对话框
        historyAddframe.setVisible(true);// 使添加号码对话框可见
        selecttable();// 重新加载表格中的数据
    }

    protected void do_button_1_actionPerformed(ActionEvent e) {// “批量添加号码”按钮添加动作事件的监听
        AllAddNumberframe allAddNumberframe = new AllAddNumberframe();// 批量添加对话框
        allAddNumberframe.setVisible(true);// 使批量添加对话框可见
        selecttable();// 重新加载表格中的数据
    }
    protected void do_button_4_actionPerformed(ActionEvent e)
    {
        ForecastAddframe forecastAddframe = new ForecastAddframe(); //
        forecastAddframe.setVisible(true);
    }
    protected void do_button_3_actionPerformed(ActionEvent e)
    {
        SparBuoy sparBuoy = new SparBuoy();         //
        sparBuoy.setVisible(true);                  //
    }

    protected void do_button_5_actionPerformed(ActionEvent e) {// “中奖查询”按钮添加动作事件的监听
        SelectBonusframe selectBonusframe = new SelectBonusframe();// 中奖查询对话框
        selectBonusframe.setVisible(true);// 使中奖查询对话框可见

    }

    protected void do_updatebutton_actionPerformed(ActionEvent e) {// “修改开奖号码”按钮添加动作事件的监听
        int row = table.getSelectedRow();
        if (row == -1) {// 如果用户没有选择任何行，则进行提示
            JOptionPane.showMessageDialog(this, "请选择要修改的奖号", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        UpDateNumber up = new UpDateNumber();// 实例化修改开奖期数
        up.setNumber(Integer.parseInt(table.getValueAt(row, 0).toString()));// 获取所要修改奖号的期数
        HistoryUpdateframe historyUpdateframe = new HistoryUpdateframe();// 修改开奖号码对话框
        historyUpdateframe.setVisible(true);// 使修改开奖号码对话框可见
        selecttable();// 重新加载表格中的数据
    }
    protected void do_latePageButton_actionPerformed(ActionEvent e)
    {
        currentPageNumber--;
        Vector dataVector = defaultModel.getDataVector();//
        DefaultTableModel newModeel = new DefaultTableModel();//
        //
        newModeel.setColumnIdentifiers(new Object[]
                {"期数", "第1位", "第2位", "第3位", "第4位", "第5位", "第6位", "第7位", "开奖时间"});
        for (int i = 0; i < pageSize; i++)
        {
            newModeel.addRow((Vector) dataVector.elementAt((int) (pageSize * (currentPageNumber - 1) + i)));
        }
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(newModeel);
        if (currentPageNumber == 1)
        {
            firstPageButton.setEnabled(false);
            latePageButton.setEnabled(false);
        }
        nextPageButton.setEnabled(true);
        lastPageButton.setEnabled(true);
    }

    protected void do_nextPageButton_actionPerformed(ActionEvent e)
    {
        currentPageNumber++;
        Vector dataVector = defaultModel.getDataVector();//
        DefaultTableModel newModel = new DefaultTableModel();//
        //
        newModel.setColumnIdentifiers(new Object[]
                {"期数", "第1位", "第2位", "第3位", "第4位", "第5位", "第6位", "第7位", "开奖时间"});
        if (currentPageNumber == maxPageNumber)
        {
            int lastPageSize = (int) (defaultModel.getRowCount() - pageSize * (maxPageNumber - 1));
            for (int i = 0; i < lastPageSize; i++)
            {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
            }
            nextPageButton.setEnabled(false);
            lastPageButton.setEnabled(false);
        }
        else
        {
            for (int i = 0; i < pageSize; i++)
            {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (currentPageNumber - 1) + i)));
            }
        }
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(newModel);
        firstPageButton.setEnabled(true);
        latePageButton.setEnabled(true);
    }
    protected void do_firstPageButton_actionPerformed(ActionEvent e)
    {
        currentPageNumber = 1;//
        Vector dataVector = defaultModel.getDataVector();//
        DefaultTableModel newModel = new DefaultTableModel();//
        //
        newModel.setColumnIdentifiers(new Object[]
                {"期数", "第1位", "第2位", "第3位", "第4位", "第5位", "第6位", "第7位", "开奖时间"});
        for (int i = 0; i < pageSize; i++)
        {
            newModel.addRow((Vector) dataVector.elementAt(i));
        }
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(newModel);
        firstPageButton.setEnabled(false);
        latePageButton.setEnabled(false);
        nextPageButton.setEnabled(true);
        lastPageButton.setEnabled(true);
    }
    //
    protected void do_lastPageButton_actionPerformed(ActionEvent e)
    {
        currentPageNumber = maxPageNumber;
        Vector dataVector = defaultModel.getDataVector();//
        DefaultTableModel newModel = new DefaultTableModel();//
        //
        newModel.setColumnIdentifiers(new Object[]
                {"期数", "第1位", "第2位", "第3位", "第4位", "第5位", "第6位", "第7位", "开奖时间"});
        int lastPageSize = (int)(defaultModel.getRowCount() - pageSize * (maxPageNumber - 1));
        if (lastPageSize == maxrows)
        {
            for (int i = 0; i < pageSize; i++)
            {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
            }
        }
        else
        {
            for (int i = 0; i < lastPageSize; i++)
            {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
            }
        }

        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(newModel);
        firstPageButton.setEnabled(true);
        latePageButton.setEnabled(true);
        nextPageButton.setEnabled(false);
        lastPageButton.setEnabled(false);
    }
    public void selecttable()//
    {
        defaultModel = (DefaultTableModel) table.getModel();//
        defaultModel.setRowCount(0);//
        //
        defaultModel.setColumnIdentifiers(new Object[]
                {"期数", "第1位", "第2位", "第3位", "第4位", "第5位", "第6位", "第7位", "开奖时间"});
        String sql = "select count(id) from tb_history";//
        ConnMySQL con = new ConnMySQL();//
        ResultSet rs = con.showAll(sql);
        try
        {
            if (rs.next())//
            {
                maxrows = rs.getInt(1);
            }
        }
        catch (SQLException eq)
        {
            eq.printStackTrace();
        }
        if (maxrows != 0)//
        {
            //
            sql = "select * from tb_history order by number desc";
            rs = con.showAll(sql);
            try
            {
                while (rs.next())
                {
                    defaultModel.addRow(new Object[]
                            {rs.getInt(2),rs.getInt(3),rs.getInt(4),
                             rs.getInt(5),rs.getInt(6),rs.getInt(7),
                             rs.getInt(8),rs.getInt(9), rs.getInt(10)});
                }
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            //
            maxPageNumber = (int) (maxrows % pageSize == 0 ? maxrows/pageSize : maxrows/pageSize + 1);
            DefaultTableModel newModel = new DefaultTableModel();//
            //
            newModel.setColumnIdentifiers(new Object[]
                    {"期数", "第1位", "第2位", "第3位", "第4位", "第5位", "第6位", "第7位", "开奖时间"});
            for (int i = 0; i < pageSize; i++)
            {
                //
                newModel.addRow((Vector) defaultModel.getDataVector().elementAt(i));
            }
            table.getTableHeader().setReorderingAllowed(false);
            table.setModel(newModel);//
            firstPageButton.setEnabled(false);//
            latePageButton.setEnabled(false);//
            nextPageButton.setEnabled(true);//
            lastPageButton.setEnabled(true);//
        }
        else
        {
            firstPageButton.setEnabled(false);//
            latePageButton.setEnabled(false);//
            nextPageButton.setEnabled(false);//
            latePageButton.setEnabled(false);//
        }
    }
}