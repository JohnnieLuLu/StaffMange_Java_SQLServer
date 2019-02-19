package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Change extends JFrame{

	private JMenuBar menuBar;
	private JButton confirm = new JButton("�ύ");
	private JButton cancel = new JButton("ȡ��");
	private JComboBox pro = new JComboBox();
	private JComboBox dept = new JComboBox();
	
	public Change() {
		// TODO Auto-generated constructor stub
		super("��������");
		this.setSize(600, 300);
		this.setLocation(500, 250);
		
		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBackground(new Color(120,200,255));
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu search = new JMenu("��ѯ");
		menuBar.add(search);
		
		JMenuItem salary = new JMenuItem("����");
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
		JMenuItem attendance = new JMenuItem("����");
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
		
		JMenu staff = new JMenu("����");
		menuBar.add(staff);
		
		JMenuItem change =  new JMenuItem("��ְ");
		staff.add(change);
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				ResignE res = new ResignE();
				res.setVisible(true);
			}
		});
		staff.addSeparator();
		
		JMenu update = new JMenu("������Ϣ����");
		menuBar.add(update);
		
		JMenuItem pwd = new JMenuItem("���������޸�");
		pwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				updatePassword upp = new updatePassword();
				upp.setVisible(true);
			}
		});
		
		JLabel word_1 = new JLabel("��ѡ����ϣ������Ĳ���:");
		upPanel.add(word_1);
		word_1.setBounds(90, 30, 180, 20);
		

		dept.addItem("���²�");
		dept.addItem("�з���");
		dept.addItem("�г���");
		dept.addItem("����");
		upPanel.add(dept);
		dept.setBounds(280, 30, 100, 20);
		
		JLabel word_2 = new JLabel("��ѡ����ϣ����ְ��:");
		upPanel.add(word_2);
		word_2.setBounds(90, 60, 180, 20);
		
		pro.addItem("Employee");
		pro.addItem("Manager");
		upPanel.add(pro);
		pro.setBounds(280, 60, 100, 20);
		
		upPanel.add(confirm);
		confirm.setBounds(180, 100, 60, 25);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Calendar today = Calendar.getInstance();
				int presentyear = today.get(Calendar.YEAR);
				int nextmonth = today.get(Calendar.MONTH);
				db dbcon = new db();
				String pro_selected = pro.getSelectedItem().toString();
				String dept_selected = dept.getSelectedItem().toString();
				String sql = "insert into change values('" + Loginframe.id +"','" + presentyear +"','" + nextmonth + "','" + Loginframe.deptName + "','"+dept_selected + "','" + pro_selected + "')";
				if(dept_selected.equals(Loginframe.deptName)){
					if(pro_selected.equals(Loginframe.confirmPro)){
						JOptionPane.showMessageDialog(null, "�����ڸò��ŵ��θ�ְ��", "����!", JOptionPane.ERROR_MESSAGE);
					}
					else{
						PreparedStatement prestate;
						try {
							prestate = dbcon.PreparedStatement(sql);
							prestate.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "���ύ����");
					}
				}
				else{
					PreparedStatement prestate;
					try {
						prestate = dbcon.PreparedStatement(sql);
						prestate.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "���ύ����");
				}
			}
		});
		
		upPanel.add(cancel);
		cancel.setBounds(300, 100, 60, 25);
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
		
	}

}
