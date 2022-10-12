package com.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

import com.dao.ForecastDao;
import com.dao.HistoryDao;
import com.dao.LotteryValidate;

public class ForecastAddframe extends JDialog implements Runnable, ActionListener 
{// 随机选号对话框
	private JPanel jpl;// 内容面板
	JButton bt1 = new JButton();// 显示随机选号第1位的按钮
	JButton bt2 = new JButton();// 显示随机选号第2位的按钮
	JButton bt3 = new JButton();// 显示随机选号第3位的按钮
	JButton bt4 = new JButton();// 显示随机选号第4位的按钮
	JButton bt5 = new JButton();// 显示随机选号第5位的按钮
	JButton bt6 = new JButton();// 显示随机选号第6位的按钮
	JButton bt7 = new JButton();// 显示随机选号第7位的按钮
	JButton but = new JButton("");// “机选一注”按钮
	private final JTextField sevenTextField = new JTextField();// “选号号码”文本框
	private final JLabel label = new JLabel("第");// “第”标签
	private final JLabel label_1 = new JLabel("期");// “期”标签
	private final JTextField numberTextField = new JTextField();// “开奖期数”文本框
	private final JButton btnNewButton = new JButton("");// “购买”按钮
	private final JButton btnNewButton_1 = new JButton("");// “关闭”按钮
	private final JLabel noteLabel = new JLabel("");// “提示”标签
	// 复选框0~9
	private final JCheckBox ckBox_1 = new JCheckBox("1");
	private final JCheckBox ckBox_2 = new JCheckBox("2");
	private final JCheckBox ckBox_3 = new JCheckBox("3");
	private final JCheckBox ckBox_4 = new JCheckBox("4");
	private final JCheckBox ckBox_5 = new JCheckBox("5");
	private final JCheckBox ckBox_6 = new JCheckBox("6");
	private final JCheckBox ckBox_7 = new JCheckBox("7");
	private final JCheckBox ckBox_8 = new JCheckBox("8");
	private final JCheckBox ckBox_9 = new JCheckBox("9");
	private final JCheckBox ckBox_0 = new JCheckBox("0");
	private final JLabel lblTitle_1 = new JLabel("号码");// “号码”标签
	private final JLabel lblSameNum_1 = new JLabel("同号的个数");// “同号的个数”标签
	private final JLabel lblTitle_2 = new JLabel("号码");// “号码”标签
	private final JLabel lblSameNum_2 = new JLabel("同号的个数");// “同号的个数”标签
	// 与0~9复选框对应的文本框
	private JTextField tf_1;
	private JTextField tf_2;
	private JTextField tf_3;
	private JTextField tf_4;
	private JTextField tf_5;
	private JTextField tf_6;
	private JTextField tf_7;
	private JTextField tf_8;
	private JTextField tf_9;
	private JTextField tf_0;
	private JRadioButton rdbtnHave;// 单选按钮“有”
	private JRadioButton rabtnNone;// 单选按钮“无”
	boolean bol = true;// 控制选号时滚动效果的开启(true)与终止(false)
	int index = 0;// 初始化奖号变换时间
	int i = 0;// 初始化奖号
	int a;// 随机选号第1位的数字
	int b;// 随机选号第2位的数字
	int c;// 随机选号第3位的数字
	int d;// 随机选号第4位的数字
	int e;// 随机选号第5位的数字
	int f;// 随机选号第6位的数字
	int g;// 随机选号第7位的数字

