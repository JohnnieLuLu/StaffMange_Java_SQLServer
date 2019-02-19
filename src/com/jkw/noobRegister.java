package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class noobRegister extends JFrame {

	private JMenuBar menuBar;
	private JTextField noobID,noobName,noobBirthyear,noobHireYear,noobHireMon;
	private JComboBox sexSel = new JComboBox();
	private JSpinner spin_day,spin_month;
	private JButton add = new JButton("添加");
	
	public noobRegister() {
		// TODO Auto-generated constructor stub
		super("新员工入职登记");
		this.setSize(600, 300);
		this.setLocation(500, 250);
		
		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBackground(new Color(120,200,255));
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu staff = new JMenu("人事");
		menuBar.add(staff);
		
		JMenu staffManage = new JMenu("人事管理");
		staff.add(staffManage);
		
		staffManage.addSeparator();
		JMenu oldGuy = new JMenu("在职员工");
		staffManage.add(oldGuy);
		JMenuItem resignM = new JMenuItem("辞退");
		oldGuy.add(resignM);
		resignM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				fuckAway fa = new fuckAway();
				fa.setVisible(true);
			}
		});
		oldGuy.addSeparator();
		JMenuItem changeM = new JMenuItem("调动");
		oldGuy.add(changeM);
		changeM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				handelChange hadc = new handelChange();
				hadc.setVisible(true);
			}
		});
		oldGuy.addSeparator();
		JMenuItem retire = new JMenuItem("退休");
		oldGuy.add(retire);
		retire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenu salary = new JMenu("工资管理");
		menuBar.add(salary);
		
		JMenuItem salaryM = new JMenuItem("修改工资");
		salary.add(salaryM);
		salaryM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				SalaryM salM = new SalaryM();
				salM.setVisible(true);
			}
		});
		
		JLabel welcome = new JLabel("您好，" + Loginframe.headName + "经理,请开始为您的部门--" + Loginframe.deptName + "--添加新员工" );
		upPanel.add(welcome);
		welcome.setBounds(15, 10, 350, 20);
		
		JLabel words_1 = new JLabel("姓名");
		upPanel.add(words_1);
		words_1.setBounds(30, 40, 50, 20);
		
		noobName = new JTextField(10);
		upPanel.add(noobName);
		noobName.setBounds(70, 40, 60, 20);
		
		JLabel words_2 = new JLabel("性别");
		upPanel.add(words_2);
		words_2.setBounds(160, 40, 50,20);
		
		upPanel.add(sexSel);
		sexSel.addItem("男");
		sexSel.addItem("女");
		sexSel.setBounds(200, 40, 50, 20);
		
		JLabel words_3 = new JLabel("出生日期");
		upPanel.add(words_3);
		words_3.setBounds(270, 40, 70, 20);
		
		noobBirthyear = new JTextField(10);
		upPanel.add(noobBirthyear);
		noobBirthyear.setBounds(340, 40, 60, 20);
		
		spin_month = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
		upPanel.add(spin_month);
		spin_month.setBounds(420, 40, 50,20);
		
		spin_day = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
		upPanel.add(spin_day);
		spin_day.setBounds(480, 40, 50,20);
		
		JLabel words_4 = new JLabel("入职时间");
		upPanel.add(words_4);
		words_4.setBounds(10, 70, 70, 20);
		
		noobHireYear = new JTextField(10);
		upPanel.add(noobHireYear);
		noobHireYear.setBounds(70, 70, 60, 20);
		
		noobHireMon = new JTextField(10);
		upPanel.add(noobHireMon);
		noobHireMon.setBounds(140, 70, 60, 20);
		
		JLabel words_5 = new JLabel("新人ID");
		upPanel.add(words_5);
		words_5.setBounds(270, 70, 50, 20);
		
		noobID = new JTextField(10);
		upPanel.add(noobID);
		noobID.setBounds(340, 70, 60, 20);
		
		upPanel.add(add);
		add.setBounds(250, 120, 60, 30);
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				db dbcon = new db();
				String str = noobBirthyear.getText()+ "-" + spin_month.getValue() + "-" + spin_day.getValue();
				String y = noobHireYear.getText();
				String m = noobHireMon.getText();
				try {
					PreparedStatement prestate = dbcon.PreparedStatement("insert into person values('" + noobID.getText().trim() +"','" + noobName.getText().trim() +"','" + sexSel.getSelectedItem() + "','"+str +"','" + y + "','" + m + "','Employee','" + Loginframe.deptName+ "','123')");
					prestate.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.toString(), "错误!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton exit_ = new JButton("退出");
		basicPanel.add(exit_,BorderLayout.SOUTH);
		exit_.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		basicPanel.add(upPanel,BorderLayout.CENTER);
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setVisible(true);
		
	}
	
	/*public static void main(String args[]){
		new noobRegister();
	}*/
}


