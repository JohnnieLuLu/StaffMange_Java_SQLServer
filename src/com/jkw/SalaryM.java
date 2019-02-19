package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class SalaryM extends JFrame{
	
	private JMenuBar menuBar;
	private JComboBox cmb_ID = new JComboBox();
	private JTextField baseSalary,bonus,taxDeduct;
	private int presentyear,presentmonth;
	private JButton confirm = new JButton("ȷ��");
	private JButton achieve = new JButton("��ȡ�۳�");
	
	public SalaryM() {
		// TODO Auto-generated constructor stub
		super("���ʹ���");
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
		
		JMenuItem noob = new JMenuItem("��Ա��ע��");
		staffManage.add(noob);
		noob.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				noobRegister nrg = new noobRegister();
				nrg.setVisible(true);
			}
		});
		staffManage.addSeparator();
		
		JMenu oldGuy = new JMenu("��ְԱ��");
		staffManage.add(oldGuy);
		
		JMenuItem attendanceM = new JMenuItem("����");
		oldGuy.add(attendanceM);
		attendanceM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				AttendanceM atdm = new AttendanceM();
				atdm.setVisible(true);
			}
		});
		oldGuy.addSeparator();
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
		
		JLabel words = new JLabel("���ã�" + Loginframe.headName + "����,�뿪ʼ����" + Loginframe.deptName + "Ա������:" );
		upPanel.add(words);
		words.setBounds(15, 10, 320, 20);
		 
		JLabel  words_1 = new JLabel("Ա�����");
		upPanel.add(words_1);
		words_1.setBounds(30, 40, 70, 20);
		
		upPanel.add(cmb_ID);
		cmb_ID.setBounds(150, 40, 60, 20);
		
		JLabel words_2 = new JLabel("��������");
		upPanel.add(words_2);
		words_2.setBounds(300, 40, 70, 20);
		
		baseSalary = new JTextField(10);
		upPanel.add(baseSalary);
		baseSalary.setBounds(410, 40, 100, 20);
		
		JLabel words_3 = new JLabel("����");
		upPanel.add(words_3);
		words_3.setBounds(30, 75, 70, 20);
		
		bonus = new JTextField(10);
		upPanel.add(bonus);
		bonus.setBounds(150, 75, 70, 20);
		
		JLabel words_5 = new JLabel("˰��۳�");
		upPanel.add(words_5);
		words_5.setBounds(30, 110, 70, 20);
		
		taxDeduct = new JTextField(10);
		upPanel.add(taxDeduct);
		taxDeduct.setBounds(150, 110, 100, 20);
		taxDeduct.setEditable(false);
		
		upPanel.add(achieve);
		achieve.setBounds(250, 150, 90, 30);
		achieve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				double taxDedNum = Double.parseDouble(baseSalary.getText());
				taxDeduct.setText(""+taxDedNum*0.1);
			}
		});
		
		upPanel.add(confirm);
		confirm.setBounds(250, 180, 90, 30);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				double taxDedNum = Double.parseDouble(baseSalary.getText());
				
				db dbcon = new db();
				String id = cmb_ID.getSelectedItem().toString();
				PreparedStatement prestate;
				try {
					prestate = dbcon.PreparedStatement("insert into pay values('" + id +"','" + presentyear +"','" + presentmonth + "','" + Integer.parseInt(baseSalary.getText()) + "','" + Integer.parseInt(bonus.getText()) + "','0','" + Double.parseDouble(taxDeduct.getText()) + "')");
					prestate.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"���¹����ѷ��ţ������ظ����ţ�", "����!", JOptionPane.ERROR_MESSAGE);//��������

				}
		
			}
		});
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				db dbcon = new db();
				try {
					ResultSet rs = dbcon.executeQuery("select ID from person where Dept = '"+ Loginframe.deptName + "'");
					while(rs.next()){
						cmb_ID.addItem(rs.getString(1).trim());
					}
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
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
		
		Calendar today = Calendar.getInstance();
		presentyear = today.get(Calendar.YEAR);
		presentmonth = today.get(Calendar.MONTH)+1;
		
	}



	
}
