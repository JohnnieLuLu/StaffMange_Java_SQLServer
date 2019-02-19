package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class AttendanceE extends JFrame{

	private JMenuBar menuBar;
	private JSpinner spin_year,spin_month;
	private JButton search_all = new JButton("查询全部");
	private JButton search_ = new JButton("查询");
	private DefaultTableModel tablemodel;
	
	public AttendanceE() {
		// TODO Auto-generated constructor stub
		super("考勤查询");
		this.setSize(610, 300);
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
				SalaryE sle = new SalaryE();
				sle.setVisible(true);
			}
		});
		
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
		JMenuItem resign = new JMenuItem("辞职");
		resign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ResignE rsg = new ResignE();
				setVisible(false);
				rsg.setVisible(true);
			}
		});
		staff.add(resign);
		
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
		
		JButton exit_ = new JButton("退出");
		basicPanel.add(exit_,BorderLayout.SOUTH);
		exit_.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		JLabel year = new JLabel("年份");
		upPanel.add(year);
		year.setBounds(40, 0, 30, 30);
		
		JLabel month = new JLabel("月份");
		upPanel.add(month);
		month.setBounds(170, 0, 30, 30);
		
		Calendar today = Calendar.getInstance();
		int presentyear = today.get(Calendar.YEAR);
		int nextmonth = today.get(Calendar.MONTH);
		nextmonth = nextmonth % 12 + 1;
		if(nextmonth == 1){
			presentyear++;
		}
		
		spin_year = new JSpinner(new SpinnerNumberModel(presentyear, 2000, presentyear, 1));
		upPanel.add(spin_year);
		spin_year.setBounds(70, 0, 60,30);
		
		spin_month = new JSpinner(new SpinnerNumberModel(nextmonth, 1, 12, 1));
		upPanel.add(spin_month);
		spin_month.setBounds(200, 0, 60,30);
		
		String titles[] = {"职工号","年","月","迟到次数","早退天数","请假次数","旷工次数","考勤扣除(元)"};
		this.tablemodel = new DefaultTableModel(titles,0);
		JTable jtable = new JTable(this.tablemodel);
		JScrollPane scroll = new JScrollPane(jtable);
		scroll.setBounds(0, 35, 600, 270);
		upPanel.add(scroll);
		
		upPanel.add(search_);
		search_.setBounds(300, 0, 60, 30);
		search_.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int y = Integer.parseInt(""+spin_year.getValue());
				int m = Integer.parseInt(""+spin_month.getValue());
				db dbcon = new db();
				try {
					ResultSet rs = dbcon.executeQuery("select * from [attendance] where ID='" + Loginframe.id +"' and year = '" + y +"' and month ='" + m + "'");
					ArrayList<AttendanceEnity> n= new ArrayList<>();
					AttendanceEnity attendance = new AttendanceEnity();
					while(rs.next()){
						attendance.setID(rs.getString("ID"));
						attendance.setYear(rs.getInt("year"));
						attendance.setMon(rs.getInt("month"));
						attendance.setLateNum(rs.getInt("lateNum"));
						attendance.setEarlyNum(rs.getInt("earlyNum"));
						attendance.setHoliNum(rs.getInt("holiNum"));
						attendance.setAbNum(rs.getInt("abNum"));
						attendance.setDeduct(rs.getFloat("deduct"));
						n.add(attendance);
					}
					rs.close();
					tablemodel.setRowCount(n.size());
					for(int j =0 ; j < n.size(); j++){
						tablemodel.setValueAt(n.get(j).getID(), j, 0);
						tablemodel.setValueAt(n.get(j).getYear(), j, 1);
						tablemodel.setValueAt(n.get(j).getMon(), j, 2);
						tablemodel.setValueAt(n.get(j).getLateNum(), j, 3);
						tablemodel.setValueAt(n.get(j).getEarlyNum(), j, 4);
						tablemodel.setValueAt(n.get(j).getHoliNum(), j, 5);
						tablemodel.setValueAt(n.get(j).getAbNum(), j, 6);
						tablemodel.setValueAt(n.get(j).getDeduct(), j, 7);
					}
					dbcon.closeConn();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		upPanel.add(search_all);
		search_all.setBounds(400, 0, 90, 30);
		search_all.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				db dbcon = new db();
				try {
					ResultSet rs = dbcon.executeQuery("select * from [attendance] where ID='" + Loginframe.id +"'");
					ArrayList<AttendanceEnity> n= new ArrayList<>();
					AttendanceEnity attendance = new AttendanceEnity();
					while(rs.next()){
						attendance.setID(rs.getString("ID"));
						attendance.setYear(rs.getInt("year"));
						attendance.setMon(rs.getInt("month"));
						attendance.setLateNum(rs.getInt("lateNum"));
						attendance.setEarlyNum(rs.getInt("earlyNum"));
						attendance.setHoliNum(rs.getInt("holiNum"));
						attendance.setAbNum(rs.getInt("abNum"));
						attendance.setDeduct(rs.getFloat("deduct"));
						n.add(attendance);
					}
					rs.close();
					tablemodel.setRowCount(n.size());
					
					dbcon.closeConn();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		basicPanel.add(upPanel,BorderLayout.CENTER);
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*public static void main(String arg[]){
		AttendanceE jjj = new AttendanceE();
		jjj.setVisible(true);
	}
*/
}