	public ForecastAddframe() {// 随机选号对话框的构造方法
		setModal(true);// 使随机选号对话框总在最前
		setTitle("随机选号");// 设置随机选号对话框的标题
		// 设置随机选号对话框的标题图标
		setIconImage(Toolkit.getDefaultToolkit().getImage(ForecastAddframe.class.getResource("/imgs/log.png")));
		setResizable(false);// 随机选号对话框不可改变大小
		HistoryDao his = new HistoryDao();// 实例化操作开奖信息
		numberTextField.setText(his.selectNumber() + "");// 设置“开奖期数”文本框中的内容
		numberTextField.setEditable(false);// 设置“开奖期数”文本框不可编辑
		numberTextField.setBounds(331, 88, 64, 24);// 设置“开奖期数”文本框的位置和宽高
		numberTextField.setColumns(10);// 设置“开奖期数”文本框的宽度
		sevenTextField.setBounds(327, 410, 170, 27);// 设置“选号号码”文本框的位置和宽高
		sevenTextField.setColumns(10);// 设置“选号号码”文本框的宽度
		jpl = new JPanel();// 内容面板
		jpl.setLayout(new BorderLayout(0, 0));// 设置内容面板的布局为边界布局
		setContentPane(jpl);// 把内容面板置于随机选号对话框中
		BackgroundPanel pl = new BackgroundPanel();// 自定义背景面板
		pl.setImage(getToolkit().getImage(getClass().getResource("/imgs/001.png")));// 设置背景面板的图片
		jpl.add(pl, BorderLayout.CENTER);// 添加背景面板到内容面板
		// 设置“机选一注”按钮的图标
		but.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/img_btn/111.png")));
		but.setBounds(240, 410, 62, 28);// 设置“机选一注”按钮的位置以及宽高
		pl.add(but);// 把“机选一注”按钮置于自定义背景面板中
		but.addActionListener(this);// 为“机选一注”按钮添加动作事件的监听
		this.getContentPane().add(pl);// 将随机选号对话框中的控件置于自定义背景面板中
		/*
		 * 设置显示随机选号第1位~第7位的按钮的位置以及宽高， 并把显示随机选号第1位~第7位的按钮置于自定义背景面板中
		 */
		bt1.setBounds(74, 128, 84, 63);
		pl.add(bt1);
		bt2.setBounds(159, 128, 84, 63);
		pl.add(bt2);
		bt3.setBounds(244, 128, 84, 63);
		pl.add(bt3);
		bt4.setBounds(329, 128, 84, 63);
		pl.add(bt4);
		bt5.setBounds(414, 128, 84, 63);
		pl.add(bt5);
		bt6.setBounds(499, 128, 84, 63);
		pl.add(bt6);
		bt7.setBounds(584, 128, 84, 63);
		pl.add(bt7);
		pl.add(sevenTextField);// 把“选号号码”文本框置于自定义背景面板中
		pl.add(numberTextField);// 把“开奖期数”文本框置于自定义背景面板中
		label.setBounds(299, 90, 22, 18);// 设置“第”标签的位置以及宽高
		pl.add(label);// 把“第”标签置于自定义背景面板中
		label_1.setBounds(415, 90, 22, 18);// 设置“期”标签的位置以及宽高
		pl.add(label_1);// 把“期”标签置于自定义背景面板中
		// 设置“购买”按钮的图标
		btnNewButton.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/img_btn/a02.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);// 为“购买”按钮添加动作事件的监听
			}
		});
		btnNewButton.setBounds(239, 451, 109, 74);// 设置“购买”按钮的位置和宽高
		pl.add(btnNewButton);// 把“购买”按钮置于自定义背景面板中
		// 设置“关闭”按钮的图标
		btnNewButton_1.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/img_btn/a07.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_1_actionPerformed(e);// 为“关闭”按钮添加动作事件的监听
			}
		});
		btnNewButton_1.setBounds(389, 451, 108, 79);// 设置“关闭”按钮的位置和宽高
		pl.add(btnNewButton_1);// 把“关闭”按钮置于自定义背景面板中
		noteLabel.setBounds(507, 413, 202, 20);// 设置“提示”标签的位置和宽高
		noteLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));// 设置“提示”标签内字体的样式和大小
		noteLabel.setForeground(Color.RED);// 设置“提示”标签内字体的颜色
		pl.add(noteLabel);// 把“提示”标签添加到背景面板中
		// “机选要求：”标签
		JLabel label_2 = new JLabel("机选要求：");
		label_2.setBounds(280, 214, 62, 15);
		pl.add(label_2);
		// 单选按钮“有”
		rdbtnHave = new JRadioButton("有");
		rdbtnHave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_rdbtnHave_actionPerformed(e);// 单选按钮“有”动作事件的监听
			}
		});
		rdbtnHave.setBounds(350, 210, 40, 23);
		pl.add(rdbtnHave);
		// 单选按钮“无”
		rabtnNone = new JRadioButton("无");
		rabtnNone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_rabtnNone_actionPerformed(e);// 单选按钮“无”动作事件的监听
			}
		});
		rabtnNone.setSelected(true);
		rabtnNone.setBounds(415, 210, 40, 23);
		pl.add(rabtnNone);
		// 按钮组
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnHave);
		group.add(rabtnNone);
		// 复选框“1”
		ckBox_1.setEnabled(false);
		ckBox_1.setBounds(240, 269, 38, 23);
		pl.add(ckBox_1);
		// 复选框“2”
		ckBox_2.setEnabled(false);
		ckBox_2.setBounds(240, 295, 38, 23);
		pl.add(ckBox_2);
		// 复选框“3”
		ckBox_3.setEnabled(false);
		ckBox_3.setBounds(240, 321, 38, 23);
		pl.add(ckBox_3);
		// 复选框“4”
		ckBox_4.setEnabled(false);
		ckBox_4.setBounds(240, 347, 38, 23);
		pl.add(ckBox_4);
		// 复选框“5”
		ckBox_5.setEnabled(false);
		ckBox_5.setBounds(240, 373, 38, 23);
		pl.add(ckBox_5);
		// 复选框“6”
		ckBox_6.setEnabled(false);
		ckBox_6.setBounds(381, 269, 38, 23);
		pl.add(ckBox_6);
		// 复选框“7”
		ckBox_7.setEnabled(false);
		ckBox_7.setBounds(381, 295, 38, 23);
		pl.add(ckBox_7);
		// 复选框“8”
		ckBox_8.setEnabled(false);
		ckBox_8.setBounds(381, 321, 38, 23);
		pl.add(ckBox_8);
		// 复选框“9”
		ckBox_9.setEnabled(false);
		ckBox_9.setBounds(381, 347, 38, 23);
		pl.add(ckBox_9);
		// 复选框“0”
		ckBox_0.setEnabled(false);
		ckBox_0.setBounds(381, 373, 38, 23);
		pl.add(ckBox_0);
		// 与复选框“1”对应的文本框
		tf_1 = new JTextField();
		tf_1.setEnabled(false);
		tf_1.setBounds(290, 270, 66, 21);
		pl.add(tf_1);
		tf_1.setColumns(10);
		// 与复选框“2”对应的文本框
		tf_2 = new JTextField();
		tf_2.setEnabled(false);
		tf_2.setBounds(290, 296, 66, 21);
		pl.add(tf_2);
		tf_2.setColumns(10);
		// 与复选框“3”对应的文本框
		tf_3 = new JTextField();
		tf_3.setEnabled(false);
		tf_3.setBounds(290, 322, 66, 21);
		pl.add(tf_3);
		tf_3.setColumns(10);
		// 与复选框“4”对应的文本框
		tf_4 = new JTextField();
		tf_4.setEnabled(false);
		tf_4.setBounds(290, 348, 66, 21);
		pl.add(tf_4);
		tf_4.setColumns(10);
		// 与复选框“5”对应的文本框
		tf_5 = new JTextField();
		tf_5.setEnabled(false);
		tf_5.setBounds(290, 374, 66, 21);
		pl.add(tf_5);
		tf_5.setColumns(10);
		// 与复选框“6”对应的文本框
		tf_6 = new JTextField();
		tf_6.setEnabled(false);
		tf_6.setBounds(431, 270, 66, 21);
		pl.add(tf_6);
		tf_6.setColumns(10);
		// 与复选框“7”对应的文本框
		tf_7 = new JTextField();
		tf_7.setEnabled(false);
		tf_7.setBounds(431, 296, 66, 21);
		pl.add(tf_7);
		tf_7.setColumns(10);
		// 与复选框“8”对应的文本框
		tf_8 = new JTextField();
		tf_8.setEnabled(false);
		tf_8.setBounds(431, 322, 66, 21);
		pl.add(tf_8);
		tf_8.setColumns(10);
		// 与复选框“9”对应的文本框
		tf_9 = new JTextField();
		tf_9.setEnabled(false);
		tf_9.setBounds(431, 348, 66, 21);
		pl.add(tf_9);
		tf_9.setColumns(10);
		// 与复选框“0”对应的文本框
		tf_0 = new JTextField();
		tf_0.setEnabled(false);
		tf_0.setBounds(431, 374, 66, 21);
		pl.add(tf_0);
		tf_0.setColumns(10);
		// “号码”标签
		lblTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_1.setBounds(240, 249, 38, 15);
		pl.add(lblTitle_1);
		// “同号的个数”标签
		lblSameNum_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSameNum_1.setBounds(290, 249, 66, 15);
		pl.add(lblSameNum_1);
		// “号码”标签
		lblTitle_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_2.setBounds(381, 249, 38, 15);
		pl.add(lblTitle_2);
		// “同号的个数”标签
		lblSameNum_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSameNum_2.setBounds(431, 249, 66, 15);
		pl.add(lblSameNum_2);
		this.setBounds(350, 100, 753, 626);// 随机选号对话框的位置和宽高
	}

	// 单选按钮“无”动作事件的监听
	protected void do_rabtnNone_actionPerformed(ActionEvent e)
	{
		//
		ckBox_1.setSelected(false);
		ckBox_2.setSelected(false);
		ckBox_3.setSelected(false);
		ckBox_4.setSelected(false);
		ckBox_5.setSelected(false);
		ckBox_6.setSelected(false);
		ckBox_7.setSelected(false);
		ckBox_8.setSelected(false);
		ckBox_9.setSelected(false);
		ckBox_0.setSelected(false);
		//
		ckBox_1.setEnabled(false);
		ckBox_2.setEnabled(false);
		ckBox_3.setEnabled(false);
		ckBox_4.setEnabled(false);
		ckBox_5.setEnabled(false);
		ckBox_6.setEnabled(false);
		ckBox_7.setEnabled(false);
		ckBox_8.setEnabled(false);
		ckBox_9.setEnabled(false);
		ckBox_0.setEnabled(false);
		//
		tf_1.setText("");
		tf_2.setText("");
		tf_3.setText("");
		tf_4.setText("");
		tf_5.setText("");
		tf_6.setText("");
		tf_7.setText("");
		tf_8.setText("");
		tf_9.setText("");
		tf_0.setText("");
		//
		tf_1.setEnabled(false);
		tf_2.setEnabled(false);
		tf_3.setEnabled(false);
		tf_4.setEnabled(false);
		tf_5.setEnabled(false);
		tf_6.setEnabled(false);
		tf_7.setEnabled(false);
		tf_8.setEnabled(false);
		tf_9.setEnabled(false);
		tf_0.setEnabled(false);
	}

	// 单选按钮“有”动作事件的监听
	protected void do_rdbtnHave_actionPerformed(ActionEvent e)
	{
		//
		ckBox_1.setEnabled(true);
		ckBox_2.setEnabled(true);
		ckBox_3.setEnabled(true);
		ckBox_4.setEnabled(true);
		ckBox_5.setEnabled(true);
		ckBox_6.setEnabled(true);
		ckBox_7.setEnabled(true);
		ckBox_8.setEnabled(true);
		ckBox_9.setEnabled(true);
		ckBox_0.setEnabled(true);
		//
		itemEvent(ckBox_1, tf_1);
		itemEvent(ckBox_2, tf_2);
		itemEvent(ckBox_3, tf_3);
		itemEvent(ckBox_4, tf_4);
		itemEvent(ckBox_5, tf_5);
		itemEvent(ckBox_6, tf_6);
		itemEvent(ckBox_7, tf_7);
		itemEvent(ckBox_8, tf_8);
		itemEvent(ckBox_9, tf_9);
		itemEvent(ckBox_0, tf_0);
	}

	@Override
	public void run()
	{
		if (rdbtnHave.isSelected() && tf_1.getText().equals("") && tf_2.getText().equals("")
		&& tf_3.getText().equals("") && tf_4.getText().equals("") && tf_5.getText().equals("")
		&& tf_6.getText().equals("") && tf_7.getText().equals("") && tf_8.getText().equals("")
		&& tf_9.getText().equals("") && tf_0.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "警告：文本框不能为空！",
					"警告", JOptionPane.WARNING_MESSAGE);
			but.setEnabled(true);
			return;
		}
		if (rdbtnHave.isSelected() && (exchangeInteger(tf_1) + exchangeInteger(tf_2)
			+ exchangeInteger(tf_3) + exchangeInteger(tf_4) + exchangeInteger(tf_5)
			+ exchangeInteger(tf_6) + exchangeInteger(tf_7) + exchangeInteger(tf_8)
			+ exchangeInteger(tf_9) + exchangeInteger(tf_0) > 7))
		{
			JOptionPane.showMessageDialog(null, "警告：号码个数多于7个！",
					"警告", JOptionPane.WARNING_MESSAGE);
		}
		String s = "";				//
		Random ram = new Random();	//
		if (rabtnNone.isSelected())	//
		{
			while (bol)
			{
				try
				{
					if (i >= 10)		//
					{
						i = 0;
					}
					if (index < (500 - ram.nextInt(20)))
					{
						a = i;
						bt1.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + i + ".png")));
						//
					}
					if (index < (1000 - ram.nextInt(20)))
					{
						b = i;
						bt2.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + i + ".png")));
						//
					}
					if (index < (1500 - ram.nextInt(20)))
					{
						c = i;
						bt3.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + i + ".png")));
						//
					}
					if (index < (2000 - ram.nextInt(20)))
					{
						d = i;
						bt4.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + i + ".png")));
						//
					}
					if (index < (3000 - ram.nextInt(20)))
					{
						e = i;
						bt5.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + i + ".png")));
						//
					}
					if (index < (4000 - ram.nextInt(20)))
					{
						f = i;
						bt6.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + i + ".png")));
						//
					}
					if (index < (5000 - ram.nextInt(20)))
					{
						g = i;
						bt7.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + i + ".png")));
						//
					}
					switch (index)			//
					{
						case 500:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + a);    //
							break;
						case 1000:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + b);    //
							break;
						case 1500:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + c);    //
							break;
						case 2000:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + d);    //
							break;
						case 3000:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + e);    //
							break;
						case 4000:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + f);    //
							break;
						case 5000:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + g);    //
							bol = false;
							but.setEnabled(true);			//
							break;
					}
					i++;									// i = i + 1
					Thread.sleep(0);					//
					index++;								//
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

		if (rdbtnHave.isSelected())							//
		{
			List<Integer> list = new ArrayList<>();			//
			String[] str = new String[10];					//
			//
			str[0] = itemEvent(ckBox_1, tf_1);
			str[1] = itemEvent(ckBox_2, tf_2);
			str[2] = itemEvent(ckBox_3, tf_3);
			str[3] = itemEvent(ckBox_4, tf_4);
			str[4] = itemEvent(ckBox_5, tf_5);
			str[5] = itemEvent(ckBox_6, tf_6);
			str[6] = itemEvent(ckBox_7, tf_7);
			str[7] = itemEvent(ckBox_8, tf_8);
			str[8] = itemEvent(ckBox_9, tf_9);
			str[9] = itemEvent(ckBox_0, tf_0);
			//
			for (int i = 0; i < str.length; i++)
			{
				String[] text = str[i].split(": ");
				if (text.length == 2)
				{
					int number = Integer.parseInt(text[0]);//
					int quantity = Integer.parseInt(text[1]);//
					if (quantity > 1)
					{
						for (int j = 0; j < quantity; j++)
						{
							list.add(number);
						}
					}
					else
					{
						list.add(number);
					}
				}
			}

			//
			while (list.size() < 7)
			{
				int num = ram.nextInt(10);		//
				if (list.contains(num))				//
				{
					continue;						//
				}
				else								//
				{
					list.add(num);					//
				}
			}

			List<Integer> indexes = new ArrayList<>();//
			//
			while (indexes.size() < 7)
			{
				int index = ram.nextInt(7);
				if (indexes.contains(index))
				{
					continue;
				}
				else
				{
					indexes.add(index);
				}
			}

			while (bol)
			{
				try
				{
					if (index < (500 - ram.nextInt(20)))
					{
						a = list.get(indexes.get(0));
						bt1.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + a + ".png")));
						//
					}
					if (index < (1000 - ram.nextInt(20)))
					{
						b = list.get(indexes.get(1));;
						bt2.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + b + ".png")));
						//
					}
					if (index < (1500 - ram.nextInt(20)))
					{
						c = list.get(indexes.get(2));;
						bt3.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + c + ".png")));
						//
					}
					if (index < (2000 - ram.nextInt(20)))
					{
						d = list.get(indexes.get(3));;
						bt4.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + d + ".png")));
						//
					}
					if (index < (3000 - ram.nextInt(20)))
					{
						e = list.get(indexes.get(4));;
						bt5.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + e + ".png")));
						//
					}
					if (index < (4000 - ram.nextInt(20)))
					{
						f = list.get(indexes.get(5));;
						bt6.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + f + ".png")));
						//
					}
					if (index < (5000 - ram.nextInt(20)))
					{
						g = list.get(indexes.get(6));;
						bt7.setIcon(new ImageIcon(ForecastAddframe.class.getResource("/imgs/" + g + ".png")));
						//
					}
					switch (index)			//
					{
						case 500:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + a);    //
							break;
						case 1000:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + b);    //
							break;
						case 1500:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + c);    //
							break;
						case 2000:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + d);    //
							break;
						case 3000:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + e);    //
							break;
						case 4000:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + f);    //
							break;
						case 5000:            //
							s = sevenTextField.getText();    //
							sevenTextField.setText(s + g);    //
							bol = false;
							but.setEnabled(true);			//
							break;
					}
					i++;									// i = i + 1
					Thread.sleep(0);					//
					index++;								//
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		noteLabel.setText("");					//
		bt1.setText("");						//
		bt2.setText("");						//
		bt3.setText("");						//
		bt4.setText("");						//
		bt5.setText("");						//
		bt6.setText("");						//
		bt7.setText("");						//
		sevenTextField.setText("");				//
		bol = true;
		index = 0;								//
		but.setEnabled(false);					//
		Thread th1 = new Thread(this);	//
		th1.start();							//
	}

	// “购买”添加动作事件的监听
	protected void do_btnNewButton_actionPerformed(ActionEvent e)
	{
		LotteryValidate validate = new LotteryValidate();	// 实例化验证信息
		//
		if (validate.validateNumber(sevenTextField.getText()))
		{
			noteLabel.setText("购买号码格式不正确");			//
		}
		//
		if (validate.validateNumber(sevenTextField.getText()))
		{
			noteLabel.setText("");							//
			ForecastDao fr = new ForecastDao();				//
			Boolean b;
			b = fr.addForecastDao(
					Integer.parseInt(numberTextField.getText()), sevenTextField.getText()
					);
			if (b)											//
			{
				noteLabel.setText("购买成功！");				//
				HistoryDao his = new HistoryDao();			//
				numberTextField.setText(his.selectNumber() + "");//
			}
			else
			{
				noteLabel.setText("购买失败！");
			}
		}
	}

	// “关闭”按钮动作事件的监听
	protected void do_btnNewButton_1_actionPerformed(ActionEvent e)
	{
		this.setVisible(false);					//
	}

	// 复选框的选项事件
	private String itemEvent(JCheckBox ckBox, JTextField tf)
	{
		ckBox.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)		//
				{
					tf.setEnabled(true);							//
					tf.setText("1");								//
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED)	//
				{
					tf.setText("");									//
					tf.setEnabled(false);							//
				}
			}
		});
		return ckBox.getText() + ": " + tf.getText();				//
	}

	// 将文本框的内容转换为int型
	private int exchangeInteger(JTextField tf)
	{
		int number;						//
		if ("".equals(tf.getText()))	//
		{
			number = 0;
		}
		else											//
		{
			number = Integer.parseInt(tf.getText());	//
		}
		return number;
	}
}
