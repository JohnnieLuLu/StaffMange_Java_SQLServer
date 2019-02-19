package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.table.*;


public class SalaryE extends JFrame{

	private JMenuBar menuBar;
	private JSpinner spin_year,spin_month;
	private JButton search_all = new JButton("查询全部");
	private JButton search_ = new JButton("查询");
	private DefaultTableModel tablemodel;
	
	
	public SalaryE() {
		// TODO Auto-generated constructor stub
		super("工资查询");
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
		
		JMenuItem attendance = new JMenuItem("考勤");
		search.add(attendance);
		attendance.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				AttendanceE atde = new AttendanceE();
				atde.setVisible(true);
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
		
		String titles[] = {"职工号","年","月","基本工资(元)","奖金(元)","考勤扣除(元)","税务扣除(元)","实发工资(元)"};
		this.tablemodel = new DefaultTableModel(titles,0);
		JTable jtable = new JTable(this.tablemodel);
		JScrollPane scroll = new JScrollPane(jtable);
		scroll.setBounds(0, 35, 600, 270);
		upPanel.add(scroll);
		
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
		basicPanel.add(year);
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
		
		upPanel.add(search_);
		search_.setBounds(300, 0, 60, 30);
		search_.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int y = Integer.parseInt(""+spin_year.getValue());
				int m = Integer.parseInt(""+spin_month.getValue());
				try {
					db dbcon = new db();
					ResultSet rs = dbcon.executeQuery("select * from pay where ID='" + Loginframe.id +"' and year = '" + y +"' and month ='" + m + "'");
					ArrayList<SalaryEnity> v= new ArrayList<>();
					SalaryEnity salary = new SalaryEnity();
					while(rs.next()){
						salary.setID(rs.getString("ID"));
						salary.setYear(rs.getInt("year"));
						salary.setMonth(rs.getInt("month"));
						salary.setBaseSalary(rs.getInt("baseSalary"));
						salary.setBonus(rs.getInt("bonus"));
						salary.setAttendance_deduct(rs.getInt("attendanceDeduct"));
						salary.setTax_deduct(rs.getDouble("taxDeduct"));
						salary.setActual_salary(rs.getDouble("actualSalary"));
						v.add(salary);
					}
					rs.close();
					tablemodel.setRowCount(v.size());
					for(int j =0 ; j < v.size(); j++){
						tablemodel.setValueAt(v.get(j).getID(), j, 0);
						tablemodel.setValueAt(v.get(j).getYear(), j, 1);
						tablemodel.setValueAt(v.get(j).getMonth(), j, 2);
						tablemodel.setValueAt(v.get(j).getBaseSalary(), j, 3);
						tablemodel.setValueAt(v.get(j).getBonus(), j, 4);
						tablemodel.setValueAt(v.get(j).getAttendance_deduct(), j, 5);
						tablemodel.setValueAt(v.get(j).getTax_deduct(), j, 6);
						tablemodel.setValueAt(v.get(j).getActual_salary(), j, 7);
					}
					if(v.size() == 0){
						JOptionPane.showMessageDialog(null, "未发放该月工资！", "错误!", JOptionPane.ERROR_MESSAGE);
					}
					dbcon.closeConn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
					ResultSet rs = dbcon.executeQuery("select * from pay where ID='" + Loginframe.id +"'");
					ResultSetMetaData rsmd = rs.getMetaData();
					ArrayList<SalaryEnity> v= new ArrayList<>();
					while(rs.next()){
						SalaryEnity salary = new SalaryEnity();
						salary.setID(rs.getString("ID"));
						salary.setYear(rs.getInt("year"));
						salary.setMonth(rs.getInt("month"));
						salary.setBaseSalary(rs.getInt("baseSalary"));
						salary.setBonus(rs.getInt("bonus"));
						salary.setAttendance_deduct(rs.getInt("attendanceDeduct"));
						salary.setTax_deduct(rs.getDouble("taxDeduct"));
						salary.setActual_salary(rs.getDouble("actualSalary"));
						v.add(salary);
					}
					rs.close();
					tablemodel.setRowCount(v.size());
					System.out.println(v.get(0).getMonth());
					System.out.println(v.get(1).getMonth());
					for(int j =0 ; j < v.size(); j++){
						tablemodel.setValueAt(v.get(j).getID(), j, 0);
						tablemodel.setValueAt(v.get(j).getYear(), j, 1);
						tablemodel.setValueAt(v.get(j).getMonth(), j, 2);
						tablemodel.setValueAt(v.get(j).getBaseSalary(), j, 3);
						tablemodel.setValueAt(v.get(j).getBonus(), j, 4);
						tablemodel.setValueAt(v.get(j).getAttendance_deduct(), j, 5);
						tablemodel.setValueAt(v.get(j).getTax_deduct(), j, 6);
						tablemodel.setValueAt(v.get(j).getActual_salary(), j, 7);
					}
					dbcon.closeConn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		basicPanel.add(upPanel,BorderLayout.CENTER);
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setVisible(true);
	}
	
	/*public static void main(String args[]){
		new SalaryE();
	}*/
	
}
