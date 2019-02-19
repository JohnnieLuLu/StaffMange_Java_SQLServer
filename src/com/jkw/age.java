package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
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

public class age extends JFrame{

	private JMenuBar menuBar;
	private JComboBox cmb_ID = new JComboBox();
	private JButton cal = new JButton("计算");
	
	public age() {
		// TODO Auto-generated constructor stub
		super("欢迎你");
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
		
		JMenuItem noob = new JMenuItem("新员工注册");
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
		
		JMenu oldGuy = new JMenu("在职员工");
		staffManage.add(oldGuy);
		
		JMenuItem attendanceM = new JMenuItem("考勤");
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
		
		JMenuItem salaryM = new JMenuItem("发放工资");
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
		
		JButton exit_ = new JButton("退出");
		basicPanel.add(exit_,BorderLayout.SOUTH);
		exit_.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		JLabel id = new JLabel("员工ID");
		upPanel.add(id);
		id.setBounds(170, 50, 50, 20);
		
		upPanel.add(cmb_ID);
		cmb_ID.setBounds(300, 50, 60, 20);
		
		upPanel.add(cal);
		cal.setBounds(250, 100, 60, 30);
		cal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				db dbcon = new db();
				String callProcedure = "{call Pro_CalAge(?,?)}";
				String id = (String) cmb_ID.getSelectedItem();
				try {
					CallableStatement callStmt = dbcon.prepareCall(callProcedure);
					callStmt.setString(1, id);
					callStmt.registerOutParameter(2, java.sql.Types.INTEGER);
					callStmt.execute();
					int i = callStmt.getInt(2);
					callStmt.close();
					JOptionPane.showMessageDialog(null, "该人今年" + i + "岁");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		
		
		basicPanel.add(upPanel,BorderLayout.CENTER);
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
