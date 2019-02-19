package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class updatePassword extends JFrame{

	private JMenuBar menuBar;
	private JButton confirm = new JButton("提交");
	private JPasswordField newPwd,newPwd_;
	
	public updatePassword() {
		// TODO Auto-generated constructor stub
		super("修改密码");
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
		
		JMenuItem risign =  new JMenuItem("辞职");
		staff.add(risign);
		risign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				ResignE res = new ResignE();
				res.setVisible(true);
			}
		});
		staff.addSeparator();

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
		
		
		JLabel words_newPwd = new JLabel("请输入新密码");
		upPanel.add(words_newPwd);
		words_newPwd.setBounds(170, 50, 150, 20);
		
		newPwd = new JPasswordField();
		upPanel.add(newPwd);
		newPwd.setBounds(300, 50, 100, 20);
		
		JLabel words_newPwd_2nd = new JLabel("请再次确认");
		upPanel.add(words_newPwd_2nd);
		words_newPwd_2nd.setBounds(170, 80, 150, 20);
		
		newPwd_ = new JPasswordField();
		upPanel.add(newPwd_);
		newPwd_.setBounds(300, 80, 100, 20);
		
		upPanel.add(confirm);
		confirm.setBounds(250, 130, 60, 30);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				db dbcon = new db();
				String pwd_1 = newPwd.getText();
				String pwd_2 = newPwd_.getText();
				String sql = "update person set Password = '" + pwd_1 + "' where ID = '" + Loginframe.id + "'";
				if(pwd_1.equals(pwd_2)){
					PreparedStatement prestate;
					try {
						prestate = dbcon.PreparedStatement(sql);
						prestate.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "两次密码不一致，扑街！", "错误!", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		basicPanel.add(upPanel,BorderLayout.CENTER);
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new updatePassword();
	}

}
