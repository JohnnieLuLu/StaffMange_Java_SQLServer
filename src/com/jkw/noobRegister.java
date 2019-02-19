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
	private JButton add = new JButton("���");
	
	public noobRegister() {
		// TODO Auto-generated constructor stub
		super("��Ա����ְ�Ǽ�");
		this.setSize(600, 300);
		this.setLocation(500, 250);
		
		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBackground(new Color(120,200,255));
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu staff = new JMenu("����");
		menuBar.add(staff);
		
		JMenu staffManage = new JMenu("���¹���");
		staff.add(staffManage);
		
		staffManage.addSeparator();
		JMenu oldGuy = new JMenu("��ְԱ��");
		staffManage.add(oldGuy);
		JMenuItem resignM = new JMenuItem("����");
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
		JMenuItem changeM = new JMenuItem("����");
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
		JMenuItem retire = new JMenuItem("����");
		oldGuy.add(retire);
		retire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenu salary = new JMenu("���ʹ���");
		menuBar.add(salary);
		
		JMenuItem salaryM = new JMenuItem("�޸Ĺ���");
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
		
		JLabel welcome = new JLabel("���ã�" + Loginframe.headName + "����,�뿪ʼΪ���Ĳ���--" + Loginframe.deptName + "--�����Ա��" );
		upPanel.add(welcome);
		welcome.setBounds(15, 10, 350, 20);
		
		JLabel words_1 = new JLabel("����");
		upPanel.add(words_1);
		words_1.setBounds(30, 40, 50, 20);
		
		noobName = new JTextField(10);
		upPanel.add(noobName);
		noobName.setBounds(70, 40, 60, 20);
		
		JLabel words_2 = new JLabel("�Ա�");
		upPanel.add(words_2);
		words_2.setBounds(160, 40, 50,20);
		
		upPanel.add(sexSel);
		sexSel.addItem("��");
		sexSel.addItem("Ů");
		sexSel.setBounds(200, 40, 50, 20);
		
		JLabel words_3 = new JLabel("��������");
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
		
		JLabel words_4 = new JLabel("��ְʱ��");
		upPanel.add(words_4);
		words_4.setBounds(10, 70, 70, 20);
		
		noobHireYear = new JTextField(10);
		upPanel.add(noobHireYear);
		noobHireYear.setBounds(70, 70, 60, 20);
		
		noobHireMon = new JTextField(10);
		upPanel.add(noobHireMon);
		noobHireMon.setBounds(140, 70, 60, 20);
		
		JLabel words_5 = new JLabel("����ID");
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
					JOptionPane.showMessageDialog(null, e1.toString(), "����!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton exit_ = new JButton("�˳�");
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


