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
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AttendanceM extends JFrame{

	private JMenuBar menuBar;
	private JComboBox cmb_ID = new JComboBox();
	private JSpinner spin_year,spin_month,spin_late,spin_early,spin_ab,spin_holiday;
	private JButton confirm = new JButton("确认");
	
	public AttendanceM() {
		// TODO Auto-generated constructor stub
		super("考勤管理");
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
		
		JLabel words = new JLabel("您好，" + Loginframe.headName + "经理,请开始管理" + Loginframe.deptName + "员工考勤:" );
		upPanel.add(words);
		words.setBounds(15, 10, 320, 20);
		
		JLabel words_1 = new JLabel("员工ID");
		upPanel.add(words_1);
		words_1.setBounds(30, 40, 70, 20);
		
		upPanel.add(cmb_ID);
		cmb_ID.setBounds(80, 40, 60, 20);
		
		Calendar today = Calendar.getInstance();
		int presentyear = today.get(Calendar.YEAR);
		int nextmonth = today.get(Calendar.MONTH);
		nextmonth = nextmonth % 12 + 1;
		if(nextmonth == 1){
			presentyear++;
		}
		
		JLabel words_year = new JLabel("年");
		upPanel.add(words_year);
		words_year.setBounds(235, 40, 30, 20);
	
		
		spin_year = new JSpinner(new SpinnerNumberModel(presentyear, 2000, presentyear, 1));
		upPanel.add(spin_year);
		spin_year.setBounds(170, 40, 60,20);
		
		spin_month = new JSpinner(new SpinnerNumberModel(nextmonth, 1, 12, 1));
		upPanel.add(spin_month);
		spin_month.setBounds(250, 40, 60,20);
		
		JLabel words_mon = new JLabel("月");
		upPanel.add(words_mon);
		words_mon.setBounds(315, 40, 60, 20);
		
		JLabel words_late = new JLabel("迟到次数");
		upPanel.add(words_late);
		words_late.setBounds(30, 65, 70, 20);
		
		spin_late = new JSpinner(new SpinnerNumberModel(0, 0, 31, 1));
		upPanel.add(spin_late);
		spin_late.setBounds(90, 65, 60, 20);
		
		JLabel words_early = new JLabel("早退次数");
		upPanel.add(words_early);
		words_early.setBounds(160, 65, 70, 20);
		
		spin_early = new JSpinner(new SpinnerNumberModel(0, 0, 31, 1));
		upPanel.add(spin_early);
		spin_early.setBounds(220, 65, 60, 20);
		
		JLabel words_holi = new JLabel("请假天数");
		upPanel.add(words_holi);
		words_holi.setBounds(290, 65, 70, 20);
		
		spin_holiday = new JSpinner(new SpinnerNumberModel(0, 0, 31, 1));
		upPanel.add(spin_holiday);
		spin_holiday.setBounds(350, 65, 60, 20);
		
		JLabel words_ab = new JLabel("旷工次数");
		upPanel.add(words_ab);
		words_ab.setBounds(420, 65, 70, 20);
		
		spin_ab = new JSpinner(new SpinnerNumberModel(0, 0, 31, 1));
		upPanel.add(spin_ab);
		spin_ab.setBounds(480, 65, 60, 20);
		
		upPanel.add(confirm);
		confirm.setBounds(250, 120, 60, 30);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int y = Integer.parseInt(""+spin_year.getValue());
				int m = Integer.parseInt(""+spin_month.getValue());
				int lat = Integer.parseInt(""+spin_late.getValue());
				int ear = Integer.parseInt(""+spin_early.getValue());
				int holi = Integer.parseInt(""+spin_holiday.getValue());
				int abs = Integer.parseInt(""+spin_ab.getValue());
				db dbcon = new db();
				try {
					PreparedStatement prestate = dbcon.PreparedStatement("insert into attendance values ('" + cmb_ID.getSelectedItem() + "','" + y + "','" + m + "','" + lat + "','" + ear + "','" + holi + "','" + abs + "')");
					prestate.executeUpdate();
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
		new AttendanceM();
	}*/
	
}
