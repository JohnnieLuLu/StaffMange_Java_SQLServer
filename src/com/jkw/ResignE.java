package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ResignE extends JFrame{
	
	private JButton confirm = new JButton("提交");
	private JButton cancel = new JButton("取消");
	private JMenuBar menuBar;
	private JSpinner spin_year,spin_month;
	
	public ResignE() {
		// TODO Auto-generated constructor stub
		super("辞职申请");
		this.setSize(600, 300);
		this.setLocation(500, 250);
		
		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBackground(new Color(120,200,255));
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu search = new JMenu("查询");
		menuBar.add(search);
		
		JMenuItem salary = new JMenuItem("工资");
		search.add(salary);
		salary.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				SalaryE salary_e = new SalaryE();
				salary_e.setVisible(true);
			}
		});
		search.addSeparator();
		JMenuItem attendance = new JMenuItem("考勤");
		attendance.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				AttendanceE attendance_e = new AttendanceE();
				attendance_e.setVisible(true);
			}
		});
		search.add(attendance);
		
		JMenu staff = new JMenu("人事");
		menuBar.add(staff);
		
		JMenuItem change =  new JMenuItem("调动");
		staff.add(change);
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				Change ch = new Change();
				ch.setVisible(true);
			}
		});
		staff.addSeparator();
		
		JMenu update = new JMenu("个人信息更改");
		menuBar.add(update);
		
		JMenuItem pwd = new JMenuItem("个人密码修改");
		pwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				updatePassword upp = new updatePassword();
				upp.setVisible(true);
			}
		});
		
		JLabel words = new JLabel("请问你真的要离开本公司，放弃美好的前程吗？");
		upPanel.add(words);
		words.setBounds(150,20,400,20);
		
		JLabel words_ = new JLabel("请选择您预计离开的时间");
		upPanel.add(words_);
		words_.setBounds(50, 45, 190,25);
		
		Calendar today = Calendar.getInstance();
		int presentyear = today.get(Calendar.YEAR);
		int nextmonth = today.get(Calendar.MONTH);
		nextmonth = nextmonth % 12 + 1;
		if(nextmonth == 1){
			presentyear++;
		}
		
		spin_year = new JSpinner(new SpinnerNumberModel(presentyear, presentyear, presentyear, 1));
		upPanel.add(spin_year);
		spin_year.setBounds(200, 45, 60,25);
		
		spin_month = new JSpinner(new SpinnerNumberModel(nextmonth, nextmonth, nextmonth+3, 1));
		upPanel.add(spin_month);
		spin_month.setBounds(300,45, 60,25);
		
		upPanel.add(confirm);
		confirm.setBounds(200, 90, 60, 25);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				int y = Integer.parseInt(""+spin_year.getValue());
				int m = Integer.parseInt(""+spin_month.getValue());
				db dbcon = new db();
				String sql = "insert into resign values('" + Loginframe.id +"','" + y+"','" + m + "')";
				try {
					PreparedStatement prestate = dbcon.PreparedStatement(sql);
					prestate.executeUpdate();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
		});
		
		upPanel.add(cancel);
		cancel.setBounds(300, 90, 60, 25);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				MainframeE mfe = new MainframeE(Loginframe.headName);
				mfe.setVisible(true);
			}
		});
		
		basicPanel.add(upPanel,BorderLayout.CENTER);
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setVisible(true);
	}

	/*public static void main(String args[]){
		new Resign();
	}*/
}
